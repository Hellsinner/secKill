package seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        long id = 1000;
        Date date = new Date();
        int i = seckillDao.reduceNumber(1000, date);
        System.out.println(i);
    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iphone6
         Seckill{seckillId=1000, name='1000元秒杀iphone6',
         number=100,
         startTime=Tue May 22 00:00:00 CST 2018,
         endTime=Thu May 23 00:00:00 CST 2019,
         createTime=null}
         */
    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills){
            System.out.println(seckill);
        }
        /**
         * Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100,
         * startTime=Tue May 22 00:00:00 CST 2018, endTime=Thu May 23 00:00:00 CST 2019, createTime=null}
         Seckill{seckillId=1001, name='500元秒杀iPad2', number=200,
         startTime=Sun May 22 00:00:00 CST 2016, endTime=Thu May 23 00:00:00 CST 2019, createTime=null}
         Seckill{seckillId=1002, name='300元秒杀小米4', number=300,
         startTime=Sun May 22 00:00:00 CST 2016, endTime=Thu May 23 00:00:00 CST 2019, createTime=null}
         Seckill{seckillId=1003, name='200元秒杀红米note', number=400,
         startTime=Sun May 22 00:00:00 CST 2016, endTime=Mon May 23 00:00:00 CST 2016, createTime=null}
         */
    }
}