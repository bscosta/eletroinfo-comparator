package com.eletroinfo.eletroinfo.comparator.cptr.entitie;

import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Costa
 */

@Entity
@Table(name = "budget", schema = "cptr")
@DynamicUpdate
public class Budget extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Column(name = "date_expire")
    private LocalDateTime dateExpire;

    private String note;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "budget_item", schema = "cptr",
            joinColumns = { @JoinColumn(name = "budget_id")},
            inverseJoinColumns = { @JoinColumn(name = "item_id")})
    private List<Item> items = new ArrayList<>();

    @Transient
    private Boolean isUpdateItem;

    public Boolean isNovo() {
        return this.id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDateTime getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(LocalDateTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getUpdateItem() {
        return isUpdateItem;
    }

    public void setUpdateItem(Boolean updateItem) {
        isUpdateItem = updateItem;
    }
}
