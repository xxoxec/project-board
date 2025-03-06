package com.project.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article; // 게시글 (ID)
    private String content; // 본문

    private LocalDateTime createAt; // 생성일지
    private String createBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일자
    private String modifiedBy; // 수정자
}
