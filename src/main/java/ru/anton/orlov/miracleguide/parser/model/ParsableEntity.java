package ru.anton.orlov.miracleguide.parser.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.antlr.v4.runtime.misc.Nullable;
import ru.anton.orlov.miracleguide.utils.ImageUtils;
import ru.anton.orlov.miracleguide.utils.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by antonorlov on 15/10/15.
 */
@MappedSuperclass
public abstract class ParsableEntity {

    @Id
    @GeneratedValue
    protected Long id = 0L;

    @Column
    protected String link;

    @Column
    protected String name;

    @Column
    protected String translitName;

    @JsonIgnore
    @Column
    protected String imageLink;

    @Column
    @Nullable
    protected String imageName;

    protected ParsableEntity() {
    }

    public ParsableEntity(String link) {
        this.link = link;
    }

    public ParsableEntity(String link, String name) {
        this.link = link;
        this.name = name;
        if(name!=null){
            this.translitName = StringUtils.transliterate(StringUtils.removeWhitespaces(name)).toLowerCase();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(final String imageLink) {
        this.imageLink = imageLink;
    }


    public String getTranslitName() {
        if(translitName == null){
            translitName =  StringUtils.transliterate(StringUtils.removeWhitespaces(name)).toLowerCase();
        }
        return translitName;
    }

    public void setTranslitName(String translitName) {
        this.translitName = translitName;
    }

    @JsonGetter("imageName")
    public String getImageName() {
        if(imageName == null && imageLink != null){
            imageName = ImageUtils.getImageName(imageLink);
        }
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParsableEntity that = (ParsableEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
