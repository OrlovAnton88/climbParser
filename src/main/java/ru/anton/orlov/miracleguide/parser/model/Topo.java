package ru.anton.orlov.miracleguide.parser.model;

import ru.anton.orlov.miracleguide.model.Coordinates;

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

    private Coordinates coordinates;

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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Topo{");
        sb.append("coordinates=").append(coordinates);
        sb.append(", routes=").append(routes);
        sb.append('}');
        return sb.toString();
    }
}
