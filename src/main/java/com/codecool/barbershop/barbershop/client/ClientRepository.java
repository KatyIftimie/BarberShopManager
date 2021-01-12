package com.codecool.barbershop.barbershop.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    ClientModel getByClientId(long clientId);

    @Modifying
    @Query("delete from ClientModel c where c.clientId= :clientId")
    void deleteClientModelByClientId(long clientId);



}
