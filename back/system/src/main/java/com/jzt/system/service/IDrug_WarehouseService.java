package com.jzt.system.service;

import com.jzt.system.bean.Drug_Warehouse;

import java.util.List;

/**
 * Created by  on 2019/9/17 16:21
 */
public interface IDrug_WarehouseService {
    List<Drug_Warehouse> findAll();
    Drug_Warehouse findById(long id);
    void saveOrUpdate(Drug_Warehouse drug_warehouse);
    void deleteById(long id);
    void batchDelete(long[] ids);
    void batchUpdate(long[] ids);

}
