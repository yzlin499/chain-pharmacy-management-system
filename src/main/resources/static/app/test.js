const template = `
<textarea id="L_content" name="content" style="" 
required lay-verify="required" placeholder="请输入发表内容" class="layui-textarea editor"></textarea>


`;


export const appName = "test";
export default {
    template: template,
    created: () => {
        layui.use(['easyeditor'], function () {
            var easyeditor = layui.easyeditor;
            easyeditor.init({
                elem: '.editor' //textarea 元素class
                , uploadUrl: '/upload' //图片上传地址
                , uploadSize: '' //限制的上传大小 单位kb 默认 1024k
                //,codeSkin:'notepad' //代码框样式 默认不填 与notepad
                //,style:'fange' //内置样式  目前只有默认 和 方格两种
                //,buttonColor:'#FFB800' //自定义按钮颜色
                //,hoverBgColor:'red'  // 自定义鼠标悬浮背景颜色
                //,hoverColor:'#000000' // 自定义鼠标悬浮按钮颜色
            });
        });
    }
}
