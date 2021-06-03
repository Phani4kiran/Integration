package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InstrumentsRepository extends JpaRepository<Instruments, Long> {
    List<Instruments> findByInstrumentCode(String instrumentCode);

    List<Instruments> findByClients(Clients clients);

    List<Instruments> findByInstrumentPrice(double instrumentPrice);

    List<Instruments> findByInstrumentDate(LocalDateTime instrumentDate);

}
