import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <div class="layui-form" lay-filter="activityCreateForm">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <select name="type">
                        <option value="">请选择</option>
                        <option :value="r.type" v-for="r in activity">{{r.name}}</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">参数</label>
                <div class="layui-input-block">
                    <input type="text" name="param" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea" name="content"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" @click="submit">创建活动</button>
                </div>
            </div>
        </div>
    </div>
`;

export const ActivityCreate = "activity-create";
Vue.component(ActivityCreate, {
    template: template,
    data: () => ({
        activity: GLOBAL.Activities
    }),
    props: {},
    created: function () {
        layui.use('form', function () {

        });
    },
    methods: {
        submit: function () {
            let data = layui.form.val("activityCreateForm");
            axios.post("/api/activity", data)
                .then(response => {
                    if (response.status === 201) {
                        layer.msg("数据创建成功");
                    }
                }).catch(function (error) {
                layer.msg("数据修改失败");
                console.log(error);
            });
            layui.form.val("activityCreateForm", {});
        }
    }
});

export default ActivityCreate
