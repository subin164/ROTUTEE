package com.greedy.rotutee.member.service;

import com.greedy.rotutee.member.entity.AttachedFile;
import com.greedy.rotutee.member.entity.Member;
import com.greedy.rotutee.member.repository.MemberRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class FileHandler {

    private final MemberRepository memberRepository;

    @Autowired
    public FileHandler(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public List<AttachedFile> UserFileUpload(List<MultipartFile> files, int memberNo) throws IOException {

        // 반환할 파일 리스트
        List<AttachedFile> fileList = new ArrayList<>();
        Member member = memberRepository.findById(memberNo).get();

        // 전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(files)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            for(MultipartFile multipartFile : files) {

                String fileUploadDirectory  = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\img\\saveImg";
                String thumbnailDirectory = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\sg\\img\\thumbnail";

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

//                File thumbnailFile = new File(thumbnailDirectory); //파일 저장 경로 확인, 없으면 만든다.
//                if (!thumbnailFile.exists()) {
//                    thumbnailFile.mkdirs();
//                }

                File file = new File(fileUploadDirectory); //파일 저장 경로 확인, 없으면 만든다.
                if (!file.exists()) {
                    file.mkdirs();
                }

//                multipartFile.transferTo(new File(thumbnailDirectory + "/" + savedFileName));
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

//                multipartFile.transferTo(file);
//                multipartFile.transferTo(thumbnailFile);

                file.setWritable(true);
                file.setReadable(true);

//                String extension;
//                String contentType = multipartFile.getContentType();
//
//                System.out.println("contentType = " + contentType);
//
//                if(ObjectUtils.isEmpty(contentType)) {
//                    break;
//                }
//
//                extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//
//                System.out.println("extension = " + extension);
//
//                String new_file_name = current_date + "_"+System.nanoTime() + "." + extension;
//
//                System.out.println("new_file_name = " + new_file_name);
            }
            return fileList;
        }

        return null;

    }
}
