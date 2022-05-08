package hu.mobilalkfejl.a5d_ultrahang;

import java.time.LocalDate;


public class BookingData {
    private String time;
    private String date;
    private boolean status;

    public BookingData(String time, String date) {   //String date
        this.time = time;
        this.date = date;
        this.status = true;
    }

    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public boolean isStatus() {return status;}
    public void setStatus(boolean status) {this.status = status;}
}
