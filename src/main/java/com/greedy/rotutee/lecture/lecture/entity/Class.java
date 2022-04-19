package com.greedy.rotutee.lecture.lecture.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Lecture_Class")
@Table(name = "TBL_CLASS")
public class Class {

    @Id
    @Column(name = "CLASS_NO")
    private int classNo;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @JoinColumn(name = "CHAPTER_NO")
    @ManyToOne
    private Chapter chapter;

}
