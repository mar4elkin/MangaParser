package com.mar4elkin;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

//TODO Удалить потом
public class OrganizerSelector {
    public void selectSite(String x){
       switch (x){
           case "mangaChan":
                MangaChan mangaChan = new MangaChan();
                //System.out.println(mangaChan.getTags());
                //ArrayList<String> pUrl = new ArrayList<String>();
                //pUrl = mangaChan.getPageUrls();
                //System.out.println(mangaChan.getPageManga(pUrl.get(1)));
                //System.out.println(mangaChan.getChapters("/manga/69667-copper-frozen-au.html"));
               //String[] k = mangaChan.getImageSet("/online/467876-outlanders_v6_ch23.html");
               //System.out.println(Arrays.toString(k));

               break;
           case "readManga":
               //do something
               System.out.println("readManga has been selected");
               break;
           default:
               System.out.println("plz check ur out");
       }
    }
}
