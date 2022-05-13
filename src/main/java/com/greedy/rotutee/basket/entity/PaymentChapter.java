package com.greedy.rotutee.basket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.payment.entity
 * fileName : PaymentChapter
 * author : soobeen
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-13          soobeen     최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Payment_Chapter")
@Table(name = "TBL_CHAPTER")
@SequenceGenerator(
        name = "PAYMENT_CHAPTER_SEQ_GENERATOR",
        sequenceName = "CHAPTER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class PaymentChapter {

    @Id
    @Column(name = "CHAPTER_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PAYMENT_CHAPTER_SEQ_GENERATOR"
    )
    private int chapterNo;

    @Column(name = "LECTURE_NO")
    private int lectureNo;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @Override
    public String toString() {
        return "LMSChapter{" +
                "chapterNo=" + chapterNo +
                ", lectureNo=" + lectureNo +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }
}
