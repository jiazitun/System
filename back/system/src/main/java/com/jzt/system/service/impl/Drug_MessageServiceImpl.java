package com.jzt.system.service.impl;

import com.jzt.system.bean.Drug_Message;
import com.jzt.system.bean.Drug_MessageExample;
import com.jzt.system.mapper.Drug_MessageMapper;
import com.jzt.system.service.IDrug_MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  on 2019/10/20 23:07
 */
@Service
public class Drug_MessageServiceImpl implements IDrug_MessageService {
    @Resource
    private Drug_MessageMapper drug_messageMapper;
    @Override
    public List<Drug_Message> findAll() {
        Drug_MessageExample example = new Drug_MessageExample();
        return drug_messageMapper.selectByExample(example);
    }

    @Override
    public Drug_Message findById(long id) {
        return drug_messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public Drug_Message findBycardID(String cardID) {
        return drug_messageMapper.selectAllBycardID(cardID);

    }

    @Override
    public List<Drug_Message> findByIds(long[] ids) {
        return null;
    }

    @Override
    public void saveOrUpdate(Drug_Message drug_message) {
        if(drug_message.getId() == null){
            drug_messageMapper.insert(drug_message);
        }else {
            drug_messageMapper.updateByPrimaryKey(drug_message);
        }
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void batchDelete(long[] ids) {

    }

    @Override
    public List<Drug_Message> findByCategory(String category) {
        return null;
    }

    @Override
    public Drug_Message selectDMByCardid(String cardid) {
        return drug_messageMapper.selectDMByCardid(cardid);
    }

    @Override
    public void inserts(List<Drug_Message> list) {

    }

    @Override
    public void updateById(Drug_Message record) {
        drug_messageMapper.updateById(record);
    }

}
