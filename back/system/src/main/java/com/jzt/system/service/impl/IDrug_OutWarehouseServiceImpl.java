package com.jzt.system.service.impl;

import com.jzt.system.bean.Drug_OutWarehouse;
import com.jzt.system.bean.Drug_OutWarehouseExample;
import com.jzt.system.mapper.Drug_OutWarehouseMapper;
import com.jzt.system.service.IDrug_OutWarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2019/10/8 21:05
 */
@Service
public class IDrug_OutWarehouseServiceImpl implements IDrug_OutWarehouseService {
    @Resource
    private Drug_OutWarehouseMapper drug_outWarehouseMapper;

    @Override
    public List<Drug_OutWarehouse> findAll() {
        Drug_OutWarehouseExample example = new Drug_OutWarehouseExample();
        return drug_outWarehouseMapper.selectByExample(example);
    }

    @Override
    public Drug_OutWarehouse findById(long id) {
        return drug_outWarehouseMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Drug_OutWarehouse> findByIds(long[] ids) {
        List<Drug_OutWarehouse> list = new ArrayList<>();
        for (long i : ids)
            list.add(drug_outWarehouseMapper.selectByPrimaryKey(i));
        return list;
    }

    @Override
    public void saveOrUpdate(Drug_OutWarehouse drug_outWarehouse) {
        if (drug_outWarehouse.getId() == null) {
            drug_outWarehouseMapper.insert(drug_outWarehouse);
        } else {
            drug_outWarehouseMapper.updateByPrimaryKey(drug_outWarehouse);
        }
    }

    @Override
    public void deleteById(long id) {
        drug_outWarehouseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) {
        for (long id : ids) {
            drug_outWarehouseMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void inserts(List<Drug_OutWarehouse> list) {
        drug_outWarehouseMapper.inserts(list);
    }

    @Override
    public void updateById(Drug_OutWarehouse record) {
        drug_outWarehouseMapper.updateById(record);
    }

    @Override
    public List<Drug_OutWarehouse> findByCategory(String category) {
        Drug_OutWarehouseExample example = new Drug_OutWarehouseExample();
        example.createCriteria().andCategoryEqualTo(category);
        return drug_outWarehouseMapper.selectByExample(example);
    }
}