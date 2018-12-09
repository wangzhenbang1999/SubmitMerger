package com.wangzhenbang.SubmitMerger.util;

import com.wangzhenbang.SubmitMerger.dao.IAdminDao;
import com.wangzhenbang.SubmitMerger.dao.ICustomerDao;
import com.wangzhenbang.SubmitMerger.dao.impl.AdminJDBCDao;
import com.wangzhenbang.SubmitMerger.dao.impl.CustomerJDBCDao;

//使用工厂模式来创建Dao，使得业务层与Dao数据持久层完全解绑，使得修改DAO之后，不用在业务层中修改DAO接口的实现类
public class DAOFactory {
    public static IAdminDao getAdminDao() {
        return new AdminJDBCDao();
    }

    public static ICustomerDao getCustomerDao() {
        return new CustomerJDBCDao();
    }
}
