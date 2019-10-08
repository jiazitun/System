package com.jzt.system.bean;

/**
 * Created by  on 2019/10/7 18:47
 */
public class MyClass {
    private String DataType;
    private String tagId;
    private String IsRegister;
    private String name;
    private String norms;
    private String price;
    private String firm;
    private String category;


    public String getIsRegister() {
        return IsRegister;
    }

    public void setIsRegister(String isRegister) {
        IsRegister = isRegister;
    }
    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
