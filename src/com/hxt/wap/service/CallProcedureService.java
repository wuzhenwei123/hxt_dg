package com.hxt.wap.service;

import java.util.HashMap;
import java.util.Map;

import com.hxt.wap.dao.CallProcedureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "callProcedureService")
public class CallProcedureService {
    @Autowired(required = false)
    private CallProcedureDao callProcedureMapper;

    public String callGenerateSnNo(Map map) throws Exception {
        String sn = this.callProcedureMapper.callGenerateSnNo(map);
        return sn;
    }

}
