package com.curso.fullstack.back.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 4, max = 12)
    private String nombre;
    @Column(nullable = false)
    @NotBlank
    private String apellido;
    @Column(nullable = false, unique = false)
    @Email
    private String email;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha no puede ser nula")
    private Date createdAt;
    @Column(nullable = true)
    private String foto;

    // La derecha es el modelo e izquirda su relacion muchos clientes pertenecen a una region
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull( message = "La region no puede ser nula")
    @JoinColumn(name = "region_id")
    //Se ignoran las propiedades que egenera por detras sprinboot que deben ser ignorados
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Region region;

    // @PrePersist
    // public void prePersist() {
    // this.createdAt = new Date();
    // }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    private static final long serialVersionUID = 1L;
}
