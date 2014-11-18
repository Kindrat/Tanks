package com.github.kindrat.programmerwars.tanks.server.persistence.entity;


import com.github.kindrat.programmerwars.tanks.common.dto.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role", schema = "player")
public class UserGroup implements Serializable {

    private static final long serialVersionUID = -1579189396899547441L;

    @Id
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Role userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return userRole;
    }

    public void setRole(Role role) {
        this.userRole = role;
    }

}
