package com.repair.agency.model.utils;

import com.repair.agency.model.entity.Invoice;

import java.util.List;

public class TableSorter {
    public void sort(String sortingType, List<Invoice> invoiceList) {
        if (sortingType == null) {
            return;
        }
        switch (sortingType) {
            case "By date":
                invoiceList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                break;
            case "By status":
                invoiceList.sort((o1, o2) -> o2.getStatus().compareTo(o1.getStatus()));
                break;
            case "By price":
                invoiceList.sort(((o1, o2) -> o2.getPrice().compareTo(o1.getPrice())));
                break;
            default:
                break;
        }
    }
}
