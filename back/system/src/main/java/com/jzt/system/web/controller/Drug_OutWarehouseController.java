package com.jzt.system.web.controller;

import com.jzt.system.bean.Drug_OutWarehouse;
import com.jzt.system.service.IDrug_OutWarehouseService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by  on 2019/10/8 21:27
 */
@RestController
@RequestMapping("/drug_OutWarehouse")
public class Drug_OutWarehouseController {
    @Autowired
    private IDrug_OutWarehouseService drug_outWarehouseService;

    @GetMapping("findAll")
    private Message findAll(){
        List<Drug_OutWarehouse> list = drug_outWarehouseService.findAll();
        return MessageUtils.success(list);
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(Drug_OutWarehouse drug_outWarehouse){
        drug_outWarehouseService.saveOrUpdate(drug_outWarehouse);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("deleteById")
    private Message deleteById(Long id){
        drug_outWarehouseService.deleteById(id);
        return MessageUtils.success("删除成功");
    }

    @GetMapping("findById")
    private Message findById(Long id){
        Drug_OutWarehouse drug_outWarehouse=drug_outWarehouseService.findById(id);
        return MessageUtils.success(drug_outWarehouse);
    }
    @PostMapping("batchDelete")
    public Message batchDelete(@RequestBody long[] ids){
        drug_outWarehouseService.batchDelete(ids);
        return MessageUtils.success("删除成功！");
    }
    @PostMapping("batchUpdate")
    public Message batchUpdate(@RequestBody long[] ids){
        List<Drug_OutWarehouse> list = drug_outWarehouseService.findByIds(ids);
        drug_outWarehouseService.inserts(list);
        drug_outWarehouseService.batchDelete(ids);
        return MessageUtils.success("操作成功！");
    }
    @GetMapping("updateById")
    private Message updateById(Long id){
        drug_outWarehouseService.updateById(drug_outWarehouseService.findById(id));
        drug_outWarehouseService.deleteById(id);
        return MessageUtils.success("操作成功");
    }


}
