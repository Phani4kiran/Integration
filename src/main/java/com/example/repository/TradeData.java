package com.example.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TradeData {
    private String clientId;
    private String name;
    private Double price;
    private Date  date;
}