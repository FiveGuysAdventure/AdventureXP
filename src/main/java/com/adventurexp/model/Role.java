package com.adventurexp.model;

import com.adventurexp.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table (name = "role")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleName roleName;

    public Role(){}
    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
