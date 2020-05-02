package com.dao;

import com.model.pojo.Association;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssociationMapper {
    public List<Association> findAssociation(@Param("assoNames") String assoNames);
}
