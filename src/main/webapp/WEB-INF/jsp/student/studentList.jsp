<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生信息</title>

    <!-- Bootstrap -->
    <link href="${ctx}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/thirdparty/bootstrap/css/bootstrap-table.min.css" rel="stylesheet"/>
    <link type="text/css" href="${ctx}/thirdparty/bootstrap/css/bootstrapValidator.min.css"
          rel="stylesheet"/>
    <link type="text/css" href="${ctx}/thirdparty/bootstrap/css/bootstrap-datetimepicker.css"
          rel="stylesheet"/>
    <link type="text/css" href="${ctx}/thirdparty/easydialog/easydialog.css"
          rel="stylesheet"/>
</head>
<body>
<div class="panel-body" style="padding-bottom: 0px;">
    <div class="panel panel-default">
        <div class="panel-heading">查询条件</div>
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top: 15px">
                    <label class="control-label col-sm-1"
                           for="txt_search_name">姓名</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control"
                               id="txt_search_name">
                    </div>
                    <div class="col-sm-4" style="text-align: left;">
                        <button type="button" id="btn_query"
                                class="btn btn-primary">查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default"
                onclick="addStudentInfo()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>
    <table id="empStudentList"></table>

    <!-- 添加弹出框 -->
    <div class="modal fade" id="addOrEditModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="container-fluid">
                    <form id="addForm" class="form-horizontal">、
                        <input id="id" name="id" type="hidden"/>
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span><span
                                    class="sr-only">关闭</span>
                            </button>
                            <h4 class="modal-title">学生信息</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>
                                <div class="col-sm-5">
                                    <input name="name" id="name" class="form-control" placeholder="请输入您的真实姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">性别</label>
                                <div class="col-sm-10">
                                    <label class="checkbox-inline">
                                        <input type="radio" name="sex" value="1">男
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="radio" name="sex" value="2">女
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">生日</label>
                                <div class="col-sm-5">
                                    <input class="form-control" id="birthday" name="birthday"
                                           type="text" readonly size="16">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">手机号</label>
                                <div class="col-sm-5">
                                    <input name="telephone" id="telephone" class="form-control"
                                           placeholder="请输入手机号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">出生地</label>
                                <div class="col-sm-10">
                                    <input name="birthplace" id="birthplace" class="form-control"
                                           placeholder="请输入您的出生地">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button type="submit" id="btn_save" class="btn btn-primary">提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/thirdparty/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${ctx }/thirdparty/bootstrap/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
        src="${ctx }/thirdparty/bootstrap/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    var $ctx = "${ctx }";
</script>
<script type="text/javascript"
        src="${ctx}/thirdparty/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
        src="${ctx}/thirdparty/bootstrap/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${ctx}/thirdparty/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/thirdparty/easydialog/easydialog.min.js"></script>
<script type="text/javascript" src="${ctx }/js/student/studentList.js"></script>
</body>
</html>