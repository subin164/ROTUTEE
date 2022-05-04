package com.greedy.rotutee.board.freeboard.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "FreeBoard")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class FreeBoard {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR"
    )
    @Column(name = "BOARD_NO")
    private int boardNo;

    @Column(name = "BOARD_TITLE")
    private String boardTitle;

    @Column(name = "BOARD_CONTENT")
    private String boardContent;

    @Column(name = "BOARD_CREATION_DATE")
    private Date boardCreationDate;

    @Column(name = "BOARD_MODIFIED_DATE")
    private Date boardModifiedDate;

    @Column(name = "BOARD_DELETION_DATE")
    private Date boardDeletionDate;

    @Column(name = "BOARD_DELETE_YN")
    private char boardDeleteYN;

    @Column(name = "BOARD_VIEW_COUNT")
    private int boardViewCount;

    @ManyToOne
    @JoinColumn(name = "BOARD_CATEGORY_NO")
    private FreeBoardCategory freeBoardCategory;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private FreeBoardMember freeBoardMember;

    @Column(name = "BOARD_REPORT_COUNT")
    private int boardReportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private char bulletinBoardSecretYN;


    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.PERSIST)
    private List<FreeBoardAnswer> freeBoardAnswerList;

    public FreeBoard(){}

    public FreeBoard(int boardNo, String boardTitle, String boardContent, Date boardCreationDate, Date boardModifiedDate,
                     Date boardDeletionDate, char boardDeleteYN, int boardViewCount,
                     FreeBoardCategory freeBoardCategory, FreeBoardMember freeBoardMember, int boardReportCount,
                     char bulletinBoardSecretYN, List<FreeBoardAnswer> freeBoardAnswerList) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCreationDate = boardCreationDate;
        this.boardModifiedDate = boardModifiedDate;
        this.boardDeletionDate = boardDeletionDate;
        this.boardDeleteYN = boardDeleteYN;
        this.boardViewCount = boardViewCount;
        this.freeBoardCategory = freeBoardCategory;
        this.freeBoardMember = freeBoardMember;
        this.boardReportCount = boardReportCount;
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
        this.freeBoardAnswerList = freeBoardAnswerList;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public Date getBoardCreationDate() {
        return boardCreationDate;
    }

    public void setBoardCreationDate(Date boardCreationDate) {
        this.boardCreationDate = boardCreationDate;
    }

    public Date getBoardModifiedDate() {
        return boardModifiedDate;
    }

    public void setBoardModifiedDate(Date boardModifiedDate) {
        this.boardModifiedDate = boardModifiedDate;
    }

    public Date getBoardDeletionDate() {
        return boardDeletionDate;
    }

    public void setBoardDeletionDate(Date boardDeletionDate) {
        this.boardDeletionDate = boardDeletionDate;
    }

    public char getBoardDeleteYN() {
        return boardDeleteYN;
    }

    public void setBoardDeleteYN(char boardDeleteYN) {
        this.boardDeleteYN = boardDeleteYN;
    }

    public int getBoardViewCount() {
        return boardViewCount;
    }

    public void setBoardViewCount(int boardViewCount) {
        this.boardViewCount = boardViewCount;
    }

    public FreeBoardCategory getFreeBoardCategory() {
        return freeBoardCategory;
    }

    public void setFreeBoardCategory(FreeBoardCategory freeBoardCategory) {
        this.freeBoardCategory = freeBoardCategory;
    }

    public FreeBoardMember getFreeBoardMember() {
        return freeBoardMember;
    }

    public void setFreeBoardMember(FreeBoardMember freeBoardMember) {
        this.freeBoardMember = freeBoardMember;
    }

    public int getBoardReportCount() {
        return boardReportCount;
    }

    public void setBoardReportCount(int boardReportCount) {
        this.boardReportCount = boardReportCount;
    }

    public char getBulletinBoardSecretYN() {
        return bulletinBoardSecretYN;
    }

    public void setBulletinBoardSecretYN(char bulletinBoardSecretYN) {
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
    }

    public List<FreeBoardAnswer> getFreeBoardAnswerList() {
        return freeBoardAnswerList;
    }

    public void setFreeBoardAnswerList(List<FreeBoardAnswer> freeBoardAnswerList) {
        this.freeBoardAnswerList = freeBoardAnswerList;
    }

    @Override
    public String toString() {
        return "FreeBoard{" +
                "boardNo=" + boardNo +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", boardCreationDate=" + boardCreationDate +
                ", boardModifiedDate=" + boardModifiedDate +
                ", boardDeletionDate=" + boardDeletionDate +
                ", boardDeleteYN=" + boardDeleteYN +
                ", boardViewCount=" + boardViewCount +
                ", freeBoardCategory=" + freeBoardCategory +
                ", freeBoardMember=" + freeBoardMember +
                ", boardReportCount=" + boardReportCount +
                ", bulletinBoardSecretYN=" + bulletinBoardSecretYN +
                '}';
    }
}