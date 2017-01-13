$(function () {

    $("#birthday").datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose: true //选择日期后自动关闭
    }).on('hide', function (e) {
        $('#addForm')
            .data('bootstrapValidator')
            .updateStatus('birthday', 'NOT_VALIDATED', null)
            .validateField('birthday');
    });

    //提交
    $("#btn_save").on('click', function () {
        var bootstrapValidator = $('#addForm').data('bootstrapValidator');
        alert(bootstrapValidator.isValid());
        return;
        if (!bootstrapValidator.isValid()) {
            return;
        }
        $.ajax({
            async: false,
            url: $ctx + '/student/saveStudent',
            type: "POST",
            data: $('#addForm').serialize(),
            dataType: "json",
            success: function (result) {
                if (result.flag == '0') {
                    parent.location.reload();
                }
                else {
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;color:#666;">错误信息</div>',
                            content: '<div style="font-size:15px;color:#666;">' + result.message + '</div>',
                            yesFn: false,
                            noFn: true
                        }
                    });
                }
            }
        });
    });

    $('#addOrEditModal').on('hidden.bs.modal', function () {
        $("#addForm").data('bootstrapValidator').destroy();
        $('#addForm').data('bootstrapValidator', null);
        formValidator();
    });

    //初始化列表
    $("#empStudentList").bootstrapTable({
        url: $ctx + '/student/queryStudentByName',
        height: '500',
        undefinedText: '-',
        pagination: true, // 分页
        striped: true, // 是否显示行间隔色
        queryParamsType: "",
        queryParams: queryParams,
        cache: false, // 是否使用缓存
        pageList: [5, 10, 20],
        toolbar: "#toolbar",// 指定工具栏
        showColumns: true, // 显示隐藏列
        showRefresh: true, // 显示刷新按钮
        uniqueId: "sex", // 每一行的唯一标识
        sidePagination: "server", // 服务端处理分页
        columns: [{
            checkbox: true,
            align: 'center',
            valign: 'middle'
        }, {
            title: '姓名',
            field: 'name', // 字段
            align: 'center', // 对齐方式（左 中 右）
            valign: 'middle', //
            sortable: true
        }, {
            title: '性别',
            field: 'sex',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: function (value, row, index) {
                if (value == '1') {
                    return "男";
                }
                return "女";
            }
        }, {
            title: '生日',
            field: 'birthday',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: '出生地',
            field: 'birthplace',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: '手机号',
            field: 'telephone',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: '操作',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return '<a href="#" mce_href="#" onclick="editStudentInfo(\'' + index + '\')">编辑</a>';
            }
        }],
        responseHandler: function (res) {
            return {
                total: res.total,
                rows: res.dataList
            };
        }
    });

    //查询
    $('#btn_query').on('click', function () {
        $("#empStudentList").bootstrapTable('refresh', {url: $ctx + '/student/queryStudentByName'});
    });

    //删除信息
    $('#btn_delete').on('click', function () {
        var empStudentList = $('#empStudentList').bootstrapTable('getSelections');
        if (empStudentList.length == 0) {
            easyDialog.open({
                container: {
                    header: '<div style="font-size:15px;color:#666;">提示信息</div>',
                    content: '<div style="font-size:15px;color:#666;">请选择删除项！</div>',
                    yesFn: false,
                    noFn: true
                }
            });
            return;
        }
        var ids = new Array();
        $.each(empStudentList, function (index, element) {
            ids.push(element.id);
        });
        $.ajax({
            async: false,
            url: $ctx + '/student/delStudent',
            type: "POST",
            data: {
                ids: ids.toString()
            },
            dataType: "json",
            success: function (result) {
                if (result.flag == '0') {
                    parent.location.reload();
                }
                else {
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;color:#666;">错误信息</div>',
                            content: '<div style="font-size:15px;color:#666;">' + result.message + '</div>',
                            yesFn: false,
                            noFn: true
                        }
                    });
                }
            }
        });
    })
});

/**
 *
 */
function initTable() {

}
/** 查询条件 */
function queryParams(pageReqeust) {
    pageReqeust.name = $("#txt_search_name").val();
    return pageReqeust;
}

function addStudentInfo() {
    $('#addForm')[0].reset();
    $('#addOrEditModal').modal('show');
}
/**
 * 弹出编辑信息页面
 */
function editStudentInfo(index) {
    var empStudentList = $('#empStudentList').bootstrapTable('getData');
    $('#id').val(empStudentList[index].id);
    $('#name').val(empStudentList[index].name);
    $('#birthday').val(empStudentList[index].birthday);
    $('#telephone').val(empStudentList[index].telephone);
    $('#birthplace').val(empStudentList[index].birthplace);

    $('#addOrEditModal').modal('show');
}

/**
 * 表单验证
 */
function formValidator() {
    $('#addForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            birthday: {
                validators: {
                    notEmpty: {
                        message: '生日不能为空'
                    }
                }
            },
            birthplace: {
                validators: {
                    notEmpty: {
                        message: '出生地不能为空'
                    }
                }
            },
            telephone: {
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|7|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            }
        }
    });
}