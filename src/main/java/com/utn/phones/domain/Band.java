package com.utn.phones.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "bands")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_band")
    Integer idBand;

    @Column (name = "hour_initial")
    Integer hourInitial;

    @Column (name = "hour_finished")
    Integer hourFinished;

    @Column (name = "price")
    Float price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_tariff", nullable = false)
    Tariff tariff;

}
