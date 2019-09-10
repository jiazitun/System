package com.jzt.system.service;

import com.jzt.system.bean.Drug;

import java.util.List;

public interface IDrugService {
    List<Drug> findAll();
    Drug findById(long id);
    void saveOrUpdate(Drug drug);
    void deleteById(long id);
}
