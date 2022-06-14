package com.utn.phones.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table (name = "phones_line")
public class PhoneLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_line")
    private Integer idLine;

    @Column(name = "number_line")
    private String numberLine;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "type_line")
    @Enumerated(EnumType.STRING)
    private PhoneLineType lineType;


    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "phone_line_id_line")
    List<Bill> bills = new ArrayList<>();



}
