package com.practice.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.practice.domain.MemberDTO;

import java.util.List;

@Mapper //mapper 인터페이스, MyBatis 매핑XML에 기재된 SQL를 호출하기 위한 인터페이스
public interface MemberMapper {

    public List<MemberDTO> selectMemberList();
}
