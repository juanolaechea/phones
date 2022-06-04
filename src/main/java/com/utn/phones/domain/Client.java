package com.utn.phones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Client")
    private Integer idClient;
    @Column (name = "name")
    private String name;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "dni")
    private Integer dni;


    @Column (name = "type_user")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_city")
    private City city;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private PhoneLine phoneLine;

    @OneToMany(mappedBy = "client")
    List<Bill> bills = new ArrayList<>();



}

