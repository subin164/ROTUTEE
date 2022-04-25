package com.greedy.rotutee.member.tutorRequest.entity;

import com.greedy.rotutee.member.tutorRequest.dto.TutorApplyDTO;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.entity
 * fileName : Qualification
 * author : 7sang
 * date : 2022-04-23
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-23 7sang 최초 생성
 */

@Entity(name = "Member_Qualification")
@Table(name = "TBL_MEMBER_QUALIFICATION")
@SequenceGenerator(
        name = "QUALIFICATION_SEQ_GENERATOR",
        sequenceName = "QUALIFICATION_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Qualification {

    @Id
    @Column(name = "QUALIFICATION_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "QUALIFICATION_SEQ_GENERATOR"
    )
    private int qualificationNo;

    @JoinColumn(name = "APPLY_HISTORY_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private TutorApply tutorApply;

    @Column(name = "ACQUISITION_DATE")
    private Date acquisitionDate;

    @Column(name = "QUALIFICATION_NAME")
    private String name;

    @Column(name = "RATING")
    private String rating;

    public Qualification() {}

    public Qualification(int qualificationNo, TutorApply tutorApply, Date acquisitionDate, String name, String rating) {
        this.qualificationNo = qualificationNo;
        this.tutorApply = tutorApply;
        this.acquisitionDate = acquisitionDate;
        this.name = name;
        this.rating = rating;
    }

    public int getQualificationNo() {
        return qualificationNo;
    }

    public void setQualificationNo(int qualificationNo) {
        this.qualificationNo = qualificationNo;
    }

    public TutorApply getTutorApply() {
        return tutorApply;
    }

    public void setTutorApply(TutorApply tutorApply) {
        this.tutorApply = tutorApply;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "qualificationNo=" + qualificationNo +
//                ", tutorApply=" + tutorApply +
                ", acquisitionDate='" + acquisitionDate + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
