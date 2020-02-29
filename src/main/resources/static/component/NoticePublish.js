import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>发布公告</legend>
        </fieldset>
        <div class="layui-form layui-col-xs11">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" autocomplete="off" placeholder="请输入标题"
                           class="layui-col-xs11 layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">正文</label>
                <div class="layui-input-block">
                    <textarea name="content" required class="layui-textarea editor"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="noticePublish">立即发布</button>
                </div>
            </div>
        </div>
    </div>
`;

export const NoticePublish = "notice-publish";
Vue.component(NoticePublish, {
    template: template,
    data: () => ({}),
    props: {},
    created: function () {
        layui.use(['easyeditor', 'form'], () => {
            layui.easyeditor.init({elem: '.editor'});
            layui.form.on('submit(noticePublish)', data => {
                axios.post("/api/notices", data.field).then(response => {
                    if (response.status === 201) {
                        layer.msg("公告发布成功");
                    }
                }).catch(function (error) {
                    layer.msg("数据添加失败");
                    console.log(error);
                });
                return false;
            });
        });
    }
});

export default NoticePublish
