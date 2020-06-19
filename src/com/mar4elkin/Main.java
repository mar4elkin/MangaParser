package com.mar4elkin;

import java.io.IOException;
//TODO Удалить потом
public class Main {

    public static void main(String[] args) throws IOException {

//        ConsoleReader consoleReader = new ConsoleReader();
//        String siteName = consoleReader.nameSelector();
        String siteName = "mangaChan";

        OrganizerSelector organizerSelector = new OrganizerSelector();
        organizerSelector.selectSite(siteName);

    }
}