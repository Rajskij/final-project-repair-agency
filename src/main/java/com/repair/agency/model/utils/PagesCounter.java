package com.repair.agency.model.utils;

public class PagesCounter {
    public int count(int listSize, int rowsOnPage) {
        int pages = listSize / rowsOnPage;
        if (listSize % 10 != 0) {
            pages++;
        }
        return pages;
    }
}
