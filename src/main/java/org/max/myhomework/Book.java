package org.max.myhomework;

public class Book {

    ContentElement content;

    public Book(ContentElement content) {
        this.content = content;
    }

    public void printContent() {
        content.print();
    }
}
