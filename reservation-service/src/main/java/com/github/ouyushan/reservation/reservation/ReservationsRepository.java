package com.github.ouyushan.reservation.reservation;

import java.util.List;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/6/5 14:14
 */
public interface ReservationsRepository {

    List<Reservation> findAll();

    Reservation save(Reservation reservation);

}
