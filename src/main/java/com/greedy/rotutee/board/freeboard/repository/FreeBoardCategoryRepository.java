package com.greedy.rotutee.board.freeboard.repository;

import com.greedy.rotutee.board.freeboard.entity.FreeBoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

@Repository
public interface FreeBoardCategoryRepository extends JpaRepository<FreeBoardCategory, Integer> {

}
