package com.mar4elkin;

import java.util.ArrayList;

public class OrganizerSelector {
    public void selectSite(String x){
       switch (x){
           case "mangaChan":
                MangaChan mangaChan = new MangaChan();
                //System.out.println(mangaChan.getTags());
                //System.out.println(mangaChan.getPageUrls());
                //mangaChan.getPageManga("");

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
