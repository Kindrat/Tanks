package com.github.kindrat.programmerwars.server.persistence.entity;


import com.github.kindrat.programmerwars.server.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "UserGroup")
@Table(name = "role", schema = "player")
public class UserGroup {
    @Id
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Role userRole;
}
