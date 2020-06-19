package com.mar4elkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MangaChan {
    public ArrayList<String> getTags() {

        ArrayList<String> tags = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://mangachan.ru/").get();

            Elements links = doc.select("div.sidetags > ul > li > a");

            for (Element link : links) {
                boolean result = link.text().matches("([+,-])");
                if(!result){
                    tags.add(link.text());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tags;
    }
    public ArrayList<String> getPageUrls(){
        ArrayList<String> pages = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://mangachan.ru/catalog.html").get();

            Elements links = doc.select("div#pagination > span > a[href]");

            for (Element link : links) {
                pages.add(link.attr("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pages;
    }

    public ArrayList<String> getPageManga(String pageUrl){
        ArrayList<String> manga = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect(pageUrl).get();
            //TODO Придумать как запихнуть данные в массив а запихивать я люблю 0))))

            //Image
            Elements linksImg = doc.select("div.content_row > div.manga_images > a > img");

            //Header Text
            Elements linksHeaderText = doc.select("div.content_row > div.manga_row1 > div > h2 > a.title_link");

            //Author TODO добавить регулярку котороая проверяет поле на пустоту датебае
            Elements linksAuthor = doc.select("div.content_row > div.manga_row2 > div.row3_left > div.item2 > h3.item2 > a");

            //Status of toms TODO добавить атрибут текст
            Elements status = doc.select("div.content_row > div.manga_row3 > div.row3_left > div.item2");

            //Manga Tags TODO Добавить проверку на ","
            Elements tags = doc.select("div.content_row > div.manga_row3 > div.row3_left > div.item4 > div.genre > a");

            for (Element link : linksImg) {
                manga.add(link.attr("src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(manga);

        return manga;
    }
}
