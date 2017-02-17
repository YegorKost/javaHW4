package com.yegor.delivery_packs_task1.truks;

import com.yegor.delivery_packs_task1.station.Point;
import com.yegor.delivery_packs_task1.station.PointA;
import com.yegor.delivery_packs_task1.station.PointB;

/**
 * This class represent a truck.
 * Created by YegorKost on 12.02.2017.
 */
public class Truck implements Runnable {

    private Point point;
    private long timeOfTheWay;

    /**
     * Creates new truck.
     * @param point the station where a truck is
     * @param timeOfTheWay the time of the delivery
     */
    public Truck(Point point, long timeOfTheWay) {
        this.point = point;
        this.timeOfTheWay = timeOfTheWay * 1000;
    }

    public void run() {
        try {

            while (!Thread.interrupted()) {
                Point deliveryPoint;
                if (point.getStation().equals("A")) {
                    deliveryPoint = PointB.getPointB();
                    deliveryPack(point, deliveryPoint, timeOfTheWay);
                } else {
                    deliveryPoint = PointA.getPointA();
                    deliveryPack(point, deliveryPoint, timeOfTheWay);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void deliveryPack(Point point, Point deliveryPoint, long timeOfTheWay) throws InterruptedException {
        if (point.takePacks() > 0) {
            System.out.println("Truck " + Thread.currentThread().getName() + " takes 1 pack from " + point.getStation() + " (" +
                    point.getPacks() + " packs are kept in " + point.getStation() + ")");
            Thread.sleep(timeOfTheWay);
            deliveryPoint.putPacks();
            this.point = deliveryPoint;
            System.out.println("Truck " + Thread.currentThread().getName() + " put 1 pack to " + deliveryPoint.getStation() + " (" +
                    deliveryPoint.getPacks() + " packs are kept in " + deliveryPoint.getStation() + ")");
            Thread.sleep(1000);
        } else {
            System.out.println("-----Truck " + Thread.currentThread().getName() + " await pack in " + point.getStation() + " (" +
                    point.getPacks() + " packs are kept in " + point.getStation() + ")-----");
            Thread.sleep(500);
        }
    }
}
