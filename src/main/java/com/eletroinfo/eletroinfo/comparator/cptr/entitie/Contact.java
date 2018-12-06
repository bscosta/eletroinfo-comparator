package com.eletroinfo.eletroinfo.comparator.cptr.entitie;

import com.eletroinfo.eletroinfo.comparator.converters.ZoneIdConverter;
import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "contact", schema = "cptr")
@DynamicUpdate
public class Contact extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_contact", nullable = false)
    private String typeContact;

    @Column(name = "form_contact", nullable = false)
    private String formContact;

    private String name;

    @Column(name = "value_contact", nullable = false)
    private String valueContact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(String typeContact) {
        this.typeContact = typeContact;
    }

    public String getFormContact() {
        return formContact;
    }

    public void setFormContact(String formContact) {
        this.formContact = formContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueContact() {
        return valueContact;
    }

    public void setValueContact(String valueContact) {
        this.valueContact = valueContact;
    }
}
