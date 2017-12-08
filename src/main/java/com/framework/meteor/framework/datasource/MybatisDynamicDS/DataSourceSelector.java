package com.framework.meteor.framework.datasource.MybatisDynamicDS;

import com.framework.meteor.framework.constant.DynamicDSEnum;
import com.framework.meteor.framework.util.StringUtil;

import java.util.Arrays;

/**
 * 数据源读写选择器
 *
 * @author Meteor.wu
 * @since 2017/12/7 16:02
 */

public class DataSourceSelector {
    private static ThreadLocal<String> currentKey = new ThreadLocal<>();

    public static String getCurrentKey(){
        String key = currentKey.get();
        if(StringUtil.isNotEmpty(key))
            return key;
        else return DynamicDSEnum.READ_ONLY.name();
    }

    public static void setRO(){
        setCurrenKey(DynamicDSEnum.READ_ONLY.name());
    }
    public static void setRW(){
        setCurrenKey(DynamicDSEnum.READ_WRITE.name());
    }
    public static void setWO(){
        setCurrenKey(DynamicDSEnum.WRITE_ONLY.name());
    }

    public static void setCurrenKey(String key){
        if(Arrays.asList(DynamicDSEnum.READ_ONLY.name(), DynamicDSEnum.READ_WRITE.name(), DynamicDSEnum.WRITE_ONLY.name()).indexOf(key)>=0){
            currentKey.set(key);
        }else{
            currentKey.set(DynamicDSEnum.READ_WRITE.name());
        }
    }

}
