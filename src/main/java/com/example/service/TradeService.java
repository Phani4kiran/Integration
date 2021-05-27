package com.example.service;

import com.example.repository.TradeData;
import com.example.repository.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TradeService {
//    TradeRepository repository;

    public void post(TradeData data) {
        System.out.println("Harekrishna");
        //repository.getAll
    }
}
