package com.example.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientPricesByInstrument {
    private String instrumentCode;
    private List<ClientPriceDetails> clientPriceDetails;

}
