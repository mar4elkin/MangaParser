package com.mar4elkin;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MangaChan {
    public void test() {
        try {
            Document doc = Jsoup.connect("https://mangachan.ru/").get();

            String title = doc.title();
            System.out.println("title: " + title);

            Elements links = doc.select("div.sidetags > ul > li > a");

            for (Element link : links) {
                System.out.println(link.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
