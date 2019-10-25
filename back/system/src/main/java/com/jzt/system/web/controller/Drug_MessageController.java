package com.jzt.system.web.controller;

import com.jzt.system.bean.Drug;
import com.jzt.system.bean.Drug_Message;
import com.jzt.system.service.IDrugService;
import com.jzt.system.service.IDrug_MessageService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by  on 2019/10/21 0:01
 */
@RestController
@RequestMapping("/card")
public class Drug_MessageController {
    @Autowired
    private IDrug_MessageService drug_messageService;

    @Autowired
    private IDrugService drugService;

    @GetMapping("selectAllBycardID")
    private Message selectAllBycardID(String cardID){
//        List<CardExtend> list = cardService.selectAllByNumber(number);
//        return MessagesUtil.success(list);
        return null;
    }

    @GetMapping("openPort")
    private Message findMessageByCardID(Long id) throws Exception {
        TestController testController = new TestController();
        testController.Message();
        String s = "";
        while ("".equals(s)){
            s = testController.readComm();
        }
        testController.closeT();
        System.out.println("拿到的卡号:"+s);
        Drug_Message drug_message = drug_messageService.selectDMByCardid(s);

        System.out.println(drug_message.getName());
        Drug drug = new Drug();
        drug.setName(drug_message.getName());
        drug.setCategory(drug_message.getCategory());
        drug.setFirm(drug_message.getFirm());
        drug.setNorms(drug_message.getNorms());
        drug.setPrice(drug_message.getPrice());
        drug.setName(drug_message.getName());
        drugService.saveOrUpdate(drug);



        return MessageUtils.success("成功!");
    }
}
