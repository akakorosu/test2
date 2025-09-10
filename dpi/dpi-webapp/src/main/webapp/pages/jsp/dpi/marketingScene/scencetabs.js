
//新增一个添加框
function resetinput(index){
    let form=document.querySelectorAll("form");
    let removenode= form[index].querySelector(".remove");
    for (let i = inuptnum[index];i>1;i--){
        removeinput(removenode,index)
    }

}
function addinput(node,index){
    inuptnum[index]++;
    let parent1=node.parentElement;
    let parent2 = parent1.parentElement;
    parent2.removeChild(parent1);
    let parent3= parent2.parentElement;
    let div = document.createElement('div');
    div.className="layui-form-item";
    let str =`<label style="width: 150px" class="layui-form-label">APP应用名称${inuptnum[index]}：</label>
                <div class="layui-input-inline" style="margin-right: 10px">
                    <input name="" required onkeydown="openFlag()" onkeyup="closeFlag(queryprod2,this)" style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs app_name">
                </div>
                `;
    div.innerHTML=str;
    if(inuptnum[index]==2){
        let btn = document.createElement('div');
        btn.className="layui-inline remove";
        btn.addEventListener('click',()=>{removeinput(btn,index)});
        btn.innerHTML=`<button style="display: block"  type="button" class="layui-btn layui-btn-danger layui-btn-sm">
                            <i class="layui-icon">&nbsp;&#xe67e;</i>
                        </button>`
        parent1.appendChild(btn)
    }

    div.appendChild(parent1);

    let labdiv=getLabdiv()
    div.appendChild(labdiv);

    if(index==1||index==4){
        let content = parent3.querySelector(".content").cloneNode(true);
        content.querySelector('input').value=""
        div.appendChild(content)
    }
    parent3.appendChild(div);
    function getLabdiv() {
        let res = document.createElement("div")
        res.className="layui-form-item lab";
        res.style.display="none"
        res.style.marginLeft="30px"
        return res;
    }

}
//移除一个添加框
function removeinput(node,index){
    if(inuptnum[index]==1){
        return false
    }
    let parent1 = node.parentElement;//btns
    let parent2 = parent1.parentElement;//app-视频ID
    let parent3 = parent2.parentElement;//上面一半div
    parent3.removeChild(parent2);
    let last = parent3.lastChild;
    console.dir(last)
    while(last.nodeType==3){
        parent3.removeChild(last);
        last = parent3.lastChild;
    }
    // if(index==1||index==4){
    //     do{
    //         parent3.removeChild(last);
    //         last = parent3.lastChild;
    //     }while ((last.nodeType==3));
    // }
    inuptnum[index]--;
    if(inuptnum[index]==1){
        let btns = parent1.querySelectorAll("div");
        parent1.removeChild(btns[1]);
    }
    let lastchildren=last.querySelectorAll('div');
    last.insertBefore(parent1,lastchildren[1])
}

function setupShow() {
    document.querySelector('blockquote').style.display="none"
    tablediv.style.display = "none";
    setupdiv.style.display = "block";
}

function tableShow() {
    document.querySelector('blockquote').style.display="block"
    tablediv.style.display = "block";
    setupdiv.style.display = "none";
}

function appsetShow() {
    displayvideosetdiv.style.backgroundColor="#4c718a";
    displayappsetdiv.style.backgroundColor="#00b3ee";
    displaykeywordsetdiv.style.backgroundColor="#4c718a";
    appsetdiv.style.display = "block";
    videosetdiv.style.display = "none";
    keywordsetdiv.style.display = "none";
}

function videosetShow() {
    displayvideosetdiv.style.backgroundColor="#00b3ee";
    displayappsetdiv.style.backgroundColor="#4c718a";
    displaykeywordsetdiv.style.backgroundColor="#4c718a";
    appsetdiv.style.display = "none";
    videosetdiv.style.display = "block";
    keywordsetdiv.style.display = "none";
}

function keywordsetShow() {
    displayvideosetdiv.style.backgroundColor="#4c718a";
    displayappsetdiv.style.backgroundColor="#4c718a";
    displaykeywordsetdiv.style.backgroundColor="#00b3ee";
    appsetdiv.style.display = "none";
    videosetdiv.style.display = "none";
    keywordsetdiv.style.display = "block";
}

function backtomain(){
    let app = document.querySelector("#app");
    let appparent=document.querySelector("#appparent");
    app.style.display="block";
    let app2 = appparent.querySelector("#app2");
    appparent.removeChild(app2);
}

//////////////////////////////////////////////////////////
inuptnum = [1,1,1,1,1,1];
function getDiv(data,edit) {
    let res =document.createElement('div');
    res.id="app2"
    let str = ``;
    res.innerHTML = getAppShowHtml(data,edit);

    appendres(res,data,edit);
    if(!edit){
        res.querySelectorAll('input').forEach((item)=>{
            item.readOnly=true;
            if(item.type=="radio"){
                item.disabled=true;
            }
        })
    }

    return res;
}

function getAppShowHtml(data,edit) {
    let res =getedithtml(data,edit)
    return res;

}

function getedithtml(data,edit) {
    let app_name_input=``;//APP应用名称输入框
    let video_name_input=``;//视频名称输入框
    let add_remove =``;//添加删除APP应用输入框
    let rule_type_radio =``;//触发规则

    let submit_btn = ``;//编辑提交按钮
    if(data.type_code==1){
        if(data.rule_type==1){
            rule_type_radio = `<div class="layui-input-block">
                        <input type="radio" name="rule_type" value="1" title="累加值" checked lay-filter="beshared">
                        <input type="radio" name="rule_type" value="2" title="实时触发" lay-filter="beshared">
                    </div>
                </div>`

        }else {
            rule_type_radio = `<div class="layui-input-block">
                        <input type="radio" name="rule_type" value="1" title="累加值" lay-filter="beshared">
                        <input type="radio" name="rule_type" value="2" title="实时触发" checked lay-filter="beshared">
                    </div>
                </div>`

        }

    }else {
        rule_type_radio = `<div class="layui-input-block">
                        <input type="radio" name="rule_type" value="2" title="实时触发" checked>
                    </div>
                </div>`
    }

    if(data.app_name==null||data.app_name==""){
        app_name_input = `<div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">关键词：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="keys_strs" value="${data.keys_strs}" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                        </div >
                        <div class="layui-form-mid layui-word-aux" style="margin-left: 25px">多个关键词用“|”分割</div>
                    </div>`;
    }else {
        let split =data.app_name.split("|")
        if(edit){//编辑界面显示增加删除按钮
            if(data.type_code=="3"){

            }else if(split.length==1){
                add_remove = `<div class="layui-inline"  id="operatebtn">
                    <div class="layui-inline" onclick="addinput(this,${+data.type_code + +2})">
                        <button  style="margin-left: 30px" id="addappbtn" type="button" class="layui-btn layui-btn-warm layui-btn-sm">
                            <i class="layui-icon">&nbsp;&#xe654;</i>
                        </button>
                    </div>
                </div>`;
            }else {
                add_remove = `<div class="layui-inline"  id="operatebtn">
                    <div class="layui-inline" onclick="addinput(this,${+data.type_code + +2})">
                        <button  style="margin-left: 30px" id="addappbtn" type="button" class="layui-btn layui-btn-warm layui-btn-sm">
                            <i class="layui-icon">&nbsp;&#xe654;</i>
                        </button>
                    </div>
                    <div class="layui-inline" onclick="removeinput(this,${+data.type_code + +2})">
                        <button  style="" id="removeappbtn" type="button" class="layui-btn layui-btn-danger layui-btn-sm">
                            <i class="layui-icon">&nbsp;&#xe67e;</i>
                        </button>
                    </div>
                </div>`;
            }
        }
        inuptnum[+data.type_code+ +2]=split.length;//设置编辑界面app的数量

        split.forEach((item,index) => {
            let app_video_id=``;
            if(data.type_code==2){
                app_video_id =`<div class="layui-form-item content" style="">
                        <label style="width: 150px" class="layui-form-label">视频内容ID：</label>
                        <input name="video_code" value="${data.video_code.split('|')[index]}" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs video_code">
                    </div>`
            }
            if(index==split.length-1){
                app_name_input +=
                    `<div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">APP应用名称：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="app_name" onkeydown="openFlag()" onkeyup="closeFlag(queryprod2,this)" value="${item}" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs app_name">
                        </div >
                        ${add_remove}
                        <div class="layui-form-item lab" style="display:none;margin-left: 30px"></div>
                        ${app_video_id}
                    </div>`
            }else {
                app_name_input +=
                    `<div class="layui-form-item">
                        <label style="width: 150px" class="layui-form-label">APP应用名称：</label>
                        <div class="layui-input-inline" style="margin-right: 10px">
                            <input name="app_name" value="${item}" onkeydown="openFlag()" onkeyup="closeFlag(queryprod2,this)" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs app_name">
                        </div >
                        <div class="layui-form-item lab" style="display:none;margin-left: 30px"></div>
                        ${app_video_id}
                    </div>`
            }
        })
    }
    // if(data.video_code!=null){
    //     video_name_input = `<div class="layui-form-item" style="">
    //                     <label style="width: 150px" class="layui-form-label">视频内容ID：</label>
    //                     <input name="video_code" value="${data.video_code}" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
    //                 </div>`
    // }

    return `
        <div style="display: flex;margin-top: 20px">
            <div style="margin-right: auto;">
                <div  style="background-color: #00b3ee;width: 150px;height:25px;text-align: center;cursor: pointer;float: left;margin: 2px" >
                     <font color="#ffffff" style="width: 170px;font-weight: bold;font-size:17px" >${data.type_name}</font>
                </div>
            </div>
            <div style="display: flex;justify-content: flex-end">
                <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" onclick="backtomain()">
                    <i class="layui-icon">&#xe65c;</i> 返回
                </a>
            </div>
        </div>
        <hr class="layui-bg-black">
        <div id="appsetdiv" title="app域名配置" style="margin-top: 20px">
            <form class="layui-form" action="" lay-filter="example">
                <input name="state_name" value="${data.state_name}" class="layui-input-xs" type="hidden">
                <input name="state_code" value="${data.state_code}" class="layui-input-xs" type="hidden">
                <input name="type_code" value="${data.type_code}" class="layui-input-xs" type="hidden">
                <input name="type_name" value="${data.type_name}" class="layui-input-xs" type="hidden">
                <input name="scene_code" value="${data.scene_code}" type="hidden">
                <div>
                    <div class="layui-form-item" style="">
                        <label style="width: 150px" class="layui-form-label">营销场景名称：</label>
                        <input name="scene_text" value="${data.scene_text}" required style=" width: 200px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs">
                    </div>
                    ${video_name_input}
                    ${app_name_input}
                </div>
                <hr class="layui-bg-black">
                <div class="layui-form-item" style="margin-top: 20px">
                    <label style="width: 150px" class="layui-form-label">触发规则：</label>
                    ${rule_type_radio}
                </div>
            </form>
        </div>`;
}

function appendres(res,data,edit) {
    let rule_detail_inputs=``;//APP应用配置详情输入框
    if(data.type_code==1) {
        let display = "block";
        if(data.rule_type==2){
            display = "none"
        }
        rule_detail_inputs = `<div class="layui-form-item rule" style="display: ${display}">
                    <label style="width: 150px" class="layui-form-label">流量累加阈值：</label>
                    <input name="flow_max" value="${data.flow_max}"  style="width: 80px;color:#000000"  autocomplete="off" class="layui-input-xs layui-input-inline">
                    <div class="layui-word-aux">M</div>
                </div>
                <div class="layui-form-item" style="display: block">
                    <label style="width: 150px" class="layui-form-label">触发频次：</label>
                    <input name="go_times" value="${data.go_times}"  style="width: 100px;color:#000000"  autocomplete="off" class="layui-input-sm layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">次</div>
                    <label style="width: 150px" class="layui-form-label">流量累计周期：</label>
                    <input name="flow_time_circle" value="${data.flow_time_circle}" style="width: 100px;color:#000000" autocomplete="off" class="layui-input-xs layui-input-inline">
                    <div class="layui-form-mid layui-word-aux">天</div>
                </div>`
    }
    let div = document.createElement('div');
    let str=`${rule_detail_inputs}
    <div class="layui-form-item">
        <label style="width: 150px" class="layui-form-label">场景上线：</label>
        <input type="date" name="start_date" value="${data.start_date}" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
            <div class="layui-form-mid">
                <i class="layui-icon">&#xe637;</i>
            </div>
            <label style="width: 123px" class="layui-form-label">场景下线：</label>
            <input type="date" name="end_date" value="${data.end_date}" required style="width: 175px;color:#000000" lay-verify="required" autocomplete="off" class="layui-input-xs datepicker layui-input-inline">
                <div class="layui-form-mid">
                    <i class="layui-icon">&#xe637;</i>
                </div>
    </div>
    <div class="layui-form-item" style="display: ${edit?'block':'none'}">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
<!--            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">重置</button>-->
        </div>
    </div>`
    div.innerHTML=str;
    res.querySelector('form').appendChild(div);
}
