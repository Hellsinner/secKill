package seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import seckill.entity.SuccessKilled;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1001L;
        long phone = 13512314522L;
        int i = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1001L;
        long phone = 13512314522L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        /**
         * SuccessKilled{seckillId=1000, userPhone=13512314522, state=-1,
         * createTime=Wed May 16 23:47:06 CST 2018}
         Seckill{seckillId=1000, name='1000元秒杀iphone6', number=100,
         startTime=Tue May 22 00:00:00 CST 2018,
         endTime=Thu May 23 00:00:00 CST 2019, createTime=null}
         */
    }
}