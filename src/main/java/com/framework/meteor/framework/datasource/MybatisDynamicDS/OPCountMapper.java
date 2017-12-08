package com.framework.meteor.framework.datasource.MybatisDynamicDS;

import com.framework.meteor.framework.constant.DynamicDSEnum;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库选择器 负载算法：目前很简单的算法。可以自己设计，但是目前的流行的是在ngix里面进行读写分离，然后直接在ngix里面写好负载算法
 *
 * @author Meteor.wu
 * @since 2017/12/7 16:22
 */
@Component
public class OPCountMapper {


    private AtomicInteger ro = new AtomicInteger();
//    private AtomicInteger wo = new AtomicInteger();
//    private AtomicInteger rw = new AtomicInteger();

    public String getDataSourceString(String key, int readDataSourceSize) {
        if (key.equals(DynamicDSEnum.WRITE_ONLY.name()) || key.equals(DynamicDSEnum.WRITE_ONLY.name())) {
            return DynamicDSEnum.WRITE_ONLY.name()+1;
        }else {
            synchronized(key) {
                if (ro.get() > 70000002) {
                    ro.set(0);
                    return DynamicDSEnum.WRITE_ONLY.name() + 0;
                } else {
                    int n = ro.getAndIncrement();
                    String ret =  DynamicDSEnum.READ_ONLY.name() + n % readDataSourceSize;
                    return ret;
                }

            }
        }
    }


//    private Map<String,Integer> countMapper = new ConcurrentHashMap<>();
//    private Map<String,Integer> lastRouter = new ConcurrentHashMap<>();
//
//    public OPCountMapper(){
//        countMapper.put(DynamicDSEnum.READ_ONLY.name(),0);
//        countMapper.put(DynamicDSEnum.READ_WRITE.name(),0);
//        countMapper.put(DynamicDSEnum.WRITE_ONLY.name(),0);
//        lastRouter.put(DynamicDSEnum.READ_ONLY.name(),0);
//        lastRouter.put(DynamicDSEnum.READ_WRITE.name(),0);
//        lastRouter.put(DynamicDSEnum.WRITE_ONLY.name(),0);
//    }
//
//    public String getCurrentRouter(String key){
//        int total = countMapper.get(key);
//        if(total==0){
//            if(!key.equals(DynamicDSEnum.READ_WRITE.name()))
//                return getCurrentRouter(DynamicDSEnum.READ_WRITE.name());
//            else{
//                return null;
//            }
//        }
//        int last = lastRouter.get(key);
//        return key+"_"+(last+1)%total;
//    }
//
//
//    public String appendRo() {
//        return appendKey(DynamicDSEnum.READ_ONLY.name());
//    }
//    public String appendWo() {
//        return appendKey(DynamicDSEnum.WRITE_ONLY.name());
//    }
//    public String appendRw() {
//        return appendKey(DynamicDSEnum.READ_WRITE.name());
//    }
//
//    private String appendKey(String key){
//        int total = countMapper.get(key);
//        String sk = key+"_"+total++;
//        countMapper.put(key,total);
//        return sk;
//    }


}
