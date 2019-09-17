package com.jzt.system.mapper;

import com.jzt.system.bean.Drug_Warehouse;
import com.jzt.system.bean.Drug_WarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Drug_WarehouseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    long countByExample(Drug_WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int deleteByExample(Drug_WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int insert(Drug_Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int insertSelective(Drug_Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    List<Drug_Warehouse> selectByExample(Drug_WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    Drug_Warehouse selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int updateByExampleSelective(@Param("record") Drug_Warehouse record, @Param("example") Drug_WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int updateByExample(@Param("record") Drug_Warehouse record, @Param("example") Drug_WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int updateByPrimaryKeySelective(Drug_Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_warehouse
     *
     * @mbg.generated Tue Sep 17 16:20:44 CST 2019
     */
    int updateByPrimaryKey(Drug_Warehouse record);
}