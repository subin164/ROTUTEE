package com.greedy.rotutee.dashboard.lms.entity;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : LMSNotice
 * author : SeoYoung
 * date : 2022-05-09
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-09 SeoYoung 최초 생성
 */
import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "Lms_Notice")
@Table(name = "TBL_BOARD")
@SequenceGenerator(
        name = "LMS_NOTICE_SEQ_GENERATOR",
        sequenceName = "BOARD_NO",
        initialValue = 1,
        allocationSize = 1
)
public class LMSNotice {

    @Id
    @Column(name = "BOARD_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_NOTICE_SEQ_GENERATOR"
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

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private DashboardMember member;

    @Column(name = "BOARD_REPORT_COUNT")
    private int reportCount;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @Column(name = "BULLETIN_BOARD_SECRET_YN")
    private String secretStatus;
}
