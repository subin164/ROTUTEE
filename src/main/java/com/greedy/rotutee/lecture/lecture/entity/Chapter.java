package com.greedy.rotutee.lecture.lecture.entity;

import com.greedy.rotutee.lecture.lecture.dto.ClassDTO;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Lecture_Chapter")
@Table(name = "TBL_CHAPTER")
public class Chapter {

    @Id
    @Column(name = "CHAPTER_NO")
    private int chapterNo;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @JoinColumn(name = "LECTURE_NO")
    @ManyToOne
    private Lecture lecture;

    @OneToMany(mappedBy = "chapter")
    private List<Class> classList;

}
