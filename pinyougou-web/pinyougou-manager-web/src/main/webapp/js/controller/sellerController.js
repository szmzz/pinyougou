app.controller('sellerController', function ($scope, $controller, baseService) {
    $controller('baseController', {$scope: $scope});

    //搜索数据定义
    $scope.searchEntity = {};
    /** 分页查询品牌信息 */
    $scope.search = function (page, size) {
        /** 发送异步请求分页查询品牌数据 */
        baseService.findByPage('/seller/findByPage', page, size, $scope.searchEntity).then(function (response) {
            $scope.dataList = response.data.rows;
            /** 更新总记录数 */
            $scope.paginationConf.totalItems = response.data.total;
        });
    };

    /** 显示修改 */
    $scope.show = function(entity){
        /** 把json对象转化成一个新的json对象 */
        $scope.entity = angular.copy(entity)
    };


    /** 商家审核 */
    $scope.updateStatus = function (sellerId, status) {
        // 发送异步请求
        baseService.sendGet("/seller/updateStatus?sellerId=" + sellerId + "&status=" + status).then(function(response){
            // 获取响应数据
            if (response.data){
                // 重新加载数据
                $scope.reload();
            }else{
                alert("审核失败！");
            }
        });
    };



})