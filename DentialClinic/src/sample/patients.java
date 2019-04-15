package sample;

/**
 * Created by Mohamed Essam on 3/18/2019.
 */

public class patients {
    private String name;
    private String id;
    private String mobile;
    private String address;
    private String date;
    private String time;

    public patients(){
        this.name    = "";
        this.id      = "";
        this.mobile  = "";
        this.address = "";
    }

    public patients(String name, String id, String mobile, String address, String date, String time){
        this.name    = name;
        this.id      = id;
        this.mobile  = mobile;
        this.address = address;
        this.date    = date;
        this.time    = time;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}