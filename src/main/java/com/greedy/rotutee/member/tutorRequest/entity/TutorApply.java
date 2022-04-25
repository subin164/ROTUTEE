package com.greedy.rotutee.member.tutorRequest.entity;

import com.greedy.rotutee.member.member.dto.MemberDTO;
import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.tutorRequest.dto.CareerDTO;
import com.greedy.rotutee.member.tutorRequest.dto.QualificationDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.entity
 * fileName : TutorApply
 * author : 7sang
 * date : 2022-04-23
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-23 7sang 최초 생성
 */

@Entity(name = "Member_TutorApply")
@Table(name = "TBL_TUTOR_APPLY_HISTORY")
@SequenceGenerator(
        name = "TUTOR_APPLY_SEQ_GENERATOR",
        sequenceName = "APPLY_HISTORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class TutorApply {

    @Id
    @Column(name = "APPLY_HISTORY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TUTOR_APPLY_SEQ_GENERATOR"
    )
    private int applyHistoryNo;

    @JoinColumn(name = "MEMBER_NO")
    @ManyToOne
    private Member member;

    @Column(name = "APPLY_DATE")
    private Date applyDate;

    @Column(name = "APPLY_YN")
    private String applyYn;

    @Column(name = "APPLY_STATUS_CHANGE_DATE")
    private Date applyStatusDate;

//    @JoinColumn(name = "MEMBER_NO")
//    @ManyToOne
//    private Member admin;

    @OneToMany(mappedBy = "tutorApply", cascade = CascadeType.PERSIST)
    private List<Career> careerList;

    @OneToMany(mappedBy = "tutorApply", cascade = CascadeType.PERSIST)
    private List<Qualification> qualificationList;

    public TutorApply() {}

    public TutorApply(int applyHistoryNo, Member member, Date applyDate, String applyYn, Date applyStatusDate, Member admin, List<Career> careerList, List<Qualification> qualificationList) {
        this.applyHistoryNo = applyHistoryNo;
        this.member = member;
        this.applyDate = applyDate;
        this.applyYn = applyYn;
        this.applyStatusDate = applyStatusDate;
//        this.admin = admin;
        this.careerList = careerList;
        this.qualificationList = qualificationList;
    }

    public int getApplyHistoryNo() {
        return applyHistoryNo;
    }

    public void setApplyHistoryNo(int applyHistoryNo) {
        this.applyHistoryNo = applyHistoryNo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyYn() {
        return applyYn;
    }

    public void setApplyYn(String applyYn) {
        this.applyYn = applyYn;
    }

    public Date getApplyStatusDate() {
        return applyStatusDate;
    }

    public void setApplyStatusDate(Date applyStatusDate) {
        this.applyStatusDate = applyStatusDate;
    }

//    public Member getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Member admin) {
//        this.admin = admin;
//    }

    public List<Career> getCareerList() {
        return careerList;
    }

    public void setCareerList(List<Career> careerList) {
        this.careerList = careerList;
    }

    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @Override
    public String toString() {
        return "TutorApply{" +
                "applyHistoryNo=" + applyHistoryNo +
                ", member=" + member +
                ", applyDate=" + applyDate +
                ", applyYn='" + applyYn + '\'' +
                ", applyStatusDate=" + applyStatusDate +
//                ", admin=" + admin +
//                ", careerList=" + careerList +
//                ", qualificationList=" + qualificationList +
                '}';
    }
}
