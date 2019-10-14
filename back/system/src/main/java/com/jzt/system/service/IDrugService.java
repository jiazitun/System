package com.jzt.system.service;

import com.jzt.system.bean.Drug;

import java.util.List;

public interface IDrugService {
    List<Drug> findAll();

    Drug findById(long id);

    List<Drug> findByIds(long[] ids);

    void saveOrUpdate(Drug drug);

    void deleteById(long id);

    void batchDelete(long[] ids);

    void inserts(List<Drug> list);

    void updateById(Drug drug);

    void insertsOut(List<Drug> list);

    void updateByIdOut(Drug drug);

    List<Drug> findByCategory(String category);
}
