package com.github.ouyushan.invenory.database;

import com.github.ouyushan.invenory.model.Car;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/6/13 20:56
 */
@ApplicationScoped
public class CarInventory {

    private List<Car> cars;

    public static final AtomicLong ids = new AtomicLong(0);

    @PostConstruct
    void init() {
        cars = new CopyOnWriteArrayList<>();
        initialData();
    }

    public List<Car> getCars() {
        return cars;
    }

    private void initialData() {

        Car mazda = new Car(ids.incrementAndGet(), "Mazda", "CX-5", "Skyactiv-G");
        cars.add(mazda);

        Car ford = new Car(ids.incrementAndGet(), "Ford", "Mustang", "GT 500");
        cars.add(ford);
    }
}
