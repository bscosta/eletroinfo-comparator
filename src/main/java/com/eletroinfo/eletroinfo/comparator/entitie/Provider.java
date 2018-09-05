package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "provider_contact",
            joinColumns = { @JoinColumn(name = "provider_id", table = "provider", referencedColumnName="id")},
            inverseJoinColumns = { @JoinColumn(name = "contact_id", table = "contact", referencedColumnName="id")})
    private List<Contact> contacts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "provider_address",
            joinColumns = { @JoinColumn(name = "provider_id", table = "provider", referencedColumnName="id")},
            inverseJoinColumns = { @JoinColumn(name = "address_id", table = "address", referencedColumnName="id")})
    private List<Address> addresses;

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

    @Transient
    private Boolean isUpdateContact;

    public Boolean isNovo() {
        return this.id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public User getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(User userRegister) {
        this.userRegister = userRegister;
    }

    public User getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(User userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public LocalDateTime getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public ZoneId getZoneRegister() {
        return zoneRegister;
    }

    public void setZoneRegister(ZoneId zoneRegister) {
        this.zoneRegister = zoneRegister;
    }

    public ZoneId getZoneLastUpdate() {
        return zoneLastUpdate;
    }

    public void setZoneLastUpdate(ZoneId zoneLastUpdate) {
        this.zoneLastUpdate = zoneLastUpdate;
    }

    public Boolean getUpdateContact() {
        return isUpdateContact;
    }

    public void setUpdateContact(Boolean updateContact) {
        isUpdateContact = updateContact;
    }

    public Provider() {
        super();
        this.contacts = new ArrayList<>();
    }
}
