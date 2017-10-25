package com.asiainfo.omp.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.asiainfo.omp.model.OsmpBaseInfo;
import com.asiainfo.omp.service.BaseMonitorService;
@Component
public class OsmpBaseInfoTimer {
	@Autowired
	private BaseMonitorService bms;
	private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 定期采集ods接口信息入osmp_base_info表
	 * @throws Exception
	 */
	//@Scheduled(initialDelay =3000, fixedDelay = 1800000)
	public void generateOsmpBaseInfo() throws Exception{
		List<OsmpBaseInfo> obis=bms.getOsmpBaseInfoAll();
		if(obis.size()>0){
			Date nowDate=new Date();
			for(OsmpBaseInfo o:obis){
				Date scheduleTime=sdf.parse(o.getScheduleTime());
				if(nowDate.after(scheduleTime)
						|| (nowDate.equals(new Date(scheduleTime.getTime()-24*60*60*1000)) && nowDate.getHours() >21 && !o.getStatus().equals("3"))){
					o.setTimeOut("1");
					if("".equals(o.getErrorMsg()))
					o.setErrorMsg("超出规定完成时间");
				}else{
					o.setTimeOut("0");
				}
				double warningNum=0.1;//预警阀值
				double waveNum=0.0000;//波动率
				long lastSuccNum=o.getLastSuccNum();
				long succNum=o.getSuccNum();
				if(lastSuccNum==0||succNum==0){
				}else{
					waveNum=((succNum-lastSuccNum)/(double)lastSuccNum);
				}
				o.setWaveNum(waveNum*100);
				if(warningNum < Math.abs(waveNum)){
				   o.setWaveStatus("1");
				   if("".equals(o.getErrorMsg()))
						o.setErrorMsg("超出预警阀值"+String.format("%.2f", waveNum*100)+"%");
				}else{
					o.setWaveStatus("0");
				}
				if(bms.updateOmspBaseInfo(o) < 1){
					bms.insertOmspBaseInfo(o);
				}
			}
		}
	}
}
