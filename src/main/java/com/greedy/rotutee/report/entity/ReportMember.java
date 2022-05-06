package com.greedy.rotutee.report.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.report.entity
 * fileName : ReportMember
 * author : SeoYoung
 * date : 2022-05-05
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-05 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Report_member")
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "REPORT_MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class ReportMember {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_MEMBER_SEQ_GENERATOR"
    )
    @Column(name = "MEMBER_NO")
    private int no;

    @Column(name = "MEMBER_NAME")
    private String name;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(name = "MEMBER_PASSWORD")
    private String pwd;

    @Column(name = "MEMBER_NICKNAME")
    private String nickname;

    @Column(name = "MEMBER_PHONE_NUM")
    private String phoneNum;

    @Column(name = "MEMBER_INTRODUCTION")
    private String introduction;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

    @Column(name = "WITHDRAWAL_DATE")
    private Date withdrawalDate;

    @Column(name = "LEAVE_STATUS_YN")
    private String leaveStatusYn;

    @Column(name = "ROULETTE_CHANCE")
    private String rouletteChance;

    @Override
    public String toString() {
        return "ReportMember{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registrationDate=" + registrationDate +
                ", withdrawalDate=" + withdrawalDate +
                ", leaveStatusYn='" + leaveStatusYn + '\'' +
                ", rouletteChance='" + rouletteChance + '\'' +
                '}';
    }
}
