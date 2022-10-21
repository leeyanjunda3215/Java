# Element

是 饿了么 公司前端提供的基于Vue2的网站组件库，用于快速的构建网页

组件：组成网页的部件，例如 超链接，按钮，图片，表格.

快速入门：

```html
<!-- 引入样式 -->
<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
```

在Vue2网页的基础上，复制Element组件代码。

## 布局

Element有两种布局方式：

### layout布局

通过基础的24分栏（一行24个各种），迅速的简便的创建布局

<el-row>为一行

<el-col>为行中的格子，el-col的span属性来判断这个格子占据多少格子，最大为24个。

### Container布局

用于布局的容器组件，方便快速搭建页面的基本结构：

`<el-container>`：外层容器。当子元素中包含 `<el-header>` 或 `<el-footer>` 时，全部子元素会垂直上下排列，否则会水平左右排列。

`<el-header>`：顶栏容器。

`<el-aside>`：侧边栏容器。

`<el-main>`：主要区域容器。

`<el-footer>`：底栏容器。

## 组件