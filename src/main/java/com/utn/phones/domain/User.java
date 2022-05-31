package com.utn.phones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;
    @Column (name = "name")
    private String name;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "dni")
    private Integer dni;
    @Column (name = "password")
    private String password;
    @Column (name = "valid")
    private Boolean valid;

    @Column (name = "type_user")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_city")
    private City city;


    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "users_phone_line",
            joinColumns = @JoinColumn(name = "user_id_user"),
            inverseJoinColumns = @JoinColumn(name = "phone_line_id_line"))
    private PhoneLine phoneLine;


}

