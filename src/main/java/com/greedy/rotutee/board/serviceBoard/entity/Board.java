package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.entity
 * fileName : SerivceBoard
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Entity(name = "ServiceBoard_SerivceBoard")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Board {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR"
    )
    @Column(name = "BOARD_NO")
    private int no;

    @Column(name = "BOARD_TITLE")
    private String title;

    @Column(name = "BOARD_CONTENT")
    private String content;

    @Column(name = "BOARD_CREATION_DATE")
    private Timestamp creationDate;

    @Column(name = "BOARD_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "BOARD_DELETION_DATE")
    private Date deletionDate;

    @Column(name = "BOARD_DELETE_YN")
    private char deleteYN;

    @Column(name = "BOARD_VIEW_COUNT")
    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_CATEGORY_NO")
    private BoardCategory boardCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Column(name = "BOARD_REPORT_COUNT")
    private int reportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private char bulletinBoardSecretYN;

    @OneToMany(mappedBy = "board",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<BoardAnswer> boardAnswerList;

    public Board() {}

    public Board(int no, String title, String content, Timestamp creationDate, Date modifiedDate, Date deletionDate, char deleteYN, int viewCount, BoardCategory boardCategory, Member member, int reportCount, char bulletinBoardSecretYN, List<BoardAnswer> boardAnswerList) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.deletionDate = deletionDate;
        this.deleteYN = deleteYN;
        this.viewCount = viewCount;
        this.boardCategory = boardCategory;
        this.member = member;
        this.reportCount = reportCount;
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
        this.boardAnswerList = boardAnswerList;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public char getDeleteYN() {
        return deleteYN;
    }

    public void setDeleteYN(char deleteYN) {
        this.deleteYN = deleteYN;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public BoardCategory getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public char getBulletinBoardSecretYN() {
        return bulletinBoardSecretYN;
    }

    public void setBulletinBoardSecretYN(char bulletinBoardSecretYN) {
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
    }

    public List<BoardAnswer> getBoardAnswerList() {
        return boardAnswerList;
    }

    public void setBoardAnswerList(List<BoardAnswer> boardAnswerList) {
        this.boardAnswerList = boardAnswerList;
    }

    @Override
    public String toString() {
        return "Board{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", modifiedDate=" + modifiedDate +
                ", deletionDate=" + deletionDate +
                ", deleteYN=" + deleteYN +
                ", viewCount=" + viewCount +
                ", boardCategory=" + boardCategory +
                ", member=" + member +
                ", reportCount=" + reportCount +
                ", bulletinBoardSecretYN=" + bulletinBoardSecretYN +
//                ", boardAnswerList=" + boardAnswerList +
                '}';
    }
}
