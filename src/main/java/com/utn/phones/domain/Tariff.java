package com.utn.phones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_tariff")
     Integer idTariff;

    @Column (name = "hour_initial")
    LocalTime hourInitial;

    @Column (name = "hour_finished")
    LocalTime hourFinished;

    @Column (name = "price_x_minute")
    Float priceXminute;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @Fetch(FetchMode.JOIN)
     City cityOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @Fetch(FetchMode.JOIN)
     City cityDestination;


}
