package com.tp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tp.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}
