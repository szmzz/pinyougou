app.controller('sellerController', function ($scope, $controller, baseService) {

    $controller('baseController', {$scope: $scope});

    /** 添加商家 */
    $scope.saveOrUpdate = function(){
        /** 发送post请求 */
        baseService.sendPost("/seller/save", $scope.seller)
            .then(function(response){
                if (response.data){
                    /** 跳转到商家登录页面 */
                    location.href = "/shoplogin.html";
                }else{
                    alert("操作失败！");
                }
            });
    };




})