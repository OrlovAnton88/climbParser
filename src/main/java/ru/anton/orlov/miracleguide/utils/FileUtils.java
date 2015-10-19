package ru.anton.orlov.miracleguide.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by antonorlov on 15/10/15.
 */
public class FileUtils {


    public static void saveToFile(String text, String path, String fileName) {

        //Creating a new file
        Path newFile = Paths.get(path, fileName);
        try {
            Files.deleteIfExists(newFile);
            newFile = Files.createFile(newFile);
        } catch (IOException ex) {
            System.out.println("Error creating file");
        }

        //Writing to file
        try (BufferedWriter writer = Files.newBufferedWriter(newFile, Charset.defaultCharset())) {
            writer.append(text);
            writer.flush();
        } catch (IOException exception) {
            System.out.println("Error writing to file");
        }

    }

    public static String readFile(final String filename) {
        Path path = FileSystems.getDefault().getPath(filename);
        StringBuilder content = new StringBuilder();
        try {
            InputStream in = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = null;
            while ((line = reader.readLine()) != null) {
                content.append(line);

            }
        }catch (IOException ex){
            System.out.println("ERROR reading file["+filename+"] " +  ex.getMessage());
        }
        return content.toString();
    }
}
