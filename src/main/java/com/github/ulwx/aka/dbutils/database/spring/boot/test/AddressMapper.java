package com.github.ulwx.aka.dbutils.database.spring.boot.test;

import com.github.ulwx.aka.dbutils.database.AkaMapper;

public abstract class AddressMapper extends AkaMapper {

    public abstract void updateMd();

    public void fun(){
        this.updateMd();
    }
}
