package com.dao;

import com.model.pojo.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonMapper {

    public List<Person> findPerson(String cardNos);
}
