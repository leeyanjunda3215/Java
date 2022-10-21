# vue的安装

## 创建vue项目

进入一个文件夹，打开cmd（管理员权限），输入命令，vue init webpack my-project ，需要等待很长时间，成功会有提示。

```bash
vue init webpack my-project
```



## 启动vue的方式

进入vue的文件目录（使用cmd管理员权限），输入命令 npm run dev

```bash
//以我的电脑为例子
D:
cd desk
cd View
cd my-project
npm run dev
//成功后访问,localhost:8080端口有显示vue界面就为成功
```

## vue项目结构

![image-20221004002232103](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221004002232103.png)



| 目录/文件    | 说明                                                         |
| :----------- | :----------------------------------------------------------- |
| build        | 项目构建(webpack)相关代码                                    |
| config       | 配置目录，包括端口号等。我们初学可以使用默认的。             |
| node_modules | npm 加载的项目依赖模块                                       |
| src          | 这里是我们要开发的目录，基本上要做的事情都在这个目录里。里面包含了几个目录及文件：assets: 放置一些图片，如logo等。components: 目录里面放了一个组件文件，可以不用。App.vue: 项目入口文件，我们也可以直接将组件写这里，而不使用 components 目录。也可以当作项目的首页，也可以写所有页面中共同需要的动画或者样式,不在他上面写代码也可以 ， main.js: 项目的核心文件。 |
| static       | 静态资源目录，如图片、字体等。                               |
| test         | 初始测试目录，可删除                                         |
| .xxxx文件    | 这些是一些配置文件，包括语法配置，git配置等。                |
| index.html   | 首页入口文件，你可以添加一些 meta 信息或统计代码啥的。       |
| package.json | 项目配置文件。                                               |
| README.md    | 项目的说明文档，markdown 格式                                |

# vue的简介

vue是一套前端框架，免除原生js的DOM操作，简化书写。

基于MVVM（Model-View-ViewModel）思想，实现数据的双向绑定，将编程的关注点放在数据上

MVC（Controller Model（数据，实体类） View）：只能实现模型到视图的单向展示



官网：https://cn.vuejs.org/

引入js文件

```html
//引入vue2
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.9/dist/vue.js"></script>

// 不需要编译器
<script>
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})
    </script>

//引入vue3
<script src="https://unpkg.com/vue@3"></script>
<div id="app">{{ message }}</div>

<script>
  const { createApp } = Vue

  createApp({
    data() {
      return {
        message: 'Hello Vue!'
      }
    }
  }).mount('#app')
</script>
```

## 简单使用

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Document</title>
    <script src="https://unpkg.com/vue@3"></script>
</head>

<body>
    <div id="app">

        <!-- 插值表达式 
        {{ message }}的值和v-model="message"的input绑定，随着input输入的值而改变
        -->
        <input type="text" v-model="message"> {{ message }}


    </div>

    <script>
        const {
            createApp
        } = Vue

        createApp({
            data() {
                return {
                    message: ""
                }
            }
        }).mount('#app')
    </script>

</body>

</html>
```

## ⭐Vue常用指令

指令：HTML标签上带有 v- 前缀的特殊属性，不同的指令具有不同的含义。

常用指令

v-bind：为html标签绑定属性值，如设href，css等

v-model:  在表单元素上创建双向数据绑定

v-on：为HTML元素绑定事件

v-if：v-else：v-else-if：条件性的渲染莫元素，判定为true时渲染，否则不渲染

v-show：根据条件展示莫元素，区别在于切换的是display属性的值

v-for：列表渲染，遍历容器的元素或者对象的属性

```html
   v-bind,url就可以随时改变了
<a v-bind:href="url"></a>
<a :href="url"></a>  简写


v-on
   <div id="app">
 <input type="button" value="按钮" v-on:click="show()">
  <input type="button" value="按钮" @click="show()"> 简写  
    </div>

    <script>
        const {
            createApp
        } = Vue

        createApp({
                data() {
                    return {
                        message: "",
                        url: "https://www.baidu.com"
                    }
                },
                methods: {
                    show() {
                        alert("点击一下")
                    }
                }

            }

        ).mount('#app')
    </script>

v-if，v-else-if,v-else的使用，count在data中写一下
      <input type="text" v-model="count">
        <div v-if="count==3">div1</div>
        <div v-else-if="count==4">div2</div>
        <div v-else>div3</div>

v-show和v-if一样，条件满足就展示，只是内部渲染模式不一样。
<div v-show="count==3">div1</div>

v-if是条件满足就渲染，v-show是都渲染只是不展示使用display属性。

v-for的使用

  <div v-for="addr in addrs">
            {{addr}}
            <br>
   </div>
循环中带变量
   <div v-for="(addr,i) in addrs">
            {{i+1}},{{addr}}
            <br>
        </div>
data中加addrs的数据
    <script>
        const {
            createApp
        } = Vue

        createApp({
                data() {
                    return {                   
                        addrs: ["北京", "上海", "深圳"]
                    }
                }
            }

        ).mount('#app')
    </script>

```

## Vue的生命周期

生命周期分8个阶段：没触发一个声明周期，都会自动的执行一个生命周期的方法（钩子函数）

beforeCreate 创造前

created 创造后

beforeMount 载入前

mounted 挂载完成

beforeUpdate 更新前

updated 更新后

beforeDestory 销毁前

destoryed 销毁后

mounted:挂载完成，Vue初始化成功，HTML渲染成功 ->发送异步请求等等

```html
    <script>
        const {
            createApp
        } = Vue

        createApp({
                data() {
               
                },
                methods: {
          
                },
            //简写，页面加载完成后运行的钩子函数
                mounted() {
                    alert("Vue加载完成")
                }

            }

        ).mount('#app')
    </script>
```

# 案例

## 展示用户

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

## 添加用户

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://unpkg.com/vue@3"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="static/js/http_unpkg.com_axios_dist_axios.js"></script>
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
                        location.href = "http://localhost:8080/ajaxTest_war_exploded/VueTest.html"
                    }
                })
            }
        }
    }).mount('#app')
</script>
</body>
</html>
```

# Vue在idea的使用

## 创建vue项目

使用vue ui打开网页创建vue项目的方式

1.去官网下载node.js ,官网地址：https://nodejs.org/en/

2.电脑打开cmd,输入 npm install -g cnpm --registry=https://registry.npm.taobao.org 安装淘宝镜像

3.输入npm install -g @vue/cli安装脚手架

4.然后分别输入vue -V（这是大写的V） node -v 和 npm -v查看相应的版本号

5.在cmd上进入该目录，使用vue create xxxx创建vue项目（xxxx是你的项目名称)

![image-20221003232402273](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221003232402273.png)

![image-20221003232412939](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221003232412939.png)

![image-20221003232423413](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221003232423413.png)

6.到项目文件夹后，启动vue服务 npm run serve

## idea打开vue项目

通过idea打开vue项目后，在idea添加插件 Vue.js

![image-20221003234344863](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221003234344863.png)

App.vue中两个router-link to="xxx"，里面的xxx就是一个访问路径而已。

先写VUE文件，写一个新的vue界面。

![image-20221004000637415](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221004000637415.png)

在router/index.js 中 import 导入刚写的VUE，然后通过path,name和component 3个属性来定位他在App.vue中写router-link to="xxxx"

![image-20221004001402223](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221004001402223.png)

之后就访问对应的路径   http://localhost:8080/User

![image-20221004002428888](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20221004002428888.png)

前端使用axios跨域访问到8081的json数据，并且得到数据返回至8080端口







