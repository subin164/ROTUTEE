package com.greedy.rotutee.member.tutorRequest.dto;

import lombok.*;


/**
 * packageName : com.greedy.rotutee.member.dto
 * fileName : AttachedFileDTO
 * author : 7sang
 * date : 2022-04-20
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022-04-20 7sang 최초 생성
 */

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
    private String thumbnailAttachedFileName;
    private String storagePath;
    private String thumbnailFilePath;
    private String division;
    private String fileDeletionYn;
}
