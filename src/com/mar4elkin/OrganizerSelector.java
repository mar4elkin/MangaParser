package com.mar4elkin;

import java.util.ArrayList;
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
                mangaChan.getChapters("/manga/69667-copper-frozen-au.html");

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
