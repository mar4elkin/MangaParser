package com.mar4elkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class MangaChan {
    public ArrayList<String> getTags() {

        ArrayList<String> tags = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://manga-chan.me/").get();

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
        pages.add("");

        try {
            Document doc = Jsoup.connect("https://manga-chan.me/catalog").get();

            Elements links = doc.select("div#pagination > span > a[href]");

            for (Element link : links) {
                pages.add(link.attr("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pages;
    }

    public JSONArray getPageManga(String pageUrl){

        JSONArray manga = new JSONArray();

        ArrayList<String> mangaHeaderText = new ArrayList<String>();
        ArrayList<String> mangaImages = new ArrayList<String>();
        ArrayList<String> mangaAuthors = new ArrayList<String>();
        ArrayList<String> mangaStatus = new ArrayList<String>();
        ArrayList<String> mangaTags = new ArrayList<String>();
        ArrayList<String> mangaHref = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://manga-chan.me/catalog" + pageUrl).get();

            //Image
            Elements linksImg = doc.select("div.content_row > div.manga_images > a > img");

            //Header Text
            Elements linksHeaderText = doc.select("div.content_row > div.manga_row1 > div > h2 > a.title_link");

            //Author датебае
            Elements linksAuthor = doc.select("div.content_row > div.manga_row2 > div.row3_left > div.item2 > h3.item2");

            //Status of toms
            Elements status = doc.select("div.content_row > div.manga_row3 > div.row3_left > div.item2");

            //Manga Tags
            Elements tags = doc.select("div.content_row > div.manga_row3 > div.row3_left > div.item4 > div.genre");

            // Дальше тырим контент с сайта
            for (Element link : linksImg) {
                mangaImages.add(link.attr("src"));
            }

            for (Element link : linksHeaderText) {
                mangaHeaderText.add(link.text());
            }

            for (Element link : linksHeaderText) {
                mangaHref.add(link.attr("href"));
            }

            for (Element link : linksAuthor) {
                mangaAuthors.add(link.text().replace(",", ""));
            }

            for (Element link : status) {
                mangaStatus.add(link.text().replace(",", ""));
            }

            for (Element link : tags) {
                mangaTags.add(link.text().replace(",", ""));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //Удаление переводчиков из массив статуса датебане
        for (int i=0; i < mangaStatus.size(); i++) {
            mangaStatus.remove(i + 1);
        }

        //Запихиваем контент в один огромный мать его массив
        for (int i=0; i< mangaImages.size(); i++) {
            JSONObject subManga = new JSONObject();
            subManga
                    .put("img", mangaImages.get(i))
                    .put("link", mangaHref.get(i))
                    .put("author", mangaAuthors.get(i))
                    .put("title", mangaHeaderText.get(i))
                    .put("status", mangaStatus.get(i))
                    .put("tags", mangaTags.get(i));

            manga.put(subManga);
        }

        return manga;
    }

    public JSONArray getChapters(String mangaUrl){
        JSONArray chapters = new JSONArray();
        JSONArray chapterSorted = new JSONArray();
        ArrayList <String> ch = new ArrayList<String>();
        ArrayList <String> date = new ArrayList<String>();
        ArrayList <String> href = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://manga-chan.me" + mangaUrl).get();

            Elements linksChapter = doc.select("table.table_cha > tbody > tr > td > div.manga2 > a ");
            Elements linksDate = doc.select("table.table_cha > tbody > tr > td > div.date ");

            for (Element link : linksChapter) {
                if (!link.text().equals("Название Загружено") && !link.text().equals("")) {
                    ch.add(link.text());
                    href.add(link.attr("href"));
                }
            }

            for (Element link : linksDate) {
                if (!link.text().equals("Название Загружено") && !link.text().equals("")) {
                    date.add(link.text());
                }
            }

            for (int i = 0; i < ch.size(); i++) {
                JSONObject subManga = new JSONObject();
                subManga
                        .put("link", href.get(i))
                        .put("chapterTitle", ch.get(i))
                        .put("chapterDate", date.get(i));
                chapters.put(subManga);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sort json array from -1 to 0
        for (int i=chapters.length()-1; i>=0; i--) {
            chapterSorted.put(chapters.get(i));
        }
        return chapterSorted;
    }
    public String[] getImageSet(String mangaImageUrl){
        String imgJsArr = "";
        String[] Image = new String[0];

        try {
            Document doc = Jsoup.connect("https://manga-chan.me" + mangaImageUrl).get();
            Elements links = doc.select("script");

            for (Element link : links) {

                Pattern regex = Pattern.compile("(\"fullimg\":\\[)([^]]+)");
                Matcher m = regex.matcher(link.toString());

                while (m.find()) {
                    imgJsArr = m.group(2);
                }
            }

            Image = imgJsArr.split(",");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Image;
    }


}
