package com.jzt.system.service;

import com.jzt.system.bean.Drug_OutWarehouse;

import java.util.List;

/**
 * Created by  on 2019/10/8 20:50
 */
public interface IDrug_OutWarehouseService {
    List<Drug_OutWarehouse> findAll();

    Drug_OutWarehouse findById(long id);

    List<Drug_OutWarehouse> findByIds(long[] ids);

    void saveOrUpdate(Drug_OutWarehouse drug_outWarehouse);

    void deleteById(long id);

    void batchDelete(long[] ids);

    void inserts(List<Drug_OutWarehouse> list);

    void updateById(Drug_OutWarehouse drug_outWarehouse);

    List<Drug_OutWarehouse> findByCategory(String category);
}
