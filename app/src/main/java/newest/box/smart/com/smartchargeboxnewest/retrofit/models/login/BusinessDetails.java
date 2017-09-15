package newest.box.smart.com.smartchargeboxnewest.retrofit.models.login;

/**
 * Created by Deividas on 2017-08-08.
 */

public class BusinessDetails {
    private String name;
    private Logo logo;
    private String website;

    public BusinessDetails(String name, Logo logo, String website) {
        this.name = name;
        this.logo = logo;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public Logo getLogo() {
        return logo;
    }

    public String getWebsite() {
        return website;
    }
}
