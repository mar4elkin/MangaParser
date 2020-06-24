package com.mar4elkin;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        MangaChan mangaChan = new MangaChan();
        JSONArray k = mangaChan.getTags();
        System.out.println(k);
        System.out.println(k.get(0));
        JSONObject i = (JSONObject) k.get(0);
        System.out.println(i.getString("link"));
    }
}