package com.eletroinfo.eletroinfo.comparator.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "seller")
public class Seller extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seller_contact",
            joinColumns = { @JoinColumn(name = "seller_id", table = "seller", referencedColumnName="id")},
            inverseJoinColumns = { @JoinColumn(name = "contact_id", table = "contact", referencedColumnName="id")})
    private List<Contact> contacts;

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Boolean getUpdateContact() {
        return isUpdateContact;
    }

    public void setUpdateContact(Boolean updateContact) {
        isUpdateContact = updateContact;
    }

    public Seller() {
        super();
        this.contacts = new ArrayList<>();
    }
}
