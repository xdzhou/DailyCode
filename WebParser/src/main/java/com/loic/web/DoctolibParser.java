package com.loic.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.loic.web.utils.JsoupWrap;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class DoctolibParser {
  private static final String DOCTOLIB_ROOT = "https://www.doctolib.fr";
  private static final JsonMapper JSON_MAPPER = new JsonMapper();
  private final JsoupWrap jsoupWrap = new JsoupWrap();

  public static void main(String... args) throws IOException, InterruptedException {
    int pageLoadSize = 15;
    int loop = 10;
    DoctolibParser parser = new DoctolibParser();
    List<Site> sites = parser.loadSites(pageLoadSize);
    parser.checkAvailabilities(loop, sites);
  }

  private static boolean pickFirstSlot(JsonNode availsNode) {
    LocalDate today = LocalDate.now();
    for (int i = 0; i < availsNode.size(); i++) {
      JsonNode avail = availsNode.get(0);
      LocalDate date = LocalDate.parse(avail.get("date").asText());
      JsonNode slots = avail.get("slots");
      // only check today & tomorrow's slots
      if (slots.size() > 0 && (date.equals(today) || date.equals(today.plusDays(1)))) {
        JsonNode firstSlot = slots.get(0);
        System.out.println(firstSlot);
        return true;
      }
    }
    return false;
  }

  private static boolean eligible(String zipCode) {
    return zipCode.startsWith("92") || zipCode.startsWith("78") || zipCode.startsWith("95")
        || zipCode.startsWith("75") || zipCode.startsWith("91") || zipCode.startsWith("94");
  }

  private void checkAvailabilities(int loop, List<Site> sites) throws InterruptedException {
    while (loop > 0 && sites.size() > 0) {
      System.out.printf("Scan %d sites in loop ...%d%n", sites.size(), loop);
      Iterator<Site> iterator = sites.iterator();
      while (iterator.hasNext()) {
        Site site = iterator.next();
        String url = DOCTOLIB_ROOT + "/search_results/" + site.id + ".json?ref_visit_motive_ids%5B%5D=6970&ref_visit_motive_ids%5B%5D=7005&speciality_id=5494&search_result_format=json&limit=3&force_max_limit=2";
        String json = null;
        try {
          json = jsoupWrap.request(url, c -> c.ignoreContentType(true)).body();
          JsonNode rootNode = JSON_MAPPER.readValue(json, JsonNode.class);
          JsonNode availNode = rootNode.get("availabilities");
          String zipCode = rootNode.get("search_result").get("zipcode").asText();
          if (eligible(zipCode)) {
            if (pickFirstSlot(availNode)) {
              Toolkit.getDefaultToolkit().beep();
              System.err.println(zipCode + " : " + site.name);
              System.out.println(DOCTOLIB_ROOT + site.url);
            }
          } else {
            iterator.remove();
          }
        } catch (IOException e) {
          //e.printStackTrace();
          iterator.remove();
        }
        Thread.sleep(100);
      }
      loop--;
    }
  }

  private List<Site> loadSites(int pageLoadSize) throws IOException {
    List<Site> sites = new ArrayList<>();
    String firstUrl = "/vaccination-covid-19/courbevoie?force_max_limit=2&ref_visit_motive_id=6970&ref_visit_motive_ids%5B%5D=6970&ref_visit_motive_ids%5B%5D=7005";
    Optional<String> url = Optional.of(firstUrl);
    while (url.isPresent() && pageLoadSize > 0) {
      System.out.println("loading page ..." + pageLoadSize);
      url = loadSites(url.get(), sites);
      pageLoadSize--;
    }
    return sites;
  }

  private Optional<String> loadSites(String href, List<Site> sites) throws IOException {
    Document doc = jsoupWrap.request(DOCTOLIB_ROOT + href).parse();
    for (Element ele : doc.select("div.dl-search-result")) {
      String searchId = ele.attr("id").split("-")[2];
      Element nameEle = ele.selectFirst("a.dl-search-result-name");
      String url = nameEle.attr("href");
      String name = nameEle.child(0).text();
      Site site = new Site(name, searchId, url);
      fetchSiteIds(site);
      sites.add(site);
    }
    return Optional.ofNullable(doc.selectFirst("div.next"))
        .map(e -> e.child(0).attr("href"));
  }

  private void fetchSiteIds(Site site) throws IOException {
    Document siteRoot = jsoupWrap.request(DOCTOLIB_ROOT + site.url).parse();
    Element dataLayer = siteRoot.selectFirst("div#datalayer");
    JsonNode propsNode = JSON_MAPPER.readValue(dataLayer.attr("data-props"), JsonNode.class);
    site.profileId = propsNode.get("profile_id").asText();
    site.practiceId = propsNode.get("profile_practice_id").asText();
  }

  private static class Site {
    private final String name, id, url;
    private String profileId, practiceId;

    public Site(String name, String id, String url) {
      this.name = name;
      this.id = id;
      this.url = url;
    }
  }
}