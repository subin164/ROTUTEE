package com.greedy.rotutee.member.member.repository;

import com.greedy.rotutee.member.member.entity.PointAcquisitionPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : com.greedy.rotutee.point.repository
 * fileName : PointAcquisitionPlaceRepository
 * author : 7sang
 * date : 2022-05-02
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-05-02 7sang 최초 생성
 */

@Repository(value = "Member_PointAcquisitionPlaceRepository")
public interface PointAcquisitionPlaceRepository extends JpaRepository<PointAcquisitionPlace, Integer> {
}
