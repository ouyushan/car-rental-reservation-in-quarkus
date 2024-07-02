package com.github.ouyushan.reservation.rest;

import com.github.ouyushan.reservation.inventory.Car;
import com.github.ouyushan.reservation.inventory.GraphQLInventoryClient;
import com.github.ouyushan.reservation.inventory.InventoryClient;
import com.github.ouyushan.reservation.reservation.Reservation;
import com.github.ouyushan.reservation.reservation.ReservationsRepository;
import com.github.ouyushan.reservation.retal.RentalClient;
import io.smallrye.graphql.client.GraphQLClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/6/5 15:07
 */
@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    private final ReservationsRepository reservationsRepository;

    private final InventoryClient inventoryClient;

    private final RentalClient rentalClient;

    public ReservationResource(ReservationsRepository reservationsRepository,
                               @GraphQLClient("inventory") GraphQLInventoryClient inventoryClient,
                               @RestClient RentalClient rentalClient) {
        this.reservationsRepository = reservationsRepository;
        this.inventoryClient = inventoryClient;
        this.rentalClient = rentalClient;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Reservation make(Reservation reservation) {
        Reservation result = reservationsRepository.save(reservation);
        // this is just a dummy value for the time being
        String userId = "x";
        if (reservation.getStartDay().equals(LocalDate.now())) {
            rentalClient.start(userId, result.getId());
        }
        return result;
    }

    @GET
    @Path("/availability")
    public Collection<Car> allReservations(@RestQuery LocalDate startDay, @RestQuery LocalDate endDay) {
        // obtain all cars from inventory
        List<Car> cars = inventoryClient.allCars();
        Map<Long,Car> carMap = cars.stream().collect(Collectors.toMap(Car::getId, Function.identity()));

        // get all current reservations
        List<Reservation> reservations = reservationsRepository.findAll();
        reservations.stream().filter(reservation -> reservation.isReserved(startDay, endDay))
                .forEach(reservation -> carMap.remove(reservation.getCarId()));

        return carMap.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Reservation createReservation(Reservation reservation) {
        return reservationsRepository.save(reservation);
    }

}
