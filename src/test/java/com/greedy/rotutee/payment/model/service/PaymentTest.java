package com.greedy.rotutee.payment.model.service;

import com.greedy.rotutee.basket.dto.ClassDTO;
import com.greedy.rotutee.basket.dto.CumulateTimeDTO;
import com.greedy.rotutee.basket.dto.MemberLectureDTO;
import com.greedy.rotutee.basket.entity.Chapter;
import com.greedy.rotutee.basket.entity.Class;
import com.greedy.rotutee.basket.entity.CumulateTime;
import com.greedy.rotutee.basket.repository.*;
import com.greedy.rotutee.config.BeanConfiguration;
import com.greedy.rotutee.config.JPAConfiguration;
import com.greedy.rotutee.config.RotuteeApplication;
import com.greedy.rotutee.payment.entity.PaymentChapter;
import com.greedy.rotutee.payment.entity.PaymentClass;
import com.greedy.rotutee.payment.repository.PaymentChapterRepository;
import com.greedy.rotutee.payment.repository.PaymentClassRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.payment.model.service
 * fileName : PaymentTest
 * author : soobeen
 * date : 2022-05-13
 * description :
 * ===========================================================
 * DATE              AUTHOR      NOTE * -----------------------------------------------------------
 * 2022-05-13          soobeen     최초 생성
 */

@SpringBootTest
@ContextConfiguration(classes = {RotuteeApplication.class, JPAConfiguration.class, BeanConfiguration.class})
public class PaymentTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentChapterRepository paymentChapterRepository;
    @Autowired
    private PaymentClassRepository paymentClassRepository;
    @Autowired
    private ClassBasketCumulateTimeRepository classBasketCumulateTimeRepository;

    @Test
    public void 강의_시간_테이블_확인() {

        //given
        int lectureNo = 6;
        int memberNo = 27;
        int memberLectureNo = 3;

        //강의 시간 insert
        List<PaymentChapter> chapterList = paymentChapterRepository.findByLectureNo(lectureNo);
        List<Integer> chapterNums = new ArrayList<>();
        System.out.println("chapterNums : " + chapterNums);
        for(int i = 0; i < chapterList.size(); i++) {
            int chapterNo = chapterList.get(i).getChapterNo();
            chapterNums.add(chapterNo);
        }
        System.out.println("chapterNums = " + chapterNums);

        /* 강의에 해당하는 챕터 번호 리스트 형태 저장 */
        /*위에서 조회한 챕터1개에 해당하는 클래스 리스트 담아주기*/
        List<Integer> classNums = new ArrayList<>();
        for(int i= 0; i < chapterNums.size(); i++) {
            int chapterNo  = chapterNums.get(i);
            System.out.println("chapterNo" +chapterNo);

            List<PaymentClass> classList = paymentClassRepository.findByChapterChapterNo(chapterNo);
            System.out.println("classList :" + classList);

            for(int j = 0; j < classList.size(); j++) {
                int classNo = classList.get(j).getClassNo();
                System.out.println("classNo :" + classNo);

                classNums.add(classNo);
            }
        }
        System.out.println(classNums);
        System.out.println("gg2");

        for(int i = 0; i < classNums.size(); i++){
            System.out.println("확인" + i);
            int classNo = classNums.get(i);
            System.out.println("classNO : "+classNo);

            CumulateTimeDTO cumulateTimeDTO = new CumulateTimeDTO();
            MemberLectureDTO memberLectureDTO = new MemberLectureDTO();
            System.out.println("memberLectureNo" +memberLectureNo);


            memberLectureDTO.setMemberLectureNo(memberLectureNo);
            ClassDTO classDTO = new ClassDTO();

            classDTO.setClassNo(classNo);

            System.out.println(classNo);

            cumulateTimeDTO.setFinishedWatchingYN("N");
            cumulateTimeDTO.setMemberLecture(memberLectureDTO);
            cumulateTimeDTO.setCumClass(classDTO);
            System.out.println("cumulateTimeDTO "+cumulateTimeDTO);

            classBasketCumulateTimeRepository.save(modelMapper.map(cumulateTimeDTO, CumulateTime.class));
        }
        System.out.println("gg3");
    }
}
