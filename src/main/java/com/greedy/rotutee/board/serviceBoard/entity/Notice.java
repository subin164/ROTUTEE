package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.entity
 * fileName : Notice
 * author : 7sang
 * date : 2022-05-14
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-14 7sang 최초 생성
 */

@Entity(name = "Member_Notice")
@Table(name = "TBL_NOTICE")
@SequenceGenerator(
        name = "NOTICE_SEQ_GENERATOR",
        sequenceName = "NOTICE_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Notice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NOTICE_SEQ_GENERATOR"
    )
    private int no;

    @Column(name = "NOTICE_CONTENTS")
    private String content;

    @ManyToOne
    @JoinColumn(name = "NOTICE_CATEGORY_NO")
    private NoticeCategory noticeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(name = "NOTICE_TIME")
    private Date time;

    public Notice() {}

    public Notice(int no, String content, NoticeCategory noticeCategory, Member member, Date time) {
        this.no = no;
        this.content = content;
        this.noticeCategory = noticeCategory;
        this.member = member;
        this.time = time;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoticeCategory getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(NoticeCategory noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "no=" + no +
                ", content='" + content + '\'' +
                ", noticeCategory=" + noticeCategory +
                ", member=" + member +
                ", time=" + time +
                '}';
    }
}
