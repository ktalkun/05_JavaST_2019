package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.exception.PersistentException;

import java.util.List;

public interface ReservationDao extends Dao<Reservation> {
    List<Reservation> readByCustomer(int customerId) throws PersistentException;

    List<Reservation> readByEmployee(int employeeId) throws PersistentException;
}
