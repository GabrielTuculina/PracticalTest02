package ro.pub.cs.systems.eim.practicaltest02.model;

public class AlarmInformation {
    private String id;
    private String date;
    private String hour;

    public AlarmInformation(String id, String date, String hour) {
        this.id = id;
        this.date = date;
        this.hour = hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
