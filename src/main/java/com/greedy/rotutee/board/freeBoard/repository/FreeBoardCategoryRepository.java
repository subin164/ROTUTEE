package com.greedy.rotutee.board.freeBoard.repository;

import com.greedy.rotutee.board.freeBoard.entity.FreeBoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.greedy.rotutee.board.repository
 * fileName : CategoryRepository
 * author : soobeen
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-04-20        soobeen     최초 생성
 */

public interface FreeBoardCategoryRepository extends JpaRepository<FreeBoardCategory, Integer> {

}
