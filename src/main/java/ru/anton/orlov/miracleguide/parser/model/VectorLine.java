package ru.anton.orlov.miracleguide.parser.model;

import java.util.List;

/**
 * Created by antonorlov on 16/10/15.
 */
public class VectorLine {

    int plotWindth;
    int plotHeight;

    List<Point> points;


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
