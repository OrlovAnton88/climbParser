/**
 * Author:      oav <br>
 * Date:        12.10.15, 19:37 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
public class Route {

    private String name;
    private String desc;
    private String imageLink;

    private String level;
    private String sectorName;

    public Route(final String name, final String desc, final String imageLink) {
        this.name = name;
        this.desc = desc;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        sb.append("name='").append(name).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", imageLink='").append(imageLink).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append(", sectorName='").append(sectorName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
