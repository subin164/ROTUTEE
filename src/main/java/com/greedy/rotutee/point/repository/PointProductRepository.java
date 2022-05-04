package com.greedy.rotutee.point.repository;

import com.greedy.rotutee.point.entity.PointProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : com.greedy.rotutee.point.repository
 * fileName : PointProductRepository
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Repository(value = "Point_PointProductRepository")
public interface PointProductRepository extends JpaRepository<PointProduct, Integer> {
    List<PointProduct> findByProductStatus(String status);
}
