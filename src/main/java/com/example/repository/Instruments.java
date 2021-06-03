package com.example.repository;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instruments")
public class Instruments {
    @Id
    @Column(name = "instrument_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "instrument_code")
    private String instrumentCode;
    @Column(name = "instrument_price")
    private Double instrumentPrice;
    @Column(name = "instrument_date")
    private LocalDate instrumentDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Clients clients;
}