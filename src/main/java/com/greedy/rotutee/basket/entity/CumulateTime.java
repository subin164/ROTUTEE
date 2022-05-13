package com.greedy.rotutee.basket.entity;

import com.greedy.rotutee.basket.dto.ClassDTO;
import com.greedy.rotutee.basket.dto.MemberLectureDTO;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.basket.entity
 * fileName : CumulateTime
 * author : soobeen
 * date : 2022-05-12
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-12          soobeen     최초 생성
 */
@Entity(name = "CumulateTime")
@Table(name = "TBL_CUMULATIVE_TIME")
@SequenceGenerator(
        name =  "CUMULATIVE_TIME_BASKET_SEQ_GENERATOR",
        sequenceName = "CUMULATIVE_TIME_NO",
        allocationSize = 1,
        initialValue = 1
)
public class CumulateTime {

    @Id
    @Column(name = "CUMULATIVE_TIME_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CUMULATIVE_TIME_BASKET_SEQ_GENERATOR"
    )
    private int cumlateTimeNo;

    @Column(name = "FINISHED_WATCHING_YN")
    private String finishedWatchingYN;

    @Column(name = "WATCH_COMPLETION_DATE")
    private Date watchCompletionDate;

    @JoinColumn(name = "CLASS_NO")
    @ManyToOne
    private Class cumClass;

    @JoinColumn(name = "MEMBER_LECTURE_NO")
    @ManyToOne
    private MemberLecture memberLecture;

    public CumulateTime() {
    }

    public CumulateTime(int cumlateTimeNo, String finishedWatchingYN, Date watchCompletionDate, Class cumClass, MemberLecture memberLecture) {
        this.cumlateTimeNo = cumlateTimeNo;
        this.finishedWatchingYN = finishedWatchingYN;
        this.watchCompletionDate = watchCompletionDate;
        this.cumClass = cumClass;
        this.memberLecture = memberLecture;
    }

    public int getCumlateTimeNo() {
        return cumlateTimeNo;
    }

    public void setCumlateTimeNo(int cumlateTimeNo) {
        this.cumlateTimeNo = cumlateTimeNo;
    }

    public String getFinishedWatchingYN() {
        return finishedWatchingYN;
    }

    public void setFinishedWatchingYN(String finishedWatchingYN) {
        this.finishedWatchingYN = finishedWatchingYN;
    }

    public Date getWatchCompletionDate() {
        return watchCompletionDate;
    }

    public void setWatchCompletionDate(Date watchCompletionDate) {
        this.watchCompletionDate = watchCompletionDate;
    }

    public Class getCumClass() {
        return cumClass;
    }

    public void setCumClass(Class cumClass) {
        this.cumClass = cumClass;
    }

    public MemberLecture getMemberLecture() {
        return memberLecture;
    }

    public void setMemberLecture(MemberLecture memberLecture) {
        this.memberLecture = memberLecture;
    }

    @Override
    public String toString() {
        return "CumulateTime{" +
                "cumlateTimeNo=" + cumlateTimeNo +
                ", finishedWatchingYN='" + finishedWatchingYN + '\'' +
                ", watchCompletionDate=" + watchCompletionDate +
                ", cumClass=" + cumClass +
                ", memberLecture=" + memberLecture +
                '}';
    }
}
