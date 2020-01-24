package nl.jparengkuan;


public class WeatherDataModel {

    private String stn;
    private String date;
    private String time;
    private String temp;
    private String dewp;
    private String stp;
    private String slp;
    private String visib;
    private String wdsp;
    private String prcp;
    private String sndp;
    private String frshtt;
    private String cldc;
    private String winddir;


    public String getStn() {
        return stn;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }

    public String getDewp() {
        return dewp;
    }

    public String getStp() {
        return stp;
    }

    public String getSlp() {
        return slp;
    }

    public String getVisib() {
        return visib;
    }

    public String getWdsp() {
        return wdsp;
    }

    public String getPrcp() {
        return prcp;
    }

    public String getSndp() {
        return sndp;
    }

    public String getFrshtt() {

        return frshtt;
    }

    public String getCldc() {
        return cldc;
    }

    public String getWinddir() {
        return winddir;
    }

    public WeatherDataModel(String stn, String date, String time, String temp, String dewp, String stp, String slp, String visib, String wdsp, String prcp, String sndp, String frshtt, String cldc, String winddir) {
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

    private void setTemp(String temp) {
        this.temp = temp;
    }

    private void setDewp(String dewp) {
        this.dewp = dewp;
    }

    private void setStp(String stp) {
        this.stp = stp;
    }

    private void setSlp(String slp) {
        this.slp = slp;
    }

    private void setVisib(String visib) {
        this.visib = visib;
    }

    private void setWdsp(String wdsp) {
        this.wdsp = wdsp;
    }

    private void setPrcp(String prcp) {
        this.prcp = prcp;
    }

    private void setSndp(String sndp) {
        this.sndp = sndp;
    }

    private void setFrshtt(String frshtt) {
        this.frshtt = frshtt;
    }

    private void setCldc(String cldc) {
        this.cldc = cldc;
    }

    private void setWinddir(String winddir) {
        this.winddir = winddir;
    }
}
