package com.asiainfo.alarm.controller;

import com.asiainfo.alarm.model.*;
import com.asiainfo.alarm.service.IAlarmService;
import com.asiainfo.alarm.util.DateUtil;
import com.asiainfo.alarm.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);
    private String twoDaysAgo = DateUtil.getDayDataDate(2);     //两天前
    private String threeDaysAgo = DateUtil.getDayDataDate(3);   //三天前
    private String oneMonthAgo = DateUtil.getMonthDataDate(1);  //一个月前
    private String twoMonthAgo = DateUtil.getMonthDataDate(2);  //两个月前

    @Autowired
    private IAlarmService alarmService;

    //跳转标签库监控界面-默认汇总页面
    @RequestMapping("/labelMonitor")
    public String labelMonitor(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor";
    }

    //跳转标签库监控界面-源表
    @RequestMapping("/labelMonitor_SOURCE")
    public String labelMonitor_SOURCE(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_SOURCE";
    }

    //跳转标签库监控界面-标签
    @RequestMapping("/labelMonitor_TAG")
    public String labelMonitor_TAG(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "freemarker test!");
        return "labelMonitor_TAG";
    }


    @ResponseBody
    @GetMapping("/getSourceTableInfo")
    public Result getSourceTableInfo(@RequestParam(value = "dataCycle", defaultValue = "0") int dataCycle,
                                     @RequestParam(value = "sourceTableName", defaultValue = "") String sourceTableName,
                                     @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        int count = alarmService.getSourceTableInfoCount(dataCycle, sourceTableName);

        Page page = new Page(currentPage, pageSize, count);

        List<CocSourceTable> sourceTableList = alarmService.getSourceTableInfo(dataCycle, sourceTableName, page);

        for (CocSourceTable cocSourceTable : sourceTableList) {
            CocSourceTableExt cocSourceTableExt = alarmService.getSourceTableExtInfo(cocSourceTable.getSourceTableCode());
            int delayValue = cocSourceTableExt.getDelayValue();
        }

        return ResultUtil.success(sourceTableList);
    }

    @ResponseBody
    @GetMapping("/queryLabelInfo")
    public void queryLabelInfo(@RequestParam(value = "dataCycle", defaultValue = "0") int dataCycle,
                               @RequestParam(value = "labelName", defaultValue = "") String labelName,
                               @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (Integer.toString(dataCycle).substring(1) == "1") {
            dataCycle = 1;
        } else {
            dataCycle = 2;
        }
        int count = alarmService.queryLabelNum(labelName, dataCycle);
        Page page = new Page(currentPage, pageSize, count);
        List<CocLabel> labelList = alarmService.queryLabelInfo(dataCycle, labelName, page, twoDaysAgo, twoDaysAgo);
        for(CocLabel cocLabel: labelList){
            CocLabelExt cocLabelExt = cocLabel.getCocLabelExt();
            cocLabelExt.setWavedCustomNum(alarmService.cusNumWaved(cocLabel));
            cocLabelExt.setMoM(alarmService.calculateMoM(cocLabel));
        }
        /*Iterator<CocLabel> iterator = labelList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getClass());
        }*/
    }
}
