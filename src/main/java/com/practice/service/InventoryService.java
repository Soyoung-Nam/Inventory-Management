package com.practice.service;

import com.practice.domain.InventoryDTO;

import java.util.List;

public interface InventoryService {

    //재고목록 가져오기
    List<InventoryDTO> selectInventoryList();

    //상세재고목록 가져오기
    InventoryDTO getInventoryListBySubjectNo(int subjectNo);

    //재고 등록하기
    int insertInventory(InventoryDTO dto);
}
