package com.greedy.rotutee.member.entity;


import javax.persistence.*;

@Entity(name = "RoleMenuUrl")
@Table(name = "TBL_ROLE_MENU_URL")
public class RoleMenuUrl {

    @Id
    @Column(name = "ROLE_URL_NO")
    private int authorityMenuUrlNo;

    @ManyToOne
    @JoinColumn(name = "MENU_URL_NO")
    private MenuUrl menuUrl;

    @ManyToOne
    @JoinColumn(name = "MENU_DETAIL_NO")
    private MenuDetail menuDetail;

    @ManyToOne
    @JoinColumn(name = "ROLE_NO")
    private Role authority;

    public RoleMenuUrl() {}

    public RoleMenuUrl(int authorityMenuUrlNo, MenuUrl menuUrl, MenuDetail menuDetail, Role authority) {
        this.authorityMenuUrlNo = authorityMenuUrlNo;
        this.menuUrl = menuUrl;
        this.menuDetail = menuDetail;
        this.authority = authority;
    }

    public int getAuthorityMenuUrlNo() {
        return authorityMenuUrlNo;
    }

    public void setAuthorityMenuUrlNo(int authorityMenuUrlNo) {
        this.authorityMenuUrlNo = authorityMenuUrlNo;
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

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityMenuUrl{" +
                "authorityMenuUrlNo=" + authorityMenuUrlNo +
                ", menuUrl=" + menuUrl +
                ", menuDetail=" + menuDetail +
                ", authority=" + authority +
                '}';
    }
}
