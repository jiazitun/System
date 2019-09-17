package com.jzt.system.service.impl;
import com.jzt.system.bean.Drug_Warehouse;
import com.jzt.system.bean.Drug_WarehouseExample;
import com.jzt.system.mapper.Drug_WarehouseMapper;
import com.jzt.system.service.IDrug_WarehouseService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  on 2019/9/17 16:22
 */
@Service
public class Drug_WarehouseServiceImpl implements IDrug_WarehouseService {
    @Resource
    private Drug_WarehouseMapper drug_warehouseMapper;
    @Override
    public List<Drug_Warehouse> findAll() {
        Drug_WarehouseExample example = new Drug_WarehouseExample();
        return drug_warehouseMapper.selectByExample(example);
    }

    @Override
    public Drug_Warehouse findById(long id) {
        return drug_warehouseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Drug_Warehouse drug_warehouse) {
        if(drug_warehouse.getId()==null){
            drug_warehouseMapper.insert(drug_warehouse);
        }else{
            drug_warehouseMapper.updateByPrimaryKey(drug_warehouse);
        }
    }

    @Override
    public void deleteById(long id) {
        drug_warehouseMapper.deleteByPrimaryKey(id);
    }

}

