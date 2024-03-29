package com.loic.web.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class JsoupWrap {
  private final Map<String, String> cookies = new HashMap<>();

  public Connection.Response request(String url) throws IOException {
    return request(url, c -> {
    });
  }

  public Connection.Response request(String url, Consumer<Connection> consumer) throws IOException {
    Connection connection = Jsoup.connect(url)
        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
        .timeout(20_000)
        .cookies(cookies)
        .followRedirects(true);
    consumer.accept(connection);
    Connection.Response response = connection.execute();
    cookies.putAll(response.cookies());
    return response;
  }
}
