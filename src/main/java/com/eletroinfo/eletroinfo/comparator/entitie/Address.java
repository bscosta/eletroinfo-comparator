package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_address", nullable = false)
    private String typeAddress;

    @Column(name = "type_place", nullable = false)
    private String typePlace;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_register")
    private User userRegister;

    @ManyToOne(optional = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "user_last_update")
    private User userLastUpdate;

    @Column(name = "date_register", nullable = false)
    private LocalDateTime dateRegister;

    @Column(name = "date_last_update", nullable = true)
    private LocalDateTime dateLastUpdate;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_register", nullable = true)
    private ZoneId zoneRegister;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "zone_last_update", nullable = true)
    private ZoneId zoneLastUpdate;
}
