package com.example.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentPriceDetails {
    private String instrumentCode;
    private Double price;
    private LocalDate localDate;
}
