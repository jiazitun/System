package com.jzt.system.web.controller;

import com.jzt.system.bean.Drug;
import com.jzt.system.service.IDrugService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private IDrugService drugService;

    @GetMapping("findAll")
    private Message findAll(){
        List<Drug> list = drugService.findAll();
        return MessageUtils.success(list);
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
    private Message findById(Long id){
        Drug drug=drugService.findById(id);
        return MessageUtils.success(drug);
    }
    @PostMapping("batchDelete")
    public Message batchDelete(@RequestBody long[] ids){
        drugService.batchDelete(ids);
        return MessageUtils.success("删除成功！");
    }
    @PostMapping("batchUpdate")
    public Message batchUpdate(@RequestBody long[] ids){
        List<Drug> list = drugService.findByIds(ids);
        drugService.inserts(list);
        drugService.batchDelete(ids);
        return MessageUtils.success("操作成功！");
    }
    @GetMapping("updateById")
    private Message updateById(Long id){
        drugService.updateById(drugService.findById(id));
        drugService.deleteById(id);
        return MessageUtils.success("操作成功");
    }

    @GetMapping("findJn")
    private Message findJn(){
        String category="胶囊";
        List<Drug> list=drugService.findByCategory(category);
        return MessageUtils.success(list);
    }

    @GetMapping("findWan")
    private Message findWan(){
        String category="丸";
        List<Drug> list=drugService.findByCategory(category);
        return MessageUtils.success(list);
    }
    @GetMapping("findTj")
    private Message findTj(){
        String category="糖浆";
        List<Drug> list=drugService.findByCategory(category);
        return MessageUtils.success(list);
    }
    @GetMapping("findKFY")
    private Message findKFY(){
        String category="口服液";
        List<Drug> list=drugService.findByCategory(category);
        return MessageUtils.success(list);
    }
    @GetMapping("findPian")
    private Message findPian(){
        String category="片";
        List<Drug> list=drugService.findByCategory(category);
        return MessageUtils.success(list);
    }


}
