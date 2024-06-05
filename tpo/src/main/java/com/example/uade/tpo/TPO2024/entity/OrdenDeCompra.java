package com.example.uade.tpo.TPO2024.entity;

import java.util.List;

import com.example.uade.tpo.TPO2024.dto.FiestaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "ordenes")
@Getter
@Setter
public class OrdenDeCompra {

    public OrdenDeCompra() {

    }

    public OrdenDeCompra(User user, List<FiestaDTO> fiestas, int montoTotal) {
        this.user = user;
        this.fiestas = fiestas;
        this.montoTotal = montoTotal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // private Long userId;

    // @OneToMany(mappedBy = "orden")
    // private List<FiestaAsociada> fiestasAsociadas;

    @OneToMany(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL)
    private List<FiestaDTO> fiestas;

    @Column
    private int montoTotal;

    // Getter para el userId
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

}
