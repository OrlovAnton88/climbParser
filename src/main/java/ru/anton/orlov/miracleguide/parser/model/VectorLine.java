package ru.anton.orlov.miracleguide.parser.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by antonorlov on 16/10/15.
 */
@Entity
public class VectorLine {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int plotWindth;
    @Column
    private int plotHeight;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Point> points;

    protected VectorLine(){

    }

    public VectorLine(int plotWindth, int plotHeight, List<Point> points) {
        this.plotWindth = plotWindth;
        this.plotHeight = plotHeight;
        this.points = points;
    }

    public int getPlotWindth() {
        return plotWindth;
    }

    public void setPlotWindth(int plotWindth) {
        this.plotWindth = plotWindth;
    }

    public int getPlotHeight() {
        return plotHeight;
    }

    public void setPlotHeight(int plotHeight) {
        this.plotHeight = plotHeight;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
