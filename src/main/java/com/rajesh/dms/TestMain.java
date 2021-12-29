package com.rajesh.dms;

import java.util.HashMap;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        Map<String, String> attr = new HashMap<>();
        attr.put("firstName", "Rajesh");

        Document doc = new Document(attr);
        System.out.println(doc.getAttribute("firstName"));
        System.out.println(doc.getAttribute("lastName"));

        attr.put("lastName", "Rakesh");
        System.out.println(doc.getAttribute("lastName"));

    }
}
