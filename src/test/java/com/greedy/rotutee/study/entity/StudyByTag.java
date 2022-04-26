package com.greedy.rotutee.study.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_GROUP_BOARD_STUDY_BY_TAG")
@SequenceGenerator(
        name="STUDY_BY_TAG_GENERATOR",
        sequenceName = "STUDY_GROUP_BOARD_STUDY_BY_TAG_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyByTag {

    @Id
    @Column(name = "STUDY_TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_BY_TAG_GENERATOR"
    )
    private int studyTagNo;

    @ManyToOne
    @JoinColumn(name ="STUDY_NO")
    private Study studyNo;

    @ManyToOne
    @JoinColumn(name="TAG_NO")
    private StudyTag tagNo;

    public StudyByTag() {
    }

    public StudyByTag(int studyTagNo, Study studyNo, StudyTag tagNo) {
        this.studyTagNo = studyTagNo;
        this.studyNo = studyNo;
        this.tagNo = tagNo;
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

    public StudyTag getTagNo() {
        return tagNo;
    }

    public void setTagNo(StudyTag tagNo) {
        this.tagNo = tagNo;
    }

    @Override
    public String toString() {
        return "StudyByTag{" +
                "studyTagNo=" + studyTagNo +
                ", studyNo=" + studyNo +
                ", tagNo=" + tagNo +
                '}';
    }
}
