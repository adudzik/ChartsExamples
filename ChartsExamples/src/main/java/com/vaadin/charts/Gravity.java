package com.vaadin.charts;

import java.util.List;

import java.util.ArrayList;

public class Gravity {
    private final double insideVar;
    private final double outsideVar;

    public static class GravityInfo {
        private int radius;
        private double gravityAcc;

        public int getRadius() {
            return radius;
        }

        public double getGravityAcc() {
            return gravityAcc;
        }

        public GravityInfo(int r, double g) {
            this.radius = r;
            this.gravityAcc = g;
        }
    }

    public Gravity() {
        double G = 6.67;
        double R = 6400000;
        double M = 5.97;
        this.outsideVar = G * M * 1e13;
        this.insideVar = this.outsideVar / (R * R * R);
    }

    public List<GravityInfo> getGravityData() {
        List<GravityInfo> result = new ArrayList<>();

        for (int r = 50; r < 6400; r += 50) {
            result.add(new GravityInfo(r, this.insideVar * r * 1000));
        }

        for (int r = 6400; r < 30000; r += 50) {
            result.add(new GravityInfo(r, this.outsideVar / ((r * r) * 1e6)));
        }

        return result;
    }
}
