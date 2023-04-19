package com.practice.mapper;

import com.practice.domain.InventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {

    //재고목록 가져오기
    List<InventoryDTO> selectInventoryList();

    //상세재고목록 가져오기
    InventoryDTO getInventoryListBySubjectNo(int subjectNo);

    //재고 등록하기
    int insertInventory(InventoryDTO dto);

    //재고 삭제하기
    int deleteInventory(int subjectNo);

    //재고 수정하기
    int updateInventory(InventoryDTO dto);
}
