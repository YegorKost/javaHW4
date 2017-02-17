package com.yegor.delivery_packs_task1.station;

/**
 * Interface for delivery points.
 * Created by YegorKost on 12.02.2017.
 */
public interface Point {
    /**
     * This method returns the name of station.
     * @return the name of station
     */
    String getStation();

    /**
     * Truck arrives to the station and takes one pack.
     * @return 1 if truck takes one pack from station or 0 if there are no packs
     */
    int takePacks();

    /**
     * Truck arrives to the station and puts one pack.
     */
    void putPacks();

    /**
     * This method return the number of packs.
     * @return the number of packs
     */
    int getPacks();

    /**
     * This method sets the number of packs to the station.
     * @param i the number of packs
     */
    void setPacks(int i);
}
