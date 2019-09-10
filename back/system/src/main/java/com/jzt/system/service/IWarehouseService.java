package com.jzt.system.service;


import com.jzt.system.bean.Warehouse;

import java.util.List;

public interface IWarehouseService {
    List<Warehouse> findAll();

    void saveOrUpdate(Warehouse warehouse);

    void deleteById(Long id);
}
