package newest.box.smart.com.smartchargeboxnewest.events;

/**
 * Created by Deividas on 2017-09-10.
 */

public class OnConnectorSelectedEvent {
    private String id;

    public OnConnectorSelectedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
