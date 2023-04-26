package com.practice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
public class SearchDTO { //Pagination에서 계산되는 객체

    /* 현재 페이지 번호 */
    private int page;

    /* 페이지당 출력할 데이터 개수 */
    private int recordSize;

    /* 화면 하단에 출력할 페이지 사이즈 */
    private int pageSize;

    /* 검색 키워드 */
    private String keyword;

    /* 검색 유형 */
    private String searchType;

    /* 페이지네이션 정보 */
    private Pagination pagination;

    public SearchDTO() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }
}
