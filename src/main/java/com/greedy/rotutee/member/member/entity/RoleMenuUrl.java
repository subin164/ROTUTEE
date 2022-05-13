package com.greedy.rotutee.member.member.entity;


import javax.persistence.*;

@Entity(name = "RoleMenuUrl")
@Table(name = "TBL_ROLE_MENU_URL")
public class RoleMenuUrl {

    @Id
    @Column(name = "ROLE_URL_NO")
    private int roleMenuUrlNo;

    @ManyToOne
    @JoinColumn(name = "MENU_NO")
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_NO")
    private Role role;

    public RoleMenuUrl() {}

    public RoleMenuUrl(int roleMenuUrlNo, Menu menu, Role role) {
        this.roleMenuUrlNo = roleMenuUrlNo;
        this.menu = menu;
        this.role = role;
    }

    public int getRoleMenuUrlNo() {
        return roleMenuUrlNo;
    }

    public void setRoleMenuUrlNo(int roleMenuUrlNo) {
        this.roleMenuUrlNo = roleMenuUrlNo;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleMenuUrl{" +
                "roleMenuUrlNo=" + roleMenuUrlNo +
                ", menu=" + menu +
                ", role=" + role +
                '}';
    }
}
