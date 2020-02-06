import Vue from "/js/vue.js"

// language=HTML
const template = `
    <div>
        <table class="layui-hide" :id="tableName+'Data'" :lay-filter="tableName+'Filter'"></table>
        <script type="text/html" :id="tableName+'Toolbar'">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addData">添加数据</button>
                <button class="layui-btn layui-btn-sm" lay-event="deleteDataBySelect">删除选中数据</button>
                <!--                <div class="layui-inline">-->
                <!--                    <input class="layui-input" name="id" id="demoReload" autocomplete="off">-->
                <!--                </div>-->
                <!--                <button class="layui-btn layui-btn-sm" data-type="reload">搜索</button>-->
            </div>
        </script>
        <script type="text/html" :id="tableName+'Bar'">
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="dataDel">删除</a>
        </script>
    </div>
`;
export const LayuiTable = "layui-table";
Vue.component(LayuiTable, {
    template: template,
    data: () => ({}),
    props: {
        tableName: String,
        apiName: String,
        addDataObject: Object
    },
    created: function () {
        let api = this.apiName ? "/api/" + this.apiName : "/api/" + this.tableName + "s";
        layui.use(['jquery', 'table'], () => {
            layui.jquery.get("/api/tableField/" + this.tableName, data => {
                layui.table.render({
                    elem: `#${this.tableName}Data`,
                    url: api,
                    parseData: (res) => ({
                        "code": 0,
                        "msg": res.message,
                        "count": res.page.totalElements,
                        "data": res._embedded.medicines
                    }),
                    request: {limitName: 'size'},
                    toolbar: `#${this.tableName}Toolbar`,
                    defaultToolbar: ['filter', 'exports', 'print'],
                    cols: [data.data],
                    page: true,
                    height: 'full-250'
                });
                layui.table.on(`edit(${this.tableName}Filter)`, obj => axios.post(api, obj.data)
                    .then(response => {
                        if (response.status === 201) {
                            layer.msg("数据修改成功");
                        }
                    }).catch(function (error) {
                        layer.msg("数据修改失败");
                        console.log(error);
                    })
                );
                layui.table.on(`tool(${this.tableName}Filter)`, obj => {
                    if (obj.event === 'dataDel') {
                        axios.delete(api + "/" + obj.data.id).then(response => {
                            if (response.status === 204) {
                                layer.msg("数据删除成功");
                                obj.del();
                            }
                        }).catch(function (error) {
                            layer.msg("数据删除失败");
                            console.log(error);
                        });
                    }
                });

                let openUI = {
                    type: 2,
                    title: '标题',
                    area: ['390px', '260px'],
                    shade: 0,
                    maxmin: true,
                    content: '',
                    btn: ['保存', '取消'],
                    yes: () => {
                    },
                    btn2: () => {
                    },
                    zIndex: layer.zIndex,
                    success: layero => layer.setTop(layero)
                };
                if (this.addDataObject) {
                    for (let addDataObjectKey in this.addDataObject) {
                        openUI[addDataObjectKey] = this.addDataObject[addDataObjectKey];
                    }
                }
                layui.table.on(`toolbar(${this.tableName}Filter)`, obj => {
                    if (obj.event === 'addData') {
                        layer.open(openUI);
                    }
                });
            });
        });
    }
});
export default LayuiTable
