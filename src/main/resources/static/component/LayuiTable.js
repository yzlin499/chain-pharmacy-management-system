import Vue from "/js/vue.js"
import LayuiForm from "/component/LayuiForm.js";

// language=HTML
const template = `
    <div>
        <table class="layui-hide" :id="tableName+'Data'" :lay-filter="tableName+'Filter'"></table>
        <div :id="tableName+'Toolbar'" style="display:none">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="addData">添加数据</button>
                <button class="layui-btn layui-btn-sm" lay-event="deleteDataBySelect">删除选中数据</button>
                <div class="layui-inline" style="margin-bottom: 10px;margin-right: 10px;">
                    <input class="layui-input layui-btn-sm" style="height: 30px;" :id="tableName+'SearchKey'"
                           autocomplete="off">
                </div>
                <button class="layui-btn layui-btn-sm" lay-event="searchData">搜索</button>
            </div>
        </div>
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
        tablesName: String,
        apiData: String,
        apiField: String,
        addDataObject: Object
    },
    created: function () {
        let tablesName = this.tablesName ? this.tablesName : this.tableName + "s";
        let apiData = this.apiData ? this.apiData : "/api/" + tablesName;
        let apiField = "/api/tableField/" + (this.apiField ? this.apiField : this.tableName);
        layui.use(['jquery', 'table', 'layer'], () => {
            layui.jquery.get(apiField, data => {
                let tableObj = layui.table.render({
                    elem: `#${this.tableName}Data`,
                    url: apiData,
                    parseData: (res) => ({
                        "code": 0,
                        "msg": res.message,
                        "count": res.page.totalElements,
                        "data": res._embedded[tablesName]
                    }),
                    request: {limitName: 'size'},
                    toolbar: `#${this.tableName}Toolbar`,
                    defaultToolbar: ['filter', 'exports', 'print'],
                    autoSort: false,
                    cols: [data.data],
                    page: true,
                    height: 'full-250',
                    limit: 15,
                    limits: [15, 30, 45, 60]
                });
                layui.table.on(`edit(${this.tableName}Filter)`, obj => axios.put(apiData + "/" + obj.data.id, obj.data)
                    .then(response => {
                        if (response.status === 200) {
                            layer.msg("数据修改成功");
                        }
                    }).catch(function (error) {
                        layer.msg("数据修改失败");
                        console.log(error);
                    })
                );
                layui.table.on(`tool(${this.tableName}Filter)`, obj => {
                    if (obj.event === 'dataDel') {
                        axios.delete(apiData + "/" + obj.data.id).then(response => {
                            if (response.status === 204) {
                                layer.msg("数据删除成功");
                                obj.del();
                            }
                        }).catch(function (error) {
                            layer.msg("数据删除失败，有数据引用当前数据，无法删除");
                            console.log(error);
                        });
                    }
                });

                layui.table.on(`sort(${this.tableName}Filter)`, obj => tableObj.reload({
                    initSort: obj,
                    where: {
                        sort: obj.type ? (obj.field + "," + obj.type) : ""
                    }
                }));


                let openUI = {
                    id: `${this.tableName}Form`,
                    type: 1,
                    title: '标题',
                    area: ['500px', '400px'],
                    shade: 0,
                    maxmin: true,
                    content: `
                    <div id="${this.tableName}Form">
                        <layui-form table-name="${this.tableName}" api-field="${this.apiField ? this.apiField : this.tableName}">
                        </layui-form>
                    </div>`,
                    btn: ['保存', '取消'],
                    yes: (index, layero) => {
                        let val = layui.form.val(`${this.tableName}Form`);
                        axios.post(apiData, val).then(response => {
                            console.log(response);
                            if (response.status === 201) {
                                layer.msg("数据添加成功");
                            }
                            tableObj.reload();
                        }).catch(function (error) {
                            layer.msg("数据添加失败");
                            console.log(error);
                        });
                        layer.close(index);
                    },
                    vueObj: null,
                    zIndex: layui.layer.zIndex,
                    success: layero => {
                        layer.setTop(layero);
                        openUI.vueObj = new Vue({el: `#${this.tableName}Form`})
                    },
                    end: () => {
                        openUI.vueObj.$destroy();
                    }
                };
                if (this.addDataObject) {
                    for (let addDataObjectKey in this.addDataObject) {
                        openUI[addDataObjectKey] = this.addDataObject[addDataObjectKey];
                    }
                }
                layui.table.on(`toolbar(${this.tableName}Filter)`, obj => {
                    if (obj.event === 'addData') {
                        layer.open(openUI);
                    } else if (obj.event === 'searchData') {
                        let val = layui.jquery(`#${this.tableName}SearchKey`).val();
                        if (val !== "") {
                            tableObj.reload({
                                url: `${apiData}/search/common`,
                                where: {k: val}
                            })
                        } else {
                            tableObj.reload({
                                url: apiData
                            })
                        }

                    }
                });
            });
        });
    }
});
export default LayuiTable
