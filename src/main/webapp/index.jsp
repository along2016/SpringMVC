<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>

    <link href="${ctx}/thirdparty/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .projects-header {
            width: 60%;
            text-align: center;
            margin: 60px auto 40px auto;
            font-weight: 200;
            display: block;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="projects-header page-header">
        <h2>JavaWeb相关项目学习</h2>
        <p>这些模块是通过JavaWeb相关的框架进行开发的，旨在进行JavaWeb方面的学习</p>
    </div>

    <div class="col-sm-6 col-md-4 col-lg-3">
        <div class="thumbnail" style="height: 336px; text-align: center;">
            <a href="${ctx }/student/list" title="学生列表">
                <img class="lazy" src="images/student.png"
                     alt="学生列表" style="padding-top: 10px;">
            </a>
            <div class="caption">
                <h3>
                    <a style="text-decoration: none;" href="${ctx }/student/list" title="学生列表">
                        学生列表
                        <br>
                        <small>SpringMVC基本功能实例</small>
                    </a>
                </h3>
                <p>该模块的后台采用SpringMVC框架，前台页面则采用BootStrap及其相关框架的最新版本进行编写的。</p>
            </div>
        </div>
    </div>

    <div class="col-sm-6 col-md-4 col-lg-3">
        <div class="thumbnail" style="height: 336px; text-align: center;">
            <a href="${ctx }/student/list" title="学生列表">
                <img class="lazy" src="images/student.png"
                     alt="学生列表" style="padding-top: 10px;">
            </a>
            <div class="caption">
                <h3>
                    <a style="text-decoration: none;" href="${ctx }/student/list" title="学生列表">
                        学生列表
                        <br>
                        <small>SpringMVC基本功能实例</small>
                    </a>
                </h3>
                <p>该模块的后台采用SpringMVC框架，前台页面则采用BootStrap及其相关框架的最新版本进行编写的。</p>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/thirdparty/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>