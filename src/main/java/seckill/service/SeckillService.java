package seckill.service;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.entity.Seckill;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    //查询秒杀列表
    List<Seckill> getSeckillList();
    //根据id查询秒杀
    Seckill getById(long seckillId);
    //秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
    Exposer exportSeckillUrl(long seckillId);
    //执行秒杀操作
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

    //执行秒杀操作
    SeckillExecution executeSeckillPro(long seckillId, long userPhone, String md5);
}
