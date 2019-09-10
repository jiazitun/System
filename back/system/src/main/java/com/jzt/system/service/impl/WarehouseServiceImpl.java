package com.jzt.system.service.impl;

import com.jzt.system.bean.Warehouse;
import com.jzt.system.bean.WarehouseExample;
import com.jzt.system.mapper.WarehouseMapper;
import com.jzt.system.service.IWarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class WarehouseServiceImpl implements IWarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;
    @Override
    public List<Warehouse> findAll() {
        WarehouseExample example = new WarehouseExample();
        return warehouseMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        if(warehouse.getId() == null){
            warehouseMapper.insert(warehouse);
        }else {
            warehouseMapper.updateByPrimaryKey(warehouse);
        }
    }

    @Override
    public void deleteById(Long id) {
        warehouseMapper.deleteByPrimaryKey(id);
    }

}
