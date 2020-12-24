$(function(){
    Vue.component('button-paging-num', {
        data: function () {
            return {
                count: "SON_AAA"
            }
        },
        template: '<a href="#" >{{count}}</a>'
    });

    Vue.component('button-paging-size', {
        data: function () {
            return {
                count: "SON_AAA"
            }
        },
        template:`
            <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="form-control input-sm">
                <option value="10">10</option>
                <option value="25">25</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
        `


    });
    Vue.component('button-paging', {
        prop:["count","len","now","lenArr"],
        methods:{
            returnLenArr:function(len,count){
                var s = len%count == 0?len/count:len/count+1;
                var lenArr = new Array();
                if (s <= 5) {
                    for (var i = 1;i <= s ;i++){
                        lenArr.push(i+"");
                    }
                } else {
                    lenArr.push(1+"");
                    lenArr.push(2+"");
                    lenArr.push("...");
                    lenArr.push(s-2+"");
                    lenArr.push(s-1+"");
                }
                return lenArr;
            }
        },
        data: function () {
           return {
               count2:"AAA"
           }
        },
        template: '<div class="button-paging">' +
            '<ul class="pagination pull-right">\n' +
            '       <li class="disabled">\n' +
            '           <a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>\n' +
            '       </li>\n' +
            '       <li class="active"><a href="#">1</a></li>\n' +
            '       <li><a href="#" data-page="2" >2</a></li>\n' +
            '       <li><a href="#">3</a></li>\n' +
            '       <li><a href="#">4</a></li>\n' +
            '       <li><a href="#">5</a></li>\n' +
            '       <li>\n' +
            '           <a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>\n' +
            '       </li>' +
            '       <li>\n' +
            '           <button-paging-size></button-paging-size>\n' +
            '       </li>\n' +
            '   </ul>' +
            '</div>'
    });










});