package com.greedy.rotutee.member.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "TBL_ROLE")
public class Role {

    @Id
    @Column(name = "ROLE_NO")
    private int no;

    @Column(name = "ROLE_NAME")
    private String name;

    public Role() {}

    public Role(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
