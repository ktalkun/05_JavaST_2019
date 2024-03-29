package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.exception.LogicException;

import java.util.List;

public interface OfferService extends Service {
    List<Offer> findAll() throws LogicException;

    Offer findByIdentity(int identity) throws LogicException;

    void save(Offer user) throws LogicException;

    void delete(int identity) throws LogicException;
}
