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
    <link rel="stylesheet" type="text/css" href="../css/public/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/public/paging.css">
    <link rel="stylesheet" type="text/css" href="../css/header.css">
    <link rel="stylesheet" type="text/css" href="../css/baseMonitor.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../js/public/html5shiv.min.js"></script>
    <script type="text/javascript" src="../js/public/respond.min.js"></script>
    <![endif]-->
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
            <div class="col-md-7">
        <#--<div style="display: inline-block;align-items: center;">-->
            <div class="base-header-item" onclick="location.href='labelMonitor'">
                <img src="../images/icon_pool01.png" >
                <span>汇总</span>
            </div>
            <div class="base-header-item base-header-item-active" >
                <img src="../images/icon_lib01.png">
                <span>源表</span>
            </div>
            <!--<div class="base-header-item">
              <img src="../images/icon_a.png">
              <span>主机性能</span>
           </div>-->
            <div class="base-header-item" onclick="location.href='labelMonitor_TAG'">
                <img src="../images/icon_tag01.png">
                <span>标签</span>
            </div>
        <#--<div class="base-header-item"  onclick="location.href='baseMonitor_FILE'">-->
        <#--<img src="../images/icon_a.png">-->
        <#--<span>文件系统监控</span>-->
        <#--</div>-->
            <div style="clear:both;"></div>
        </div>
        <div class="col-md-3"></div>
    </div>
    <!--页面内容头部 end-->

    <!--页面内容操作栏 start-->
    <div class="base-opt">
        <div class="col-md-6">
            <div class="btn-group base-opt-btn" data-toggle="buttons" id="baseoptbtn">

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
                <input class="base-opt-qry-txt" type="text" placeholder="搜索源表名称" id="sourceTabName"><img
                    src="../images/icon_search.png"/></input>
                <button class="base-opt-qry-btn" id="refresh"><img src="../images/icon_d.png"/>刷新</button>
            </div>
        </div>
    </div>
    <!--页面内容操作栏 end-->

    <!--页面内容标示栏 start-->
    <div class="base-label">
        <div class="col-md-3">
            <div class="base-label-left">
                <img src="../images/icon_line.png"/>
                <span><span id="navBtn"></span>源表列表(<span id="totalnum">0</span>)</span>
            </div>
        </div>
        <div class="col-md-9">
            <div class="base-label-right">
                <ul>
                    <li><img src="../images/icon_h.png"/><span>加载成功</span>(<span id="success"
                                                                                 class="spanclass">0</span>条)
                    </li>
                    <li><img src="../images/icon_i.png"/><span>加载失败</span>(<span id="fail"
                                                                                 class="spanclass">0</span>条)
                    </li>
                    <li><img src="../images/icon_j.png"/><span>波动异常</span>(<span id="wavestatus"
                                                                                 class="spanclass">0</span>条)
                    </li>
                    <li><img src="../images/icon_k.png"/><span>进行中</span>(<span id="process"
                                                                                class="spanclass">0</span>条)
                    </li>
                    <li><img src="../images/icon_l.png"/><span>未开始</span>(<span id="notstart"
                                                                                class="spanclass">0</span>条)
                    </li>
                    <li><img src="../images/icon_g.png"/><span>延迟</span>(<span id="timeout"
                                                                               class="spanclass">0</span>条)
                    </li>
                </ul>
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
                    <th width="5%">状态</th>
                    <th width="15%">源表名</th>
                    <th width="15%">数据日期</th>
                    <th width="15%">数据量</th>
                    <th width="15%">数据量波动</th>
                    <th width="10%">环比</th>
                    <th width="5%">调度名</th>
                    <th width="5%">程序名</th>
                    <th width="10%">到达时间</th>
                    <th class="last-th" width="5%">详情</th>
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
<div class="foundaods pa">
    <div class="ods_content">

        <div class="found_top">
            <div class="jishd" style="background-color:#fff; margin-left: 20px; "></div>
            接口加载详情
            <button type="button" class="close" id="ods-close" style="margin:10px">
                <img src="./static/images/close.png" alt="">
            </button>
        </div>
        <div class="dska">
            <div class="load">
                <div class="load-img interStatus"></div>
                <div class="load-name interName">m030001</div>
                <div class="load-state result"></div>
            </div>
            <div class="cause">原因：<span class="odsWarningReason detail" title=""></span></div>
            <div class="conduct-ods">
                <div class="conduct-ods1">表名</div>
                <div class="conduct-ods2 tableName"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">开始时间</div>
                <div class="conduct-ods2 beginDate"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">实际完成时间</div>
                <div class="conduct-ods2 actualFinDate"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">要求完成时间</div>
                <div class="conduct-ods2 demainDate"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">到达时间</div>
                <div class="conduct-ods2 arriveDate"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">条数</div>
                <div class="conduct-ods2 sumMum"></div>
            </div>
            <div class="conduct-ods">
                <div class="conduct-ods1">与上月波动性</div>
                <div class="conduct-ods2 waveNum"></div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/query.js"></script>
<script type="text/javascript" src="../js/public/paging.js"></script>
<script type="text/javascript" src="../js/labelMonitor_SOURCE.js"></script>
<script type="text/javascript" src="../js/header.js"></script>
</body>
</html>