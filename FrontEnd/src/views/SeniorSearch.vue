<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 组合查询
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-select v-model="query.type" placeholder="类型" class="handle-select mr10">
                    <el-option key="不限" label="不限" value="不限"></el-option>
                    <el-option key="剧情" label="剧情" value="剧情"></el-option>
                    <el-option key="动作" label="动作" value="动作"></el-option>
                    <el-option key="犯罪" label="犯罪" value="犯罪"></el-option>
                    <el-option key="冒险" label="冒险" value="冒险"></el-option>
                    <el-option key="传记" label="传记" value="传记"></el-option>
                    <el-option key="喜剧" label="喜剧" value="喜剧"></el-option>
                    <el-option key="恐怖" label="恐怖" value="恐怖"></el-option>
                    <el-option key="西部" label="西部" value="西部"></el-option>
                    <el-option key="悬疑" label="悬疑" value="悬疑"></el-option>
                </el-select>
                <el-select v-model="query.language" placeholder="语言" class="handle-select mr10">
                    <el-option key="不限" label="不限" value="不限"></el-option>
                    <el-option key="英语" label="英语" value="英语"></el-option>
                    <el-option key="韩语" label="韩语" value="韩语"></el-option>
                    <el-option key="法语" label="法语" value="法语"></el-option>
                    <el-option key="越南语" label="越南语" value="越南语"></el-option>
                    <el-option key="普通话" label="普通话" value="普通话"></el-option>
                    <el-option key="匈牙利语" label="匈牙利语" value="匈牙利语"></el-option>
                    <el-option key="阿拉伯语" label="阿拉伯语" value="阿拉伯语"></el-option>
                    <el-option key="菲律宾语" label="菲律宾语" value="菲律宾语"></el-option>
                    <el-option key="葡萄牙语" label="葡萄牙语" value="葡萄牙语"></el-option>
                    <el-option key="意大利语" label="意大利语" value="意大利语"></el-option>
                    <el-option key="西班牙语" label="西班牙语" value="西班牙语"></el-option>
                    <el-option key="塞尔维亚语" label="塞尔维亚语" value="塞尔维亚语"></el-option>
                    </el-select>
                <el-select v-model="query.country" placeholder="国家" class="handle-select mr10">
                    <el-option key="不限" label="不限" value="不限"></el-option>
                    <el-option key="美国" label="美国" value="美国"></el-option>
                    <el-option key="英国" label="英国" value="英国"></el-option>
                    <el-option key="德国" label="德国" value="德国"></el-option>
                    <el-option key="法国" label="法国" value="法国"></el-option>
                    <el-option key="巴西" label="巴西" value="巴西"></el-option>
                    <el-option key="韩国" label="韩国" value="韩国"></el-option>
                    <el-option key="新西兰" label="新西兰" value="新西兰"></el-option>
                    <el-option key="意大利" label="意大利" value="意大利"></el-option>
                    <el-option key="墨西哥" label="墨西哥" value="墨西哥"></el-option>
                    <el-option key="加拿大" label="加拿大" value="加拿大"></el-option>
                    <el-option key="中国台湾" label="中国台湾" value="中国台湾"></el-option>
                    <el-option key="中国大陆" label="中国大陆" value="中国大陆"></el-option>
                    <el-option key="中国香港" label="中国香港" value="中国香港"></el-option>
                    <el-option key="澳大利亚" label="澳大利亚" value="澳大利亚"></el-option>
                </el-select>
                <el-select v-model="query.grade" placeholder="评分" class="handle-select mr10">
                    <el-option key="不限" label="不限" value="不限"></el-option>
                    <el-option key="9.5~10.0" label="9.5~10.0" value="9.5~10.0"></el-option>
                    <el-option key="9.0~9.5" label="9.0~9.5" value="9.0~9.5"></el-option>
                    <el-option key="8.5~9.0" label="8.5~9.0" value="8.5~9.0"></el-option>
                    <el-option key="8.0~8.5" label="8.0~8.5" value="8.0~8.5"></el-option>
                    <el-option key="7.5~8.0" label="7.5~8.0" value="7.5~8.0"></el-option>
                    <el-option key="7.0~7.5" label="7.0~7.5" value="7.0~7.5"></el-option>
                    <el-option key="6.5~7.0" label="6.5~7.0" value="6.5~7.0"></el-option>
                    <el-option key="6.0~6.5" label="6.0~6.5" value="6.0~6.5"></el-option>
                    <el-option key="6.0以下" label="6.0以下" value="6.0以下"></el-option>
                </el-select>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="movie" label="电影名" align="center"></el-table-column>
                <el-table-column prop="grade" width="100" label="评分"></el-table-column>
                <el-table-column prop="type" width="100" label="类型"></el-table-column>
                <el-table-column prop="country" width="100" label="国家"></el-table-column>
              <el-table-column prop="date" width="100" label="上映时间" :formatter="formatDate"></el-table-column>
                <el-table-column prop="duration" width="100" label="影片时长（分钟）"></el-table-column>
                <el-table-column prop="alias" width="100" label="别名"></el-table-column>
                <el-table-column label="打分" width="90" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)">打分</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>
        <!-- 这里弹窗增加电影 -->
        <el-dialog title="评分" v-model="editVisible" width="30%">
            <div class="demo-rate-block">
                <span class="demonstration">给这部电影打个分吧</span>
                <el-rate size="large" v-model="my_grade" :colors="colors" allow-half /> {{(my_grade * 2)}}
            </div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="saveEdit" type="primary" plain size="small">确 定</el-button>
                    <el-button @click="onReset" type="primary" plain size="small">重置</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive, onMounted} from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import qs from "qs";

export default {
    name: "basetable",
    setup() {

        // 前端只显示时间戳的日期部分
        const formatDate = (row, column, cellValue) => {
          if (cellValue) {
            const date = new Date(cellValue);
            return date.toISOString().split('T')[0];
          }
          return '';
        };

        const query = reactive({
            type: "",
            language: "",
            country: "",
            grade: "",
            movieId:"",
            pageIndex: 1, // 当前页
            pageSize: 10, // 每页有多少项
        });
        const TableData = ref([]); // 总表
        const tableData = ref([]); // 当前呈现出的一页，从总表中获得
        const pageTotal = ref(0);

        const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']);

        const my_grade = ref(null);
        const send_grade = reactive({
            name: "",
            movieId:"",
            grade: my_grade,

        })


        const getData = () => {
            tableData.value = TableData.value.slice(
                query.pageSize*(query.pageIndex-1),
                query.pageSize*(query.pageIndex)
            );
        }

        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            let sendpara = qs.stringify(query);
            axios.post(localStorage.getItem("ip") + "personalizedSelect", sendpara).then(
                function (response){
                    let list = response.data
                    TableData.value = list;
                    tableData.value = list.slice(
                      query.pageSize*(query.pageIndex-1),
                      query.pageSize*(query.pageIndex));
                    pageTotal.value = list.length;
                }
                );
            getData();
        };
        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getData();
        };

        const handleEdit = (index, row) => {
          // 将电影ID添加到send_grade对象中
          send_grade.movieId = row.movieId;
          send_grade.name = row.name;
          editVisible.value = true;
        };


      // 打分，这里分数需要乘2
        const editVisible = ref(false);
        const saveEdit = () => {
            editVisible.value = false;
            send_grade.grade = my_grade.value * 2;
            let sendpara = qs.stringify(send_grade);
            axios.post(localStorage.getItem("ip") + "update", sendpara).then(
                (response) => {
                    let dt = response.data;
                    if (dt){
                        ElMessage.success("评分成功");
                    } else {
                        ElMessage.error("评分失败");
                        return false;
                    }
                },
                function (err){
                    ElMessage.error("评分失败");
                    console.log(err);
                });
        };
        const onReset = () => {
            my_grade.value = 0;
        };
        
        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            my_grade,
            colors,
            handleSearch,
            handlePageChange,
            handleEdit,
            saveEdit,
            onReset,
            formatDate,
        };
    },
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}

.demo-rate-block {
  padding: 0px;
  text-align: center;
  border-right: solid 1px black;
  display: inline-block;
  width: 90%;
  box-sizing: border-box;
}
.demo-rate-block:last-child {
  border-right: none;
}
.demo-rate-block .demonstration {
  display: block;
  color: black;
  font-size: 14px;
  margin-bottom: 20px;
}
</style>
