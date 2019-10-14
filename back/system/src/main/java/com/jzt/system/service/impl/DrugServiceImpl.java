package com.jzt.system.service.impl;

import com.jzt.system.bean.Drug;
import com.jzt.system.bean.DrugExample;
import com.jzt.system.mapper.DrugMapper;
import com.jzt.system.service.IDrugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class DrugServiceImpl implements IDrugService {
    @Resource
    private DrugMapper drugMapper;
    @Override
    public List<Drug> findAll() {
        DrugExample example = new DrugExample();
        return drugMapper.selectByExample(example);
    }

    @Override
    public Drug findById(long id) {
        return drugMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Drug> findByIds(long[] ids) {
        List<Drug> list = new ArrayList<>();
        for (long i:ids)
            list.add(drugMapper.selectByPrimaryKey(i));
        return list;
    }

    @Override
    public void saveOrUpdate(Drug drug) {
        if(drug.getId()==null){
            drugMapper.insert(drug);
        }else{
            drugMapper.updateByPrimaryKey(drug);
        }
    }

    @Override
    public void deleteById(long id) {
        drugMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(long[] ids) {
        for(long id : ids){
            drugMapper.deleteByPrimaryKey(id);
        }
    }
    @Override
    public void inserts(List<Drug> list) {
        drugMapper.inserts(list);
    }

    @Override
    public void updateById(Drug record) {
        drugMapper.updateById(record);
    }

    @Override
    public void insertsOut(List<Drug> list) {
        drugMapper.insertsOut(list);
    }

    @Override
    public void updateByIdOut(Drug record) {
        drugMapper.updateByIdOut(record);
    }

    @Override
    public List<Drug> findByCategory(String category) {
        DrugExample example=new DrugExample();
        example.createCriteria().andCategoryEqualTo(category);
        return drugMapper.selectByExample(example);
    }
}
