package com.greedy.rotutee.member.entity;


import javax.persistence.*;

@Entity(name = "RoleMenuUrl")
@Table(name = "TBL_ROLE_MENU_URL")
public class RoleMenuUrl {

    @Id
    @Column(name = "ROLE_URL_NO")
    private int roleMenuUrlNo;

    @ManyToOne
    @JoinColumn(name = "MENU_URL_NO")
    private MenuUrl menuUrl;

    @ManyToOne
    @JoinColumn(name = "MENU_DETAIL_NO")
    private MenuDetail menuDetail;

    @ManyToOne
    @JoinColumn(name = "ROLE_NO")
    private Role role;

    public RoleMenuUrl() {}

    public RoleMenuUrl(int roleMenuUrlNo, MenuUrl menuUrl, MenuDetail menuDetail, Role role) {
        this.roleMenuUrlNo = roleMenuUrlNo;
        this.menuUrl = menuUrl;
        this.menuDetail = menuDetail;
        this.role = role;
    }

    public int getRoleMenuUrlNo() {
        return roleMenuUrlNo;
    }

    public void setRoleMenuUrlNo(int roleMenuUrlNo) {
        this.roleMenuUrlNo = roleMenuUrlNo;
    }

    public MenuUrl getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(MenuUrl menuUrl) {
        this.menuUrl = menuUrl;
    }

    public MenuDetail getMenuDetail() {
        return menuDetail;
    }

    public void setMenuDetail(MenuDetail menuDetail) {
        this.menuDetail = menuDetail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthorityMenuUrl{" +
                "roleMenuUrlNo=" + roleMenuUrlNo +
                ", menuUrl=" + menuUrl +
                ", menuDetail=" + menuDetail +
                ", role=" + role +
                '}';
    }
}
