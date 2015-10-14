package ru.anton.orlov.miracleguide.parser.model;

/**
 * Created by antonorlov on 15/10/15.
 */
public abstract class ParsableEntity {

    protected String link;

    protected String name;


    public ParsableEntity(String link) {
        this.link = link;
    }

    public ParsableEntity(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParsableEntity that = (ParsableEntity) o;

        if (!link.equals(that.link)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
