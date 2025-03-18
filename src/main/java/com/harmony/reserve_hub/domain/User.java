package com.harmony.reserve_hub.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Plan plan = Plan.FREE;  // Plano padrão

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.MEMBER;  // Role padrão

    private String enterpriseName;

    private String optCode;

    private LocalDateTime optExpiration;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getOptExpiration() {
        return optExpiration;
    }

    public void setOptExpiration(LocalDateTime optExpiration) {
        this.optExpiration = optExpiration;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    // Construtores
    public User() {}

    public User(UUID id, String name, String email, String password, String enterpriseName, String optCode, LocalDateTime optExpiration) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enterpriseName = enterpriseName;
        this.optCode = optCode;
        this.optExpiration = optExpiration;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public enum Role {
        MEMBER,
        MASTER
    }

    public enum Plan{
        FREE,
        STARTER,
        DELUX,
    }
}

