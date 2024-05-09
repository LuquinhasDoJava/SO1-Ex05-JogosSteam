package controller;

class SteamGame {
    private String name;
    private int year;
    private String month;
    private double avgPlayers;

    public SteamGame(String name, int year, String month, double avgPlayers) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.avgPlayers = avgPlayers;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public double getAvgPlayers() {
        return avgPlayers;
    }
}