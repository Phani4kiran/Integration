package com.example.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "Trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "product_date")
    private Date date;
}