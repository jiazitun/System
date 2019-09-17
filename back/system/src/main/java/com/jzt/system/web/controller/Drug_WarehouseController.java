package com.jzt.system.web.controller;


import com.jzt.system.bean.Drug_Warehouse;
import com.jzt.system.service.IDrug_WarehouseService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by  on 2019/9/17 16:22
 */
@RestController
@RequestMapping("/drug_Warehouse")
public class Drug_WarehouseController {
    @Autowired
    private IDrug_WarehouseService drugWarehouseService;

    @GetMapping("findAll")
    private List<Drug_Warehouse> findAll(){
        List<Drug_Warehouse> list = drugWarehouseService.findAll();
        return list;
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(Drug_Warehouse drug_warehouse){
        drugWarehouseService.saveOrUpdate(drug_warehouse);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("deleteById")
    private Message deleteById(Long id){
        drugWarehouseService.deleteById(id);
        return MessageUtils.success("删除成功");
    }

    @GetMapping("findById")
    private  Message findById(Long id){
        drugWarehouseService.findById(id);
        return  MessageUtils.success("操作成功");
    }

}
