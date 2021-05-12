package com.loic.daily.exercise;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctolibParser {
  private static final String DOCTOLIB_ROOT = "https://www.doctolib.fr";
  private static final Path OUTPUT = new File("out3.txt").toPath();

  public static void main(String... args) throws IOException {
    int pageLoadSize = 20;
    int loop = 1000;
    try {
      loadSearchIds(pageLoadSize);
      checkAvailabilities(loop);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  private static void checkAvailabilities(int loop) throws Exception {
    List<String> lines = Files.lines(OUTPUT)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
    List<Site> sites = new ArrayList<>();
    for (int i = 0; i < lines.size(); i += 3) {
      sites.add(new Site(lines.get(i), lines.get(i + 1), lines.get(i + 2)));
    }
    ObjectMapper mapper = new ObjectMapper();
    while (loop > 0) {
      System.out.println("loop " + loop + " with site count " + sites.size());
      Iterator<Site> iterator = sites.iterator();
      while (iterator.hasNext()) {
        //Thread.sleep(100);
        Site site = iterator.next();
        String url = "https://www.doctolib.fr/search_results/" + site.id + ".json?ref_visit_motive_ids%5B%5D=6970&ref_visit_motive_ids%5B%5D=7005&speciality_id=5494&search_result_format=json&limit=3&force_max_limit=2";
        String json = null;
        try {
          json = Jsoup.connect(url).ignoreContentType(true).timeout(10_000).execute().body();
          JsonNode rootNode = mapper.readValue(json, JsonNode.class);
          JsonNode availNode = rootNode.get("availabilities");
          String zipCode = rootNode.get("search_result").get("zipcode").asText();
          if (eligible(zipCode)) {
            if (availNode.size() > 0) {
              Toolkit.getDefaultToolkit().beep();
              System.err.println(zipCode);
              System.out.println(DOCTOLIB_ROOT + site.url);
            }
          } else {
            iterator.remove();
          }
        } catch (Exception e) {
          System.err.println(json);
          e.printStackTrace(System.err);
        }
      }
      Thread.sleep(15_000);
      loop--;
    }
  }

  private static boolean eligible(String zipCode) {
    return zipCode.startsWith("92") || zipCode.startsWith("78") || zipCode.startsWith("95")
        || zipCode.startsWith("75") || zipCode.startsWith("91") || zipCode.startsWith("94");
  }

  private static void loadSearchIds(int pageLoadSize) throws IOException {
    Files.write(OUTPUT, "".getBytes(StandardCharsets.UTF_8));
    String firstUrl = "/vaccination-covid-19/courbevoie?force_max_limit=2&ref_visit_motive_id=6970&ref_visit_motive_ids%5B%5D=6970&ref_visit_motive_ids%5B%5D=7005";
    Optional<String> url = Optional.of(firstUrl);
    while (url.isPresent() && pageLoadSize > 0) {
      url = loadSearchIds(url.get());
      pageLoadSize--;
    }
  }

  private static Optional<String> loadSearchIds(String href) throws IOException {
    Document doc = Jsoup.connect(DOCTOLIB_ROOT + href).get();
    for (Element ele : doc.select("div.dl-search-result")) {
      String searchId = ele.attr("id").split("-")[2];
      Element nameEle = ele.selectFirst("a.dl-search-result-name");
      String url = nameEle.attr("href");
      String name = nameEle.child(0).text();
      Files.write(OUTPUT, name.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      Files.write(OUTPUT, "\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      Files.write(OUTPUT, searchId.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      Files.write(OUTPUT, "\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      Files.write(OUTPUT, url.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      Files.write(OUTPUT, "\n".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      //System.out.println(name);
      //System.out.println(searchId);
      //System.out.println(url);
    }
    return Optional.ofNullable(doc.selectFirst("div.next"))
        .map(e -> e.child(0).attr("href"));
  }

  private static class Site {
    private final String name, id, url;

    private Site(String name, String id, String url) {
      this.name = name;
      this.id = id;
      this.url = url;
    }

    @Override
    public String toString() {
      return "Site{" +
          "name='" + name + '\'' +
          ", id='" + id + '\'' +
          ", url='" + url + '\'' +
          '}';
    }
  }
}
