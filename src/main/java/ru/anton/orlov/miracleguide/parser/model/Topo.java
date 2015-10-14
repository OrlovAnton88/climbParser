package ru.anton.orlov.miracleguide.parser.model;

import java.util.Set;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */

/**
 * Set of sectors
 */
public class Topo extends ParsableEntity {



    Set<Route> routes;

    public Topo(String link) {
        super(link);
    }

    public Topo(String link, String name) {
        super(link, name);
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(final Set<Route> routes) {
        this.routes = routes;
    }


}
