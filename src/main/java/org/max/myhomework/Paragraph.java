package org.max.myhomework;

public class Paragraph extends ContentElement {
    private String name;
    private Integer pages;

    public Paragraph(String name, Integer pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public Integer getPages() {
        return pages;
    }

    public void print() {
        System.out.print("  " + getName());
        for (int i = 0; i < 68 - getName().length(); i++) {
            System.out.print(".");
        }
        System.out.println(getPages());
    }
}
