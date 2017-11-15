package com.asiainfo.alarm.controller;

import com.asiainfo.alarm.enums.ResultEnum;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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

        int count = alarmService.queryLabelNum(labelName, dataCycle);

        Page page = new Page(currentPage, pageSize, count);

        List<CocLabel> labelList = alarmService.queryLabelInfo(dataCycle, labelName, page, DateUtil.twoDaysAgo, DateUtil.twoDaysAgo, DateUtil.oneMonthAgo, DateUtil.oneMonthAgo);

        for (CocLabel cocLabel : labelList) {
            logger.info(cocLabel.getLabelId() + "-->" + cocLabel.getLabelName() + "-->" + cocLabel.getDataDate());

            CocLabelExt cocLabelExt = new CocLabelExt();

            cocLabelExt.setLabelId(cocLabel.getLabelId());

            if (cocLabel.getDataCycle() == 1) {

                /*
                这里先查询两天前的数据表（stat_dm）是否存在：
                1.如果存在再判断两天前该标签的数据是否存在；
                    (1).如果两天前数据存在，则可以直接查出环比（stat_dm表中有ring_num字段）
                    (2).如果两天前的数据不存在，则判断当前时间是否到达该标签的源表的更新时间，如果已到源表更新时间，则延迟；如果还没到则正常。
                2.如果不存在则表示两天前数据还未开始跑（没到10点），标签状态设为未开始（-1）。
                 */
                int doPreCusNumExistCurOptime = alarmService.doPreCusNumExist(cocLabel, DateUtil.twoDaysAgo, DateUtil.twoDaysAgo);

                int doPreCusNumExistPreOptime = alarmService.doPreCusNumExist(cocLabel, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo);

                if (doPreCusNumExistCurOptime == 1) {

                    cocLabelExt.setWavedCustomNum(alarmService.cusNumWaved(cocLabel, DateUtil.twoDaysAgo, DateUtil.twoDaysAgo));

                    if (doPreCusNumExistPreOptime == 1) {

                        if (alarmService.calculateMoM(cocLabel, cocLabelExt, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo) != -1.00f) {

                            cocLabelExt.setMoM(alarmService.calculateMoM(cocLabel, cocLabelExt, DateUtil.twoDaysAgo, DateUtil.threeDaysAgo));

                            if (cocLabelExt.getMoM() > labelUtil.wavedPercent) {

                                cocLabel.setStatus(3);

                                cocLabel.setErrMsg("数据波动异常，请检查源表数据!!!");

                            } else {

                                cocLabel.setStatus(1);

                            }

                        } else {
                            cocLabel.setStatus(1);
                            cocLabel.setErrMsg("3天前数据为0，无历史有效数据!!!");

                        }
                    } else {
                        cocLabel.setStatus(1);     //3天前数据不存在，无法计算环比，状态为3天前数据异常
                        cocLabel.setErrMsg("3天前无数据，无法计算环比!!!");

                    }
                } else {
                    if (DateUtil.nowTime.isBefore(DateUtil.tclShouldTime.plusMinutes(30)) && doPreCusNumExistCurOptime == -99) {

                        cocLabel.setStatus(4);     //两天前数据不存在：1.没到tcl程序开始执行时间；
                        cocLabel.setErrMsg("还未到达tcl开始执行时间!!!");

                    } else if (DateUtil.nowTime.isAfter(DateUtil.tclShouldTime.plusMinutes(30)) && doPreCusNumExistCurOptime == -99) {

                        cocLabel.setStatus(5);     //2.已经到了时间还是没有数据（数据延迟异常）。
                        cocLabel.setErrMsg("tcl程序异常");

                    }

                    if (doPreCusNumExistCurOptime == -98) {

                        int isDelay = this.getSrcTabArrivalStatus(cocLabel);

                        if (isDelay == 2) {

                            cocLabel.setStatus(2);

                        } else if (isDelay == 1) {

                            cocLabel.setStatus(1);

                        } else if (isDelay == 3) {

                            cocLabel.setStatus(1);

                            cocLabel.setErrMsg("No sourceTable");
                        } else {

                            cocLabel.setStatus(1);

                            cocLabel.setErrMsg("Plz configure the updateTime!");
                        }
                    }

                }

                cocLabelExt.setDelayValue(DateUtil.delayValDay);


            } else {

                int doPreCusNumExistCurOptime = alarmService.doPreCusNumExist(cocLabel, DateUtil.oneMonthAgo, DateUtil.oneMonthAgo);

                int doPreCusNumExistPreOptime = alarmService.doPreCusNumExist(cocLabel, DateUtil.oneMonthAgo, DateUtil.twoMonthAgo);

                if (doPreCusNumExistCurOptime == 1) {

                    cocLabelExt.setWavedCustomNum(alarmService.cusNumWaved(cocLabel, DateUtil.oneMonthAgo, DateUtil.oneMonthAgo));

                    if (doPreCusNumExistPreOptime == 1) {

                        if (alarmService.calculateMoM(cocLabel, cocLabelExt, DateUtil.oneMonthAgo, DateUtil.twoMonthAgo) != -1.00f) {

                            cocLabelExt.setMoM(alarmService.calculateMoM(cocLabel, cocLabelExt, DateUtil.oneMonthAgo, DateUtil.twoMonthAgo));

                            if (cocLabelExt.getMoM() > labelUtil.wavedPercent) {

                                cocLabel.setStatus(3);

                                cocLabel.setErrMsg("数据波动异常，请检查源表数据!!!");

                            } else {

                                cocLabel.setStatus(1);

                            }

                        } else {

                            cocLabel.setStatus(1);

                            cocLabel.setErrMsg("两个月前数据为0，无法进行环比计算，请检查!!!");

                        }

                    } else {

                        cocLabel.setStatus(1);

                    }

                } else {
                    if (DateUtil.nowDate.isBefore(DateUtil.tclShouldDate.plusDays(1)) && doPreCusNumExistCurOptime == -99) {

                        cocLabel.setStatus(4);     //两天前数据不存在：1.没到tcl程序开始执行时间；
                        cocLabel.setErrMsg("还未到达tcl开始执行时间!!!");

                    } else if (DateUtil.nowDate.isAfter(DateUtil.tclShouldDate.plusDays(1)) && doPreCusNumExistCurOptime == -99) {

                        cocLabel.setStatus(5);     //2.已经到了时间还是没有数据表。
                        cocLabel.setErrMsg("tcl程序异常");

                    }

                    if (doPreCusNumExistCurOptime == -98) {

                        int isDelay = this.getSrcTabArrivalStatus(cocLabel);

                        if (isDelay == 2) {

                            cocLabel.setStatus(2);

                        } else if (isDelay == 1) {

                            cocLabel.setStatus(1);

                        } else if (isDelay == 3) {

                            cocLabel.setStatus(1);

                            cocLabel.setErrMsg("No sourceTable");
                        } else {

                            cocLabel.setStatus(1);

                            cocLabel.setErrMsg("Plz configure the updateTime!");
                        }

                    }

                }

                cocLabelExt.setDelayValue(DateUtil.delayValDay);

            }
            cocLabel.setCocLabelExt(cocLabelExt);
        }


        Map labelMap = new HashMap();

        labelMap.put("labelList", labelList);
        labelMap.put("count", count);

        return ResultUtil.success(labelMap);
        /*Iterator<CocLabel> iterator = labelList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getClass());
        }*/
    }

    @ResponseBody
    @GetMapping("/queryLabelDetailInfo")
    public Result queryLabelDetailInfo(@RequestParam(value = "srcTabCode") String srcTabCode) {

        CocSourceTable cocSourceTable = alarmService.getSourceTableInfoByCode(srcTabCode);

        CocSourceTableExt cocSourceTableExt = alarmService.getSourceTableExtInfo(srcTabCode);

        if (cocSourceTableExt != null) {
            cocSourceTable.setCocSourceTableExt(cocSourceTableExt);
            int dataStatus = getTableDataStatus(cocSourceTable);
            cocSourceTable.setDataStatus(dataStatus);
        }

        return ResultUtil.success(cocSourceTable);
    }

    public int getSrcTabArrivalStatus(CocLabel cocLabel) {

        boolean isDelay;

        String updateTime = null;

        CocSourceTableExt cocSourceTableExt = alarmService.getSourceTableExtInfo(cocLabel.getSrcTabCode());

        if (null != cocSourceTableExt) {

            updateTime = cocSourceTableExt.getUpdateTime();
        } else {

            return 3;

        }

        if (null != updateTime && 0 != updateTime.length()) {


            if (cocLabel.getDataCycle() == 1) {

                LocalTime localTime = LocalTime.parse(updateTime).plusHours(1);

                isDelay = DateUtil.nowTime.isAfter(localTime);

                return isDelay ? 2 : 1;
            } else {

                LocalDate updateDate = LocalDate.parse(DateUtil.nowDate.format(DateUtil.monthFormatter) + updateTime, DateUtil.dayFormatter);

                LocalDate localDate = updateDate.plusDays(1);

                isDelay = DateUtil.nowDate.isAfter(localDate);

                return isDelay ? 2 : 1;
            }

        } else {
            return 4;
        }

    }

    @ResponseBody
    @GetMapping("/updateLabelInfo")
    public Result updateLabelInfo(@RequestBody CocLabel cocLabel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        String busiCaliber = cocLabel.getBusiCaliber();
        if (StringUtils.isBlank(busiCaliber)) {
            return ResultUtil.error("标签口径不能为空!");
        }

        long labelId = cocLabel.getLabelId();
        if (Long.valueOf(labelId).longValue() == 0) {
            return ResultUtil.error("标签Id不能为0!");
        }

        if (alarmService.updateLabelInfo(labelId, busiCaliber) == 1) {

            return ResultUtil.success();
        } else {

            return ResultUtil.error("不存在的标签Id");
        }

    }
}
