package com.yegor.delivery_packs_task1.delivery;

import com.yegor.delivery_packs_task1.station.Point;
import com.yegor.delivery_packs_task1.station.PointA;
import com.yegor.delivery_packs_task1.station.PointB;
import com.yegor.delivery_packs_task1.truks.Truck;

/**
 * This class contains method that starts the "Delivery" task.
 * Created by YegorKost on 12.02.2017.
 */
public class Delivery {


    /**
     * This method starts the "Delivery" task.
     * @param packsInA the number of packs in station "A"
     * @param packsInB the number of packs in station "B"
     * @param trucksInA the number of trucks in station "A"
     * @param trucksInB the number of trucks in station "B"
     * @param secOfWayAB time that takes way from station "A" to "B"
     * @param secOfWayBA time that takes way from station "B" to "A"
     */
    public void startDelivery(int packsInA, int packsInB, int trucksInA, int trucksInB, long secOfWayAB, long secOfWayBA) {
        PointA pointA = PointA.getPointA();
        PointB pointB = PointB.getPointB();
        pointA.setPacks(packsInA);
        pointB.setPacks(packsInB);

        int moreTrucks;
        int lessTrucks;

        if (trucksInA > trucksInB) {
            moreTrucks = trucksInA;
            lessTrucks = trucksInB;
            startThreads(moreTrucks, lessTrucks, pointA, pointB, secOfWayAB, secOfWayBA);
        } else {
            moreTrucks = trucksInB;
            lessTrucks = trucksInA;
            startThreads(moreTrucks, lessTrucks, pointB, pointA, secOfWayBA, secOfWayAB);
        }
    }

    /**
     * This method starts trucks (threads) from both stations at the same time.
     * @param moreTrucks more number of trucks
     * @param lessTrucks less number of trucks
     * @param point1 station that contains more trucks before start of the delivery
     * @param point2 station that contains less trucks before start of the delivery
     * @param secOfWay1 time that takes way from point1 to point2
     * @param secOfWay2 time that takes way from point2 to point1
     */
    private void startThreads(int moreTrucks, int lessTrucks, Point point1, Point point2, long secOfWay1, long secOfWay2) {
        for (int i = 1; i <= moreTrucks; i++) {
            if (i <= lessTrucks) {
                new Thread(new Truck(point2, secOfWay2), "Mack-" + i).start();
            }
            new Thread(new Truck(point1, secOfWay1), "Scania-" + i).start();
        }
    }
}
