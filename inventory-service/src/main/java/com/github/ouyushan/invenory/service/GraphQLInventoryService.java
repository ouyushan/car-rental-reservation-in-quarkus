package com.github.ouyushan.invenory.service;

import com.github.ouyushan.invenory.database.CarInventory;
import com.github.ouyushan.invenory.model.Car;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/7/1 11:07
 */
@GraphQLApi
public class GraphQLInventoryService {

    @Inject
    CarInventory carInventory;

    @Query
    public List<Car> cars(){
        return carInventory.getCars();
    }

    @Mutation
    public Car register(Car car){
        car.setId(CarInventory.ids.incrementAndGet());
        carInventory.getCars().add(car);
        return car;
    }

    @Mutation
    public boolean remove(String licensePlateNumber){
        List<Car> cars = carInventory.getCars();
        Optional<Car> toBeremoved = cars.stream().filter(car -> car.getLicensePlateNumber().equals(licensePlateNumber)).findAny();
        return toBeremoved.map(cars::remove).orElse(false);
    }
}
