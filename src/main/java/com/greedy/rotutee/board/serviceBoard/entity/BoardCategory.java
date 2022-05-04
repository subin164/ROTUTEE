package com.greedy.rotutee.board.serviceBoard.entity;

import javax.persistence.*;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.board.serviceBoard.entity
 * fileName : Category
 * author : 7sang
 * date : 2022-05-04
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-04 7sang 최초 생성
 */

@Entity(name = "ServiceBoard_BoardCategory")
@Table(name = "TBL_BOARD_CATEGORY")
public class BoardCategory {

    @Id
    @Column(name = "BOARD_CATEGORY_NO")
    private int no;

    @Column(name = "BOARD_CATEGORY_NAME")
    private String name;

    @JoinColumn(name = "UPPER_CATEGORY_NO")
    @ManyToOne(fetch = FetchType.LAZY)
    private BoardCategory boardCategory;

    @OneToMany(mappedBy = "boardCategory", fetch = FetchType.LAZY)
    private List<BoardCategory> upperCategoryList;

    public BoardCategory() {}

    public BoardCategory(int no, String name, BoardCategory boardCategory, List<BoardCategory> upperCategoryList) {
        this.no = no;
        this.name = name;
        this.boardCategory = boardCategory;
        this.upperCategoryList = upperCategoryList;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardCategory getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public List<BoardCategory> getUpperCategoryList() {
        return upperCategoryList;
    }

    public void setUpperCategoryList(List<BoardCategory> upperCategoryList) {
        this.upperCategoryList = upperCategoryList;
    }

    @Override
    public String toString() {
        return "BoardCategory{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", boardCategory=" + boardCategory +
                ", upperCategoryList=" + upperCategoryList +
                '}';
    }
}
