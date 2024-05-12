<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 电影列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.name" placeholder="电影名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearchByName">搜索</el-button>
                <el-button type="primary" icon="el-icon-plus" plain @click="(editVisible = true)">添加电影</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="movie" label="电影名" align="center"></el-table-column>
                <el-table-column prop="grade" width="100" label="评分"></el-table-column>
                <el-table-column prop="type" width="100" label="类型"></el-table-column>
                <el-table-column prop="country" width="100" label="国家"></el-table-column>
                <el-table-column prop="language" width="100" label="语言"></el-table-column>
              <el-table-column prop="date" width="100" label="上映时间" :formatter="formatDate"></el-table-column>

              <el-table-column prop="duration" width="100" label="影片时长(分钟)"></el-table-column>
                <el-table-column prop="alias" width="100" label="别名"></el-table-column>
              <el-table-column prop="directorList" label="导演">
                <template #default="scope">
                  {{ formatList(scope.row.directorList, 'director') }}
                </template>
              </el-table-column>

              <el-table-column prop="performerList" label="演员">
                <template #default="scope">
                  {{ formatList(scope.row.performerList, 'performer') }}
                </template>
              </el-table-column>

              <el-table-column prop="scriptwriterList" label="编剧">
                <template #default="scope">
                  {{ formatList(scope.row.scriptwriterList, 'scriptwriter') }}
                </template>
              </el-table-column>




              <el-table-column label="操作" width="90" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>
        <!-- 这里弹窗增加电影 -->
        <el-dialog title="添加电影" v-model="editVisible" width="50%">
            <el-form ref="formRef" :rules="rules" :model="Movie" label-width="80px">
                <el-row :gutter="20">
                    <el-col :span="11">
                        <el-form-item label="电影名">
                            <el-input v-model="Movie.movie"></el-input>
                        </el-form-item>
                        <el-form-item label="导演">
                            <el-input v-model="Movie.directorList"></el-input>
                        </el-form-item>
                        <el-form-item label="编剧">
                            <el-input v-model="Movie.scriptwriterList"></el-input>
                        </el-form-item>
                        <el-form-item label="演员">
                            <el-input v-model="Movie.performerList"></el-input>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-input v-model="Movie.type"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="11">
                        <el-form-item label="国家">
                            <el-input v-model="Movie.country"></el-input>
                        </el-form-item>
                        <el-form-item label="语言">
                            <el-input v-model="Movie.language"></el-input>
                        </el-form-item>
                        <el-form-item label="上映时间">
                            <el-input v-model="Movie.date"></el-input>
                        </el-form-item>
                        <el-form-item label="影片时长">
                            <el-input v-model="Movie.duration"></el-input>
                        </el-form-item>
                        <el-form-item label="别名">
                            <el-input v-model="Movie.alias"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="saveEdit" type="primary" plain>确 定</el-button>
                    <el-button @click="onReset" type="primary" plain>重置</el-button>
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

      const formatList = (list, type) => {
        if (list && Array.isArray(list) && list.length > 0) {
          switch (type) {
            case 'director':
              return list.map(item => item.director).join(', ');
            case 'performer':
              return list.map(item => item.performer).join(', ');
            case 'scriptwriter':
              return list.map(item => item.scriptwriter).join(', ');
            default:
              return '';
          }
        } else {
          return '';
        }
      };

      // 前端只显示时间戳的日期部分
      const formatDate = (row, column, cellValue) => {
        if (cellValue) {
          const date = new Date(cellValue);
          return date.toISOString().split('T')[0];
        }
        return '';
      };



      const query = reactive({
            name: "",
            pageIndex: 1, // 当前页
            pageSize: 10, // 每页有多少项
        });
        const TableData = ref([]); // 总表
        const tableData = ref([]); // 当前呈现出的一页，从总表中获得
        const pageTotal = ref(0);
        
        const getData = () => {
            tableData.value = TableData.value.slice(
                query.pageSize*(query.pageIndex-1),
                query.pageSize*(query.pageIndex)
            );
        }
        onMounted(() => {
          // 这里都不需要传参
            axios.get(localStorage.getItem("ip") + "selectAll").then(
                (response) => {
                  let list = response.data;
                  TableData.value = list;
                  tableData.value = list.slice(
                      query.pageSize*(query.pageIndex-1),
                      query.pageSize*(query.pageIndex));
                  pageTotal.value = list.length;

                }
            )
        })
        getData();

        // 新增的搜索方法
        const handleSearchByName = () => {
          const searchParams = {
            movie: query.name,
          };

          console.log('Search Params:', searchParams);  //debug

          let sendpara = qs.stringify(searchParams);

          axios.post(localStorage.getItem("ip") + 'selectByName', sendpara)
              .then((response) => {
                let list = response.data;
                TableData.value = list;
                tableData.value = list.slice(
                    query.pageSize * (query.pageIndex - 1),
                    query.pageSize * (query.pageIndex));
                pageTotal.value = list.length;
              })
              .catch(error => {
                console.error('按照电影名搜索时出错:', error);
              });

          getData();
        };




      // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            getData();
        };
        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getData();
        };

        // 删除操作
        const handleDelete = (index, row) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要删除吗？", "提示", {
                type: "warning",
            })
                .then(() => {
                    let sendpara = qs.stringify(row);
                    axios.post(localStorage.getItem("ip") + "delete", sendpara).then(
                        (response) => {
                            console.log(row);
                            let dt = response.data;
                            if (dt) {
                                ElMessage.success("删除成功");
                                this.$router.go(0);
                            } else {
                                ElMessage.error("删除失败");
                                return false;
                            }
                        }
                    )
                })
                .catch(() => {});
            getData();
        };
        // 添加操作，这里可能需要注意属性数据类型的转换
        const editVisible = ref(false);
        const formRef = ref(null);
        const Movie = reactive({
            movieId:"",
            movie:"",
            grade:"",
            directorList:"",
            scriptwriterList:"",
            performerList:"",
            type:"",
            country:"",
            language:"",
            date:"",
            duration:"",
            alias:"",
            gradeNum:"",
        });
        const rules = {
            movie: [
                { required: true, message: "请输入电影名", trigger: "blur" },
            ],
            directorList: [
                { required: true, message: "请输入导演", trigger: "blur" },
            ],
            scriptwriterList: [
                { required: true, message: "请输入编剧", trigger: "blur" },
            ],
            performerList: [
                { required: true, message: "请输入演员", trigger: "blur" },
            ],
            type: [
                { required: true, message: "请输入类型", trigger: "blur" },
            ],
            country: [
                { required: true, message: "请输入国家", trigger: "blur" },
            ],
            language: [
                { required: true, message: "请输入语言", trigger: "blur" },
            ],
            date: [
                { required: true, message: "请输入上映时间，格式如20220101", trigger: "blur" },
            ],
            duration: [
                { required: true, message: "请输入影片时长，单位为分钟", trigger: "blur" },
            ],
            alias: [
                { required: false, message: "", trigger: "blur" },
            ],
        };

        const saveEdit = () => {
            editVisible.value = false;
            formRef.value.validate((valid) =>{
              if (valid){
                  Movie.date = `${Movie.date.substr(0, 4)}-${Movie.date.substr(4, 2)}-${Movie.date.substr(6, 2)} 00:00:00`;

                  let sendpara = qs.stringify(Movie);
                  axios.post(localStorage.getItem("ip") + "insert", sendpara).then(
                      (response) =>{
                          console.log(Movie);
                          let dt = response.data;
                          if (dt){
                              ElMessage.success("添加成功");
                          } else {
                              ElMessage.error("添加失败");
                              return false;
                          }
                      },
                      function (err){
                          ElMessage.error("添加失败");
                          console.log(err);
                      });
              } else {
                console.log(valid);
                ElMessage.error("请填写完整信息");
                return false;
              }
          });
        };
        const onReset = () => {
            formRef.value.resetFields();
        };
        
        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            Movie,
            formRef,
            rules,
            handleSearch,
            handleSearchByName,
            handlePageChange,
            handleDelete,
            saveEdit,
            onReset,
            formatList,
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
</style>
