package com.practice.service;

import com.practice.domain.*;
import com.practice.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    //재고목록 가져오기
    @Override
    public PagingResponse<InventoryDTO> selectInventoryList(SearchDTO dto) {
        int count = inventoryMapper.inventoryCount();
        if(count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, dto);
        dto.setPagination(pagination);

        List<InventoryDTO> list = inventoryMapper.selectInventoryList(dto);

        return new PagingResponse<>(list, pagination);
    }

    //상세재고목록 가져오기
    @Override
    public InventoryDTO getInventoryListBySubjectNo(int subjectNo) {
        return inventoryMapper.getInventoryListBySubjectNo(subjectNo);
    }

    //재고 등록하기
    @Override
    public int insertInventory(InventoryDTO dto) {
        return inventoryMapper.insertInventory(dto);
    }

    //재고 삭제하기
    @Override
    public int deleteInventory(int subjectNo) {
        return inventoryMapper.deleteInventory(subjectNo);
    }

    //재고 수정하기
    @Override
    public int updateInventory(InventoryDTO dto) {
        return inventoryMapper.updateInventory(dto);
    }
}
