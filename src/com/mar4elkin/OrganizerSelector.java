package com.mar4elkin;

import java.util.ArrayList;

public class OrganizerSelector {
    public void selectSite(String x){
       switch (x){
           case "mangaChan":
               //do something
                MangaChan mangaChan = new MangaChan();
                mangaChan.getPageManga("https://mangachan.ru/catalog.html");

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
