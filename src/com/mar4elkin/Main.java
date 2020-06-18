package com.mar4elkin;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ConsoleReader consoleReader = new ConsoleReader();
        String siteName = consoleReader.nameSelector();

        OrganizerSelector organizerSelector = new OrganizerSelector();
        organizerSelector.selectSite(siteName);

    }
}