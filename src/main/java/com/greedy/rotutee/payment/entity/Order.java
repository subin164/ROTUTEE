package com.greedy.rotutee.payment.entity;

import javax.persistence.*;

/**
 * packageName : com.greedy.rotutee.payment.entity
 * fileName : Order
 * author : soobeen
 * date : 2022-05-07
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-07          soobeen     최초 생성
 */

@Entity
@Table(name = "TBL_ORDER")
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_NO",
        initialValue = 1,
        allocationSize = 1
)
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDER_SEQ_GENERATOR"
    )
    @Column(name = "ORDER_NO")
    private int no;
}
