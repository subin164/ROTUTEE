package com.greedy.rotutee.member.member.entity;

import java.lang.reflect.Member;
import java.sql.Date;
import java.util.List;

/**
 * packageName : com.greedy.rotutee.member.member.entity
 * fileName : MemberImpl
 * author : 7sang
 * date : 2022-04-27
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-27 7sang 최초 생성
 */

public interface MemberI extends Member {

    Integer getNo();
    String getName();
    String getPwd();
    String getEmail();
    String getNickname();
    String getPhoneNum();
    String getIntroduction();
    Date getRegistrationDate();
    Date getWithdrawalDate();
    String getLeaveStatusYn();
    String getRouletteChance();
}
