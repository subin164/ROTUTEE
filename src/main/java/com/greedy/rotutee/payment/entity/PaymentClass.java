package com.greedy.rotutee.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.payment.entity
 * fileName : PaymentClass
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
@Entity(name = "PaClass")
@Table(name = "TBL_CLASS")
@SequenceGenerator(
        name = "PAYMENT_CLASS_SEQ_GENERATOR",
        sequenceName = "CLASS_NO",
        initialValue = 1,
        allocationSize = 1
)
public class PaymentClass {

    @Id
    @Column(name = "CLASS_NO")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PAYMENT_CLASS_SEQ_GENERATOR"
    )
    private int classNo;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "VIDEO_PATH")
    private String videoPath;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_NO")
    private PaymentChapter chapter;

    @Override
    public String toString() {
        return "LMSClass{" +
                "classNo=" + classNo +
                ", className='" + className + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", chapter=" + chapter +
                '}';
    }
}
