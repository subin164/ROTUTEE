package com.greedy.rotutee.member.tutorRequest.entity;

import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.entity
 * fileName : Career
 * author : 7sang
 * date : 2022-04-23
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-23 7sang 최초 생성
 */

@Entity(name = "Member_Career")
@Table(name = "TBL_MEMBER_CAREER")
@SequenceGenerator(
        name = "CAREER_SEQ_GENERATOR",
        sequenceName = "CAREER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Career {

    @Id
    @Column(name = "CAREER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CAREER_SEQ_GENERATOR"
    )
    private int CareerNo;

    @JoinColumn(name = "APPLY_HISTORY_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private TutorApply tutorApply;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "RESPONSIBILITIES")
    private String responsibilities;

    public Career() {}

    public Career(int careerNo, TutorApply tutorApply, Date startDate, Date endDate, String position, String company, String responsibilities) {
        CareerNo = careerNo;
        this.tutorApply = tutorApply;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.company = company;
        this.responsibilities = responsibilities;
    }

    public int getCareerNo() {
        return CareerNo;
    }

    public void setCareerNo(int careerNo) {
        CareerNo = careerNo;
    }

    public TutorApply getTutorApply() {
        return tutorApply;
    }

    public void setTutorApply(TutorApply tutorApply) {
        this.tutorApply = tutorApply;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    @Override
    public String toString() {
        return "Career{" +
                "CareerNo=" + CareerNo +
//                ", tutorApply=" + tutorApply +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}
