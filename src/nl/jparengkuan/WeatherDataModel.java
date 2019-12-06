package nl.jparengkuan;

import java.sql.Time;

public class WeatherDataModel {

    private String stn;
    private String date;
    private String time;
    private Float temp;
    private Float dewp;
    private Float stp;
    private Float slp;
    private Float visib;
    private Float wdsp;
    private Float prcp;
    private Float sndp;
    private String frshtt;
    private Float cldc;
    private int winddir;


    public int getStn() {
        return Integer.parseInt(stn);
    }

    public java.sql.Date getDate() {
        return java.sql.Date.valueOf(date);
    }

    public Time getTime() {
        return Time.valueOf(time);
    }

    public Float getTemp() {
        return temp;
    }

    public Float getDewp() {
        return dewp;
    }

    public Float getStp() {
        return stp;
    }

    public Float getSlp() {
        return slp;
    }

    public Float getVisib() {
        return visib;
    }

    public Float getWdsp() {
        return wdsp;
    }

    public Float getPrcp() {
        return prcp;
    }

    public Float getSndp() {
        return sndp;
    }

    public int getFrshtt() {
        return Integer.parseInt(frshtt);
    }

    public Float getCldc() {
        return cldc;
    }

    public int getWinddir() {
        return winddir;
    }

    public WeatherDataModel(String stn, String date, String time, Float temp, Float dewp, Float stp, Float slp, Float visib, Float wdsp, Float prcp, Float sndp, String frshtt, Float cldc, int winddir) {
        this.setStn(stn);
        this.setDate(date);
        this.setTime(time);
        this.setTemp(temp);
        this.setDewp(dewp);
        this.setStp(stp);
        this.setSlp(slp);
        this.setVisib(visib);
        this.setWdsp(wdsp);
        this.setPrcp(prcp);
        this.setSndp(sndp);
        this.setFrshtt(frshtt);
        this.setCldc(cldc);
        this.setWinddir(winddir);
    }

    private void setStn(String stn) {
        this.stn = stn;
    }

    private void setDate(String date) {
        this.date = date;
    }

    private void setTime(String time) {
        this.time = time;
    }

    private void setTemp(Float temp) {
        this.temp = temp;
    }

    private void setDewp(Float dewp) {
        this.dewp = dewp;
    }

    private void setStp(Float stp) {
        this.stp = stp;
    }

    private void setSlp(Float slp) {
        this.slp = slp;
    }

    private void setVisib(Float visib) {
        this.visib = visib;
    }

    private void setWdsp(Float wdsp) {
        this.wdsp = wdsp;
    }

    private void setPrcp(Float prcp) {
        this.prcp = prcp;
    }

    private void setSndp(Float sndp) {
        this.sndp = sndp;
    }

    private void setFrshtt(String frshtt) {
        this.frshtt = frshtt;
    }

    private void setCldc(Float cldc) {
        this.cldc = cldc;
    }

    private void setWinddir(int winddir) {
        this.winddir = winddir;
    }
}
