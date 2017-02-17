package com.yegor.delivery_packs_task1;

import com.yegor.delivery_packs_task1.delivery.Delivery;

/**
 * In station "A": 1 pack, 5 trucks.
 * In station "B": 10 packs, 10 trucks.
 * The way from "A" to "B" takes 1 seconds.
 * The way from "B" to "A" takes 3 seconds.
 * Trucks start from "A" and "B" at the same time.
 * Created by YegorKost on 13.02.2017.
 */
public class StartDelivery3 {

    public static void main(String[] args) {
        Delivery delivery = new Delivery();
        delivery.startDelivery(1, 10, 5, 10, 1, 3);
    }

}
