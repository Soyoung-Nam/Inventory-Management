package com.practice.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> { //재고목록과 Pagination값을 여기에 담는다. (Map에 담아도 된다.)

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }
}
