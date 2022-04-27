package com.greedy.rotutee.member.profile.service;

import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.repository.AttachedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * packageName : com.greedy.rotutee.member.service
 * fileName : FileHandler
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

@Component
public class ProfileFileHandler {

    private final MemberRepository memberRepository;
    private final AttachedFileRepository attachedFileRepository;

    @Autowired
    public ProfileFileHandler(MemberRepository memberRepository, AttachedFileRepository attachedFileRepository) {

        this.memberRepository = memberRepository;
        this.attachedFileRepository = attachedFileRepository;
    }

    public AttachedFile profileFileUpload(MultipartFile uploadFile, int memberNo) throws IOException {

        Member member = memberRepository.findById(memberNo).get();
        String division = "프로필";
        AttachedFile attachedFile = attachedFileRepository.findByMemberNoAndDivision(memberNo, division);
        System.out.println("attachedFile = " + attachedFile);
        if(attachedFile == null) {
            attachedFile = new AttachedFile();
            System.out.println("attachedFile = " + attachedFile);
        }

        String fileUploadDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\image";
        String thumbnailDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\image";

        String originFileName = uploadFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
        String thumbnailFileName = UUID.randomUUID().toString().replace("-", "") + ext;

        File file = new File(fileUploadDirectory); //파일 저장 경로 확인, 없으면 만든다.
        if (!file.exists()) {
            file.mkdirs();
        }

        uploadFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

        attachedFile.setOriginalAttachedFileName(originFileName);                                   // 원본 이름
        attachedFile.setSaveAttachedFileName(savedFileName);                                             // 저장 이름
        attachedFile.setThumbnailAttachedFileName(thumbnailFileName);                            // 썸네일 이름
        attachedFile.setThumbnailFilePath(thumbnailDirectory);                                                 // 썸네일 저장경로
        attachedFile.setStoragePath(fileUploadDirectory);                                               // 저장 경로
        attachedFile.setDivision("프로필");                                                                // 구분
        attachedFile.setMember(member);                                                                  // 회원 정보
        attachedFile.setFileDeletionYn("N");                                                            // 삭제 여부

        file.setWritable(true);
        file.setReadable(true);

        return attachedFile;
    }
}
