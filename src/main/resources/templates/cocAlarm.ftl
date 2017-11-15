<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>经分运维一体化平台-标签库监控</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="./css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/baseMonitor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="./js/public/html5shiv.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/public/respond.min.js" charset="utf-8"></script>
    <![endif]-->
    <style>
        .table_content {
            margin: 20px auto;
            width: 900px;
            z-index: 1000;
            background: #fff;
            outline: 1px solid #EAEAEA;
        }

        .form_content {
            margin: 30px auto;
            width: 900px;
            z-index: 1000;
            background: #fff;
            outline: 1px solid #EAEAEA;
        }

        .detailImg {
            margin-top: 1px;
        }

        .editImg {
            margin-left: 12px;
        }

        #detail, #edit {
            padding: 20px;
        }

        .detail, .edit {
            z-index: 3;
        }

        .detail-th {
            text-align: left;
        }

        .detail-td-key {
            text-align: right;
        }

        .detail-td-value {
            text-align: left;
        }

        .detail-td-center {
            text-align: center;
            color: red;
        }

        .init-hide {
            display: none;
        }

        #updateTime {
            height: 34px !important;
            cursor: auto !important;
            border: 1px solid #ccc !important;
            background: #fff url(./images/data.png) no-repeat right !important;
        }
    </style>
</head>
<body>
<!-- Begin page content -->
<div class="row nomargin">
    <div class="page-left">
    <#include "/header.ftl">
    </div>
    <div class="page-right">
        <!--页面内容头部 start-->
        <div class="base-header">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <div class="base-header-item base-header-item-active" onclick="location.href='cocAlarm'">
                    <img src="./images/icon_lib01.png">
                    <span>源表监控</span>
                </div>
                <div class="base-header-item" onclick="location.href='labelMonitor_TAG'">
                    <img src="./images/icon_tag01.png">
                    <span>标签监控</span>
                </div>
                <div style="clear:both;"></div>
            </div>
            <div class="col-md-4"></div>
        </div>
        <!--页面内容头部 end-->

        <!--页面内容操作栏 start-->
        <div class="base-opt">
            <div class="col-md-6">
                <div class="btn-group base-opt-btn" data-toggle="buttons" id="baseoptbtn">
                    <label class="btn base-opt-btn-item base-opt-btn-active">
                        <input type="radio" value="0">全部
                    </label>
                    <label class="btn base-opt-btn-item">
                        <input type="radio" value="1">日表
                    </label>
                    <label class="btn base-opt-btn-item">
                        <input type="radio" value="2">月表
                    </label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="base-opt-qry">
                    <input class="base-opt-qry-txt" type="text" placeholder="搜索源表名称" id="sourceTableName"><img
                        src="./images/icon_search.png"/></input>
                <#--<button class="base-opt-qry-btn" id="refresh"><img src="./images/icon_d.png"/>刷新</button>-->
                </div>
            </div>
        </div>
        <!--页面内容操作栏 end-->

        <!--页面内容标示栏 start-->
        <div class="base-label">
            <div class="col-md-3">
                <div class="base-label-left">
                    <img src="./images/icon_line.png"/>
                    <span><span id="navBtn"></span>源表列表(<span id="totalnum">0</span>)</span>
                </div>
            </div>
        </div>
        <!--页面内容标示栏 end-->

        <!--页面内容表格 start-->
        <div class="base-table">
            <div>
                <table class="base-table-tab">
                    <thead>
                    <tr>
                        <th>源表编码</th>
                        <th>源表表名</th>
                        <th>数据周期</th>
                        <th>最新数据日期</th>
                        <th>数据状态</th>
                        <th class="last-th">操作</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">
                    </tbody>
                </table>
            </div>
            <div id="pageTool" class="anniuBox" style="text-align: right;">
            </div>
            <!--页面内容表格 end-->
        </div>
        <div style="clear:both;"></div>
    </div>

    <!--详情-->
    <div class="foundaods detail">
        <div class="table_content">

            <div class="found_top">
                <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
                源表详情
                <button type="button" class="close" id="ods-close" style="margin:10px">
                    <img src="./images/close.png" alt="">
                </button>
            </div>
            <div id="detail">
            <#--<table class="table table-bordered">
                    <thead>
                    <tr>
                        <th width="100%" colspan="4" style="text-align: left;padding-left: 26px;">基本信息</th>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">源表编码：</td>
                        <td width="20%" style="text-align: left;">节点名称</td>
                        <td width="20%" style="text-align: right;">源表表名：</td>
                        <td width="20%" style="text-align: left;">xxx.xxxxxxx_xxx</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">数据周期：</td>
                        <td width="20%" style="text-align: left;">日</td>
                        <td width="20%" style="text-align: right;">最新数据日期：</td>
                        <td width="20%" style="text-align: left;">20171111</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">数据状态：</td>
                        <td width="20%" colspan="3" style="text-align: left;">未知</td>
                    </tr>
                    <tr>
                        <th width="100%" colspan="4" style="text-align: left;padding-left: 26px;">源表生成信息</th>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">源表生成时间：</td>
                        <td width="20%" style="text-align: left;">每月15日</td>
                        <td width="20%" style="text-align: right;">数据时效性：</td>
                        <td width="20%" style="text-align: left;">t-1</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">数据提供方：</td>
                        <td width="20%" style="text-align: left;">亚信</td>
                        <td width="20%" style="text-align: right;">数据生成方式：</td>
                        <td width="20%" style="text-align: left;">外部接口</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">接口号：</td>
                        <td width="20%" style="text-align: left;">S_000001</td>
                        <td width="20%" style="text-align: right;">接口机ip：</td>
                        <td width="20%" style="text-align: left;">192.168.0.1</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">接口文件路径：</td>
                        <td width="20%" colspan="3" style="text-align: left;">
                            /data1/scbass/interface/erer_yyyymmdd.txt
                        </td>
                    </tr>
                    <tr>
                        <th width="100%" colspan="4" style="text-align: left;padding-left: 26px;">联系人信息</th>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">联系人姓名：</td>
                        <td width="20%" style="text-align: left;">Google</td>
                        <td width="20%" style="text-align: right;">联系人电话：</td>
                        <td width="20%" style="text-align: left;">13800138000</td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align: right;">联系人电子邮箱：</td>
                        <td width="20%" colspan="3" style="text-align: left;">test@outlook.com</td>
                    </tr>
                    </thead>
                </table>-->
            </div>
        </div>
    </div>
<#--编辑源表信息-->
    <div class="foundaods edit">
        <div class="form_content">

            <div class="found_top">
                <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
                编辑源表详情
                <button type="button" class="close" id="closea" style="margin:10px">
                    <img src="./images/close.png" alt="">
                </button>
            </div>
            <div id="edit" style="width: 90%;margin: auto;">
                <form id="edit-form" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="sourceTableCode" class="col-sm-2 control-label">源表编码</label>
                        <div class="col-sm-4">
                            <input type="hidden" class="form-control" id="sourceTableCode">
                            <p class="form-control-static" id="sourceTableCodeP"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">源表表名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="sourceTableNameP"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="updateTime" class="col-sm-2 control-label">源表生成时间</label>
                        <div class="col-sm-4">
                            <input type="text" readOnly="readonly" class="form-control Wdate" id="updateTime">
                        </div>
                        <label for="producer" class="col-sm-2 control-label">数据提供方</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="producer">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="delayValue" class="col-sm-2 control-label">数据时效性</label>
                        <div class="col-sm-4">
                            <select id="delayValue" class="form-control">
                                <option value="1">T-1</option>
                                <option value="2">T-2</option>
                            </select>
                        </div>
                        <label for="produceType" class="col-sm-2 control-label">数据生成方式</label>
                        <div class="col-sm-4">
                            <select id="produceType" class="form-control">
                                <option value="1">调度平台</option>
                                <option value="2">外部接口</option>
                                <option value="3">独立程序</option>
                            </select>
                        </div>
                    </div>

                    <div id="task">
                        <div class="form-group">
                            <label for="taskCode" class="col-sm-2 control-label">调度号</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="taskCode">
                            </div>
                        </div>
                    </div>

                    <div id="interface" class="init-hide">
                        <div class="form-group">
                            <label for="interfaceCode" class="col-sm-2 control-label">接口号</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="interfaceCode">
                            </div>
                            <label for="interfaceServerIp" class="col-sm-2 control-label">接口机IP</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="interfaceServerIp">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="interfaceFilePath" class="col-sm-2 control-label">接口文件路径</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="interfaceFilePath">
                            </div>
                        </div>
                    </div>

                    <div id="executor" class="init-hide">
                        <div class="form-group">
                            <label for="executorServerIp" class="col-sm-2 control-label">程序部署主机IP</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="executorServerIp">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="executorFilePath" class="col-sm-2 control-label">程序部署路径</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="executorFilePath">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="contactName" class="col-sm-2 control-label">联系人姓名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="contactName">
                        </div>
                        <label for="contactTel" class="col-sm-2 control-label">联系人电话</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="contactTel">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contactEmail" class="col-sm-2 control-label">联系人电子邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="contactEmail">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="comment" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="comment" rows="2"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-9 col-sm-3">
                            <button id="cancel" type="button" class="btn btn-default">取消</button>
                            <button id="post" type="button" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="./js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/public/query.js"></script>
<script type="text/javascript" src="./js/public/paging.js"></script>
<script type="text/javascript" src="./js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="./js/header.js"></script>
<script type="text/javascript" src="./js/cocAlarm.js"></script>
</body>
</html>