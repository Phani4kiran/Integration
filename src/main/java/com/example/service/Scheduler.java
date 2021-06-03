package com.example.service;

import com.example.repository.InstrumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@EnableScheduling
public class Scheduler {
    @Autowired
    InstrumentsRepository instrumentsRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOldRecords() {
        instrumentsRepository.deleteLastMontRecords(LocalDate.now());
    }
}
