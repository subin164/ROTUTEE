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
    @Column(name = "STUDY_GROUP_BOARD_TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_TAG_GENERATOR"
    )
    private int studyTagNo;

    @ManyToOne
    @JoinColumn(name="STUDY_NO")
    public Study studyNo;

    @Column(name="STUDY_TAG_NAME")
    public String tagName;

    public StudyTag() {
    }

    public StudyTag(int studyTagNo, Study studyNo, String tagName) {
        this.studyTagNo = studyTagNo;
        this.studyNo = studyNo;
        this.tagName = tagName;
    }

    public int getStudyTagNo() {
        return studyTagNo;
    }

    public void setStudyTagNo(int studyTagNo) {
        this.studyTagNo = studyTagNo;
    }

    public Study getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Study studyNo) {
        this.studyNo = studyNo;
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
                "studyNo=" + studyNo +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
