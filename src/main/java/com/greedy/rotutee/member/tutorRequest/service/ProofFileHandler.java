package com.greedy.rotutee.member.tutorRequest.service;

import com.greedy.rotutee.member.member.entity.Member;
import com.greedy.rotutee.member.member.repository.MemberRepository;
import com.greedy.rotutee.member.profile.entity.AttachedFile;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * packageName : com.greedy.rotutee.member.tutorRequest.service
 * fileName : FileHandler
 * author : 7sang
 * date : 2022-04-22
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-22 7sang 최초 생성
 */

@Component
public class ProofFileHandler {

    private final MemberRepository memberRepository;

    @Autowired
    public ProofFileHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<AttachedFile> UserFileUpload(List<MultipartFile> files, int memberNo) throws IOException {

        // 반환할 파일 리스트
        List<AttachedFile> fileList = new ArrayList<>();
        Member member = memberRepository.findById(memberNo).get();

        // 전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(files)) {

            for(MultipartFile multipartFile : files) {

                String fileUploadDirectory  = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\proofs";
                String thumbnailDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\proofs";

                System.out.println("fileUploadDirectory = " + fileUploadDirectory);
                System.out.println("thumbnailDirectory = " + thumbnailDirectory);

                String originFileName = multipartFile.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
                String thumbnailFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                System.out.println("originFileName = " + originFileName);
                System.out.println("ext = " + ext);
                System.out.println("savedFileName = " + savedFileName);
                System.out.println("thumbnailFileName = " + thumbnailFileName);


                File file = new File(fileUploadDirectory); //파일 저장 경로 확인, 없으면 만든다.
                if (!file.exists()) {
                    file.mkdirs();
                }

                multipartFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                AttachedFile attachedFile = new AttachedFile();

                attachedFile.setOriginalAttachedFileName(originFileName);                                   // 원본 이름
                attachedFile.setSaveAttachedFileName(savedFileName);                                             // 저장 이름
                attachedFile.setThumbnailAttachedFileName(thumbnailFileName);                            // 썸네일 이름
                attachedFile.setThumbnailFilePath(thumbnailDirectory);                                                 // 썸네일 저장경로
                attachedFile.setStoragePath(fileUploadDirectory);                                               // 저장 경로
                attachedFile.setDivision("프로필");                                                                // 구분
                attachedFile.setMember(member);                                                                  // 회원 정보
                attachedFile.setFileDeletionYn("N");                                                            // 삭제 여부

                fileList.add(attachedFile);

                file.setWritable(true);
                file.setReadable(true);

            }
            return fileList;
        }

        return null;
    }
}
