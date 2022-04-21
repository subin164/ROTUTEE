package com.greedy.rotutee.board.entity;

import javax.persistence.*;
import java.sql.Date;

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
    @Column(name = "BOARD_NO", nullable = false)
    private int boardNo;

    @Column(name = "BOARD_TITLE", nullable = false)
    private String boardTitle;

    @Column(name = "BOARD_CONTENT", nullable = false)
    private String boardContent;

    @Column(name = "BOARD_CREATION_DATE", nullable = false)
    private Date boardCreationDate;

    @Column(name = "BOARD_MODIFIED_DATE")
    private Date boardModifiedDate;

    @Column(name = "BOARD_DELETION_DATE")
    private Date boardDeletionDate;

    @Column(name = "BOARD_DELETE_YN", nullable = false)
    private String boardDeleteYN;

    @Column(name = "BOARD_VIEW_COUNT", nullable = false)
    private int boardViewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_CATEGORY_NO", nullable = false)
    private FreeBoardCategory boardCategoryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private FreeBoardMember memberNo;

    @Column(name = "BOARD_REPORT_COUNT")
    private int boardReportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN", nullable = false)
    private String bulletinBoardSecretYN;

    public FreeBoard(){}

    public FreeBoard(int boardNo, String boardTitle, String boardContent, Date boardCreationDate, Date boardModifiedDate, Date boardDeletionDate, String boardDeleteYN, int boardViewCount, FreeBoardCategory boardCategoryNo, FreeBoardMember memberNo, int boardReportCount, String bulletinBoardSecretYN) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCreationDate = boardCreationDate;
        this.boardModifiedDate = boardModifiedDate;
        this.boardDeletionDate = boardDeletionDate;
        this.boardDeleteYN = boardDeleteYN;
        this.boardViewCount = boardViewCount;
        this.boardCategoryNo = boardCategoryNo;
        this.memberNo = memberNo;
        this.boardReportCount = boardReportCount;
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
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

    public String getBoardDeleteYN() {
        return boardDeleteYN;
    }

    public void setBoardDeleteYN(String boardDeleteYN) {
        this.boardDeleteYN = boardDeleteYN;
    }

    public int getBoardViewCount() {
        return boardViewCount;
    }

    public void setBoardViewCount(int boardViewCount) {
        this.boardViewCount = boardViewCount;
    }

    public FreeBoardCategory getBoardCategoryNo() {
        return boardCategoryNo;
    }

    public void setBoardCategoryNo(FreeBoardCategory boardCategoryNo) {
        this.boardCategoryNo = boardCategoryNo;
    }

    public FreeBoardMember getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(FreeBoardMember memberNo) {
        this.memberNo = memberNo;
    }

    public int getBoardReportCount() {
        return boardReportCount;
    }

    public void setBoardReportCount(int boardReportCount) {
        this.boardReportCount = boardReportCount;
    }

    public String getBulletinBoardSecretYN() {
        return bulletinBoardSecretYN;
    }

    public void setBulletinBoardSecretYN(String bulletinBoardSecretYN) {
        this.bulletinBoardSecretYN = bulletinBoardSecretYN;
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
                ", boardDeleteYN='" + boardDeleteYN + '\'' +
                ", boardViewCount=" + boardViewCount +
                ", boardCategoryNo=" + boardCategoryNo +
                ", memberNo=" + memberNo +
                ", boardReportCount=" + boardReportCount +
                ", bulletinBoardSecretYN='" + bulletinBoardSecretYN + '\'' +
                '}';
    }
}