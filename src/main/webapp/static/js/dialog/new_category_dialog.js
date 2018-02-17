/**
 * 创建类别
 * @param funWhenCreateCategorySuccess 创建成功时回调，回传参数为新标签id
 * @param funWhenCreateCategoryFail 创建失败时回调，参数为错误码和错误信息
 */
function createCategory(funWhenCreateCategorySuccess, funWhenCreateCategoryFail) {
    var title = $('#categoryTitle').val();
    var bewrite = $('#categoryBewrite').val();

    if (isStrEmpty(title)) {
        error('类别名称不能为空', 'categoryErrorMsg');
        return;
    } else {
        $('#categoryErrorMsg').html(' ');
    }

    $.post(
        '/blogger/' + pageOwnerBloggerId + '/category',
        {title: title, bewrite: bewrite},
        function (result) {
            if (result.code === 0) {
                loadCategory();
                funWhenCreateCategorySuccess(result.data);
                $('#newCategoryDialog').modal('toggle');
            } else {
                error(result.msg, 'categoryErrorMsg');
                funWhenCreateCategoryFail(result);
            }
        }, 'json'
    );

}
