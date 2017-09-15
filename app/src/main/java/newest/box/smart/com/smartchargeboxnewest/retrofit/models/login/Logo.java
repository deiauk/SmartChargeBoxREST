package newest.box.smart.com.smartchargeboxnewest.retrofit.models.login;

/**
 * Created by Deividas on 2017-08-09.
 */

public class Logo {
    private String url;
    private String thumbnail;
    private String category;
    private String type;
    private int width;
    private int height;

    public Logo(String url, String thumbnail, String category, String type, int width, int height) {
        this.url = url;
        this.thumbnail = thumbnail;
        this.category = category;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
