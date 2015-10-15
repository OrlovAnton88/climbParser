package ru.anton.orlov.miracleguide.parser.model;

/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class Route extends ParsableEntity {

    private String desc;
    private String imageLink;

    private String level;
    private String sectorName;

    private VectorLine line;;

    public Route(String link) {
        super(link);
    }

    //    public Route(final String name, final String desc, final String imageLink) {
//        this.desc = desc;
//        this.imageLink = imageLink;
//    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(final String desc) {
        this.desc = desc;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(final String imageLink) {
        this.imageLink = imageLink;
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("desc='").append(desc).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append(", sectorName='").append(sectorName).append('\'');
        sb.append(", line=").append(line);
        sb.append('}');
        return sb.toString();
    }
}
