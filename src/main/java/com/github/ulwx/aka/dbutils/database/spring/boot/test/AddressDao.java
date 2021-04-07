package com.github.ulwx.aka.dbutils.database.spring.boot.test;

import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.tool.MD;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressDao {
    private MDataBaseTemplate mDataBaseTemplate;
    public AddressDao(MDataBaseTemplate mDataBaseTemplate){
        this.mDataBaseTemplate=mDataBaseTemplate;
    }

    public List<Address> getListMd(){
        Map<String, Object> mp=new HashMap<>();

        List<Address> list=mDataBaseTemplate.queryList(Address.class,
                MD.md(),mp);
        return list;

    }

    public void updateMd0(){
        Map<String, Object> mp=new HashMap<>();
        mDataBaseTemplate.update(MD.md(),mp);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMd1(){
        Map<String, Object> mp=new HashMap<>();
        mDataBaseTemplate.update(MD.md(),mp);


    }

}
