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
    private int studyByTagNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="STUDY_NO")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAG_NO")
    private StudyTag tag;

    public StudyByTag() {
    }

    public StudyByTag(int studyByTagNo, Study study, StudyTag tag) {
        this.studyByTagNo = studyByTagNo;
        this.study = study;
        this.tag = tag;
    }

    public int getStudyByTagNo() {
        return studyByTagNo;
    }

    public void setStudyByTagNo(int studyByTagNo) {
        this.studyByTagNo = studyByTagNo;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public StudyTag getTag() {
        return tag;
    }

    public void setTag(StudyTag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "StudyByTag{" +
                "studyByTagNo=" + studyByTagNo +
                ", study=" + study +
                ", tag=" + tag +
                '}';
    }
}
