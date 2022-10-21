# 查询所有

## axios+html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShowUser</title>
    <link rel="stylesheet" href="./static/css/css-03.css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
</head>
<body>

<input type="button" id="addbtn" value="新增" onclick="">

<script>
    document.getElementById("addbtn").onclick = function (){
        location.href = "http://localhost:8080/ajaxTest_war_exploded/addUser.html"
    }
</script>


<hr>

<table id="tbl_fru">
    <tr>
        <th>名称</th>
        <th>学号</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>

</table>
<p id="test"></p>

<script >
    //当页面加载完成后，发送ajax请求
    window.onload = function (){
        axios({
            method: "get",
            url: "http://localhost:8080/ajaxTest_war_exploded/Servlet1"
        }).then(function (resp){
            //获取数据
            let users = resp.data;

            let tabledata = "   <tr>\n" +
                "        <th>名称</th>\n" +
                "        <th>学号</th>\n" +
                "        <th>年龄</th>\n" +
                "        <th>操作</th>\n" +
                "    </tr>";
            for (let i = 0; i < users.length; i++) {
                let user = users[i];

                tabledata += "  <tr >\n" +
                    "        <td >"+user.name+"</td>\n" +
                    "        <td >"+user.snumber+"</td>\n" +
                    "        <td >"+user.sex+"</td>\n" +
                    "        <td><a href=\"#\">删除</a> <a href=\"#\">修改</a></td>\n" +
                    "    </tr>";
            }
            //通过拼接字符串 来把表格填写完成,通过JQuery来shi'x
            $("#tbl_fru").html(tabledata);
        })
    }
</script>
</body>
</html>
```

```java

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            //接收json数据，获取请求体
            BufferedReader br = request.getReader();
            String params = br.readLine();

            //将json字符串转为java对象
            User user = JSON.parseObject(params,User.class);
            System.out.println(user);

            //添加到数据库中
        int i = service.addUser(user);
        response.getWriter().write("success");

    }
```

## axios+vue

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShowUser</title>
    <link rel="stylesheet" href="./static/css/css-03.css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
    <script src="https://unpkg.com/vue@3"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>

<div id="app">
    <input type="button" id="addbtn" value="新增" @click="add()">


    <hr>
    <h1>Vue的实现</h1>

    <table id="tbl_fru">
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>学号</th>
            <th>年龄</th>
            <th>操作</th>
        </tr>

<!--        使用v-for遍历tr-->
        <tr v-for="(user,i) in users">
            <td>{{i+1}}</td>
            <td>{{user.name}}</td>
            <td>{{user.snumber}}</td>
            <td>{{user.sex}}</td>
            <td><a href="#">删除</a> <a href="#">修改</a></td>
        </tr>

    </table>

</div>



<script >
    const { createApp } = Vue

    createApp({
        data() {
            return {
                users: ""
            }
        },
        mounted(){
            //页面加载完成后发送异步请求，查询数据
            var thisax = this;//扩大this的作用范围
            axios({
                method:"get",
                url:"http://localhost:8080/ajaxTest_war_exploded/Servlet1"
            }).then(function (resp) {
                thisax.users = resp.data;//相当于给模型的users设置数据
            })

        },
        methods: {
            add(){
                location.href = "http://localhost:8080/ajaxTest_war_exploded/VueAdd.html"
            }
        }
    }).mount('#app')
</script>
</body>
</html>
```

## Element+vue+axios

```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.7.9/dist/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <style>
        .el-table .warning-row {
            background: oldlace;
        }

        .el-table .success-row {
            background: #f0f9eb;
        }
    </style>
</head>
<body>
<div id="app">
<!--    完成搜索-->
    <el-form :inline="true" :model="User" class="demo-form-inline">
        <el-form-item label="姓名">
            <el-input v-model="User.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="学号">
            <el-input v-model="User.snumber" placeholder="学号"></el-input>
        </el-form-item>
        <el-form-item label="性别">
            <el-select v-model="User.sex" placeholder="性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="QueryUser">查询</el-button>
        </el-form-item>
    </el-form>

    <el-button type="danger" plain>批量删除</el-button>

    <el-button type="primary"  @click="dialogVisible = true" plain>新增</el-button>

    <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
<!--对话框中的新增表单-->
        <el-form :inline="true" :model="UserSubmit" class="demo-form-inline">
            <el-form-item label="姓名">
                <el-input v-model="UserSubmit.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="学号">
                <el-input v-model="UserSubmit.snumber" placeholder="学号"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="UserSubmit.sex" placeholder="性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="AddUser">提交</el-button>
                <el-button plain @click="dialogVisible = false">取消</el-button>

            </el-form-item>
        </el-form>
    </el-dialog>


    <template>
        <el-table
                :data="tableData"
                style="width: 100%"
                :row-class-name="tableRowClassName"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sunmber"
                    label="学号"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center">
                <el-button type="warning" round>修改</el-button>
                <el-button type="danger" round>删除</el-button>
            </el-table-column>
        </el-table>
    </template>
<!--分页工具条-->
    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
    </el-pagination>
</div>

<script>
    var app = new Vue({
        el: '#app',
        methods: {
            tableRowClassName({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-row';
                } else if (rowIndex === 3) {
                    return 'success-row';
                }
                return '';
            },
            handleSelectionChange(val) {
                //复选框选中后执行
                this.multipleSelection = val;
                console.log(this.multipleSelection)
            },
            // 查询数据
            QueryUser() {
                console.log(this.User);
            },
            //添加数据
            AddUser() {
                console.log(this.UserSubmit);
            },
            //每页显示的条数
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            //当前的页面的变化
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            }

        },
        data() {
            return {
                //表单数据
                User: {
                    name: "",
                    snumber: "",
                    sex:""
                },
                //对话框是否显示的标志
                dialogVisible: false,
                //对话框中表单提交的数据
                UserSubmit:{
                    name: "",
                    snumber: "",
                    sex:""
                },
                //表格数据
                tableData: [],
                //复选框选中数据
                multipleSelection: [],
                //当前的页码
                currentPage: 4


            }
        },
        mounted(){
            //页面加载完成后发送异步请求，查询数据
            var thisax = this;//扩大this的作用范围
            axios({
                method:"get",
                url:"http://localhost:8080/ajaxTest_war_exploded/Servlet1"
            }).then(function (resp) {
                thisax.tableData = resp.data;//相当于给模型的users设置数据
            })
        }

    })
</script>

</body>
</html>
```



# 新增用户

## axios+html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>addUser</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
</head>
<body>
<h3>添加物品</h3>
<form action="" method="post">
    名字：<input id="name" name="name"><br>
    学号：<input id="snumber" name="snumber"><br>
    性别：<input id="sex" name="sex"><br>
    <br>
    <input type="button" id="btn" value="提交">
</form>

<script>
    //1.给按钮绑定单价事件
    document.getElementById("btn").onclick = function (){
        //将表单数据转为json
        var formdata = {
            name:"",
            snumber:"",
            sex:"",
        };
        //获取表单数据
        let name = document.getElementById("name").value;
        //设置数据
        formdata.name = name

        //获取表单数据
        let snumber = document.getElementById("snumber").value;
        //设置数据
        formdata.snumber = Number(snumber)

        //获取表单数据
        let sex = document.getElementById("sex").value;
        //设置数据
        formdata.sex = sex

        console.log(formdata)
        //2.发送axios请求
        axios({
            method:"post",
            url:"http://localhost:8080/ajaxTest_war_exploded/addServlet",
            data:formdata
        }).then(function (resp){
            //判断响应数据是否为success
            if (resp.data == "success" ){
                location.href = "http://localhost:8080/ajaxTest_war_exploded/index.html"
            }
        })
    }
</script>

</body>
</html>
```

```java
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            //接收json数据，获取请求体
            BufferedReader br = request.getReader();
            String params = br.readLine();

            //将json字符串转为java对象
            User user = JSON.parseObject(params,User.class);
            System.out.println(user);

            //添加到数据库中
        int i = service.addUser(user);
        response.getWriter().write("success");

    }
```

## axios+vue

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://unpkg.com/vue@3"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
<div id="app">
    <h1>Vue添加</h1>
    <h3>添加物品</h3>
    <form action="" method="post">
        名字：<input id="name" name="name" v-model="Users.name"><br>
        学号：<input id="snumber" name="snumber" v-model="Users.snumber"><br>
        性别：<input id="sex" name="sex" v-model="Users.sex"><br>
        {{Users}}
        <br>
        <input type="button" id="btn" value="提交" @click="submitform()">
    </form>
</div>

<script>
    const { createApp } = Vue

    createApp({
        data() {
            return {
                Users: {}//
            }
        },
        methods:{
            submitform(){
                //发送ajax请求
                var thisas = this;
                axios({
                    method:"post",
                    url:"http://localhost:8080/ajaxTest_war_exploded/addServlet",
                    data:thisas.Users
                }).then(function (resp){
                    if (resp.data == "success"){
                        location.href = "VueView.html"
                    }
                })
            }
        }
    }).mount('#app')
</script>
</body>
</html>
```

## Element+vue+axios

```javascript
AddUser() {
                console.log(this.UserSubmit)
                var thisadd= this;
                axios({
                    method: "post",
                    url: "http://localhost:8080/ajaxTest_war_exploded/addServlet",
                    data:thisadd.UserSubmit
                }).then(function (resp){
                    if (resp.data == "success"){
                        thisadd.dialogVisible = false;
                        //重新查询
                        thisadd.selectAll();

                        //element的消息框
                        thisadd.$message({
                            message: '恭喜你，添加成功',
                            type: 'success'
                        });
                    }
                })

            },
```

```html
 <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
<!--对话框中的新增表单-->
        <el-form :inline="true" :model="UserSubmit" class="demo-form-inline">
            <el-form-item label="姓名">
                <el-input v-model="UserSubmit.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item label="学号">
                <el-input v-model="UserSubmit.snumber" placeholder="学号"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="UserSubmit.sex" placeholder="性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="AddUser">提交</el-button>
                <el-button plain @click="dialogVisible = false">取消</el-button>

            </el-form-item>
        </el-form>
    </el-dialog>
```

# 修改用户

```html
   
<template slot-scope="scope">
<!--                  表格中的修改和删除按钮     -->
                    <el-button type="warning" @click="UpdatedialogVisible = true;updatesnumber(scope.row)"  round>修改</el-button>

                <el-button type="danger" @click="deleteOneUser(scope.row)" round>删除</el-button>
                </template>
            </el-table-column>
<------------------- 修改用户对话框-->
    <el-dialog
            title="修改用户"
            :visible.sync="UpdatedialogVisible"
            width="30%"
            :before-close="handleClose">
        <!--修改表单-->
        <el-form :inline="true" :model="UserUpdae" class="demo-form-inline">
            <el-form-item label="姓名">
                <el-input v-model="UserUpdae.name" placeholder="姓名" ></el-input>
            </el-form-item>

            <el-form-item label="性别">
                <el-select v-model="UserUpdae.sex" placeholder="性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="UpdateUser">提交</el-button>
                <el-button plain @click="UpdatedialogVisible = false">取消</el-button>

            </el-form-item>
        </el-form>
    </el-dialog>
```



```javascript
  //传输修改的函数和修改的学号
            updatesnumber(row){
                console.log(row.snumber)

                var snumber =   row.snumber
                var snumberstr = snumber.toString();
                console.log(snumberstr)

                this.UserUpdae.snumber = snumberstr
            },
            //修改用户的对话框，和显示数据
            //修改数据
            UpdateUser(){

                console.log(this.UserUpdae)

                var thisupdate = this
                axios({
                    method:"post",
                    url:"http://localhost:8080/ajaxTest_war_exploded/User/update",
                    data:thisupdate.UserUpdae
                }).then(function (resp) {
                   if (resp.data == "success")
                       thisupdate.UpdatedialogVisible = false;
                    //重新查询
                    thisupdate.selectAll();

                    //element的消息框
                    thisupdate.$message({
                        message: '恭喜你，修改成功',
                        type: 'success'
                    });
                })

            },
```

```java
public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");

        //接收json数据，获取请求体
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //将json字符串转为java对象
        User user = com.alibaba.fastjson2.JSON.parseObject(params,User.class);
        System.out.println(user);
        int sunmber = user.getSnumber();
        String name = user.getName();
        String sex = user.getSex();

        service.UpdataUser(name,sex,sunmber);

        resp.getWriter().write("success");
        System.out.println("success");
    }
```



# 删除用户

```java
public void delect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader br = req.getReader();
        String params = br.readLine();

        int snumebr = Integer.parseInt(params);

        service.deleteOneUser(snumebr);

        resp.getWriter().write("success");

    }
```

```html
                <template slot-scope="scope">
<!--                  表格中的修改和删除按钮     -->
                    <el-button type="warning" @click="UpdatedialogVisible = true;updatesnumber(scope.row)"  round>修改</el-button>

                <el-button type="danger" @click="deleteOneUser(scope.row)" round>删除</el-button>
                </template>
```

```javascript
   //删除一个用户
            deleteOneUser(row){

                var thisdeleteone = this
                axios({
                    method:"post",
                    url:"http://localhost:8080/ajaxTest_war_exploded/User/delect",
                    data:row.snumber
                    }).then(function (resp) {
                    if (resp.data == "success")
                        thisdeleteone.UpdatedialogVisible = false;
                    //重新查询
                    thisdeleteone.selectAll();

                    //element的消息框
                    thisdeleteone.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                })

            },
```



# 批量删除

```javascript
 //批量删除
            deleteBySnumbers(){
                //为了防止误操作，弹出一个确认提示框
                    this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        //用户点击确认按钮
                        //snumber的数组,从this.multipleSelection获取
                        for (let i = 0; i < this.multipleSelection.length ; i++) {
                            let selectElement = this.multipleSelection[i];
                            this.selectSunmbers[i] = selectElement.snumber;
                        }
                        console.log(this.selectSunmbers)
                        var thisdelectAll = this
                        axios({
                            method:"post",
                            url:"http://localhost:8080/ajaxTest_war_exploded/User/delectBysnumbers",
                            data:thisdelectAll.selectSunmbers
                        }).then(function (resp){
                            if (resp.data == "success"){
                                //重新查询
                                thisdelectAll.selectAll();
                                //element的消息框
                                thisdelectAll.$message({
                                    message: '删除成功',
                                    type: 'success'
                                });
                            }
                        })
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });



            },
```

```java
 /**
     * 删除多个用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delectBysnumbers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.接收数据，[1,2,34]
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        //转为数组
        int[] snumbers = JSON.parseObject(params, int[].class);
        service.deleteByIds(snumbers);
        resp.getWriter().write("success");
    }

```

# 分页查询

## 分析

使用Limit关键字，两个参数，第一个参数是，开始的索引，参数2是查询的条目数。

```sql
Select * from xsb limit 0,5
```

页面传递的参数：当前页面，每页显示的条数。

后端传输给前端的数据：当前页数 List，总记录数totalCount



开始的索引=（当前页码-1）*每页显示的条数

查询的条目数=每页显示的条数



# 条件查询