$(function () {

    //初始化列表
    initTable();

    //验证表单
    initFormValidator();

    //初始化时间插件
    initDatetimepicker();

    //提交
    $("#btn_save").on('click', function () {
        saveData();
    });

    //删除信息
    $('#btn_delete').on('click', function () {
        deleteData();
    });

    $('#addOrEditModal').on('hidden.bs.modal', function () {
        $("#addForm").data('bootstrapValidator').destroy();
    });

    $('#addOrEditModal').on('show.bs.modal', function () {
        initFormValidator();
    });

    //查询
    $('#btn_query').on('click', function () {
        $("#empStudentList").bootstrapTable('refresh', {url: $ctx + '/student/queryStudentByName'});
    });

    $("#birthplace").AMapPositionPicker();

});

/**
 * 初始化列表数据
 */
function initTable() {
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
}

/**
 * 保存信息
 */
function saveData() {
    //如果按钮的类型为button（非submit），需要手动对表单进行校检
    $('#addForm').data('bootstrapValidator').validate();
    var bootstrapValidator = $('#addForm').data('bootstrapValidator');
    var id = $("#id").val();
    if (!bootstrapValidator.isValid() && id == '') {
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
            } else {
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
}
/**
 * 删除信息
 */
function deleteData() {
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
}
/**
 * 初始化时间插件
 */
function initDatetimepicker() {
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
}
/** 查询条件 */
function queryParams(pageReqeust) {
    pageReqeust.name = $("#txt_search_name").val();
    return pageReqeust;
}

function addStudentInfo() {
    $('#addForm')[0].reset();
    $('#id').val('');
    $("input[name='sex']").eq(0).attr("checked",'checked');
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
    $('#birthplace').val(empStudentList[index].birthplace);
    $('#telephone').val(empStudentList[index].telephone);

    var sex = empStudentList[index].sex;

    if(sex == 1){
        $("input[name='sex']").eq(0).attr("checked",'checked');
    } else if(sex == 2){
        $("input[name='sex']").eq(1).attr("checked",'checked');
    }
    $('#addOrEditModal').modal('show');
}

/**
 * 表单验证
 */
function initFormValidator() {
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