package com.loic.daily.exercise;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Request;
import feign.RetryableException;
import feign.Retryer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JsoupWrap {
  private Map<String, String> cookies = Collections.emptyMap();
  private final Retryer retryer = new Retryer.Default(500, SECONDS.toMillis(3),5);

  public Connection.Response response(String url) throws IOException {
    return response(url, c -> {
    });
  }

  public Connection.Response response(String url, Consumer<Connection> consumer) throws IOException {
    Supplier<Connection> supplier =()->{
      Connection connection = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
        .timeout(20_000)
        .cookies(cookies)
        .followRedirects(true);
      consumer.accept(connection);
      return connection;
    };
    Connection.Response response = executeWithRetry(supplier);
    cookies = response.cookies();
    return response;
  }

  private Connection.Response executeWithRetry(Supplier<Connection> supplier) throws IOException {
    Retryer copy = retryer.clone();
    while (true) {
      try {
        return supplier.get().execute();
      } catch (IOException e) {
        System.err.println("Retry needed : " + e.getMessage());
        RetryableException ex = new RetryableException("", Request.HttpMethod.GET, e, null);
        try {
          copy.continueOrPropagate(ex);
        }catch (RetryableException th){
          throw e;
        }
      }
    }
  }
}
