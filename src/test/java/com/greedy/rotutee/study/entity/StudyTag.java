package com.greedy.rotutee.study.entity;

import javax.persistence.*;

@Entity(name="studyTag")
@Table(name = "TBL_STUDY_GROUP_BOARD_TAG")
@SequenceGenerator(
        name="STUDY_TAG_GENERATOR",
        sequenceName = "STUDY_GROUP_BOARD_TAG_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyTag {

    @Id
    @Column(name = "TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_TAG_GENERATOR"
    )

    private int studyTagNo;

    @Column(name="STUDY_TAG_NAME")
    public String tagName;

    public StudyTag() {
    }

    public StudyTag(int studyTagNo, String tagName) {
        this.studyTagNo = studyTagNo;
        this.tagName = tagName;
    }

    public int getStudyTagNo() {
        return studyTagNo;
    }

    public void setStudyTagNo(int studyTagNo) {
        this.studyTagNo = studyTagNo;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "StudyTag{" +
                "studyTagNo=" + studyTagNo +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
