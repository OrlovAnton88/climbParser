package ru.anton.orlov.miracleguide.parser.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
@Entity
public class Route extends ParsableEntity {

    @Column
    private String description;

    @Column
    private Long areaId;

    @Column
    private Long topoId;

    @Column
    private String level;

    //todo: do we need below?
    @Column
    private String sectorName;

    @OneToOne(cascade= CascadeType.ALL)
    private VectorLine line;

    public Route() {
        super();
    }

    public Route(String link) {
        super(link);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(final String level) {
        this.level = level;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(final String sectorName) {
        this.sectorName = sectorName;
    }

    public VectorLine getLine() {
        return line;
    }

    public void setLine(VectorLine line) {
        this.line = line;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(final Long areaId) {
        this.areaId = areaId;
    }

    public Long getTopoId() {
        return topoId;
    }

    public void setTopoId(final Long topoId) {
        this.topoId = topoId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("description='").append(description).append('\'');
        sb.append(", areaId=").append(areaId);
        sb.append(", topoId=").append(topoId);
        sb.append(", level='").append(level).append('\'');
        sb.append(", sectorName='").append(sectorName).append('\'');
        sb.append(", line=").append(line);
        sb.append('}');
        return sb.toString();
    }
}
