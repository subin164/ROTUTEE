package com.greedy.rotutee.lecture.mylecture.service;

import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.dashboard.lms.dto.LMSLatelyViewDTO;
import com.greedy.rotutee.dashboard.lms.entity.LMSLatelyViewClass;
import com.greedy.rotutee.dashboard.lms.repository.LMSLatelyViewRepository;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardLectureDTO;
import com.greedy.rotutee.dashboard.mypage.dto.tutee.DashboardMemberLectureDTO;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardCompletedLecture;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardLecture;
import com.greedy.rotutee.dashboard.mypage.entity.DashboardMember;
import com.greedy.rotutee.dashboard.mypage.entity.MyPageMemberLecture;
import com.greedy.rotutee.dashboard.mypage.repository.DashboardLectureRepository;
import com.greedy.rotutee.dashboard.mypage.repository.MypageMemberLectureRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * packageName : com.greedy.rotutee.lecture.mylecture.service
 * fileName : MyLecutureServiceTests
 * author : SeoYoung
 * date : 2022-04-24
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-24 SeoYoung 최초 생성
 */
@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class MyLecutureServiceTests {

    @Autowired
    private MypageMemberLectureRepository memberLectureRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DashboardLectureRepository lectureRepository;
    @Autowired
    private LMSLatelyViewRepository lmsLatelyViewRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void init() {
        assertNotNull(memberLectureRepository);
        assertNotNull(modelMapper);
        assertNotNull(entityManager);
    }
    @Test
    public void 수강테이블_조회_확인() {

        //given
        int memberNo = 27;

        //when

        String query1 = "SELECT\n" +
                "       A.LECTURE_NO\n" +
                "     , A.LECTURE_NAME\n" +
                "     , A.LECTURE_PRICE\n" +
                "     , A.LECTURE_LEVEL\n" +
                "     , A.LECTURE_SUMMARY\n" +
                "     , A.LECTURE_DETAILS\n" +
                "     , A.REVISION_HISTORY\n" +
                "     , A.LECTURE_APPROVAL_STATUS\n" +
                "     , A.LECTURE_OPENING_DATE\n" +
                "     , A.MEMBER_NO\n" +
                "     , A.APPLICATION_DATE\n" +
                "     , A.APPLICATION_DIVISION\n" +
                "     , A.LECTURE_CATEGORY_NO\n" +
                "  FROM TBL_LECTURE A\n" +
                " WHERE A.LECTURE_NO IN (SELECT\n" +
                "                               B.LECTURE_NO\n" +
                "                          FROM TBL_MEMBER_LECTURE B\n" +
                "                          JOIN TBL_LECTURE C ON (C.LECTURE_NO = B.LECTURE_NO)\n" +
                "                         WHERE B.MEMBER_NO = ?" +
                "                       )";
        Query nativeQuery = entityManager.createNativeQuery(query1, "mypageMemberLectureMapping");

        List<DashboardLecture> learningEntities = nativeQuery.setParameter(1, memberNo).getResultList();
        List<DashboardLectureDTO> learning = learningEntities.stream().map(dashboardLecture -> modelMapper.map(dashboardLecture, DashboardLectureDTO.class)).collect(Collectors.toList());

        /* 듣고있는 강의 수강번호 */
        String jpql1 = "SELECT memberLecture.memberLectureNo FROM Mypage_MemberLecture memberLecture " +
                "WHERE memberLecture.member.memberNo = :memberNo ";
        Query query2 = entityManager.createQuery(jpql1).setParameter("memberNo", memberNo);
        List<Integer> memberLectureNums = query2.getResultList();

        memberLectureNums.forEach(System.out::println);

        /* 회원별 수강번호에 따른 클래스리스트가 담겨있는 리스트*/
        List<List> completedLectureCategoryEntityList = new ArrayList<>();
        for(int i = 0; i < memberLectureNums.size(); i++){

            int memberLectureNo = memberLectureNums.get(i);
            /* 수강번호로 누적시간 테이블에서 회원이 듣고있는 강의의 모든 클래스 상태 읽어옴 */
            String jpql2 = "SELECT complete FROM Dashboard_Completedlecture  as complete " +
                    "WHERE complete.memberLecture.memberLectureNo = :memberLectureNo ";
            List<DashboardCompletedLecture> completedLectureEntityList = entityManager.createQuery(jpql2, DashboardCompletedLecture.class)
                    .setParameter("memberLectureNo", memberLectureNo)
                    .getResultList();
            completedLectureCategoryEntityList.add(completedLectureEntityList);
        }
        for (List list : completedLectureCategoryEntityList) {
            System.out.println("list = " + list);
        }

        /* 한 멤버가 듣고있는 강의의 수만큼 반복문 */
        List<DashboardLectureDTO> completedLectureInfoList = new ArrayList<>();
        for(int i = 0; i <completedLectureCategoryEntityList.size(); i++){
            /* 한 강의의 클래스 리스트*/
            List<DashboardCompletedLecture> completedLectureList = completedLectureCategoryEntityList.get(i);
            /* 한 강의당 총 클래스 수 */
            double totalClassCountOfLecture = completedLectureList.size();
            System.out.println("totalClassCountOfLecture = " + totalClassCountOfLecture);
            /* 한 강의당 완료된 클래스 개수(completedStatus : Y의 총 개수) */
            double CompletedClassCount = 0;
            for(int j = 0; j < completedLectureList.size(); j++){
                if(completedLectureList.get(j).getWatchedStatus().equals("Y ")){
                    CompletedClassCount += 1;
                }
            }
            System.out.println("CompletedClassCount = " + CompletedClassCount);
            int progress = (int)(Math.round(CompletedClassCount/totalClassCountOfLecture * 100));
            System.out.println("progress = " + progress);
            learning.get(i).setProgress(progress);
        }
        //then
        assertNotNull(learning);
        for (DashboardLectureDTO dashboardLectureDTO : learning) {
            System.out.println("dashboardLectureDTO = " + dashboardLectureDTO);
        }

            

    }

    @Test
    public void 진행률_계산_조회() {

        //given
        int memberNo = 27;


    }


}
