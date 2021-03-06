<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <metacharset
    ="utf-8">
    <metahttp-equiv
    ="X-UA-Compatible"content="IE=edge">
    <metaname
    ="viewport"content="width=device-width, initial-scale=1">
    <title>经分运维一体化平台-基础监控</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="./css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="./css/header.css">
    <link rel="stylesheet" type="text/css" href="./css/baseMonitor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="./js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="./js/public/respond.min.js"></script>
    <![endif]-->
    <style>

        .labelDetail {
            z-index: 3;
        }

        .table_content {
            margin: 20px auto;
            width: 900px;
            z-index: 1000;
            background: #fff;
            outline: 1px solid #EAEAEA;
        }

        .label_content {
            margin: 20px auto;
            width: 900px;
            z-index: 1000;
            background: #fff;
            outline: 1px solid #EAEAEA;
        }

        .labelDetailImg {
            margin-top: 1px;
        }

        .labelEditImg {
            margin-left: 12px;
        }

        #labelDetail, #edit {
            padding: 20px;
        }

        .labelDetail, .edit {
            z-index: 3;
        }

        .Basic-th {
            text-align: left;
        }

        .Basic-td-key {
            text-align: right;
        }

        .Basic-td-value {
            text-align: left;
        }

        .Basic-td-center {
            text-align: center;
            color: red;
        }

        .init-hide {
            display: none;
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
            <#--<div style="display: inline-block;align-items: center;">-->
            <#-- <div class="base-header-item" onclick="location.href='labelMonitor'">
                 <img src="./images/icon_pool01.png">
                 <span>汇总</span>
             </div>-->
                <div class="base-header-item" onclick="location.href='cocAlarm'">
                    <img src="./images/icon_lib01.png">
                    <span>源表监控</span>
                </div>
                <!--<div class="base-header-item">
                  <img src="./images/icon_a.png">
                  <span>主机性能</span>
               </div>-->
                <div class="base-header-item base-header-item-active" onclick="location.href='labelMonitor_TAG'">
                    <img src="./images/icon_tag01.png">
                    <span>标签监控</span>
                </div>
            <#--<div class="base-header-item"  onclick="location.href='baseMonitor_FILE'">-->
            <#--<img src="./images/icon_a.png">-->
            <#--<span>文件系统监控</span>-->
            <#--</div>-->
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
                        <input type="radio" dataCycle="0">全部</input>
                    </label>
                    <label class="btn base-opt-btn-item">
                        <input type="radio" dataCycle="1">日</input>
                    </label>
                    <label class="btn base-opt-btn-item ">
                        <input type="radio" dataCycle="2">月</input>
                    </label>
                </div>
                <!--<select class="base-opt-sel">
                    <option value="all">全部</option>
                    <option value="1">明细1</option>
                    <option value="2">明细2</option>
                    <option value="3">明细3</option>
                </select>-->
            </div>
            <div class="col-md-6">
                <div class="base-opt-qry">
                <#--<select class="base-opt-qry-sel" data-type="1" id="select_button1">-->
                <#--<option value="all">全部</option>-->
                <#--<option value="day">日接口</option>-->
                <#--<option value="mon">月接口</option>-->
                <#--</select>-->
                <#--<select class="base-opt-qry-sel" data-type="2" id="select_button2">-->
                <#--<option value="all">全部接口</option>-->
                <#--<option value="imp">重要接口</option>-->
                <#--<option value="4g">4G彩信接口</option>-->
                <#--<option value="base">基础接口</option>-->
                <#--</select>-->
                    <input class="base-opt-qry-txt" type="text" placeholder="搜索标签名称" id="labelName"><img
                        src="./images/icon_search.png"/></input>
                    <button class="base-opt-qry-btn" id="refresh"><img src="./images/icon_d.png"/>刷新</button>
                </div>
            </div>
        </div>
        <!--页面内容操作栏 end-->

        <!--页面内容标示栏 start-->
        <div class="base-label">
            <div class="col-md-3">
                <div class="base-label-left">
                    <img src="./images/icon_line.png"/>
                    <span><span id="navBtn"></span>标签列表(<span id="totalRecord">0</span>)</span>
                </div>
            </div>
        <#--<div class="col-md-9">-->
        <#--<div class="base-label-right">-->
        <#--<ul>-->
        <#--<li><img src="./images/icon_h.png"/><span>正常</span>(<span id="normal"-->
        <#--class="spanclass">0</span>条)-->
        <#--</li>-->
        <#--<li><img src="./images/icon_i.png"/><span>延迟异常</span>(<span id="delay"-->
        <#--class="spanclass">0</span>条)-->
        <#--</li>-->
        <#--<li><img src="./images/icon_j.png"/><span>波动异常</span>(<span id="waved"-->
        <#--class="spanclass">0</span>条)-->
        <#--</li>-->
        <#--</ul>-->
        <#--</div>-->
        <#--</div>-->
        </div>
        <!--页面内容标示栏 end-->

        <!--页面内容表格 start-->
        <div class="base-table">
            <div>
                <table class="base-table-tab">
                    <thead>
                    <tr>
                        <th width="5%">状态</th>
                        <th width="20%">标签名</th>
                        <th width="5%">数据周期</th>
                        <th width="10%">数据日期</th>
                        <th width="10%">数据量</th>
                        <th width="5%">数据量波动</th>
                        <th width="5%">环比</th>
                        <th width="10%">对应源表</th>
                        <th class="last-th" width="10%">操作</th>
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
    <div class="foundaods labelDetail">
        <div class="table_content">

            <div class="found_top">
                <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
                标签详情
                <button type="button" class="close" id="label-detailClose" style="margin:10px">
                    <img src="./images/close.png" alt="">
                </button>
            </div>
            <div id="labelDetail">

            </div>
        </div>
    </div>

<#--编辑源表信息-->

    <div class="foundaods edit">
        <div class="label_content">

            <div class="found_top">
                <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
                编辑标签详情
                <button type="button" class="close" id="label-EditClose" style="margin:10px">
                    <img src="./images/close.png" alt="">
                </button>
            </div>
            <div id="edit" style="width: 90%;margin: auto;">
                <form id="edit-form" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="labelId" class="col-sm-2 control-label">标签ID</label>
                        <div class="col-sm-4">
                            <input type="hidden" class="form-control" id="labelId">
                            <p class="form-control-static" id="labelIdP"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">标签名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="labelNameP"></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="busiCaliber" class="col-sm-2 control-label">标签口径</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="busiCaliber">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-9 col-sm-3">
                            <button id="cancel" type="button" class="btn btn-default">取消</button>
                            <button id="post" type="button" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                <#--<div class="form-group">-->
                <#--<label for="sourceTableCode" class="col-sm-2 control-label">源表编码</label>-->
                <#--<div class="col-sm-4">-->
                <#--<input type="hidden" class="form-control" id="sourceTableCode">-->
                <#--<p class="form-control-static" id="sourceTableCodeP"></p>-->
                <#--</div>-->
                <#--</div>-->

                </form>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="./js/public/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="./js/public/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/public/query.js"></script>
    <script type="text/javascript" src="./js/public/paging.js"></script>
    <script type="text/javascript" src="./js/labelMonitor_TAG.js"></script>
    <script type="text/javascript" src="./js/header.js"></script>
</body>
</html>