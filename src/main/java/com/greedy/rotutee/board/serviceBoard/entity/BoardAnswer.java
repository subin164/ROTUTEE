package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.entity
 * fileName : BoardAnswer
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Entity(name = "ServiceBoard_BoardAnswer")
@Table(name = "TBL_ANSWER")
@SequenceGenerator(
        name = "ANSWER_SEQ_GENERATOR",
        sequenceName = "ANSWER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class BoardAnswer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ANSWER_SEQ_GENERATOR"
    )
    @Column(name = "ANSWER_NO")
    private int no;

    @Column(name = "ANSWER_CONTENT")
    private String content;

    @Column(name = "ANSWER_YN")
    private char answerYn;

    @Column(name = "ANSWER_REPORT_COUNT")
    private int reportCount;

    @Column(name = "ANSWER_CREATED_DATE")
    private Date createdDate;

    @Column( name = "ANSWER_MODIFY_DATE")
    private Date modifyDate;

    @Column(name = "ANSWER_DELETE_DATE")
    private Date deleteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="BOARD_NO")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    public BoardAnswer() {}

    public BoardAnswer(int no, String content, char answerYn, int reportCount, Date createdDate, Date modifyDate, Date deleteDate, Board board, Member member) {
        this.no = no;
        this.content = content;
        this.answerYn = answerYn;
        this.reportCount = reportCount;
        this.createdDate = createdDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
        this.board = board;
        this.member = member;
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

    public char getAnswerYn() {
        return answerYn;
    }

    public void setAnswerYn(char answerYn) {
        this.answerYn = answerYn;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "BoardAnswer{" +
                "no=" + no +
                ", content='" + content + '\'' +
                ", answerYn=" + answerYn +
                ", reportCount=" + reportCount +
                ", createdDate=" + createdDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                ", board=" + board +
                ", member=" + member +
                '}';
    }
}
