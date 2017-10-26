$(document).ready(function () {
    //初始化网页导航模块
    Header = new Header();
    Header.init();
    Header.show("labelMonitor");
    $(".base-table").css("height", (document.documentElement.clientHeight - 150) + "px");
    //页面顶部菜单模块样式控制
    $(".base-header-item").click(function () {
        $(".base-header-item-active").removeClass("base-header-item-active");
        $(this).addClass("base-header-item-active");
    });
    $(".base-opt-btn-item").click(function () {
        $(".base-opt-btn-active").removeClass("base-opt-btn-active");
        $(this).addClass("base-opt-btn-active");
    });
    var dataCyle = "";
    var interType = "";
    var isImport = "";
    var interName = "";

    //获取首页跳转Url的参数
    var getParm = function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        var chartStatus;
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest.chartStatus;
    }

    var chartStatus = getParm();

    //按钮组点击事件
    $(".base-opt-btn").on("click", ".base-opt-btn-item", function () {
        dataCyle = $("input", this).attr("data-id");
        $(".base-opt-btn-active").removeClass("base-opt-btn-active");
        $(this).addClass("base-opt-btn-active");
        //清空选择框和搜索框的内容
        $(".base-opt-qry-sel").val("all");//选择框，已注释
        $(".base-opt-qry-txt").val("");//搜索框
        refresh(dataCyle, 1, "");
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
        refresh(dataCyle, 1, "");
    });


    //查询框动态查询
    $(".base-opt-qry-txt").keyup(function () {
        refresh(dataCyle, 1, "");
    });
    // //第一下拉框查询
    // $("#select_button1").change(function(){
    //     //var $sel=$("#select_button1 option:checked").val();
    //     refresh(dataCyle,1,"");
    // });
    // //第二下拉框查询
    // $("#select_button2").change(function(){
    //     //var $sel=$("#select_button2 option:checked").val();
    //     refresh(dataCyle,1,"");
    // });

    //数量点击查询
    $(".base-label-right li").click(function () {
        var status = $(this).find(".spanclass").attr("id");
        refresh(dataCyle, 1, status);
    });

    //查询数量
    var notstart = 0;//未开始条数
    var process = 0;//进行中条数
    var success = 0;//成功条数
    var fail = 0;//失败条数
    var wavestatus = 0;//波动条数
    var timeout = 0;//超时条数
    var totalnum = 0;//总条数
    var count = 0;//分页总条数
    var querySourceTabNum = function (dataCyle, sourceTabName, status) {
        //interName = $("#interName").val();
        // sourceTabName = $("#sourceTabName").val();
        $.ajax({
            type: "GET",
            url: "/AlarmController/querySourceTabNum",//查询总条数
            dataType: "json",
            data: {"sourceTabName": sourceTabName, "dataCyle": dataCyle, "status": status},
            async: false,
            success: function (data) {
                if (typeof(data) != 'undefined' && data != null) {
                    count = data.count;
                    if (count == 0) {
                        count = count + 1;
                    }
                } else {
                    count = 1;//分页总数
                }
            }
        });
    }

    //分页查询
    var refresh = function (dataCyle, page, status) {
        // interType = $("#select_button1 option:checked").val();
        // isImport = $("#select_button2 option:checked").val();
        sourceTabName = $("#sourceTabName").val();
        querySourceTabNum(dataCyle, sourceTabName, status);
        $.ajax({
            type: "GET",
            url: "AlarmController/querySourceTabInfo",
            dataType: "json",
            data: {
                "page": page,
                "dataCyle": dataCyle,
                "sourceTabName": sourceTabName,
                "status": status
            },
            success: function (data) {
                var $tr = $("<tr></tr>");
                var $td = $("<td></td>");
                //var totalnum=0,notstart=0,success=0,fail=0,process=0,wavestatus=0,timeout=0;
                $("#tbody").empty();
                $.each(data, function (k, v) {
                    if (k == 0) {
                    }
                    var $trTmp = $tr.clone();
                    if (totalnum == 0) {
                        //totalnum=totalnum+1;
                        $trTmp.append($td.clone().attr("colspan", "10").text("暂无数据"));
                    } else {
                        $trTmp.append($td.clone().append(Header.setOdsStatus(v.status, v.timeOut)));
                        $trTmp.append($td.clone().text(v.interName).attr("title", v.errorDec));
                        /**预警原因**/
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
                        }
                        //$trTmp.append($td.clone().append(msg));
                        $trTmp.append($td.clone().text(v.tableName == undefined || v.tableName == "" ? "" : v.tableName));
                        $trTmp.append($td.clone().text(v.beginDate));
                        $trTmp.append($td.clone().text(v.actualFinDate));
                        $trTmp.append($td.clone().text(v.arriveDate));
                        $trTmp.append($td.clone().text(v.sumMum));
                        var waveNum = "";
                        if (v.waveNum >= 0) {
                            waveNum = "<img src='../images/icon_up.png'/>" + Header.dataformat(v.waveNum, 2) + "%";
                        } else {
                            waveNum = "<img src='../images/icon_down.png'/>" + Header.dataformat(-v.waveNum, 2) + "%";
                        }
                        $trTmp.append($td.clone().append(waveNum));
                        $trTmp.append($td.clone().text(v.updateTime));

                        var $tdLast = $td.clone();
                        $($tdLast).bind("click", function () {
                            $(".foundaods").show();
                            $(".interStatus").html(Header.setOdsStatus(v.status, v.timeOut));
                            if (v.status == 0) {
                                $(".result").html("加载成功");
                            } else if (v.status == -1) {
                                $(".result").html("调度失败");
                            } else if (v.status == 2) {
                                $(".result").html("波动异常");
                            } else if (v.status == 3) {
                                $(".result").html("进行中");
                            } else if (v.status == 4) {
                                $(".result").html("未开始");
                            } else if (v.status == 99) {
                                $(".result").html("超时");
                            }
                            $(".interName").html(v.interName);
                            $(".detail").html(v.errorDec);
                            $(".beginDate").html(v.beginDate);
                            $(".tableName").html(v.tableName);
                            $(".actualFinDate").html(v.actualFinDate);
                            $(".arriveDate").html(v.arriveDate);
                            $(".demainDate").html(v.demainDate);
                            $(".sumMum").html(v.sumMum);
                            $(".waveNum").html(v.waveNum + "%");
                        });
                        $trTmp.append($tdLast.addClass("odsInterDeatil_click").append("<img src='../images/icon_detials.png'>"));
                    }
                    $("#tbody").append($trTmp);
                });
                //if (status == null || status == "") {
                $("#totalnum").text(totalnum);
                $("#notstart").text(notstart);
                $("#success").text(success);
                $("#fail").text(fail);
                $("#process").text(process);
                $("#wavestatus").text(wavestatus);
                $("#timeout").text(timeout);
                //}

                //分页初始化
                $("#pageTool").html("");
                $("#pageTool").Paging({
                    pagesize: 8, count: count, current: page, callback: function (page, size, count) {
                        refresh(dataCyle, page, status);
                    }
                });

            },
        });
    };

    //查询数据源
    var queryDataSource = function (moduleId) {
        $.ajax({
            type: "GET",
            url: "/commonUtilController/queryTypeByModuleId",
            dataType: "json",
            data: {"moduleId": moduleId},
            success: function (data) {
                var $baseButton = $("#baseoptbtn");
                if (data != null) {
                    $.each(data, function (k, v) {
                        if (k == 0) {
                            var $Label = $("<label class=" + '"btn base-opt-btn-item base-opt-btn-active"' + "></label>");
                            var $Input = $("<input type=" + '"radio"' + " data-id=" + '"' + v.type_id + '"' + ">" + v.type_name + "</input>");
                            $Label.append($Input);
                            $baseButton.append($Label);
                            dataCyle = v.type_id;
                        } else {
                            var $Label = $("<label class=" + '"btn base-opt-btn-item"' + "></label>");
                            var $Input = $("<input type=" + '"radio"' + " data-id=" + '"' + v.type_id + '"' + ">" + v.type_name + "</input>");
                            $Label.append($Input);
                            $baseButton.append($Label);
                        }
                    });
                } else {
                    alert("请配置数据库信息!");
                }
                refresh(dataCyle, 1, chartStatus);//页面初始化加载列表数据
            }
        });
    }

    queryDataSource(4);//页面初始化加载数据源

});
