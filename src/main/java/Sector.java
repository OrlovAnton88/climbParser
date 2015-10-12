import java.util.List;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class Sector {
    
    private String name;
    
    List<Route> routes;

    public Sector(final String name, final List<Route> routes) {
        this.name = name;
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(final List<Route> routes) {
        this.routes = routes;
    }
}
