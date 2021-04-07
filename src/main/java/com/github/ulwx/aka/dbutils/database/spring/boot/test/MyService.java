package com.github.ulwx.aka.dbutils.database.spring.boot.test;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {
    private AddressDao addressDao;
    private AddressMapper mapper;

    public MyService(AddressDao addressDao,AddressMapper mapper) {
        this.addressDao = addressDao;
        this.mapper=mapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdb(){

        List<Address> list2= addressDao.getListMd();
        //声明了Propagation.NESTED事务
        addressDao.updateMd0();
        try {
            addressDao.updateMd1();
        }catch (Exception e){
            e.printStackTrace();
        }
       //MyService方法的内部调用会使用被调用方法上声明的事务失效，所以需要用下面方式调用
       // ((MyService) AopContext.currentProxy()).updateMdbOther();
        //MyService方法的内部调用会使用被调用方法上声明的事务失效，下面的内部调用还是在updateMdb()方法的事务里。
       // updateMdbOther();
        int i=0;

    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void updateMdbOther(){
        System.out.println("call updateMdbOther");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void testMapper(){
         mapper.updateMd();
         mapper.fun();
    }
}
