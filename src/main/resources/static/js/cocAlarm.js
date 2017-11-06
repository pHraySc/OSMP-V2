$(document).ready(function () {
    var _dataCycle = 0;
    var _pageSize = 8;
    var _currentPage = 1;
    var _sourceTableName = "";

    var _status = {};
    _status[0] = "未知";
    _status[1] = "正常";
    _status[2] = "延迟";

    var _images = {};
    _images[0] = "./images/icon_j.png";
    _images[1] = "./images/icon_h.png";
    _images[2] = "./images/icon_i.png";

    var _produceTypeName = {};
    _produceTypeName[1] = "调度平台";
    _produceTypeName[2] = "外部接口";
    _produceTypeName[3] = "独立程序（jar、shell等）";

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
    $(".base-opt-btn-item").click(function () {
        $(".base-opt-btn-active").removeClass("base-opt-btn-active");
        $(this).addClass("base-opt-btn-active");
    });

    var setWdatePicker = function (dataCycle) {
        if (dataCycle == 1) {
            $("#updateTime").unbind('focus').focus(function () {
                WdatePicker({
                    dateFmt: 'HH:mm:ss', onpicked: function (dp) {
                        $dp.$('updateTime').blur();
                    }
                });
            });
        } else {
            $("#updateTime").unbind('focus').focus(function () {
                WdatePicker({
                    dateFmt: 'dd', onpicked: function (dp) {
                        $dp.$('updateTime').blur();
                    }
                });
            });
        }
    }

    $("#produceType").change(function () {
        var produceType = $("#produceType").val();
        if (produceType == 1) {
            $("#interface").hide();
            $("#interfaceCode").val("");
            $("#interfaceServerIp").val("");
            $("#interfaceFilePath").val("");

            $("#executor").hide();
            $("#executorServerIp").val("");
            $("#executorFilePath").val("");

            $("#task").show();
        } else if (produceType == 2) {
            $("#task").hide();
            $("#taskCode").val("");

            $("#executor").hide();
            $("#executorServerIp").val("");
            $("#executorFilePath").val("");

            $("#interface").show();
        } else {
            $("#task").hide();
            $("#taskCode").val("");

            $("#interface").hide();
            $("#interfaceCode").val("");
            $("#interfaceServerIp").val("");
            $("#interfaceFilePath").val("");

            $("#executor").show();
        }
    });

    $("#cancel").click(function () {
        $(".foundaods.edit").hide();
    });

    $("#post").click(function () {
        var cocSourceTableExt = {};
        cocSourceTableExt.sourceTableCode = $("#sourceTableCode").val();
        cocSourceTableExt.updateTime = $("#updateTime").val();
        cocSourceTableExt.producer = $("#producer").val();
        cocSourceTableExt.delayValue = $("#delayValue").val();
        cocSourceTableExt.produceType = $("#produceType").val();
        cocSourceTableExt.taskCode = $("#taskCode").val();
        cocSourceTableExt.interfaceCode = $("#interfaceCode").val();
        cocSourceTableExt.interfaceServerIp = $("#interfaceServerIp").val();
        cocSourceTableExt.interfaceFilePath = $("#interfaceFilePath").val();
        cocSourceTableExt.executorServerIp = $("#executorServerIp").val();
        cocSourceTableExt.executorFilePath = $("#executorFilePath").val();
        cocSourceTableExt.contactName = $("#contactName").val();
        cocSourceTableExt.contactTel = $("#contactTel").val();
        cocSourceTableExt.contactEmail = $("#contactEmail").val();
        cocSourceTableExt.comment = $("#comment").val();

        $.ajax({
            type: "POST",
            url: "./alarm/updateSourceTableExtInfo",
            contentType: 'application/json',
            data: JSON.stringify(cocSourceTableExt),
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    $(".foundaods.edit").hide();
                    alert(data.msg);
                    refresh(_dataCycle, _currentPage);
                } else {
                    alert(data.msg);
                }
            }
        });
    });

    var getTableDataStatus = function (dataStatus) {
        return _status[dataStatus];
    }

    var getTableDataStatusImg = function (dataStatus) {
        return "<img src=" + _images[dataStatus] + ">";
    }

    var getTableProduceTypeName = function (produceType) {
        return _produceTypeName[produceType];
    }

    //查看源表详情
    $(".base-opt-btn").on("click", ".base-opt-btn-item", function () {
        $(".base-opt-btn-active").removeClass("base-opt-btn-active");
        $(this).addClass("base-opt-btn-active");
        $("#tableSpaceName").val("");

        _dataCycle = $("input", this).val();
        refresh(_dataCycle, 1);
    });

    var showTableInfo = function (sourceTableCode) {
        $("#detail").empty();
        $.ajax({
            type: "GET",
            url: "./alarm/getSourceTableInfoByCode",
            dataType: "json",
            data: {"sourceTableCode": sourceTableCode},
            success: function (data) {
                if (data.code == 0) {
                    var cocSourceTable = data.data;

                    var $table = $("<table class='table table-bordered'></table>");
                    var $tr = $("<tr></tr>");
                    var $th = $("<th></th>");
                    var $td = $("<td></td>");

                    //基本信息
                    var $tmpTr = $tr.clone();
                    $tmpTr.append($th.clone().addClass("detail-th").attr("colspan", "4").text("基本信息"));
                    $table.append($tmpTr);

                    $tmpTr = $tr.clone();
                    $tmpTr.append($td.clone().addClass("detail-td-key").text("源表编码："));
                    $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.sourceTableCode));
                    $tmpTr.append($td.clone().addClass("detail-td-key").text("源表表名："));
                    $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.sourceTableName));
                    $table.append($tmpTr);

                    $tmpTr = $tr.clone();
                    $tmpTr.append($td.clone().addClass("detail-td-key").text("数据周期："));
                    $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.dataCycle == 1 ? "日" : "月"));
                    $tmpTr.append($td.clone().addClass("detail-td-key").text("最新数据日期："));
                    $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.dataDate));
                    $table.append($tmpTr);

                    $tmpTr = $tr.clone();
                    $tmpTr.append($td.clone().addClass("detail-td-key").text("数据状态："));
                    $tmpTr.append($td.clone().addClass("detail-td-value").attr("colspan", "3").text(getTableDataStatus(cocSourceTable.dataStatus)));
                    $table.append($tmpTr);

                    //源表生成信息
                    $tmpTr = $tr.clone();
                    $tmpTr.append($th.clone().addClass("detail-th").attr("colspan", "4").text("源表生成信息"));
                    $table.append($tmpTr);

                    if (cocSourceTable.dataStatus == 0) {
                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-center").attr("colspan", "4").text("未录入相关信息！"));
                        $table.append($tmpTr);
                    } else {
                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("源表生成时间："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.updateTime));
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("数据时效性："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text("T-" + cocSourceTable.cocSourceTableExt.delayValue + (cocSourceTable.dataCycle == 1 ? "日" : "月")));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("数据提供方："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.producer));
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("数据生成方式："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text(getTableProduceTypeName(cocSourceTable.cocSourceTableExt.produceType)));
                        $table.append($tmpTr);

                        if (cocSourceTable.cocSourceTableExt.produceType == 1) {//调度平台
                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("调度号："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").attr("colspan", "3").text(cocSourceTable.cocSourceTableExt.taskCode));
                            $table.append($tmpTr);
                        } else if (cocSourceTable.cocSourceTableExt.produceType == 2) {//外部接口
                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("接口号："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.interfaceCode));
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("接口机IP："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.interfaceServerIp));
                            $table.append($tmpTr);

                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("接口文件路径："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").attr("colspan", "3").text(cocSourceTable.cocSourceTableExt.interfaceFilePath));
                            $table.append($tmpTr);
                        } else {//独立程序
                            $tmpTr = $tr.clone();
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("程序部署主机IP："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.executorServerIp));
                            $tmpTr.append($td.clone().addClass("detail-td-key").text("程序部署路径："));
                            $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.executorFilePath));
                            $table.append($tmpTr);
                        }

                        //联系人信息
                        $tmpTr = $tr.clone();
                        $tmpTr.append($th.clone().addClass("detail-th").attr("colspan", "4").text("联系人信息"));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("联系人姓名："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.contactName));
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("联系人电话："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").text(cocSourceTable.cocSourceTableExt.contactTel));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-key").text("联系人电子邮箱："));
                        $tmpTr.append($td.clone().addClass("detail-td-value").attr("colspan", "3").text(cocSourceTable.cocSourceTableExt.contactEmail));
                        $table.append($tmpTr);

                        //备注
                        $tmpTr = $tr.clone();
                        $tmpTr.append($th.clone().addClass("detail-th").attr("colspan", "4").text("备注"));
                        $table.append($tmpTr);

                        $tmpTr = $tr.clone();
                        $tmpTr.append($td.clone().addClass("detail-td-value").attr("colspan", "4").text(cocSourceTable.cocSourceTableExt.comment));
                        $table.append($tmpTr);
                    }

                    $("#detail").append($table);

                    $(".foundaods.detail").show();
                } else {
                    alert(data.msg);
                }
            }
        });
    }

    //编辑源表信息
    var updateTableInfo = function (sourceTableCode) {
        document.getElementById("edit-form").reset();
        $.ajax({
            type: "GET",
            url: "./alarm/getSourceTableInfoByCode",
            dataType: "json",
            data: {"sourceTableCode": sourceTableCode},
            success: function (data) {
                if (data.code == 0) {
                    var cocSourceTable = data.data;

                    $("#sourceTableCode").val(sourceTableCode);
                    $("#sourceTableCodeP").html(sourceTableCode);
                    $("#sourceTableNameP").html(cocSourceTable.sourceTableName);

                    if (cocSourceTable.cocSourceTableExt != null) {
                        $("#updateTime").val(cocSourceTable.cocSourceTableExt.updateTime);
                        $("#producer").val(cocSourceTable.cocSourceTableExt.producer);
                        $("#delayValue").val(cocSourceTable.cocSourceTableExt.delayValue);
                        $("#produceType").val(cocSourceTable.cocSourceTableExt.produceType);

                        if (cocSourceTable.cocSourceTableExt.produceType == 1) {
                            $("#interface").hide();
                            $("#executor").hide();
                            $("#task").show();

                            $("#taskCode").val(cocSourceTable.cocSourceTableExt.taskCode);
                        } else if (cocSourceTable.cocSourceTableExt.produceType == 2) {
                            $("#task").hide();
                            $("#executor").hide();
                            $("#interface").show();

                            $("#interfaceCode").val(cocSourceTable.cocSourceTableExt.interfaceCode);
                            $("#interfaceServerIp").val(cocSourceTable.cocSourceTableExt.interfaceServerIp);
                            $("#interfaceFilePath").val(cocSourceTable.cocSourceTableExt.interfaceFilePath);
                        } else {
                            $("#task").hide();
                            $("#interface").hide();
                            $("#executor").show();

                            $("#executorServerIp").val(cocSourceTable.cocSourceTableExt.executorServerIp);
                            $("#executorFilePath").val(cocSourceTable.cocSourceTableExt.executorFilePath);
                        }

                        $("#contactName").val(cocSourceTable.cocSourceTableExt.contactName);
                        $("#contactTel").val(cocSourceTable.cocSourceTableExt.contactTel);
                        $("#contactEmail").val(cocSourceTable.cocSourceTableExt.contactEmail);
                        $("#comment").val(cocSourceTable.cocSourceTableExt.comment);
                    }

                    setWdatePicker(cocSourceTable.dataCycle);

                    $(".foundaods.edit").show();
                } else {
                    alert(data.msg);
                }
            }
        });
    }

    /**详情模态框的关闭**/
    $("#closea").click(function () {
        $(".foundaods.edit").hide();
    });
    $("#ods-close").click(function () {
        $(".foundaods.detail").hide();
    });

    //查询框动态查询
    $(".base-opt-qry-txt").keyup(function () {
        refresh(_dataCycle, 1);
    });

    //列表分页
    var refresh = function (dataCycle, currentPage) {
        _sourceTableName = $.trim($("#sourceTableName").val());
        $.ajax({
            type: "GET",
            url: "./alarm/getSourceTableInfo",
            dataType: "json",
            data: {
                "dataCycle": dataCycle,
                "currentPage": currentPage,
                "pageSize": _pageSize,
                "sourceTableName": _sourceTableName
            },
            success: function (result) {
                if (result.code != 0) {
                    alert(result.msg);
                    return;
                }
                var data = result.data.dataList;
                var $tr = $("<tr></tr>");
                var $td = $("<td></td>");
                var $img = $("<img>");
                $("#tbody").empty();
                if (data.length == 0) {
                    var $trTmp = $tr.clone();
                    $trTmp.append($td.clone().attr("colspan", "5").text("暂无数据"));
                } else {
                    $.each(data, function (k, v) {
                        var $trTmp = $tr.clone();

                        $trTmp.append($td.clone().text(v.sourceTableCode));
                        $trTmp.append($td.clone().text(v.sourceTableName));
                        $trTmp.append($td.clone().text(v.dataCycle == 1 ? "日" : "月"));
                        $trTmp.append($td.clone().text(v.dataDate));
                        $trTmp.append($td.clone().append(getTableDataStatusImg(v.dataStatus)));

                        var $tdLast = $td.clone();
                        $tdLast.append($img.clone().addClass("detailImg").attr("src", "./images/icon_detials.png").bind("click", function () {
                            showTableInfo(v.sourceTableCode);
                        }));
                        $tdLast.append($img.clone().addClass("editImg").attr("src", "./images/icon_revise1.png").bind("click", function () {
                            updateTableInfo(v.sourceTableCode);
                        }));

                        $trTmp.append($tdLast);

                        $("#tbody").append($trTmp);
                    });
                }
                $("#totalnum").text(result.data.totalRecord);

                //分页初始化
                $("#pageTool").html("");
                $("#pageTool").Paging({
                    pagesize: _pageSize,
                    count: result.data.totalRecord,
                    current: currentPage,
                    callback: function (currentPage) {
                        _currentPage = currentPage;
                        refresh(dataCycle, currentPage);
                    }
                });

            }
        });
    };
    refresh(0, 1);
});
