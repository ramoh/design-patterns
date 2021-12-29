package com.rajesh.dms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentManagementSystem {

    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentsView = Collections.unmodifiableList(documents);

    // Register the importers
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("invoice", new InvoiceImporter());
    }

    public void importFile(final String path) throws IOException {
        final File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int speratorIndex = path.lastIndexOf(".");
        if (speratorIndex != -1) {
            if (speratorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension found for the file:" + path);

            }

            final String extension = path.substring(speratorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);

            if (importer == null) {
                throw new UnknownFileTypeException("For file:" + path);
            }
            final Document document = importer.importFile(file);
            documents.add(document);

        } else {
            throw new UnknownFileTypeException("No extension found for the file:" + path);
        }
    }

    public List<Document> contents() {
        return this.documentsView;
    }

    public List<Document> search(final String query) {
        return documents.stream()
                .filter(Query.parse(query))
                .collect(Collectors.toList());
    }
}
