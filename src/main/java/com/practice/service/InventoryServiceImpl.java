package com.practice.service;

import com.practice.domain.InventoryDTO;
import com.practice.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    //재고목록 가져오기
    @Override
    public List<InventoryDTO> selectInventoryList() {
        return inventoryMapper.selectInventoryList();
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
}
