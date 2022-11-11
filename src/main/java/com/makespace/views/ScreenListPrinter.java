package com.makespace.views;

import java.util.List;

public class ScreenListPrinter implements Printer{
    final List<String> results;

    public ScreenListPrinter(List<String> results) {
        this.results = results;
    }

    @Override
    public void print() {
        if(results == null)
                return;
        this.results.forEach(System.out::println);
    }
}
