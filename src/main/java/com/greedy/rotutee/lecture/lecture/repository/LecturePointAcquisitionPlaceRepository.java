package com.greedy.rotutee.lecture.lecture.repository;

import com.greedy.rotutee.lecture.lecture.entity.PointAcquisitionPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName      : com.greedy.rotutee.lecture.lecture.repository
 * fileName         : LecturePointAcquisitionPlaceRepository
 * author           : SEOK
 * date             : 2022-05-09
 * description      :
 * ==========================================================
 * DATE            AUTHOR              NOTE
 * ----------------------------------------------------------
 * 2022-05-09      SEOK         최초 생성
 */
@Repository
public interface LecturePointAcquisitionPlaceRepository extends JpaRepository<PointAcquisitionPlace, Integer> {
    PointAcquisitionPlace findByPlaceName(String place);
}
