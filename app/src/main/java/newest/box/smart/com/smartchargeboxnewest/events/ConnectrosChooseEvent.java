package newest.box.smart.com.smartchargeboxnewest.events;

import java.util.List;

import newest.box.smart.com.smartchargeboxnewest.retrofit.models.locations.Connectors;

/**
 * Created by Deividas on 2017-09-10.
 */

public class ConnectrosChooseEvent {
    private List<Connectors> connectors;
    private String id;

    public ConnectrosChooseEvent(List<Connectors> connectors, String id) {
        this.connectors = connectors;
        this.id = id;
    }

    public List<Connectors> getConnectors() {
        return connectors;
    }

    public String getId() {
        return id;
    }
}
