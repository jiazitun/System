package com.jzt.system.web.controller;

import com.jzt.system.bean.Drug;
import com.jzt.system.service.IDrugService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private IDrugService drugService;

    @GetMapping("findAll")
    private List<Drug> findAll(){
        List<Drug> list = drugService.findAll();
        return list;
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(Drug drug){
        drugService.saveOrUpdate(drug);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("deleteById")
    private Message deleteById(Long id){
        drugService.deleteById(id);
        return MessageUtils.success("删除成功");
    }

    @GetMapping("findById")
    private  Message findBuId(Long id){
        drugService.findById(id);
        return  MessageUtils.success("操作成功");
    }

}
