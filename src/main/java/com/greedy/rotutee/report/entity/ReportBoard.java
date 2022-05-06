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
 * fileName : ReportBoard
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
@Entity(name = "Report_board")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "REPORT_BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class ReportBoard {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORT_BOARD_SEQ_GENERATOR"
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

    @Column(name = "BOARD_CATEGORY_NO")
    private int boardCategoryNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private  ReportMember member;

    @Column(name = "BOARD_REPORT_COUNT")
    private int boardReportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private char bulletinBoardSecretYN;

//    @OneToMany(mappedBy = "reportBoard", cascade = CascadeType.PERSIST)
//    private List<ReportBoardAnswer> reportBoardAnswerList;


    @Override
    public String toString() {
        return "ReportBoard{" +
                "boardNo=" + boardNo +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", boardCreationDate=" + boardCreationDate +
                ", boardModifiedDate=" + boardModifiedDate +
                ", boardDeletionDate=" + boardDeletionDate +
                ", boardDeleteYN=" + boardDeleteYN +
                ", boardViewCount=" + boardViewCount +
                ", boardCategoryNo=" + boardCategoryNo +
                ", member=" + member +
                ", boardReportCount=" + boardReportCount +
                ", bulletinBoardSecretYN=" + bulletinBoardSecretYN +
                '}';
    }
}
