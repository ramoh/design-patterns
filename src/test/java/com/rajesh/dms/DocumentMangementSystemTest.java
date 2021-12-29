package com.rajesh.dms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class DocumentMangementSystemTest {
    private static final String RESOURCES = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String RAJESH = "Rajesh Mohanty";

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws Exception {

        system.importFile(LETTER);
        final Document document = onlyDocument();
        assertAttributeEqual(document, Attributes.PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttributes() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();
        assertAttributeEqual(document, Attributes.PATIENT, RAJESH);
        assertAttributeEqual(document, Attributes.ADDRESS, "123 Fake Street\n" +
                "Sarjapura\n" +
                "Bangalore\n" +
                "Karnataka");
        assertAttributeEqual(document, Attributes.BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertAttributeEqual(document, Attributes.TYPE, "LETTER");

    }

    @Test
    public void shouldImportImageAttributes() throws Exception {
        system.importFile(XRAY);
        final Document document = onlyDocument();
        assertAttributeEqual(document, Attributes.WIDTH, "320");
        assertAttributeEqual(document, Attributes.HEIGHT, "179");
        assertAttributeEqual(document, Attributes.TYPE, "IMAGE");
    }

    @Test
    public void shouldImportReportAttribute() throws Exception {
        system.importFile(REPORT);
        final Document document = onlyDocument();
        isReport(document);
    }

    @Test
    public void shouldImportInvoiceAttributes() throws Exception {
        system.importFile(INVOICE);

        final Document document = onlyDocument();

        assertAttributeEqual(document, Attributes.PATIENT, RAJESH);
        assertAttributeEqual(document, Attributes.AMOUNT, "100");
        assertAttributeEqual(document, Attributes.TYPE, "INVOICE");
    }

    @Test
    public void shouldBeAbleToSearchFilesByAttributes() throws Exception {

        system.importFile(LETTER);
        system.importFile(REPORT);
        system.importFile(XRAY);

        final List<Document> documents = system.search("patient:Rajesh,body:Diet Coke");
        Assert.assertThat(documents, Matchers.hasSize(1));
        final Document document = documents.get(0);
        isReport(document);
    }

    private void isReport(final Document document) {
        assertAttributeEqual(document, Attributes.PATIENT, RAJESH);
        assertAttributeEqual(document, Attributes.BODY,
                "On 5th January 2022 I examined Rajesh's teeth.\n" +
                        "We discussed his switch from drinking Coke to Diet Coke.\n" +
                        "No new problems were noted with his teeth.");
        assertAttributeEqual(document, Attributes.TYPE, "REPORT");
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception {
        system.importFile("somerandom.txt");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFile() throws Exception {
        system.importFile(RESOURCES + "unknown.txt");
    }

    private void assertAttributeEqual(final Document document, final String attributeName, String expectedValue) {
        Assert.assertEquals("Document has a wrong value for " + attributeName, expectedValue,
                document.getAttribute(attributeName));
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        Assert.assertThat(documents, Matchers.hasSize(1));
        return documents.get(0);
    }

}
