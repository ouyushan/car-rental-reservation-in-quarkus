package com.github.ouyushan.reservation.inventory;

import jakarta.inject.Singleton;

import java.util.List;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/6/5 12:26
 */
@Singleton
public class InMemoryInventoryClient implements InventoryClient{

    private static final List<Car> cars = List.of(
            new Car(1L, "ABC-123", "Ford", "Mustang"),
            new Car(2L, "XYZ-123", "Tesla", "Model S"),
            new Car(3L, "QWE-123", "BMW", "M3")
    );

    @Override
    public List<Car> allCars() {
        return cars;
    }
}
