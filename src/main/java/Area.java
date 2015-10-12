import java.util.List;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class Area {

    private String name;

    private List<Sector> sectors;

    public Area(final String name, final List<Sector> sectors) {
        this.name = name;
        this.sectors = sectors;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(final List<Sector> sectors) {
        this.sectors = sectors;
    }
}
