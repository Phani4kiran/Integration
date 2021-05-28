package com.example.service;

import com.example.controller.TradeRequest;
import com.example.repository.Trade;
import com.example.repository.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TradeService {
    TradeRepository repository;

    public void post(TradeRequest dataR) {
        System.out.println("Harekrishna");
        List<Trade> trades =  repository.findAll();
        System.out.println("Hare krishna "+trades.size());
        //repository.getAll
    }
}
