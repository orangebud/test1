package com.epam.bench.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Peter_Olah1 on 7/16/2015.
 * <p/>
 * Contains the FileWriter which was made to save contents to a text file.
 */
public class FileWriter {

    public FileWriter(String path, String line1, String line2) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(line1);
            writer.println(line2);
            writer.close();
        } else {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(line1);
            writer.println(line2);
            writer.close();
        }
    }
}