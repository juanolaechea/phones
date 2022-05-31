package com.utn.phones.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "calls")
public class Call{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_call")
     Integer idCall;

    @Column(name="phone_line_origin")
    String phoneLineOrigin;
    @Column(name = "phone_line_destination")
    String phoneLineDestination;
    @Column(name="date_time")
    LocalDateTime dateTime;
    @Column (name = "duration")
    Long duration;

    @Column (name = "total_price")
    Float totalPrice;
    @Column (name = "price_x_minute")
    Float priceXmin;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private City cityOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private City cityDestination;











}

