# aka-dbutils-spring-boot-starter-test
1. 准备mysql数据库
2. 添加test数据库，执行src/db/test.sql脚本可添加test数据库用于测试
3. 修改com.github.ulwx.aka.dbutils.database.spring.boot.starter.test.MyConfiguration类里的dataSource()方法里关于数据库连接的配置，数据库连接地址和用户密码修改成自己的。
4. 运行com.github.ulwx.aka.dbutils.database.spring.boot.starter.test.TestApplication类

