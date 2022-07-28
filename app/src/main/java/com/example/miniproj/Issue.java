package com.example.miniproj;

public class Issue {
    private int id;
    private String desc, etat ,gps;
    public Issue(String desc, String etat, String gps) {
        this.desc = desc;
        this.etat = etat;
        this.gps = gps;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", etat='" + etat + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }
}
