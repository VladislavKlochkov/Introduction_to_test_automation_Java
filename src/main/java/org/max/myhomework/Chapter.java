package org.max.myhomework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chapter extends ContentElement {
    List<ContentElement> contentsComponents = new ArrayList<>();
    private String name;
    private Integer pages;

    public Chapter(String name, Integer pages) {
        this.name = name;
        this.pages = pages;
    }

    public void add(ContentElement contentElement) {
        contentsComponents.add(contentElement);
    }

    public String getName() {
        return name;
    }

    public Integer getPages() {
        return pages;
    }

    public void print() {
        System.out.print("\n" + getName());
        for (int i = 0; i < 70 - getName().length(); i++) {
            System.out.print(".");
        }
        System.out.println(getPages());

        Iterator<ContentElement> iterator = contentsComponents.iterator();
        contentsComponents.stream()
                .takeWhile(x -> iterator.hasNext())
                .map(n -> iterator.next())
                .forEach(ContentElement::print);
    }
}
