package com.utn.phones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
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







}
