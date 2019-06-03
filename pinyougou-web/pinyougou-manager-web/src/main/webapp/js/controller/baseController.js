// 基础控制器
app.controller('baseController',function ($scope) {

    /** 分页指令配置信息对象  */
    $scope.paginationConf = {
        currentPage: 1, // 当前页码
        totalItems: 0,  // 总记录数
        itemsPerPage: 10, // 每页显示的记录数
        perPageOptions: [10, 20, 30], // 页码下拉列表框
        onChange: function () { // 改变事件
            $scope.reload(); //重新加载
        }
    };
    /** 重新加载列表数据 */
    $scope.reload = function () {
        /** 切换页码  */
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    $scope.ids = []
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.ids.push(id)
        } else {
            var index = $scope.ids.indexOf(id);
            $scope.ids.splice(index, 1)
        }
    }

});