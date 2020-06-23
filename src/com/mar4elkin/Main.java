package com.mar4elkin;

import org.json.JSONArray;

public class Main {

    public static void main(String[] args) {
        MangaChan mangaChan = new MangaChan();
        JSONArray k = mangaChan.getImageSet("/online/469187-outlanders_v8_ch34.html");
        System.out.println(k);

    }
}