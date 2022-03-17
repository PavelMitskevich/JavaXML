package com.mitskevich.task2.handler;

import com.mitskevich.task2.entity.AbstractMedicine;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;

public class ConsoleMedicineHandler extends DefaultHandler {
    private Set<AbstractMedicine> medicines;//todo page = 499

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startDocument() {
        System.out.println("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String tagData = qName + " ";
        for (int i = 0; i < attrs.getLength(); i++) {
            tagData += " " + attrs.getQName(i) + "=" + attrs.getValue(i);
        }
        System.out.print(tagData);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.print(" " + qName);
    }

    @Override
    public void endDocument() {
        System.out.println("\nParsing ended");
    }
}
