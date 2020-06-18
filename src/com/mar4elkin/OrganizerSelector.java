package com.mar4elkin;

public class OrganizerSelector {
    public void selectSite(String x){
       switch (x){
           case "mangaChan":
               //do something
                MangaChan mangaChan = new MangaChan();
                mangaChan.test();
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
