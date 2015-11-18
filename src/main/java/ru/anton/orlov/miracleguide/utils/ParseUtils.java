package ru.anton.orlov.miracleguide.utils;

import ru.anton.orlov.miracleguide.model.Coordinates;

import java.util.Optional;

/**
 * Author:      oav <br>
 * Date:        18.11.15, 17:51 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class ParseUtils {


    public static Optional<Coordinates> getAreaCoordinates(final String styleAttr){
        final int i = styleAttr.indexOf("center=");
        if(i == -1){
            return Optional.empty();
        }
        int start = i + "center=".length();
        int end = styleAttr.indexOf("&zoom=");

        final String coordString = styleAttr.substring(start,end);

        final String[] split = coordString.split(",");
        if(split.length == 2){
            try{
                Double latitude = Double.valueOf(split[0]);
                Double longitude = Double.valueOf(split[1]);
                Coordinates toReturn = new Coordinates(latitude,longitude);
                return Optional.ofNullable(toReturn);
            }catch (NumberFormatException ex){
                ex.printStackTrace();
                return Optional.empty();
            }

        }
        return Optional.empty();

    }

}
