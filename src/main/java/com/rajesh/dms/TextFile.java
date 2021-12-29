package com.rajesh.dms;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class TextFile {
    private final Map<String, String> attributes = new HashMap<>();
    private final List<String> lines;

    TextFile(final File file) throws IOException {
        attributes.put(Attributes.PATH, file.getPath());
        this.lines = Files.lines(file.toPath()).collect(Collectors.toList());
    }

    Map<String, String> getAttributes() {
        return this.attributes;
    }

    int addLines(final int start, final Predicate<String> isEnd, final String attributeName) {
        final StringBuilder accumulator = new StringBuilder();
        int lineNumber;

        for (lineNumber = start; lineNumber < this.lines.size(); ++lineNumber) {
            final String line = lines.get(lineNumber);

            if (isEnd.test(line)) {
                break;
            }
            accumulator.append(line);
            accumulator.append("\n");
        }

        this.attributes.put(attributeName, accumulator.toString().trim());
        return lineNumber;
    }

    void addLineSuffix(final String prefix, final String attributeName) {
        for (final String line : lines) {
            if (line.startsWith(prefix)) {
                this.attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }

}
