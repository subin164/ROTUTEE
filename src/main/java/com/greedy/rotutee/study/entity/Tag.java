package com.greedy.rotutee.study.entity;

import javax.persistence.*;

@Entity(name = "Tag")
@Table(name = "TBL_STUDY_GROUP_BOARD_TAG")
@SequenceGenerator(
        name = "STUDY_SEQ_GENERATOR",
        sequenceName = "STUDY_GROUP_BOARD_TAG_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Tag {

    @Id
    @Column(name = "STUDY_GROUP_BOARD_TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_SEQ_GENERATOR"
    )
    public int TagNo;

    @ManyToOne
    @JoinColumn(name = "STUDY_NO", insertable = false, updatable = false)
    public Study study;

    @Column(name = "STUDY_TAG_NAME")
    public String tagName;

    public Tag() {
    }

    public Tag(int tagNo, Study study, String tagName) {
        TagNo = tagNo;
        this.study = study;
        this.tagName = tagName;
    }

    public int getTagNo() {
        return TagNo;
    }

    public void setTagNo(int tagNo) {
        TagNo = tagNo;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "TagNo=" + TagNo +
                ", study=" + study +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}