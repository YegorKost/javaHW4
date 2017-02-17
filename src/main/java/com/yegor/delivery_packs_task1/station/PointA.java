package com.yegor.delivery_packs_task1.station;

/**
 * This class represents the station "A".
 * Created by YegorKost on 12.02.2017.
 */
public class PointA implements Point {
    private static final String STATION = "A";
    private int packs;

    private PointA(){}

    private static class PointASingleton {
        static final PointA pointA = new PointA();
    }

    public static PointA getPointA() {
        return PointASingleton.pointA;
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
