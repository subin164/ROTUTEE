package com.greedy.rotutee.study.entity;

import javax.persistence.*;
import java.util.List;

@Entity
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

    private int tagNo;

    @Column(name="TAG_NAME")
    public String tagName;

    public StudyTag() {
    }

    public StudyTag(int tagNo, String tagName) {
        this.tagNo = tagNo;
        this.tagName = tagName;
    }

    public int getTagNo() {
        return tagNo;
    }

    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
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
                "tagNo=" + tagNo +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
