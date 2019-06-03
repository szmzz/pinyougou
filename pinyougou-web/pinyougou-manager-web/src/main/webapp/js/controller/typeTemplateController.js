app.controller('typeTemplateController', function ($scope, $controller, baseService) {

    $controller('baseController', {$scope: $scope});


    /** 查询条件对象 */
    $scope.searchEntity = {};
    $scope.search = function (page, size) {
        baseService.findByPage('/typeTemplate/findByPage', page, size, $scope.searchEntity).then(function (response) {
            $scope.dataList = response.data.rows;
            $scope.paginationConf.totalItems = response.data.total;
        })
    };


    $scope.jsonArr2Str = function (jsonArrStr) {
        jsonArrStr = angular.fromJson(jsonArrStr)
        for (var i = 0; i < jsonArrStr.length; i++) {
            if (i == 0) {
                str = jsonArrStr[i].text
            }
            str += "," + jsonArrStr[i].text
        }
        return str;
    }


    //查询下拉框的品牌数据
    $scope.findBrandList = function () {
        baseService.sendGet('/brand/findBrandList').then(function (value) {
            $scope.brandList = {data: value.data};
        })
    }
    //查询下拉框的规格数据
    $scope.findspecList = function () {
        baseService.sendGet('/specification/findSpecificationList').then(function (value) {
            $scope.specList = {data: value.data};
        })
    }


    $scope.entity = {customAttributeItems: []}
    $scope.addRows = function () {
        $scope.entity.customAttributeItems.push({})
    }
    $scope.deleteRows = function ($event, id) {
        var index = $scope.entity.customAttributeItems.indexOf(id);
        $scope.entity.customAttributeItems.splice(index)
    }


    $scope.saveOrUpdate = function () {
        var url = '/typeTemplate/save'
        if ($scope.entity.id) {
            url = '/typeTemplate/update'
        }
        baseService.sendPost(url, $scope.entity).then(function (value) {
            if (value.data == "true") {
                $scope.reload()
            } else {
                alert("保存失败")
            }
        })
    }


    $scope.show = function (entity) {
        $scope.entity = angular.copy(entity)
        $scope.entity.brandIds = angular.fromJson($scope.entity.brandIds)
        $scope.entity.specIds = angular.fromJson($scope.entity.specIds)
        $scope.entity.customAttributeItems = angular.fromJson($scope.entity.customAttributeItems)
    }


    $scope.delete = function () {
        baseService.deleteById('/typeTemplate/delete', $scope.ids).then(function (value) {
            if (value.data == "true") {
                $scope.reload();
            } else {
                alert("操作失败")
            }
        })

    }
})