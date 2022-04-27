package com.greedy.rotutee.dashboard.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSBoard
 * author : SeoYoung
 * date : 2022-04-26
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-26 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Lms_Board")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "LMS_BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSBoard {

    @Id
    @Column(name = "BOARD_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_BOARD_SEQ_GENERATOR"
    )
    private int boardNo;

    @Column(name = "BOARD_TITLE")
    private String title;

    @Column(name = "BOARD_CONTENT")
    private String content;

    @Column(name = "BOARD_CREATION_DATE")
    private Date createdDate;

    @Column(name = "BOARD_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "BOARD_DELETION_DATE")
    private Date deletedDate;

    @Column(name = "BOARD_DELETE_YN")
    private String deleteStatus;

    @Column(name = "BOARD_VIEW_COUNT")
    private int count;

    @Column(name = "BOARD_CATEGORY_NO")
    private int categoryNo;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name = "BOARD_REPORT_COUNT")
    private int reportCount;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private String secretStatus;

    @Override
    public String toString() {
        return "LMSBoard{" +
                "boardNo=" + boardNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", deletedDate=" + deletedDate +
                ", deleteStatus='" + deleteStatus + '\'' +
                ", count=" + count +
                ", categoryNo=" + categoryNo +
                ", memberNo=" + memberNo +
                ", reportCount=" + reportCount +
                ", secretStatus='" + secretStatus + '\'' +
                '}';
    }
}
