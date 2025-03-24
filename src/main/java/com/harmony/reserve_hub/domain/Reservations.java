package com.harmony.reserve_hub.domain;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "Reservations")
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientCPF;

    @Column(nullable = false)
    private ZonedDateTime checkIn;

    @Column(nullable = false)
    private ZonedDateTime checkOut;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status =  Status.CONFIRMADA;

    @Column(nullable = false)
    private String room;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String paymentStatus;

    private ZonedDateTime createdAt = ZonedDateTime.now();

    private ZonedDateTime updatedAt = ZonedDateTime.now();

    @ManyToOne
    @JoinColumn(name = "enter" +
            "prise_id", referencedColumnName = "id", nullable = false)
    private Enterprise enterpriseId;

    public Reservations() {
    }

    public Reservations(
            String clientName,
            String clientCPF,
            ZonedDateTime checkIn,
            ZonedDateTime checkOut,
            String room,
            String origin,
            String paymentStatus,
            Enterprise enterpriseId
    ) {
        this.clientName = clientName;
        this.clientCPF = clientCPF;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = Status.CONFIRMADA;
        this.room = room;
        this.origin = origin;
        this.paymentStatus = paymentStatus;
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.enterpriseId = enterpriseId;
    }

    public enum Status {
        CONFIRMADA,
        CANCELADA,
        ANDAMENTO,
        FINALIZADA,
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCPF() {
        return clientCPF;
    }

    public void setClientCPF(String clientCPF) {
        this.clientCPF = clientCPF;
    }

    public ZonedDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(ZonedDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public ZonedDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(ZonedDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Enterprise getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Enterprise enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
