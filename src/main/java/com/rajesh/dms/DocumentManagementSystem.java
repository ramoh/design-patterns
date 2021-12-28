package com.rajesh.dms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {

    // Register the importers
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("letter", new ImageImporter());
    }

    public void importFile(String path) {
    }

    public List<Document> contents() {
        return null;
    }
}
