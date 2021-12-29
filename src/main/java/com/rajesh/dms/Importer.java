package com.rajesh.dms;

import java.io.File;
import java.io.IOException;

interface Importer {

    Document importFile(File file) throws IOException;
}
