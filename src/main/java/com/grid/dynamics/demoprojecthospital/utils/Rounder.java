package com.grid.dynamics.demoprojecthospital.utils;

public class Rounder {
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
