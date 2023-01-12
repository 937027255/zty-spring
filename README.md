# zty-spring
学习spring源码仿写的简易版spring框架

该简易版spring框架包括如下功能：
1. 通过编写xml文件向ioc容器中注册实例
2. 通过@**Component**注解进行包扫描注册实例
3. 通过@**Value**注解为类的属性绑定properties文件的值
4. 通过@**AutoWired**注解进行实例字段的自动注入
5. 通过**三级缓存**解决循环依赖的问题
6. 整合**JdbcTemplate**操作数据库
7. 在mybatis.core包中编写mybatis的核心代码
8. 将mybatis的核心代码整合到spring框架的生命周期中
 
