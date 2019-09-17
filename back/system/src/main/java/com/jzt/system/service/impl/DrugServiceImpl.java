package com.jzt.system.service.impl;

import com.jzt.system.bean.Drug;
import com.jzt.system.bean.DrugExample;
import com.jzt.system.mapper.DrugMapper;
import com.jzt.system.service.IDrugService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}
