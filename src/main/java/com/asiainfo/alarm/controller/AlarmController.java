package com.asiainfo.alarm.controller;

import com.asiainfo.alarm.model.*;
import com.asiainfo.alarm.service.IAlarmService;
import com.asiainfo.alarm.util.DateUtil;
import com.asiainfo.alarm.util.ResultUtil;
import com.asiainfo.alarm.util.labelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private IAlarmService alarmService;

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
    public Result queryLabelInfo(@RequestParam(value = "dataCycle", defaultValue = "0") int dataCycle,
                                 @RequestParam(value = "labelName", defaultValue = "") String labelName,
                                 @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                 @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                 @RequestParam(value = "status", defaultValue = "") String status) {
        Map<String, Integer> labelStatus = new HashMap<String, Integer>();
        int normal = 0;
        int delay = 0;
        int waved = 0;
        int count = alarmService.queryLabelNum(labelName, dataCycle);
        Page page = new Page(currentPage, pageSize, count);
        List<CocLabel> labelList = alarmService.queryLabelInfo(dataCycle, labelName, page, DateUtil.twoDaysAgo, DateUtil.twoDaysAgo);

        for (CocLabel cocLabel : labelList) {
            CocLabelExt cocLabelExt = new CocLabelExt();
            cocLabelExt.setLabelId(cocLabel.getLabelId());
            cocLabelExt.setWavedCustomNum(alarmService.cusNumWaved(cocLabel, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo));
            cocLabelExt.setMoM(alarmService.calculateMoM(cocLabel, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo));
            if (cocLabel.getDataCycle() == 1) {
                cocLabelExt.setDelayValue(DateUtil.delayValDay);
                LocalDate localDate = LocalDate.now();
                if (Long.parseLong(localDate.format(DateUtil.dayFormatter)) - Long.parseLong(cocLabel.getDataDate()) <= cocLabelExt.getDelayValue() + 1) {
                    if (cocLabelExt.getMoM() > labelUtil.wavedPercent) {
                        waved++;
                        cocLabel.setStatus(3);
                    } else {
                        normal++;
                        cocLabel.setStatus(1);
                    }
                } else {
                    delay++;
                    cocLabel.setStatus(2);
                }
            } else {
                cocLabelExt.setDelayValue(DateUtil.delayValMonth);
            }
            cocLabel.setCocLabelExt(cocLabelExt);
        }

        labelStatus.put("normal", normal);
        labelStatus.put("delay", delay);
        labelStatus.put("waved", waved);

        Map labelMap = new HashMap();

        labelMap.put("labelList", labelList);
        labelMap.put("labelStatus", labelStatus);
        labelMap.put("count", count);

        return ResultUtil.success(labelMap);
        /*Iterator<CocLabel> iterator = labelList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getClass());
        }*/
    }
}
