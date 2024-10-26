package com.ssafy.sandbox.paging.repository;

import com.ssafy.sandbox.paging.dto.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyBatisPagingMapper {

    List<Paging> cursorPaging(@Param("cursorId") Long cursorId, @Param("count") int count);

    List<Paging> offsetPaging(@Param("size") int size, @Param("offset") int offset);

    boolean insertPagingData(@Param("paging") List<Paging> paging);

    int getTotalCount();
}
