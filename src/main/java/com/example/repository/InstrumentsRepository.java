package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstrumentsRepository extends JpaRepository<Instruments, Long> {
    List<Instruments> findByInstrumentCode(String instrumentCode);

    List<Instruments> findByClients(Clients clients);

    @Modifying
    @Query(value = "delete from instruments i where i.instrumentDate - :today >30", nativeQuery = true)
    void deleteLastMontRecords(LocalDate today);

}
