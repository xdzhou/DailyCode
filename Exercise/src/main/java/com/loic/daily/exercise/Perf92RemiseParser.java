package com.loic.daily.exercise;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Perf92RemiseParser {
  private static int count = 0;

  public static void main(String... args) throws InterruptedException {
    while (true) {
      try {
        process();
      } catch (IOException e) {
        System.out.println("error " + e.getMessage());
      }
      Thread.sleep(10_000);
    }
  }

  private static void process() throws IOException {
    String initUrl = "https://www.hauts-de-seine.gouv.fr/booking/create/12083/1";
    Map<String, String> headers = new HashMap<>();
    headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0");
    headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.put("Accept-Language", "fr-FR,en-US;q=0.7,en;q=0.3");
    headers.put("Content-Type", "application/x-www-form-urlencoded");
    headers.put("Origin", "https://www.hauts-de-seine.gouv.fr");
    headers.put("DNT", "1");
    headers.put("Connection", "keep-alive");
    headers.put("Referer", initUrl);
    headers.put("Upgrade-Insecure-Requests", "1");

    JsoupWrap jsoup = new JsoupWrap();
    Document doc = jsoup.response(initUrl).parse();
    Element fieldset = doc.selectFirst("fieldset#fchoix_Booking");
    Elements radios = fieldset.select("input.radio");
    int curChoice = (count++) % radios.size();
    String choiceValue = radios.get(curChoice).attr("value");


    Document rdvDoc;
    try {
      rdvDoc = jsoup.response(initUrl, c -> c
          .method(Connection.Method.POST)
          .data("planning", choiceValue)
          .data("nextButton", "Etape suivante")
          .headers(headers)).parse();
      System.out.println("choice " + choiceValue);
    } catch (IOException e) {
      count--;
      throw e;
    }

    Element form = rdvDoc.selectFirst("form#FormBookingCreate");
    if (form.text().contains("Il n'existe plus de plage horaire libre")) {
      System.out.println("retry later on");
    } else {
      System.err.println("QUICK");
    }
  }
}
