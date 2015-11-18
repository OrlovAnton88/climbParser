package ru.anton.orlov.miracleguide.parser.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.anton.orlov.miracleguide.model.Coordinates;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
@JsonPropertyOrder({ "id","name","translitName","imageName","coordinates","topos"})
@Entity
public class Area  extends ParsableEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Coordinates coordinates;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Topo> topos;

    public Area() {
        super();
    }

    public Area(String link) {
        super(link);
    }

    public Set<Topo> getTopos() {
        return topos;
    }

    public void setTopos(final Set<Topo> topos) {
        this.topos = topos;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Area(String link, String name) {
        super(link, name);
    }
}
