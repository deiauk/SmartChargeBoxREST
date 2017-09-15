package newest.box.smart.com.smartchargeboxnewest.events;

/**
 * Created by Deividas on 2017-09-10.
 */

public class TimeRangeSelectedEvent {
    public String startHour;
    public String startMin;
    public String endHour;
    public String endMin;

    private String startDay, endDay;
    private String startMonth, endMonth;
    private String startYear, endYear;

    private String connectorId;

    public TimeRangeSelectedEvent(String startYear, String startMonth, String startDay,
                                  int startHour, int startMin, String endYear, String endMonth,
                                  String endDay, int endHour, int endMin, String connectorId) {
        this.startHour = getFormatNumber(Integer.toString(startHour));
        this.startMin = getFormatNumber(Integer.toString(startMin));
        this.endHour = getFormatNumber(Integer.toString(endHour));
        this.endMin = getFormatNumber(Integer.toString(endMin));

        this.startYear = startYear;
        this.startMonth = getFormatNumber(startMonth);
        this.startDay = getFormatNumber(startDay);

        this.endYear = endYear;
        this.endMonth = getFormatNumber(endMonth);
        this.endDay = getFormatNumber(endDay);

        this.connectorId = connectorId;
    }

    private String getFormatNumber(String number) {
        if(number.length() == 2) {
            return number;
        }
        return "0" + number;
    }

    public String getStartTime() {
        return this.startYear + "-" + this.startMonth + "-" + this.startDay + "T" + this.startHour + ":" + this.startMin + ":00.000Z";
    }

    public String getEndTime() {
        return this.endYear + "-" + this.endMonth + "-" + this.endDay + "T" + this.endHour + ":" + this.endMin + ":00.000Z";
    }

    public String getConnectorId() {
        return connectorId;
    }
}