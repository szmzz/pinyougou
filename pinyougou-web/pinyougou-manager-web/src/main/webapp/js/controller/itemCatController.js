app.controller('itemCatController', function ($scope, $controller, baseService) {

    $controller('baseController', {$scope: $scope});


    //根据父类ID查询分类
    $scope.findItemCatByParentId = function (id) {
        baseService.sendGet('/itemCat/findItemCatByParentId?id=' + id).then(function (value) {
            $scope.dataList = value.data


        })
    }


    /** 默认为1级 */
    $scope.grade = 1
    //查询下一级
    $scope.selectList = function (entity, grade) {
        $scope.grade=grade

        if(grade==1){
            $scope.itemCat_1=null
            $scope.itemCat_2=null
        }
        if(grade==2){
            $scope.itemCat_1=entity
            $scope.itemCat_2=null
        }
        if(grade==3){
            $scope.itemCat_2=entity
        }
        $scope.findItemCatByParentId(entity.id)
    }


})