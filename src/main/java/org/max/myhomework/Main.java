package org.max.myhomework;

public class Main {
    public static void main(String[] args) {
        ContentElement content = new Chapter("Content ", 1);

        ContentElement introduction = new Chapter("Introduction ", 2);
        ContentElement firstChapter = new Chapter("1. First chapter ", 5);
        ContentElement secondChapter = new Chapter("2. Second chapter ", 37);
        ContentElement thirdChapter = new Chapter("3. Third chapter ", 73);

        content.add(introduction);
        content.add(firstChapter);
        content.add(secondChapter);
        content.add(thirdChapter);

        firstChapter.add(new Paragraph(
                "1.1 First paragraph of the first chapter ",
                5
        ));
        firstChapter.add(new Paragraph(
                "1.2 Second paragraph of the first chapter ",
                10
        ));
        firstChapter.add(new Paragraph(
                "1.3 Third paragraph of the first chapter",
                17
        ));

        secondChapter.add(new Paragraph(
                "2.1 First paragraph of the second chapter ",
                37
        ));

        secondChapter.add(new Paragraph(
                "2.2 Second paragraph of the second chapter ",
                50
        ));

        thirdChapter.add(new Paragraph(
                "3.1 First paragraph of the third chapter ",
                73
        ));
        thirdChapter.add(new Paragraph(
                "3.2 Second paragraph of the third chapter ",
                78
        ));
        thirdChapter.add(new Paragraph(
                "3.3 Third paragraph of the third chapter ",
                80
        ));

        System.out.println("\"Java Composite Design Pattern\" Vladislav K., 2023");

        Book book = new Book(content);

        book.printContent();
    }
}
