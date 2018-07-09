package com.hxt.wap.dao;


import com.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository("callProcedureDao")
public class CallProcedureDao extends BaseDao {
    @Autowired
    private SqlMapClientTemplate sqlMapClient;
    public String callGenerateSnNo(Map map){
        Object obj = sqlMapClient.queryForObject("Wap.callGenerateSnNo", map);
        return obj==null?"":obj.toString();
    }

}
