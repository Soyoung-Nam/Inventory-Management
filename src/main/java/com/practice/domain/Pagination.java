package com.practice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination { //계산된 값

    /* 전체 데이터 수 */
    private int totalRecordCount;

    /* 전체 페이지 수 */
    private int totalPageCount;

    /* 첫 페이지 번호 */
    private int startPage;

    /* 끝 페이지 번호 */
    private int endPage;

    /* LIMIT 시작 위치 */
    private int limitStart;

    /* 이전 페이지 존재 여부 */
    private boolean existPrevPage;

    /* 다음 페이지 존재 여부 */
    private boolean existNextPage;

    public Pagination(int totalRecordCount, SearchDTO dto) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(dto);
        }
    }

    private void calculation(SearchDTO dto) {

        //전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / dto.getRecordSize()) + 1;

        //현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (dto.getPage() > totalPageCount) {
            dto.setPage(totalPageCount);
        }

        //첫 페이지 번호 계산
        startPage = ((dto.getPage() - 1) / dto.getPageSize()) * dto.getPageSize() + 1;

        //끝 페이지 번호 계산
        endPage = startPage + dto.getPageSize() - 1;

        //끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        //LIMIT 시작 위치 계산
        limitStart = (dto.getPage() - 1) * dto.getRecordSize();

        //이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        //다음 페이지 존재 여부 확인
        existNextPage = (endPage * dto.getRecordSize()) < totalRecordCount;
    }
}
