package com.mar4elkin;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList <String> detailpage = new ArrayList<String>();

        try {
            Document doc = Jsoup.connect("https://manga-chan.me" + mangaUrl).get();

            Elements links = doc.select("table.table_cha > tbody > tr ");

            for (Element link : links) {
                if (!link.text().equals("Название Загружено") && !link.text().equals("")) {
                    detailpage.add(link.text());
                }
            }
            for (String s : detailpage) {
                //Что за говнокод
                String[] dateArray = s.split("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");

                Pattern pattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
                List<String> res = new ArrayList<>();
                Matcher matcher = pattern.matcher(s);
                while (matcher.find()) {
                    res.add(matcher.group(0));
                }

                JSONObject subManga = new JSONObject();
                subManga
                        .put("chapterTitle", dateArray[0])
                        .put("chapterDate", res);
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

}
