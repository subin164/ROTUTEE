package com.greedy.rotutee.study.entity;

import javax.persistence.*;
import java.util.List;

/**
 * The type StudyTag.
 */
@Entity
@Table(name = "TBL_STUDY_GROUP_BOARD_TAG")
@SequenceGenerator(
        name="STUDY_TAG_GENERATOR",
        sequenceName = "STUDY_GROUP_BOARD_TAG_NO",
        initialValue = 1,
        allocationSize = 1
)
public class StudyTag {

    /**
     * The Tag no.
     */
    @Id
    @Column(name = "TAG_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STUDY_TAG_GENERATOR"
    )

    private int tagNo;

    /**
     * The Tag name.
     */
    @Column(name="TAG_NAME")
    public String tagName;

    /**
     * Instantiates a new Study tag.
     */
    public StudyTag() {
    }

    /**
     * Instantiates a new Study tag.
     *
     * @param tagNo   the tag no
     * @param tagName the tag name
     */
    public StudyTag(int tagNo, String tagName) {
        this.tagNo = tagNo;
        this.tagName = tagName;
    }

    /**
     * Gets tag no.
     * author : SeoYoung Kim
     * description :
     *
     * @return the tag no
     */
    public int getTagNo() {
        return tagNo;
    }

    /**
     * Sets tag no.
     *
     * @param tagNo the tag no
     */
    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    /**
     * Gets tag name.
     * author : SeoYoung Kim
     * description :
     *
     * @return the tag name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets tag name.
     *
     * @param tagName the tag name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        return "StudyTag{" +
                "tagNo=" + tagNo +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
