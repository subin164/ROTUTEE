package com.greedy.rotutee.board.FQABoard.repsositrory;

import com.greedy.rotutee.board.FQABoard.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "FQABoard_BoardCategoryRepository")
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
