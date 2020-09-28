
// 渲染我的项目
function loadMyProject(target) {
    $.post("../store/myProject",function(res){
        if (res.code == 200) {
            $(target).append("");
            $.each(res.data,function(i,temp){
                let cls = "";
                if (temp.entranceType == "iframe") {
                    cls = "_iframe";
                }
                let html = '<li class="'+cls+'" data-entrance="'+temp.entrance+'" data-name="'+temp.projectName+'">';
                html += '<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span> '
                html += '<span class="glyphicon-class">'+temp.projectName+'</span></li>';
                $(target).append(html)
            });
            if (res.data.length > 0) {
                $(target).find("._iframe").on("click",function () {
                    layer.open({
                        type: 2,
                        title: $(this).data("name"),
                        maxmin: true,
                        shadeClose: true, //点击遮罩关闭层
                        area : ['800px' , '520px'],
                        content: $(this).data("entrance")
                    });
                });
            } else {
                $(target).append("没有项目");
            }
        }
    });
}
