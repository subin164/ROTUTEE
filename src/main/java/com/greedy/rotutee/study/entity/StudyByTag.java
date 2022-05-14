package com.greedy.rotutee.study.entity;

import javax.persistence.*;

/**
 * The type StudyByTag.
 */
@Entity
@Table(name = "TBL_STUDY_GROUP_BOARD_STUDY_BY_TAG")
@SequenceGenerator(
        name="STUDY_BY_TAG_GENERATOR",
        sequenceName = "STUDY_GROUP_BOARD_STUDY_BY_TAG_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyByTag {

    /**
     * The Study by tag no.
     */
    @Id
    @Column(name = "STUDY_TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_BY_TAG_GENERATOR"
    )
    private int studyByTagNo;

    /**
     * The Study.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="STUDY_NO")
    private Study study;

    /**
     * The Tag.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TAG_NO")
    private StudyTag tag;

    /**
     * Instantiates a new Study by tag.
     */
    public StudyByTag() {
    }

    /**
     * Instantiates a new Study by tag.
     *
     * @param studyByTagNo the study by tag no
     * @param study        the study
     * @param tag          the tag
     */
    public StudyByTag(int studyByTagNo, Study study, StudyTag tag) {
        this.studyByTagNo = studyByTagNo;
        this.study = study;
        this.tag = tag;
    }

    /**
     * Gets study by tag no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the study by tag no
     */
    public int getStudyByTagNo() {
        return studyByTagNo;
    }

    /**
     * Sets study by tag no.
     *
     * @param studyByTagNo the study by tag no
     */
    public void setStudyByTagNo(int studyByTagNo) {
        this.studyByTagNo = studyByTagNo;
    }

    /**
     * Gets study.
     * author : SeoYoung Kim
     * description :
     *
     * @return the study
     */
    public Study getStudy() {
        return study;
    }

    /**
     * Sets study.
     *
     * @param study the study
     */
    public void setStudy(Study study) {
        this.study = study;
    }

    /**
     * Gets tag.
     * author : SeoYoung Kim
     * description :
     *
     * @return the tag
     */
    public StudyTag getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(StudyTag tag) {
        this.tag = tag;
    }

    /**
     * methodName : toString
     * author : SeoYoung Kim
     * description :
     *
     * @return string
     */
    @Override
    public String toString() {
        return "StudyByTag{" +
                "studyByTagNo=" + studyByTagNo +
                ", study=" + study +
                ", tag=" + tag +
                '}';
    }
}
