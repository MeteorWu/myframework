package com.framework.meteor;

import com.framework.meteor.work.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyframeworkApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void stringRedisTemplateTest() {
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
	}


	@Test
	public void StringRedisTest(){
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set("t1", 1);

		valueOperations.setIfAbsent("t2", 2);
		valueOperations.set("t3", "3", 100);
		Object andSet = valueOperations.getAndSet("t3", "cc");
		System.out.println(andSet);

        Map<String, Object> map = new HashMap<>();
        map.put("t4", 3);
        map.put("t5", "d3");
        valueOperations.multiSet(map);

        valueOperations.increment("t2", 1);
        System.out.println(valueOperations.get("t2"));

	}

	@Test
    public void listRedisTest(){
        ListOperations listOperations = redisTemplate.opsForList();
//        listOperations.leftPush("l1", 0);
//        listOperations.set("l1", 0, 1);
//        System.out.println(listOperations.index("l1", 0));
//        System.out.println(listOperations.range("l1", 0,-1));
//
//        listOperations.leftPush("l1", 2);
//        listOperations.leftPop("l1");
//
//
//        listOperations.leftPush("l2", "c");
//        String[] stringarrays = new String[]{"1","2","3"};
////        listOperations.leftPushAll("l2", stringarrays);
//        System.out.println(listOperations.range("l1", 0,-1));

        User user = new User();
        user.setUserId("1");
        user.setSex(1);
        user.setUsername("nkik");
        user.setPassword("dksf");
        listOperations.leftPush("u1", user);
        System.out.println(listOperations.range("u1", 0, -1));
        User u1 = (User)listOperations.leftPop("u1");
        System.out.println(u1.getUserId());
    }

    @Test
    public void setRedisTest() {
        SetOperations setOperations = redisTemplate.opsForSet();
        String[] strarrays = new String[]{"strarr1","sgtarr2"};
        setOperations.add("s1", strarrays);
//        setOperations.remove("s1", strarrays);
        System.out.println(setOperations.size("s1"));
        System.out.println(setOperations.members("s1"));

        // redis 2.8.0以后
//        Cursor s1 = setOperations.scan("s1", ScanOptions.NONE);
//        while(s1.hasNext()){
//            System.out.println(s1.next());
//        }

//        User user1 = new User();
//        user1.setUserId("1");
//        user1.setSex(1);
//        user1.setUsername("nkik");
//        user1.setPassword("dksf");
//
//        User user2 = new User();
//        user2.setUserId("2");
//        user2.setSex(1);
//        user2.setUsername("2222");
//        user2.setPassword("2222");
//        List<User> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
//        setOperations.add("users", users.toArray());
//        System.out.println(setOperations.size("users"));
    }

    @Test
    public void zsortRedisTest(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        User user1 = new User();
        user1.setUserId("1");
        user1.setSex(0);
        user1.setUsername("nkik");
        user1.setPassword("dksf");

        User user2 = new User();
        user2.setUserId("2");
        user2.setSex(2);
        user2.setUsername("2222");
        user2.setPassword("2222");
        zSetOperations.add("users", user1, 0.1);
        zSetOperations.add("users", user2, 2.0);

        System.out.println(zSetOperations.range("users", 0, 1));
        System.out.println(zSetOperations.count("users", 0, 1));
        System.out.println(zSetOperations.zCard("users"));
    }
}
