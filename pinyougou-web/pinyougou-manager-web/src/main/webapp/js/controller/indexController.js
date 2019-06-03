//定义登录模块
app.controller('indexController', function ($scope, baseService) {
    //异步访问
    $scope.showLoginName = function () {
        baseService.sendGet('/showLoginName').then(function (value) {
            $scope.loginName = value.data.loginName;
        })
    }
})

