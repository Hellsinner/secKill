package seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.entity.Seckill;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            long phone = 13545448559L;
            String md5 = "c8a5cca5bce49f3f0658f8c939adb803";
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}",seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
            /**
             * WARN  seckill.service.SeckillServiceTest - exposer=
             * Exposer{exposed=false, md5='null', seckillId=1001,
             * now=1526536955047, start=1463846400000, end=1463932800000}
             */
        }
        /**
         * seckill.service.SeckillServiceTest - exposer=Exposer{exposed=true,
         * md5='c8a5cca5bce49f3f0658f8c939adb803', seckillId=1000, now=0, start=0, end=0}
         */
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 13545448559L;
        String md5 = "c8a5cca5bce49f3f0658f8c939adb803";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("result={}",seckillExecution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }

        /**
         * INFO  seckill.service.SeckillServiceTest - result=SeckillExecution{seckillId=1000,
         * state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000,
         * userPhone=13545448559, state=0, createTime=Thu May 17 13:57:34 CST 2018}}
         */
    }

    @Test
    public void executeSeckillPro() {
        long seckillId = 1000;
        long phone = 12354857549L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution seckillExecution =
                    seckillService.executeSeckillPro(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}