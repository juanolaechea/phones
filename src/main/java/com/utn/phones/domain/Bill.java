package com.utn.phones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
     Integer id;

    @Column (name = "amount_calls")
     Integer amountCalls;
    @Column (name = "cost")
     Float cost;
    @Column (name = "total_price")
     Float totalPrice;
    @Column (name = "bill_date")
     Date billDate;
    @Column (name = "expiration_date")
     Date expirationDate;
    @Column (name = "paid")
     boolean paid;


     @ManyToOne
     @JoinColumn(name = "phone_line_id_line")
     PhoneLine phoneLine;

    @ManyToOne
    @JoinColumn(name = "client_id_client")
    Client client;






}
