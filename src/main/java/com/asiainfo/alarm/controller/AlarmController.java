package com.asiainfo.alarm.controller;

import com.asiainfo.alarm.model.*;
import com.asiainfo.alarm.service.IAlarmService;
import com.asiainfo.alarm.util.DateUtil;
import com.asiainfo.alarm.util.ResultUtil;
import com.asiainfo.alarm.util.labelUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result getSourceTableInfo(@RequestParam(value = "dataCycle", defaultValue = "0") int dataCycle, @RequestParam(value = "sourceTableName", defaultValue = "") String sourceTableName, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        int count = alarmService.getSourceTableInfoCount(dataCycle, sourceTableName);

        Page page = new Page(currentPage, pageSize, count);

        List<CocSourceTable> sourceTableList = alarmService.getSourceTableInfo(dataCycle, sourceTableName, page);

        for (CocSourceTable cocSourceTable : sourceTableList) {
            CocSourceTableExt cocSourceTableExt = alarmService.getSourceTableExtInfo(cocSourceTable.getSourceTableCode());

            if (cocSourceTableExt != null) {
                cocSourceTable.setCocSourceTableExt(cocSourceTableExt);
                int dataStatus = getTableDataStatus(cocSourceTable);
                cocSourceTable.setDataStatus(dataStatus);
            }

        }

        page.setDataList(sourceTableList);
        return ResultUtil.success(page);
    }

    @ResponseBody
    @GetMapping("/getSourceTableInfoByCode")
    public Result getSourceTableInfoByCode(@RequestParam("sourceTableCode") String sourceTableCode) {

        CocSourceTable cocSourceTable = alarmService.getSourceTableInfoByCode(sourceTableCode);

        CocSourceTableExt cocSourceTableExt = alarmService.getSourceTableExtInfo(sourceTableCode);

        if (cocSourceTableExt != null) {
            cocSourceTable.setCocSourceTableExt(cocSourceTableExt);
            int dataStatus = getTableDataStatus(cocSourceTable);
            cocSourceTable.setDataStatus(dataStatus);
        }

        return ResultUtil.success(cocSourceTable);
    }

    @ResponseBody
    @PostMapping("/updateSourceTableExtInfo")
    public Result updateSourceTableExtInfo(@RequestBody @Valid CocSourceTableExt cocSourceTableExt, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        if (cocSourceTableExt.getProduceType() == 1) {
            String taskCode = cocSourceTableExt.getTaskCode();
            if (StringUtils.isBlank(taskCode)) {
                return ResultUtil.error("调度号不能为空！");
            }
        } else if (cocSourceTableExt.getProduceType() == 2) {
            String interfaceCode = cocSourceTableExt.getInterfaceCode();
            if (StringUtils.isBlank(interfaceCode)) {
                return ResultUtil.error("接口号不能为空！");
            }
            String interfaceServerIp = cocSourceTableExt.getInterfaceServerIp();
            if (StringUtils.isBlank(interfaceServerIp)) {
                return ResultUtil.error("接口机IP不能为空！");
            }
            String interfaceFilePath = cocSourceTableExt.getInterfaceFilePath();
            if (StringUtils.isBlank(interfaceFilePath)) {
                return ResultUtil.error("接口文件路径不能为空！");
            }
        } else {
            String executorServerIp = cocSourceTableExt.getExecutorServerIp();
            if (StringUtils.isBlank(executorServerIp)) {
                return ResultUtil.error("独立程序部署主机IP不能为空！");
            }
            String executorFilePath = cocSourceTableExt.getExecutorFilePath();
            if (StringUtils.isBlank(executorFilePath)) {
                return ResultUtil.error("独立程序部署路径不能为空！");
            }
        }

        alarmService.updateSourceTableExtInfo(cocSourceTableExt);

        return ResultUtil.success();
    }

    public static int getTableDataStatus(CocSourceTable cocSourceTable) {

        int tableDataCycle = cocSourceTable.getDataCycle();
        String dataDate = cocSourceTable.getDataDate();
        int delayValue = cocSourceTable.getCocSourceTableExt().getDelayValue();

        String rightDataDate;

        if (tableDataCycle == 1) {
            rightDataDate = DateUtil.getDayDataDate(delayValue);
        } else {
            rightDataDate = DateUtil.getMonthDataDate(delayValue);
        }

        if (dataDate.equals(rightDataDate)) {
            return TableDataStatus.NORMAL;
        } else {
            String preRightDataDate;

            if (tableDataCycle == 1) {
                preRightDataDate = DateUtil.getDayDataDate(delayValue + 1);
            } else {
                preRightDataDate = DateUtil.getMonthDataDate(delayValue + 1);
            }

            boolean isAfter = DateUtil.isAfterUpdateTime(cocSourceTable.getDataCycle(), cocSourceTable.getCocSourceTableExt().getUpdateTime());

            if (!dataDate.equals(preRightDataDate)) {
                return TableDataStatus.DELAY;
            } else {
                return isAfter ? TableDataStatus.DELAY : TableDataStatus.NORMAL;
            }
        }
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

            if (alarmService.doPreCusNumExist(cocLabel.getLabelId(), DateUtil.twoDaysAgo, DateUtil.twoDaysAgo)) {
                cocLabelExt.setWavedCustomNum(alarmService.cusNumWaved(cocLabel, DateUtil.twoDaysAgo, DateUtil.twoDaysAgo));

                if (alarmService.doPreCusNumExist(cocLabel.getLabelId(), DateUtil.twoDaysAgo, DateUtil.threeDaysAgo)) {
                    cocLabelExt.setMoM(alarmService.calculateMoM(cocLabel, cocLabelExt, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo));

                } else {
                    cocLabel.setStatus(-2);     //3天前数据不存在，无法计算环比，状态为3天前数据异常
                    cocLabel.setErrMsg("3天前数据异常，无法计算环比，请检查!!!");
                }
            } else {
                cocLabel.setStatus(-1);     //两天前数据不存在：1.可能源表还没到（进行中）；2.源表已经到了还是没有数据（数据延迟异常）。
                cocLabel.setErrMsg("2天前数据异常，无法计算数据波动量，请检查!!!");
            }

            System.out.println(cocLabel.getLabelId() + "----" + cocLabel.getLabelName() + "====" + cocLabel.getDataDate());

            if (cocLabel.getDataCycle() == 1) {
                cocLabelExt.setDelayValue(DateUtil.delayValDay);

                if (!DateUtil.isDelay(cocLabel.getDataDate(), cocLabelExt.getDelayValue())) {

                    if (cocLabelExt.getMoM() > labelUtil.wavedPercent || cocLabelExt.getMoM() == -1.00f || cocLabelExt.getMoM() == 0) {
                        waved++;
                        cocLabel.setStatus(3);
                        cocLabel.setErrMsg("数据波动异常，请检查源表数据!!!");

                    } else {
                        normal++;
                        cocLabel.setStatus(1);

                    }
                } else {
                    delay++;
                    cocLabel.setStatus(2);
                    cocLabel.setErrMsg("数据延迟异常，请检查源表到达时间!!!");

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
