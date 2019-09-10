package com.jzt.system.web.controller;

import com.jzt.system.bean.Warehouse;
import com.jzt.system.service.IWarehouseService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;
    @GetMapping("findAll")
    private List<Warehouse> findAll(){
        List<Warehouse> list = warehouseService.findAll();
        return list;
    }

    @PostMapping("saveOrUpdate")
    private Message saveOrUpdate(Warehouse warehouse){
        warehouseService.saveOrUpdate(warehouse);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("deleteById")
    private Message deleteById(Long id){
        warehouseService.deleteById(id);
        return MessageUtils.success("删除成功");
    }

}
