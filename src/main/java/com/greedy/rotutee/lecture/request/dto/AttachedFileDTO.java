package com.greedy.rotutee.lecture.request.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttachedFileDTO {

    private int attachedFileNo;
    private String originalAttachedFileName;
    private String saveAttachedFileName;
    private String thumbnailFileName;
    private String storageFile;
    private String thumbnailFilePath;
    private String division;
    private String fileDeletionYN;
    private MemberDTO member;
    private LectureDTO lecture;
    private ClassDTO classes;

}
