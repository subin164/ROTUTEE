package com.greedy.rotutee.main.controller.member.dto;

public class RoleDTO {

    private int no;           //권한코트
    private String name;        //권한명
    private String desc;        //권한설정

    public RoleDTO() {}

    public RoleDTO(int no, String name, String desc) {
        this.no = no;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
