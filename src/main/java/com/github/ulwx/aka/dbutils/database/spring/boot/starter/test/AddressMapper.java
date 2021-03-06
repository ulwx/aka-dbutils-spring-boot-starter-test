package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test;

import com.github.ulwx.aka.dbutils.database.AkaMapper;

public abstract class AddressMapper extends AkaMapper {

    public abstract void updateMd();

    public void update(){
        this.updateMd();
    }

    public void query(){
        Address address=new Address();
        address.setAddressId(1);
        this.getMdDataBase().queryOneBy(address);
        this.updateMd();
    }
}
