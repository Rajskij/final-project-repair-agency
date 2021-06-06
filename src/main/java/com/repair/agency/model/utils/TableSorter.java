package com.repair.agency.model.utils;

import com.repair.agency.model.entity.Invoice;

import java.util.List;

public class TableSorter {
    public void sort(String sortingType, List<Invoice> invoiceList) {
        if (sortingType == null) {
            return;
        }
        switch (sortingType) {
            case "ByDate":
                invoiceList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                break;
            case "ByStatus":
                invoiceList.sort((o1, o2) -> o2.getStatus().compareTo(o1.getStatus()));
                break;
            case "ByPrice":
                invoiceList.sort(((o1, o2) -> o2.getPrice().compareTo(o1.getPrice())));
                break;
            default:
                break;
        }
    }
}
