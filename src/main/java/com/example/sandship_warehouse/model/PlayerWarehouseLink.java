package com.example.sandship_warehouse.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "player_warehouse")
public class PlayerWarehouseLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK__PLAYER__ID"))
    private Player player;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK__MATERIAL__ID"))
    private Warehouse warehouse;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_type_id", foreignKey = @ForeignKey(name = "FK__MATERIAL__TYPE__ID"))
    private MaterialType materialType;

    @Column(name = "quantity")
    private Long quantity;

}
