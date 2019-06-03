app.controller('specificationController', function ($scope, $controller, baseService) {

    $controller('baseController', {$scope: $scope});


    /** 查询条件对象 */
    $scope.searchEntity = {};
    $scope.search = function (page, size) {
        baseService.findByPage('/specification/findByPage', page, size, $scope.searchEntity).then(function (response) {
            $scope.dataList = response.data.rows;
            $scope.paginationConf.totalItems = response.data.total;
        })
    };

    //定义规格选项数组
    $scope.entity = {specificationOptionList: []}
    //点击修改按钮回显数据
    $scope.show = function (entity) {
        $scope.entity = angular.copy(entity)
        baseService.sendGet('/specification/findSpecOption?id=' + $scope.entity.id).then(function (response) {
            $scope.entity.specificationOptionList = response.data
        })
    }

    //删除一行规格选
    $scope.deleteRows = function (index) {
        var aindex = $scope.entity.specificationOptionList.indexOf(index);
        $scope.entity.specificationOptionList.splice(aindex, 1)
    }

    //增加一行规格选项
    $scope.addRows = function () {
        $scope.entity.specificationOptionList.push({})
    }


    //保存或者修改
    $scope.saveOrUpdate = function () {
        var url = '/specification/save'
        if ($scope.entity.id) {
            url = '/specification/update'
        }
        baseService.sendPost(url, $scope.entity).then(function (response) {
            if (response.data) {
                $scope.reload();
            } else {
                alert("操作失败")
            }
        })
    }

    $scope.delete = function () {
        baseService.deleteById('/specification/delete', $scope.ids).then(function (response) {
            if (response.data) {
                $scope.reload();
            } else {
                alert("删除失败")
            }
        })
    }

});