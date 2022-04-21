package com.greedy.rotutee.study.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "study")
@Table(name = "TBL_STUDY_GROUP_BOARD")
@SequenceGenerator(
        name="STUDY_NO_GENERATOR",
        sequenceName = "STUDY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Study {

    @Id
    @Column(name = "STUDY_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_NO_GENERATOR"
    )
    private int studyNo;

    @Column(name = "STUDY_TITLE")
    private String title;

    @Column(name = "STUDY_CONTENT")
    private String content;

    @Column(name = "STUDY_WRITE_DATE")
    private java.sql.Date startDate;

    @Column(name = "STUDY_RECRUITMENT_END_DATE")
    private java.sql.Date endDate;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Column(name="WRITER_MEMBER_NO")
    private String writer;

    @Column(name = "STUDY_LIMITED_MEMBER_NUM")
    private int limited;

    @Column(name="STUDY_BOARD_VIEW_COUNT")
    private int count;

    @Column(name = "STUDY_INVITE_LINK")
    private String linked;

    @Column(name = "STUDY_RECRUITMENT_STATUS")
    private String status;

    @Column(name = "STUDY_TAG_NO")
    private int tagNo;

    @Override
    public String toString() {
        return "Study{" +
                "studyNo=" + studyNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", memberNo=" + memberNo +
                ", writer='" + writer + '\'' +
                ", limited=" + limited +
                ", count=" + count +
                ", linked='" + linked + '\'' +
                ", status='" + status + '\'' +
                ", tagNo=" + tagNo +
                '}';
    }
}