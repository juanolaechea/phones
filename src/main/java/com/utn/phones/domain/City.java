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
@Table (name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
     Integer idCity;

    @Column(name = "name")
     String name;

    @Column(name = "code")
     public String code;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_state", nullable = false)
     State state;

    @OneToMany(mappedBy = "city")
    @JsonBackReference
    List<Client> users = new ArrayList<Client>();




}
