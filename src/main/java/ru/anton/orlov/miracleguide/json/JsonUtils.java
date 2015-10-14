package ru.anton.orlov.miracleguide.json;

import com.google.gson.Gson;

/**
 * Created by antonorlov on 15/10/15.
 */
public class JsonUtils {

    public static String getJson(final Object area){

        return new Gson().toJson(area);

    }

}
