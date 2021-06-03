package com.example.repository;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Builder
@Data
@NoArgsConstructor
@Component
@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "client_code")
    private String clientCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "clients")
    private List<Instruments> instruments;

    public Clients(long id, String clientCode, List<Instruments> instruments) {
        this.id = id;
        this.clientCode = clientCode;
        this.instruments = new ArrayList<>();
    }
}
