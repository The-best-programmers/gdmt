package com.joys.gdmt.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joys.gdmt.Entities.Duty;
import com.joys.gdmt.Entities.VO.DutyVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DutyMapper extends BaseMapper<Duty> {
    @Select("SELECT d.id,d.name,o.name AS organname,r.name AS rolename FROM duty d,organ o,role r WHERE d.organid=o.id AND d.roleid=r.id")
    List<DutyVO> dutylist();

    @Select("DELETE FROM userduty WHERE dutyid = #{id}")
    void deleteUserDuty(Integer id);
}