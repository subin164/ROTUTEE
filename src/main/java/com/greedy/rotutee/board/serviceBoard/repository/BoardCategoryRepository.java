package com.greedy.rotutee.board.serviceBoard.repository;

import com.greedy.rotutee.board.serviceBoard.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ServiceBoard_BoardCategoryRepository")
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
