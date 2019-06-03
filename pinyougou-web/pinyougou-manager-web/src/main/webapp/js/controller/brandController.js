app.controller('brandController', function ($scope, $controller, baseService) {

    $controller('baseController', {$scope: $scope});

    $scope.findAll = function () {
        baseService.sendGet('/brand/findAll').then(function (response) {
            $scope.dataList = response.data;
        })
    }

    // 添加或修改品牌
    $scope.saveOrUpdate = function () {
        // 请求参数
        //alert(JSON.stringify($scope.entity));
        var url = "save"; // 添加URL
        // 判断id是否存在
        if ($scope.entity.id) {
            url = "update"; // 修改URL
        }
        // 发送异步请求
        baseService.sendPost("/brand/" + url, $scope.entity).then(function (response) {
            // 获取响应数据 true|false
            if (response.data) {
                // 重新查询品牌数据
                $scope.reload();
            } else {
                alert("操作失败！");
            }
        });
    };

    //修改按钮点击的时候,回显示数据
    $scope.show = function (entity) {
        $scope.entity = angular.copy(entity);
    }


    //根据ID删除
    $scope.delete = function () {
        baseService.deleteById('/brand/delete', $scope.ids).then(function (response) {
            if (response.data) {
                $scope.reload();
            }
        })
    }

    //搜索数据定义
    $scope.searchEntity = {};
    /** 分页查询品牌信息 */
    $scope.search = function (page, size) {
        /** 发送异步请求分页查询品牌数据 */
        baseService.findByPage('/brand/findByPage', page, size, $scope.searchEntity).then(function (response) {
            $scope.dataList = response.data.rows;
            /** 更新总记录数 */
            $scope.paginationConf.totalItems = response.data.total;
        });
    };

    //全选,全不选
    var status = true;
    $scope.selectAllCheck = function () {
        var event = document.getElementsByClassName("event");
        var xuanzhong = document.getElementById("xuanzhong");
        if (xuanzhong.innerText == "全选") {
            //全选
            for (var i = 0; i < $scope.dataList.length; i++) {

                $scope.ids.push($scope.dataList[i].id)
            }
            xuanzhong.innerText = "全不选"
        } else {
            xuanzhong.innerText = "全选"
            //全不选
            for (var i = 0; i < $scope.dataList.length; i++) {
                var index = $scope.ids.indexOf($scope.dataList[i].id);
                $scope.ids.splice(index, 1)
            }
        }
        for (var i = 0; i < event.length; i++) {
            event[i].checked = status
        }
        status = !status;
    }
})