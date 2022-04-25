package com.greedy.rotutee.board.freeBoard.entity;

import javax.persistence.*;

@Entity(name= "FreeBoardCategory")
@Table(name = "TBL_BOARD_CATEGORY")
@SequenceGenerator(
        name = "CATEGORY_SEQ_GENERATOR",
        sequenceName = "BOARD_CATEGORY_NO",
        initialValue = 1,
        allocationSize = 1
)
public class FreeBoardCategory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GENERATOR"
    )
    @Column(name = "BOARD_CATEGORY_NO", nullable = false)
    private int boardCategoryNo;

    @Column(name = "BOARD_CATEGORY_NAME")
    private String boardCategoryName;

    @Column(name = "UPPER_CATEGORY_NO")
    private int upperCategoryNo;

    public FreeBoardCategory() {
    }

    public FreeBoardCategory(int boardCategoryNo, String boardCategoryName, int upperCategoryNo) {
        this.boardCategoryNo = boardCategoryNo;
        this.boardCategoryName = boardCategoryName;
        this.upperCategoryNo = upperCategoryNo;
    }

    public int getBoardCategoryNo() {
        return boardCategoryNo;
    }

    public void setBoardCategoryNo(int boardCategoryNo) {
        this.boardCategoryNo = boardCategoryNo;
    }

    public String getBoardCategoryName() {
        return boardCategoryName;
    }

    public void setBoardCategoryName(String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }

    public int getUpperCategoryNo() {
        return upperCategoryNo;
    }

    public void setUpperCategoryNo(int upperCategoryNo) {
        this.upperCategoryNo = upperCategoryNo;
    }

    @Override
    public String toString() {
        return "FreeBoardCategory{" +
                "boardCategoryNo=" + boardCategoryNo +
                ", boardCategoryName='" + boardCategoryName + '\'' +
                ", upperCategoryNo=" + upperCategoryNo +
                '}';
    }
}