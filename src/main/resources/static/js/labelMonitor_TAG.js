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

        var dataCycle = 0;
        var pageSize = 8;
        var currentPage = 1;

        var _status = {};
        // 1.正常 2.延迟异常 3.波动异常 4.tcl为执行 5.tcl执行异常
        _status[1] = "正常";
        _status[2] = "延迟异常";
        _status[3] = "波动异常";
        _status[4] = "tcl未到执行时间";
        _status[5] = "tcl执行异常";


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
        var showLabelDetail = function (cocLabel) {
            $("#labelDetail").empty();
            $.ajax({
                type: "GET",
                url: "./alarm/queryLabelDetailInfo",
                dataType: "json",
                data: {"srcTabCode": cocLabel.srcTabCode},
                success: function (data) {
                    if (data.code == 0) {
                        var cocSourceTable = data.data;


                        var $table = $("<table class='table table-bordered'></table>");
                        var $tr = $("<tr></tr>");
                        var $th = $("<th></th>");
                        var $td = $("<td></td>");

                        //标签详情
                        var $tmpTr = $tr.clone();
                        $tmpTr.append($th.clone().addClass("Basic-th").attr("colspan", "3").text("标签详情"));
                        $tmpTr.append($th.clone().addClass("Basic-th").html(Header.setLabelStatus(cocLabel.status)));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签名："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.labelName));
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签Id："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.labelId));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("依赖源表名："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.srcTabName));
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("依赖源表字段："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.srcTabColName));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签状态："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(getLabelSatus(cocLabel.status)));
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("原因："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.errMsg));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签使用次数 ："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.useTimes));
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签口径："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.busiCaliber));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签路径 ："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.labelRoot));
                        $tmpTr.append($td.clone().addClass("Basic-td-key").text("标签源表code："));
                        $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocLabel.srcTabCode));
                        $table.append($tmpTr);

                        //源表信息
                        $tmpTr = $tr.clone();
                        $tmpTr.append($th.clone().addClass("Basic-th").attr("colspan", "4").text("源表信息"));
                        $table.append($tmpTr);

                        if (cocSourceTable.dataStatus == 0) {

                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("Basic-td-center").attr("colspan", "4").text("未录入源表信息!"));
                            $table.append($tmpTr);

                        } else {


                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("Basic-td-key").text("源表生成时间："));
                            $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocSourceTable.cocSourceTableExt.updateTime));
                            $tmpTr.append($td.clone().addClass("Basic-td-key").text("数据时效性："));
                            $tmpTr.append($td.clone().addClass("Basic-td-value").text("T-" + cocSourceTable.cocSourceTableExt.delayValue + (cocSourceTable.cocSourceTableExt.dataCycle == 1 ? "日" : "月")));
                            $table.append($tmpTr);

                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("Basic-td-key").text("联系人姓名："));
                            $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocSourceTable.cocSourceTableExt.contactName));
                            $tmpTr.append($td.clone().addClass("Basic-td-key").text("联系电话/邮箱："));
                            $tmpTr.append($td.clone().addClass("Basic-td-value").text(cocSourceTable.cocSourceTableExt.contactTel + "/" + cocSourceTable.cocSourceTableExt.contactEmail));
                            $table.append($tmpTr);
                        }


                        $("#labelDetail").append($table);

                        $(".foundaods.labelDetail").show();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }

        /**标签标签信息**/
        var updateLabelInfo = function (cocLabel) {
            document.getElementById("edit-form").reset();

            $("#labelId").val(cocLabel.labelId);
            $("#labelIdP").html(cocLabel.labelId);
            $("#labelNameP").html(cocLabel.labelName);
            $("#busiCaliber").val(cocLabel.busiCaliber);

            $(".foundaods.edit").show();
        };
        /**提交模态框数据**/
        $("#post").click(function () {
            var cocLabel = {};

            cocLabel.labelId = $("#labelId").val();
            cocLabel.labelName = $("#labelNameP").text();
            cocLabel.busiCaliber = $("#busiCaliber").val();

            $.ajax({
                type: "POST",
                url: "./alarm/updateLabelInfo",
                contentType: 'application/json',
                data: JSON.stringify(cocLabel),
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $(".foundaods.edit").hide();
                        alert(data.msg);
                        refresh(dataCycle, currentPage);
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });

        /**取消模态框**/
        $("#cancel").click(function () {
            $(".foundaods.edit").hide();
        });

        /**详情模态框的关闭**/
        $("#label-detailClose").click(function () {
            $(".foundaods.labelDetail").hide();
        });
        $("#label-EditClose").click(function () {
            $(".foundaods.edit").hide();
        });

        //刷新按钮点击事件
        $("#refresh").click(function () {
            $(".base-opt-qry-sel").val("all");
            $(".base-opt-qry-txt").val("");
            refresh(dataCycle, 1, "");
        });

        var getLabelSatus = function (index) {
            return _status[index];
        }


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
            labelName = $.trim($("#labelName").val());
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
                    if (data.code != 0) {
                        alert(data.msg);
                        return;
                    }
                    var labelList = data.data.labelList;
                    // var labelStatus = data.data.labelStatus;
                    var $tr = $("<tr></tr>");
                    var $td = $("<td></td>");
                    var $img = $("<img>");
                    totalRecord = data.data.count;
                    // normal = labelStatus.normal;
                    // delay = labelStatus.delay;
                    // waved = labelStatus.waved;
                    //var totalnum=0,notstart=0,success=0,fail=0,process=0,wavestatus=0,timeout=0;
                    $("#tbody").empty();
                    if (labelList.length == 0) {
                        var $trTmp = $tr.clone();
                        $trTmp.append($td.clone().attr("colspan", "5").text("暂无数据"));
                    } else {
                        $.each(labelList, function (k, v) {
                            var $trTmp = $tr.clone();
                            $trTmp.append($td.clone().append(Header.setLabelStatus(v.status)));
                            $trTmp.append($td.clone().text(v.labelName).attr("title", v.errMsg));
                            $trTmp.append($td.clone().text(v.dataCycle == 1 ? "日" : "月"));
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

                            var $tdLast = $td.clone();

                            $tdLast.append($img.clone().addClass("labelDetailImg").attr("src", "./images/icon_detials.png").bind("click", function () {
                                showLabelDetail(v);
                            }));

                            $tdLast.append($img.clone().addClass("labelEditImg").attr("src", "./images/icon_revise1.png").bind("click", function () {
                                updateLabelInfo(v);
                            }));

                            $trTmp.append($tdLast);

                            $("#tbody").append($trTmp);

                        });
                    }
                    //if (status == null || status == "") {
                    $("#totalRecord").text(totalRecord);
                    // $("#normal").text(normal);
                    // $("#delay").text(delay);
                    // $("#waved").text(waved);
                    //}

                    //分页初始化
                    $("#pageTool").html("");
                    $("#pageTool").Paging({
                        pagesize: 8,
                        count: totalRecord,
                        current: currentPage,
                        callback: function (currentPage) {
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

    }
);
