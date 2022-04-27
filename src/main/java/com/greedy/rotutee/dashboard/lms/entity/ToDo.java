package com.greedy.rotutee.dashboard.lms.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**
 * packageName : com.greedy.rotutee.dashboard.lms.entity
 * fileName : ToDo
 * author : SeoYoung
 * date : 2022-04-25
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-25 SeoYoung 최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Lms_ToDo")
@Table(name = "TBL_TODO")
@SequenceGenerator(
        name = "LMS_TODO_SEQ_GENERATOR",
        sequenceName = "TODO_NO",
        initialValue = 1,
        allocationSize = 1
)
public class ToDo {

    @Id
    @Column(name = "TODO_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "LMS_TODO_SEQ_GENERATOR"
    )
    private int todoNo;

    @Column(name = "TODO_CONTENT")
    private String content;

    @Column(name = "TODO_REGISTED_DATE")
    private Date registedDate;

    @Column(name = "ACHIEVEMENT_YN")
    private String achievementStatus;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @Column(name = "MEMBER_LECTURE_NO")
    private int memberLectureNo;

    @Override
    public String toString() {
        return "ToDo{" +
                "todoNo=" + todoNo +
                ", content='" + content + '\'' +
                ", registedDate=" + registedDate +
                ", achievementStatus='" + achievementStatus + '\'' +
                ", lectureNo=" + lectureNo +
                ", memberLectureNo=" + memberLectureNo +
                '}';
    }
}
