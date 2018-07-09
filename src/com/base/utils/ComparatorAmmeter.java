package com.base.utils;


import java.util.Comparator;
import java.util.Map;

public class ComparatorAmmeter implements Comparator {


    public int compare(Object arg0, Object arg1) {
        Map map =(Map)arg0;
        Map map1=(Map)arg1;
        Integer a = Integer.valueOf(map.get("totalFee").toString());
        Integer b = Integer.valueOf(map1.get("totalFee").toString());
        //比较欠费金额
        int flag = map.get("totalFee").toString().compareTo(map1.get("totalFee").toString());
        return flag<0?1:-1;
    }

}