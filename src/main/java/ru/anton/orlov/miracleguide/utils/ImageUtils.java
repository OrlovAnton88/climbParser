package ru.anton.orlov.miracleguide.utils;

import ru.anton.orlov.miracleguide.Conf;

import java.io.*;
import java.net.URL;

/**
 * Created by antonorlov on 13/10/15.
 */
public class ImageUtils {

    private static final String folderPath = Conf.RESOURSES_PATH + "images/";

    public static File getImage(String src) throws IOException {

        String folder = null;

        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        System.out.println(name);

        File f = new File(folderPath + name);
        if(f.exists() && !f.isDirectory()) {
            System.out.println("image already exists");
            return f;
        }

        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));

        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();

        return new File(folderPath + name);

    }
}
