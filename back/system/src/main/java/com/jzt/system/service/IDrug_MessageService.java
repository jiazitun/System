package com.jzt.system.service;

import com.jzt.system.bean.Drug_Message;

import java.util.List;

public interface IDrug_MessageService {
    List<Drug_Message> findAll();

    Drug_Message findById(long id);

    Drug_Message findBycardID(String cardID);

    List<Drug_Message> findByIds(long[] ids);

    void saveOrUpdate(Drug_Message drug_message);

    void deleteById(long id);

    void batchDelete(long[] ids);

    List<Drug_Message> findByCategory(String category);

    Drug_Message selectDMByCardid(String cardid);

    void inserts(List<Drug_Message> list);

    void updateById(Drug_Message drug_message);
}
