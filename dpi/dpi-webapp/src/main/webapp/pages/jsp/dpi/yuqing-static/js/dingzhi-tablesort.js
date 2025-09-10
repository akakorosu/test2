$(function () {
    //列表排序
    for (var i = 0; i < tableList.length; i++) {
        haha(tableList[i]);
    }
});
function haha(table) {
    var tbody = document.querySelector(table).tBodies[0];
    var th = document.querySelector(table).tHead.rows[0].cells;
    var td = tbody.rows;
    for(var i = 1;i < th.length;i++){
        th[i].flag = 1;
        th[i].onclick = function(){
            //排序
            sort(td,tbody,this.getAttribute('data-type'),this.flag,this.cellIndex);
            //重置序号
            reset(td.length,tbody);
            this.flag = -this.flag;
        };
    };
}
function sort(td1,tbody1,str,flag,n){
    var arr = [];
    for(var i = 0;i < td1.length;i++){
        arr.push(td1[i]);
    };
    arr.sort(function(a,b){
        if (str === 'title' ){
            return method('string',a.cells[n].children[0].innerHTML,b.cells[n].children[0].innerHTML) * flag;
        }
        return method(str,a.cells[n].innerHTML,b.cells[n].innerHTML) * flag;
    });
    for(var i = 0;i < arr.length;i++){
        tbody1.appendChild(arr[i]);
    };
};
function method(str,a,b){
    switch(str){
        case 'num':
            return a-b;
            break;
        case 'string':
            return a.localeCompare(b,"zh");
            break;
        default:
            return Date.parse(a)-Date.parse(b);
    };
};
function reset(tdnum,tbody1) {
    for (var i = 0; i < tdnum; i++) {
        tbody1.rows[i].cells[0].innerHTML = (i+1).toString();
    }
}