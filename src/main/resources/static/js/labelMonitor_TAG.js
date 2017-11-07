$(document).ready(function () {
    //初始化网页导航模块
    Header = new Header();
    Header.init();
    Header.show("cocAlarm");
    $(".base-table").css("height", (document.documentElement.clientHeight - 150) + "px");
    //页面顶部菜单模块样式控制
    $(".base-header-item").click(function () {
        $(".base-header-item-active").removeClass("base-header-item-active");
        $(this).addClass("base-header-item-active");
    });
    var dataCycle = "";

    //获取首页跳转Url的参数
    var getParm = function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        var chartStatus;
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);//键值对
            }
        }
        return theRequest.chartStatus;
    }

    var chartStatus = getParm();

    //按钮组点击事件
    $(".base-opt-btn").on("click", ".base-opt-btn-item", function () {
        dataCycle = $("input", this).attr("dataCycle");
        $(".base-opt-btn-active").removeClass("base-opt-btn-active");
        $(this).addClass("base-opt-btn-active");
        //清空选择框和搜索框的内容
        // $(".base-opt-qry-sel").val("all");
        // $(".base-opt-qry-txt").val("");
        refresh(dataCycle, 1, "");
    })


    /**详情模态框**/
    $(".odsInterDeatil_click").click(function () {
        $(".foundaods").show();
    });
    /**详情模态框的关闭**/
    $("#closea").click(function () {
        $(".founda").hide();
    });
    $("#ods-close").click(function () {
        $(".foundaods").hide();
    });

    //刷新按钮点击事件
    $("#refresh").click(function () {
        $(".base-opt-qry-sel").val("all");
        $(".base-opt-qry-txt").val("");
        refresh(dataCycle, 1, "");
    });


    //查询框动态查询
    $(".base-opt-qry-txt").keyup(function () {
        refresh(dataCycle, 1, "");
    });
    // //第一下拉框查询
    // $("#select_button1").change(function(){
    //     //var $sel=$("#select_button1 option:checked").val();
    //     refresh(dataCycle,1,"");
    // });
    // //第二下拉框查询
    // $("#select_button2").change(function(){
    //     //var $sel=$("#select_button2 option:checked").val();
    //     refresh(dataCycle,1,"");
    // });

    //数量点击查询（设置点击样式）
    $(".base-label-right li").click(function () {
        var status = $(this).find(".spanclass").attr("id");
        refresh(dataCycle, 1, status);
    });

    //分页查询
    var refresh = function (dataCycle, currentPage, status) {
        labelName = $("#labelName").val();
        $.ajax({
            type: "GET",
            url: "./alarm/queryLabelInfo",
            dataType: "json",
            data: {
                "currentPage": currentPage,
                "labelName": labelName,
                "dataCycle": dataCycle,
                "status": status
            },
            success: function (data) {
                var $tr = $("<tr></tr>");
                var $td = $("<td></td>");
                totalRecord = data.data.count;
                normal = data.data.labelStatus.normal;
                delay = data.data.labelStatus.delay;
                waved = data.data.labelStatus.waved;
                //var totalnum=0,notstart=0,success=0,fail=0,process=0,wavestatus=0,timeout=0;
                $("#tbody").empty();
                $.each(data.data.labelList, function (k, v) {
                    var $trTmp = $tr.clone();
                    if (totalRecord == 0) {
                        $trTmp.append($td.clone().attr("colspan", "10").text("暂无数据"));
                    } else {
                        $trTmp.append($td.clone().append(Header.setLabelStatus(v.status)));
                        $trTmp.append($td.clone().text(v.labelName).attr("title", v.cocLabelExt.delayValue));
                        /**预警原因
                         var msg = "";
                         if (v.status == "1" || v.status == "2") {
                        } else if (v.status == "-2") {
                            if (v.errorMsg == "" || v.errorMsg == "文件未到") {
                            } else {
                                msg = $("<div class= 'warning-reason' style='background-color:#fb7f54;' title='" + v.errorMsg + "'>" + v.errorMsg + "</div>");
                            }
                        } else {
                            if (v.errorMsg == "" || v.errorMsg == "文件未到") {
                            } else {
                                msg = $("<div class= 'warning-reason' style='background-color:#ffb430;' title='" + v.errorMsg + "'>" + v.errorMsg + "</div>");
                            }
                        }**/
                        //$trTmp.append($td.clone().append(msg));
                        $trTmp.append($td.clone().text(v.dataDate == undefined || v.dataDate == "" ? "" : v.dataDate));
                        $trTmp.append($td.clone().text(v.customNum));
                        var wavedCustomNum = "";
                        if (v.cocLabelExt.wavedCustomNum >= 0) {
                            wavedCustomNum = "<img src='./images/icon_up.png'/>" + Header.dataformat(v.cocLabelExt.wavedCustomNum, 1);
                        } else {
                            wavedCustomNum = "<img src='./images/icon_down.png'/>" + Header.dataformat(-v.cocLabelExt.wavedCustomNum, 1);
                        }
                        $trTmp.append($td.clone().append(wavedCustomNum));
                        $trTmp.append($td.clone().text(Header.dataformat(v.cocLabelExt.moM, 2) + "%"));
                        $trTmp.append($td.clone().text(v.srcTabName));
                        $trTmp.append($td.clone().text(v.srcTabColName));

                        var $tdLast = $td.clone();
                        $($tdLast).bind("click", function () {
                            $(".foundaods").show();
                            $(".labelStatus").html(Header.setLabelStatus(v.status));
                            if (v.status == 1) {
                                $(".result").html("正常");
                            } else if (v.status == 2) {
                                $(".result").html("延迟异常");
                            } else if (v.status == 3) {
                                $(".result").html("波动异常");
                            }
                            $(".labelName").html(v.labelName);
                            $(".reason").html("暂无");
                            $(".tableName").html(v.srcTabName + "(" + v.srcTabCode +")");
                            $(".dataCycle").html(v.dataCycle);
                            $(".dataDate").html(v.dataDate);
                            $(".custom_num").html(v.customNum);
                            $(".updateTime").html(v.cocLabelExt.updateTime);
                            $(".shouldUpdateTime").html(v.cocLabelExt.scheduleTime);
                            $(".finishTime").html(v.cocLabelExt.finishTime);
                            $(".contactP").html(v.cocLabelExt.official);
                        });
                        $trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='./images/icon_detials.png'>"));
                    }
                    $("#tbody").append($trTmp);
                });
                //if (status == null || status == "") {
                $("#totalRecord").text(totalRecord);
                $("#normal").text(normal);
                $("#delay").text(delay);
                $("#waved").text(waved);
                //}

                //分页初始化
                $("#pageTool").html("");
                $("#pageTool").Paging({
                    pagesize: 8,
                    count: totalRecord,
                    current: currentPage,
                    callback: function (currentPage, size, count) {
                        refresh(dataCycle, currentPage, status);
                    }
                });

            },
        });
    };

    //查询数据源
    var initPage = function () {
        dataCycle = $(".base-opt-btn-active").find("input").attr("dataCycle");
        refresh(dataCycle, 1, chartStatus);//页面初始化加载列表数据
    }

    initPage();//页面初始化加载数据源

});
