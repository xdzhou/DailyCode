package com.loic.web;

import com.loic.web.utils.JsoupWrap;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Perf92RemiseParser {
  private int count = 0;

  public static void main(String... args) throws InterruptedException {
    new Perf92RemiseParser().process();
  }

  public void process() throws InterruptedException {
    boolean found = false;
    while (!found) {
      long sleepSec = 20;
      try {
        found = hasFreePlace();
      } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
        sleepSec = 120;
      }
      Thread.sleep(sleepSec * 1_000);
    }
  }

  private boolean hasFreePlace() throws IOException {
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
    Document doc = jsoup.request(initUrl).parse();
    Element fieldset = doc.selectFirst("fieldset#fchoix_Booking");
    Elements radios = fieldset.select("input.radio");
    int curChoice = (count++) % radios.size();
    String choiceValue = radios.get(curChoice).attr("value");

    Document rdvDoc;
    try {
      rdvDoc = jsoup.request(initUrl, c -> c
          .method(Connection.Method.POST)
          .data("planning", choiceValue)
          .data("nextButton", "Etape suivante")
          .headers(headers)).parse();
      System.out.println("choice N" + (curChoice+1) + " with value " + choiceValue);
    } catch (IOException e) {
      count--;
      throw e;
    }

    Element form = rdvDoc.selectFirst("form#FormBookingCreate");
    if (form.text().contains("Il n'existe plus de plage horaire libre")) {
      System.out.println("retry later on");
    } else {
      Toolkit.getDefaultToolkit().beep();
      System.out.println("Quick on choice N" + (curChoice+1));
      return true;
    }
    return false;
  }
}
