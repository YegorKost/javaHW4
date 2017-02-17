package com.yegor.delivery_packs_task1.station;

/**
 * This class represents the station "B".
 * Created by YegorKost on 12.02.2017.
 */
public class PointB implements Point {
    private static final String STATION = "B";
    private int packs;

    private PointB(){}

    private static class PointBSingleton {
        static final PointB pointB = new PointB();
    }

    public static PointB getPointB() {
        return PointBSingleton.pointB;
    }

    @Override
    public synchronized int takePacks() {
        if (packs > 0){
            packs--;
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public synchronized void putPacks() {
        packs++;
    }

    @Override
    public synchronized int getPacks() {
        return packs;
    }

    @Override
    public synchronized void setPacks(int packs) {
        this.packs = packs;
    }

    @Override
    public String getStation() {
        return STATION;
    }
}
