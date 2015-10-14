package ru.anton.orlov.miracleguide.parser.model;

import java.util.Set;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class Area  extends ParsableEntity {

    private Set<Topo> topos;

    public Area(String link) {
        super(link);
    }

    public Set<Topo> getTopos() {
        return topos;
    }

    public void setTopos(final Set<Topo> topos) {
        this.topos = topos;
    }

    public Area(String link, String name) {
        super(link, name);
    }
}
