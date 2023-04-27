package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCrawler {
    private String url;
    private ArrayList<String> links;

    public ArrayList<String> linkCrawl(ArrayList<String> links) {
        this.links = links;
        try{
            Connection con = Jsoup.connect(this.url);
            Document doc = con.get();
            if(con.response().statusCode() == 200) {
                List<Element> elements = doc.getElementsByClass("body container");
                Element gridHighlight = elements.iterator().next()
                        .getElementsByClass("grid highlight").iterator().next();
                Element articleWrap = gridHighlight
                        .getElementsByClass("article-wrap").iterator().next();

                Element articleGrid = articleWrap
                        .getElementsByClass("article grid").iterator().next();
                List<Element> articleItems = articleGrid
                        .getElementsByClass("article-item");
                for (Element articleItem : articleItems) {
                    List<Element> articleThumbs = articleItem
                            .getElementsByClass("article-thumb");
                    for (Element articleThumb : articleThumbs) {
                        String link = "https://dantri.com.vn"
                                + articleThumb.select("a").attr("href");
                        links.add(link);
                    }
                }
                Element articleColum = articleWrap
                        .getElementsByClass("article column").iterator().next();
                List<Element> articleItems1 = articleColum
                        .getElementsByClass("article-item");
                for(Element articleItem1 : articleItems1){
                    List<Element> articleThumbs = articleItem1
                            .getElementsByClass("article-thumb");
                    for (Element articleThumb : articleThumbs) {
                        String link = "https://dantri.com.vn"
                                + articleThumb.select("a").attr("href");
                        links.add(link);
                    }
                }
                Element articleList = elements.iterator().next()
                        .getElementsByClass("main").iterator().next()
                        .getElementsByClass("article list").iterator().next();
                List<Element> articleItems2 = articleList
                        .getElementsByClass("article-item");
                for(Element articleItem2 : articleItems2){
                    List<Element> articleThumbs = articleItem2
                            .getElementsByClass("article-thumb");
                    for (Element articleThumb : articleThumbs) {
                        String link = "https://dantri.com.vn"
                                + articleThumb.select("a").attr("href");
                        links.add(link);
                    }
                }

                return links;
            }
        }catch (IOException e){
            return null;
        }
        return null;
    }
}
