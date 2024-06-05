package com.github.ouyushan.reservation.reservation;

import java.time.LocalDate;

/**
 * @description:
 * @author: ouyushan
 * @email: ouyushan@hotmail.com
 * @date: 2024/6/5 14:08
 */
public class Reservation {

    private Long id;
    private String userId;
    private Long carId;
    private LocalDate startDay;
    private LocalDate endDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public Reservation() {
    }

    /**
     * Check if the given duration overlaps with this reservation
     *
     * @param startDay
     * @param endDay
     * @return
     */
    public boolean isReserved(LocalDate startDay, LocalDate endDay) {
        return (!(endDay.isAfter(this.startDay) || startDay.isAfter(this.endDay)));
    }
}
