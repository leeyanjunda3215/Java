Javascript

javaScript控制了网页的行为

## 直接写入HTML输入流

```javascript
document.write("<h1>这是一个标题</h1>");
document.write("<p>这是一个段落。</p>");
```

 您只能在 HTML 输出中使用 document.write。如果您在文档加载后使用该方法，会覆盖整个文档。

## 对事件的反应

```html
<button type="button" onclick="alert('欢迎!')">点我!</button>
```

## 改变HTML的内容

```javascript
x=document.getElementById("demo");  //查找元素
x.innerHTML="Hello JavaScript";    //改变内容
```

**document.getElementById("*****some id*****")**。这个方法是 HTML DOM 中定义的。

DOM (**D**ocument **O**bject **M**odel)（文档对象模型）是用于访问 HTML 元素的正式 W3C 标准。

```html
//点亮灯泡
<script>
function changeImage()
{
    element=document.getElementById('myimage')
    if (element.src.match("bulbon"))
    {
        element.src="/images/pic_bulboff.gif";
    }
    else
    {
        element.src="/images/pic_bulbon.gif";
    }
}
</script>
<img loading="lazy" id="myimage" onclick="changeImage()" src="/images/pic_bulboff.gif" width="100" height="180">
```

element.src.match("bulbon") 的作用意思是：检索 <img id="myimage" onclick="changeImage()" src="/images/pic_bulboff.gif" width="100" height="180"> 里面 src 属性的值有没有包含 bulbon 这个字符串，如果存在字符串 bulbon*，图片* src 更新为 bulboff.gif*，若匹配不到* bulbon 字符串，src 则更新为 bulbon.gif

## 改变HTML格式

改变 HTML 元素的样式，属于改变 HTML 属性的变种。

```javascript
x=document.getElementById("demo")  //找到元素 
x.style.color="#ff0000";           //改变样式
```

## 验证输入

```javascript
if isNaN(x) {
    alert("不是数字");
}
```

HTML 中的 Javascript 脚本代码必须位于 **<script>** 与 **</script>** 标签之间。

Javascript 脚本代码可被放置在 HTML 页面的 **<body>** 和 **<head>** 部分中。

# JavaScript

## 用法

JavaScrip位于HTML中的<scrip></scrip>标签中，head和body都可以。HTML5都自动匹配JavaScrip。

## 输出

- 使用 **window.alert()** 弹出警告框。
- 使用 **document.write()** 方法将内容写到 HTML 文档中。
- 使用 **innerHTML** 写入到 HTML 元素。
- 使用 **console.log()** 写入到浏览器的控制台。

### window.alert()

弹出一个警告框。

```javascript
window.alert("test")
```

### 操作HTML元素

如需从 JavaScript 访问某个 HTML 元素，您可以使用 document.getElementById(*id*) 方法。

请使用 "id" 属性来标识 HTML 元素，并 innerHTML 来获取或插入元素内容：

因为用 innerHTML 插入文本到网页中有可能成为网站攻击的媒介，从而产生潜在的安全风险问题。所以HTML 5 中指定不执行由 innerHTML 插入的。

### 写到HTML文档

```javascript
document.write(Date()); //注意分号
```

请使用 document.write() 仅仅向文档输出写内容。

如果在文档已完成加载后执行 document.write，**整个 HTML 页面将被覆盖。**

### 写到控制台

如果您的浏览器支持调试，你可以使用 **console.log()** 方法在浏览器中显示 JavaScript 值。

浏览器中使用 F12 来启用调试模式， 在调试窗口中点击 "Console" 菜单。

```javascript
 **console.log()**
```

## 语法

JavaScript 是一个脚本语言。它是一个轻量级，但功能强大的编程语言。

### 字面量

固定值称为字面量。

**数字（Number）字面量** 可以是整数或者是小数，或者是科学计数(e)。

**字符串（String）字面量** 可以使用单引号或双引号

**表达式字面量** 用于计算

**数组（Array）字面量** 定义一个数组

**对象（Object）字面量** 定义一个对象

**函数（Function）字面量** 定义一个函数

```javascript
3.14//数字字面量 
"liyanjun" //字符串字面量
5 + 6 //表达式字面量
[40, 100, 1, 5, 25, 10] //数组字面量
{firstName:"John", lastName:"Doe", age:50, eyeColor:"blue"} //对象字面量
function myFunction(a, b) { return a * b;} //函数字面量
```

### 变量

使用关键字 **var** 来定义变量， 使用等号来为变量赋值

```javascript
var x, length
x = 5
length = 6
```

### 语句

在 HTML 中，JavaScript 语句用于向浏览器发出命令。

语句是用分号分隔

### 函数

JavaScript 语句可以写在函数内，函数可以重复引用：

**引用一个函数** = 调用函数(执行函数内的语句)。

```javascript
function myFunction(a, b) {
    return a * b;                                // 返回 a 乘以 b 的结果,注意分号！！
}
```

### 字符集

JavaScript 使用 Unicode 字符集。Unicode 覆盖了所有的字符，包含标点等字符。

JavaScript 对大小写是敏感的。

## 数据类型

**值类型(基本类型)**：字符串（String）、数字(Number)、布尔(Boolean)、空（Null）、未定义（Undefined）、Symbol。

**引用数据类型（对象类型）**：对象(Object)、数组(Array)、函数(Function)，还有两个特殊的对象：正则（RegExp）和日期（Date）。

### 对象

对象用于键值对

```javascript
var person = {firstName:"John", lastName:"Doe", age:50, eyeColor:"blue"} 
```

访问对象属性

```javascript
person.lastName; 
//或者
person["lastName"];
```

对象方法

如果你要访问 person 对象的 fullName 属性，它将作为一个定义函数的字符串返回：

```html
<!DOCTYPE html>

<body>

<p>创建和使用对象方法。</p>
<p>对象方法是一个函数定义,并作为一个属性值存储。</p>
<p id="demo1"></p>
<p id="demo2"></p>
<script>
var person = {
    firstName: "John",
    lastName : "Doe",
    id : 5566,
    fullName : function() 
	{
       return this.firstName + " " + this.lastName;
    }
};
document.getElementById("demo1").innerHTML = "不加括号输出函数表达式："  + person.fullName;
document.getElementById("demo2").innerHTML = "加括号输出函数执行结果："  +  person.fullName();
</script>

</body>

```

### 函数

函数就是包裹在花括号中的代码块，前面使用了关键词 function：

```javascript
function functionname()
{
    // 执行代码
}
```

带参函数

```javascript
function myFunction(var1,var2)
{
代码
}
```

带返回值的函数

```javascript
function myFunction()
{
    var x=5;
    return x;
}
```

在您仅仅希望退出函数时 ，也可使用 return 语句。返回值是可选的.

```javascript
function myFunction(a,b)
{
    if (a>b)
    {
        return; //退出函数
    }
    x=a+b
}
```

#### 局部和全局变量

在 JavaScript 函数内部声明的变量（使用 var）是*局部*变量，所以只能在函数内部访问它。（该变量的作用域是局部的）。

您可以在不同的函数中使用名称相同的局部变量，因为只有声明过该变量的函数才能识别出该变量。

只要函数运行完毕，本地变量就会被删除。

在函数外声明的变量是全局变量，网页上的所有脚本和函数都能访问它。

### 字符串

字符串可以像python一样用 + 号连接。

字符串和数字相加。返回字符串。

## 事件

HTML 事件是发生在 HTML 元素上的事情。

当在 HTML 页面中使用 JavaScript 时， JavaScript 可以触发这些事件。

HTML 事件可以是浏览器行为，也可以是用户行为。

以下是 HTML 事件的实例：

- HTML 页面完成加载
- HTML input 字段改变时
- HTML 按钮被点击

通常，当事件发生时，你可以做些事情。

在事件触发时 JavaScript 可以执行一些代码。

HTML 元素中可以添加事件属性，使用 JavaScript 代码来添加 HTML 元素。

```javascript
<button onclick="getElementById('demo').innerHTML=Date()">现在的时间是?</button>
//修改自身
<button onclick="this.innerHTML=Date()">现在的时间是?</button>
```

### 常见的事件

| 事件        | 描述                                 |
| :---------- | :----------------------------------- |
| onchange    | HTML 元素改变                        |
| onclick     | 用户点击 HTML 元素                   |
| onmouseover | 鼠标指针移动到指定的元素上时发生     |
| onmouseout  | 用户从一个 HTML 元素上移开鼠标时发生 |
| onkeydown   | 用户按下键盘按键                     |
| onload      | 浏览器已完成页面的加载               |

事件可以用于处理表单验证，用户输入，用户行为及浏览器动作:

- 页面加载时触发事件
- 页面关闭时触发事件
- 用户点击按钮执行动作
- 验证用户输入内容的合法性

可以使用多种方法来执行 JavaScript 事件代码：

- HTML 事件属性可以直接执行 JavaScript 代码
- HTML 事件属性可以调用 JavaScript 函数
- 你可以为 HTML 元素指定自己的事件处理程序
- 你可以阻止事件的发生。

## 比较

== 等于 

===绝对等于  (值和类型均相等)

```javascript
//在常规的比较中，数据类型是被忽略的，以下 if 条件语句返回 true
var x = 10;
var y = "10";
if (x == y)
//在严格的比较运算中，=== 为恒等计算符，同时检查表达式的值与类型，以下 if 条件语句返回 false
var x = 10;
var y = "10";
if (x === y)
//switch 语句会使用恒等计算符(===)进行比较:
//以下实例会执行 alert 弹窗
var x = 10;
switch(x) {
    case 10: alert("Hello");
}
//以下实例由于类型不一致不会执行 alert 弹窗
var x = 10;
switch(x) {
    case "10": alert("Hello");
}
```

!=不等于

!== 不绝对等于（值和类型有一个不相等，或两个都不相等）

## 条件语句

- **if 语句** - 只有当指定条件为 true 时，使用该语句来执行代码
- **if...else 语句** - 当条件为 true 时执行代码，当条件为 false 时执行其他代码
- **if...else if....else 语句**- 使用该语句来选择多个代码块之一来执行
- **switch 语句** - 使用该语句来选择多个代码块之一来执行

## 循环

```javascript
var person={fname:"Bill",lname:"Gates",age:56}; 
 
for (x in person)  // x 为属性名
{
    txt=txt + person[x];
}
```

## null

在 JavaScript 中 null 表示 "什么都没有"。

null是一个只有一个值的特殊类型。表示一个空对象引用。

你可以设置为 null 来清空对象。你可以设置为 undefined 来清空对象:

```javascript
var person = null;      var person = undefined;  
```

在 JavaScript 中, **undefined** 是一个没有设置值的变量。

**typeof** 一个没有值的变量会返回 **undefined**。

```javascript
person = undefined;          // 值为 undefined, 类型是undefined
```

null 和 undefined 的值相等，但类型不等：

```javascript
typeof undefined       // undefined
typeof null         // object
null === undefined      // false
null == undefined      // true
```

## 类型转换

在 JavaScript 中有 6 种不同的数据类型：

- string
- number
- boolean
- object
- function
- symbol

3 种对象类型：

- Object
- Date
- Array

2 个不包含任何值的数据类型：

- null
- undefined

```javascript
typeof "John"                 // 返回 string
typeof 3.14                   // 返回 number
typeof NaN                    // 返回 number
typeof false                  // 返回 boolean
typeof [1,2,3,4]              // 返回 object
typeof {name:'John', age:34}  // 返回 object
typeof new Date()             // 返回 object
typeof function () {}         // 返回 function
typeof myCar                  // 返回 undefined (如果 myCar 没有声明)
typeof null                   // 返回 object
```

- NaN 的数据类型是 number
- 数组(Array)的数据类型是 object
- 日期(Date)的数据类型为 object
- null 的数据类型是 object
- 未定义变量的数据类型为 undefined

如果对象是 JavaScript Array 或 JavaScript Date ，我们就无法通过 **typeof** 来判断他们的类型，因为都是 返回 object。

### constructor 属性

**constructor** 属性返回所有 JavaScript 变量的构造函数。

```javascript
"John".constructor                 // 返回函数 String()  { [native code] }
(3.14).constructor                 // 返回函数 Number()  { [native code] }
false.constructor                  // 返回函数 Boolean() { [native code] }
[1,2,3,4].constructor              // 返回函数 Array()   { [native code] }
{name:'John', age:34}.constructor  // 返回函数 Object()  { [native code] }
new Date().constructor             // 返回函数 Date()    { [native code] }
function () {}.constructor         // 返回函数 Function(){ [native code] }
```

全局方法 **String()** 可以将数字转换为字符串。Number 方法 **toString()** 也是有同样的效果。

```javascript
String(123)       // 将数字 123 转换为字符串并返回
x.toString()
(123).toString()
(100 + 23).toString()
```

## 正则表达式

使用单个字符串来描述、匹配一系列符合某个句法规则的字符串搜索模式。

### 语法

```
/正则表达式主体/修饰符(可选)
var patt = /runoob/i
/runoob/i  是一个正则表达式。
runoob  是一个正则表达式主体 (用于检索)。
i  是一个修饰符 (搜索不区分大小写)。
```

在 JavaScript 中，正则表达式通常用于两个字符串方法 : search() 和 replace()。

**search()** 方法用于检索字符串中指定的子字符串，或检索与正则表达式相匹配的子字符串，并返回子串的起始位置。

**replace()** 方法用于在字符串中用一些字符串替换另一些字符串，或替换一个与正则表达式匹配的子串。

```javascript
var str = "Visit Runoob!"; 
var n = str.search(/Runoob/i//正则
//输出结果：6
var str = "Visit Runoob!"; 
var n = str.search("Runoob");//字符串，字符串参数会转换为正则表达式。
//输出结果：6
```

正则表达式参数可用在以上方法中 (替代字符串参数)。
正则表达式使得搜索功能更加强大(如实例中不区分大小写)。

**修饰符** 可以在全局搜索中不区分大小写:

| 修饰符 | 描述                                                     |
| :----- | :------------------------------------------------------- |
| i      | 执行对大小写不敏感的匹配。                               |
| g      | 执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。 |
| m      | 执行多行匹配。                                           |

| 表达式 | 描述                       |
| :----- | :------------------------- |
| [abc]  | 查找方括号之间的任何字符。 |
| [0-9]  | 查找任何从 0 至 9 的数字。 |
| (x\|y) | 查找任何以 \| 分隔的选项。 |

方括号用于查找某个范围内的字符

元字符是拥有特殊含义的字符：

| 元字符 | 描述                                        |
| :----- | :------------------------------------------ |
| \d     | 查找数字。                                  |
| \s     | 查找空白字符。                              |
| \b     | 匹配单词边界。                              |
| \uxxxx | 查找以十六进制数 xxxx 规定的 Unicode 字符。 |

量词:

| 量词 | 描述                                  |
| :--- | :------------------------------------ |
| n+   | 匹配任何包含至少一个 *n* 的字符串。   |
| n*   | 匹配任何包含零个或多个 *n* 的字符串。 |
| n?   | 匹配任何包含零个或一个 *n* 的字符串。 |

test() 方法是一个正则表达式方法。

test() 方法用于检测一个字符串是否匹配某个模式，如果字符串中含有匹配的文本，则返回 true，否则返回 false。

以下实例用于搜索字符串中的字符 "e"：

```javascript
var patt = /e/;
patt.test("The best things in life are free!");
//字符串中含有 "e"，所以该实例输出为true
```

exec() 方法是一个正则表达式方法。

exec() 方法用于检索字符串中的正则表达式的匹配。

该函数返回一个数组，其中存放匹配的结果。如果未找到匹配，则返回值为 null。

以下实例用于搜索字符串中的字母 "e":

```javascript
/e/.exec("The best things in life are free!");
```

### 判断输入的字符串是否为数字，字母和下划线

```javascript
//判断是否为字母
val2 = "asaaa"
var isletter2 = /^[a-zA-Z]+$/.test(val2);
document.write(isletter2);
//判断是否为数字
val = "123456"
var isnum = /^\d+$/.test(val);
document.write(isnum);
document.write("<br>");

//判断是否是字母，数字，下划线
function isValid(str)
{ return /^\w+$/.test(str); }
//\w 元字符用于查找单词字符，即从 a 到 z、A 到 Z、0 到 9 的字符。它与 [a-zA-Z_0-9] 相同。
str = "1234abd__"
document.write(isValid(str));
document.write("<br>");
```

## 注意事项

### 浮点

JavaScript 中的所有数据都是以 64 位**浮点型数据(float)** 来存储。

所有的编程语言，包括 JavaScript，对浮点型数据的精确度都很难确定

```javascript
var x = 0.1;
var y = 0.2;
var z = x + y            // z 的结果为 0.30000000000000004
if (z == 0.3)            // 返回 false
//可以使用乘法解决
var z = (x * 10 + y * 10) / 10;       // z 的结果为 0.3
```

### null和undefined

在 JavaScript 中, **null** 用于对象, **undefined** 用于变量，属性和方法。

对象只有被定义才有可能为 null，否则为 undefined。

如果我们想测试对象是否存在，在对象还没定义时将会抛出一个错误。

```javascript
if (typeof myObj !== "undefined" && myObj !== null) 
```

## 表单

### 表单验证

HTML 表单验证可以通过 JavaScript 来完成。

以下实例代码用于判断表单字段(fname)值是否存在， 如果不存在，就弹出信息，阻止表单提交

```javascript
function validateForm() {
    var x = document.forms["myForm"]["fname"].value;
    if (x == null || x == "") {
        alert("需要输入名字。");
        return false;
    }
}
```

以上 JavaScript 代码可以通过 HTML 代码来调用

```javascript
<form name="myForm" action="demo_form" onsubmit="return validateForm()" method="post">
名字: <input type="text" name="fname">
<input type="submit" value="提交">
</form>
```

### HTML表达自动验证

HTML 表单验证也可以通过浏览器来自动完成。

如果表单字段 (fname) 的值为空, **required** 属性会阻止表单提交：

```javascript
<form action="demo_form.php" method="post">
  <input type="text" name="fname" required="required">
  <input type="submit" value="提交">
</form>
```

### 数据验证

数据验证用于确保用户输入的数据是有效的。

典型的数据验证有：

- 必需字段是否有输入?
- 用户是否输入了合法的数据?
- 在数字字段是否输入了文本?

大多数情况下，数据验证用于确保用户正确输入数据。

数据验证可以使用不同方法来定义，并通过多种方式来调用。

**服务端数据验证**是在数据提交到服务器上后再验证。

**客户端数据验证**是在数据发送到服务器前，在浏览器上完成验证。

### HTML约束认证

HTML5 新增了 HTML 表单的验证方式：约束验证（constraint validation）。

约束验证是表单被提交时浏览器用来实现验证的一种算法。

HTML 约束验证基于：

- **HTML 输入属性**
- **CSS 伪类选择器**
- **DOM 属性和方法**

约束验证 HTML 输入属性

| 属性     | 描述                     |
| :------- | :----------------------- |
| disabled | 规定输入的元素不可用     |
| max      | 规定输入元素的最大值     |
| min      | 规定输入元素的最小值     |
| pattern  | 规定输入元素值的模式     |
| required | 规定输入元素字段是必需的 |
| type     | 规定输入元素的类型       |

约束验证 CSS 伪类选择器

| 选择器    | 描述                                    |
| :-------- | :-------------------------------------- |
| :disabled | 选取属性为 "disabled" 属性的 input 元素 |
| :invalid  | 选取无效的 input 元素                   |
| :optional | 选择没有"optional"属性的 input 元素     |
| :required | 选择有"required"属性的 input 元素       |
| :valid    | 选取有效值的 input 元素                 |

### 验证API

约束验证 DOM 方法

| Property            | Description                                                  |
| :------------------ | :----------------------------------------------------------- |
| checkValidity()     | 如果 input 元素中的数据是合法的返回 true，否则返回 false。   |
| setCustomValidity() | 设置 input 元素的 validationMessage 属性，用于自定义错误提示信息的方法。使用 setCustomValidity 设置了自定义提示后，validity.customError 就会变成 true，checkValidity 总是会返回 false。如果要重新判断需要取消自定义提示，方式如下：`setCustomValidity('')  setCustomValidity(null)  setCustomValidity(undefined)` |

以下实例如果输入信息不合法，则返回错误信息

```html
<input id="id1" type="number" min="100" max="300" required>
<button onclick="myFunction()">验证</button>

<p>如果输入的数字小于 100 或大于300，会提示错误信息。</p>

<p id="demo"></p>

<script>
function myFunction() {
    var inpObj = document.getElementById("id1");
    if (inpObj.checkValidity() == false) {
        document.getElementById("demo").innerHTML = inpObj.validationMessage;
    } else {
        document.getElementById("demo").innerHTML = "输入正确";
    }
}
</script>
```

## This

面向对象语言中 this 表示当前对象的一个引用。

但在 JavaScript 中 this 不是固定不变的，它会随着执行环境的改变而改变。

- 在方法中，this 表示该方法所属的对象。
- 如果单独使用，this 表示全局对象。
- 在函数中，this 表示全局对象。
- 在函数中，在严格模式下，this 是未定义的(undefined)。
- 在事件中，this 表示接收事件的元素。
- 类似 call() 和 apply() 方法可以将 this 引用到任何对象。

## let和const

let 声明的变量只在 let 命令所在的代码块内有效。

const 声明一个只读的常量，一旦声明，常量的值就不能改变。



JavaScript 块级作用域(Block Scope)

使用 var 关键字声明的变量不具备块级作用域的特性，它在 {} 外依然能被访问到。

```
{ 
    var x = 2; 
}
// 这里可以使用 x 变量
```

在 ES6 之前，是没有块级作用域的概念的。

ES6 可以使用 let 关键字来实现块级作用域。

let 声明的变量只在 let 命令所在的代码块 **{}** 内有效，在 **{}** 之外不能访问。

```
{ 
    let x = 2;
}
// 这里不能使用 x 变量
```

## JSON

（**J**ava**S**cript **O**bject **N**otation）

JSON 是用于存储和传输数据的格式。

JSON 通常用于服务端向网页传递数据 。

### 语法

- 数据为 键/值 对。
- 数据由逗号分隔。
- 大括号保存对象
- 方括号保存数组

```json
{"sites"
 :
 [
    {"name":"Runoob", "url":"www.runoob.com"}, 
    {"name":"Google", "url":"www.google.com"},
    {"name":"Taobao", "url":"www.taobao.com"}
]
}
```

JSON 数据格式为 键/值 对，就像 JavaScript 对象属性。

键/值对包括字段名称（在双引号中），后面一个冒号，然后是值：

```json
"name":"Runoob"
```

JSON 对象保存在大括号内。

就像在 JavaScript 中, 对象可以保存多个 键/值 对，通过逗号 ， 来隔开。

```json
{"name":"Runoob", "url":"www.runoob.com"}
```

JSON 数组保存在中括号内。

就像在 JavaScript 中, 数组可以包含对象：

```json
"sites"
:
[
    {"name":"Runoob", "url":"www.runoob.com"}, 
    {"name":"Google", "url":"www.google.com"},
    {"name":"Taobao", "url":"www.taobao.com"}
]
```

### JSON 转JS 对象

通常我们从服务器中读取 JSON 数据，并在网页中显示数据。

简单起见，我们网页中直接设置 JSON 字符串 

首先，创建 JavaScript 字符串，字符串为 JSON 格式的数据

```html
<p id="demo"></p>
<script>
    var text = '{"testall":[{"liyanjun":"2434"},{"liyanni":"12"}]}';
    obj = JSON.parse(text);
    document.getElementById("demo").innerHTML = obj.testall[0].liyanjun + " " + obj.testall[1].liyanni;
</script>
```

相关函数

| 函数             | 描述                                           |
| :--------------- | :--------------------------------------------- |
| JSON.parse()     | 用于将一个 JSON 字符串转换为 JavaScript 对象。 |
| JSON.stringify() | 用于将 JavaScript 值转换为 JSON 字符串         |

## void

 **void** 是 JavaScript 中非常重要的关键字，该操作符指定要计算一个表达式但是不返回值。

在用户点击链接后显示警告信息:

```html
<p>点击以下链接查看结果：</p>
<a href="javascript:void(alert('Warning!!!'))">点我!</a>
```

以下实例中参数 a 将返回 undefined :

```javascript
function getValue(){
   var a,b,c;
   a = void ( b = 5, c = 7 );
   document.write('a = ' + a + ' b = ' + b +' c = ' + c );
}
```

href="#"与href="javascript:void(0)"的区别

**#** 包含了一个位置信息，默认的锚是**#top** 也就是网页的上端。

而javascript:void(0), 仅仅表示一个死链接。

在页面很长的时候会使用 **#** 来定位页面的具体位置，格式为：**# + id**。

如果你要定义一个死链接请使用 javascript:void(0) 。

```html
<a href="javascript:void(0);">点我没有反应的!</a>
<a href="#pos">点我定位到指定位置!</a>
<br>
...
<br>
<p id="pos">尾部定位点</p>
```

## 异步编程

使用分线程来分担主线程的任务。

### 回调函数

回调函数就是一个函数，它是在我们启动一个异步任务的时候就告诉它：等你完成了这个任务之后要干什么。这样一来主线程几乎不用关心异步任务的状态了，他自己会善始善终。

```javascript
function print() {
    document.getElementById("demo").innerHTML="RUNOOB!";
}
setTimeout(print, 3000);
//这段程序中的 setTimeout 就是一个消耗时间较长（3 秒）的过程，它的第一个参数是个回调函数，第二个参数是毫秒数，这个函数执行之后会产生一个子线程，子线程会等待 3 秒，然后执行回调函数 "print"，在命令行输出 "RUNOOB!"。
```

## 异步 AJAX

除了 setTimeout 函数以外，异步回调广泛应用于 AJAX 编程。

XMLHttpRequest 常常用于请求来自远程服务器上的 XML 或 JSON 数据。一个标准的 XMLHttpRequest 对象往往包含多个回调：

```javascript
var xhr = new XMLHttpRequest();
 
//onload,在请求成功时调用
xhr.onload = function () {
    // 输出接收到的文字数据
    document.getElementById("demo").innerHTML=xhr.responseText;
}
//onerror,在请求失败时调用
xhr.onerror = function () {
    document.getElementById("demo").innerHTML="请求出错";
}
 
// 发送异步 GET 请求
xhr.open("GET", "https://www.runoob.com/try/ajax/ajax_info.txt", true);
xhr.send();
```

XMLHttpRequest 的 onload 和 onerror 属性都是函数，分别在它请求成功和请求失败时被调用。如果你使用完整的 jQuery 库，也可以更加优雅的使用异步 AJAX：

```javascript
$.get("https://www.runoob.com/try/ajax/demo_test.php",function(data,status){
    alert("数据: " + data + "\n状态: " + status);
});
```

## Promise

Promise 是一个 ECMAScript 6 提供的类，目的是更加优雅地书写复杂的异步任务。多次调用异步函数。

构建 Promise

```javascript
new Promise(function (resolve, reject) {
    // 要做的事情...
});
```

分三次输出字符串，第一次间隔 1 秒，第二次间隔 4 秒，第三次间隔 3 秒

```javascript
//不推荐
setTimeout(function () {
    console.log("First");
    setTimeout(function () {
        console.log("Second");
        setTimeout(function () {
            console.log("Third");
        }, 3000);
    }, 4000);
}, 1000);
```

```javascript
//推荐
new Promise(function (resolve, reject) {
    setTimeout(function () {
        console.log("First");
        resolve();
    }, 1000);
}).then(function () {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            console.log("Second");
            resolve();
        }, 4000);
    });
}).then(function () {
    setTimeout(function () {
        console.log("Third");
    }, 3000);
});
```

promise的使用

Promise 构造函数只有一个参数，是一个函数，这个函数在构造之后会直接被异步运行，所以我们称之为起始函数。起始函数包含两个参数 resolve 和 reject。

当 Promise 被构造时，起始函数会被异步执行：

```javascript
new Promise(function (resolve, reject) {
    console.log("Run");
});
```

resolve 和 reject 都是函数，其中调用 resolve 代表一切正常，reject 是出现异常时所调用的。

Promise 类有 .then() .catch() 和 .finally() 三个方法，这三个方法的参数都是一个函数，.then() 可以将参数中的函数添加到当前 Promise 的正常执行序列，.catch() 则是设定 Promise 的异常处理序列，.finally() 是在 Promise 执行的最后一定会执行的序列。 .then() 传入的函数会按顺序依次执行，有任何异常都会直接跳到 catch 序列

```javascript
new Promise(function (resolve, reject) {
    var a = 0;
    var b = 1;
    if (b == 0) reject("Divide zero");
    else resolve(a / b);
}).then(function (value) {
    console.log("a / b = " + value);
}).catch(function (err) {
    console.log(err);
}).finally(function () {
    console.log("End");
});
```

resolve() 中可以放置一个参数用于向下一个 then 传递一个值，then 中的函数也可以返回一个值传递给 then。但是，如果 then 中返回的是一个 Promise 对象，那么下一个 then 将相当于对这个返回的 Promise 进行操作，这一点从刚才的计时器的例子中可以看出来。

reject() 参数中一般会传递一个异常给之后的 catch 函数用于处理异常。

但是请注意以下两点：

- resolve 和 reject 的作用域只有起始函数，不包括 then 以及其他序列；
- resolve 和 reject 并不能够使起始函数停止运行，别忘了 return。

# JavaScript库

JavaScript 框架：动画、DOM 操作以及 Ajax 处理

- jQuery
- Prototype
- MooTools

## JQuery

**jQuery** 是目前最受欢迎的 JavaScript 框架。

它使用 CSS 选择器来访问和操作网页上的 HTML 元素（DOM 对象）。

jQuery 同时提供 companion UI（用户界面）和插件。

许多大公司在网站上使用 jQuery。

```html
https://cdn.staticfile.org/jquery/3.4.0/jquery.min.js
<script src="https://cdn.staticfile.org/jquery/3.4.0/jquery.min.js"></script>
```

主要的 jQuery 函数是 $() 函数（jQuery 函数）。如果您向该函数传递 DOM 对象，它会返回 jQuery 对象，带有向其添加的 jQuery 功能。

jQuery 允许您通过 CSS 选择器来选取元素。

在 JavaScript 中，您可以分配一个函数以处理窗口加载事件：

```javascript
//普通javaSrcipt
function myFunction()
{
    var obj=document.getElementById("h01");
    obj.innerHTML="Hello jQuery";
}
onload=myFunction;
//JQuery方式
function myFunction()
{
    $("#h01").html("Hello jQuery");
}
$(document).ready(myFunction);
```

HTML DOM 文档对象被传递到 jQuery ：$(document)。

当您向 jQuery 传递 DOM 对象时，jQuery 会返回以 HTML DOM 对象包装的 jQuery 对象。

jQuery 函数会返回新的 jQuery 对象，其中的 ready() 是一个方法。

由于在 JavaScript 中函数就是变量，因此可以把 myFunction 作为变量传递给 jQuery 的 ready 方法。

```html
<script>
    function myfunction() {
        $("#test1").html("TestJQuery")
    }
    $(document).ready(myfunction);
</script>
<p id="test1"></p>
```

### 语法

通过 jQuery，您可以选取（查询，query） HTML 元素，并对它们执行"操作"（actions）。

基础语法： **$(selector).action()**

- 美元符号定义 jQuery
- 选择符（selector）"查询"和"查找" HTML 元素，例如html元素的id。
- jQuery 的 action() 执行对元素的操作

实例:

- $(this).hide() - 隐藏当前元素
- $("p").hide() - 隐藏所有 <p> 元素
- $("p.test").hide() - 隐藏所有 class="test" 的 <p> 元素
- $("#test").hide() - 隐藏 id="test" 的元素

所有 jQuery 函数位于一个 document ready 函数中，只有通过ready函数进入JQuery。

```javascript
$(document).ready(function(){
    // 执行代码
});
//简单写法
$(function(){
   // 开始写 jQuery 代码...
});
```

### 选择器

## 更多实例

| 语法                     | 描述                                                    |
| :----------------------- | :------------------------------------------------------ |
| $("*")                   | 选取所有元素                                            |
| $(this)                  | 选取当前 HTML 元素                                      |
| $("p.intro")             | 选取 class 为 intro 的 <p> 元素                         |
| $("p:first")             | 选取第一个 <p> 元素                                     |
| $("ul li:first")         | 选取第一个 <ul> 元素的第一个 <li> 元素                  |
| $("ul li:first-child")   | 选取每个 <ul> 元素的第一个 <li> 元素                    |
| $("[href]")              | 选取带有 href 属性的元素                                |
| $("a[target='_blank']")  | 选取所有 target 属性值等于 "_blank" 的 <a> 元素         |
| $("a[target!='_blank']") | 选取所有 target 属性值不等于 "_blank" 的 <a> 元素       |
| $(":button")             | 选取所有 type="button" 的 <input> 元素 和 <button> 元素 |
| $("tr:even")             | 选取偶数位置的 <tr> 元素                                |
| $("tr:odd")              | 选取奇数位置的 <tr> 元素                                |

### 事件

页面对不同访问者的响应叫做事件。

事件处理程序指的是当 HTML 中发生某些事件时所调用的方法。

实例：

- 在元素上移动鼠标。
- 选取单选按钮
- 点击元素

在事件中经常使用术语"触发"（或"激发"）例如： "当您按下按键时触发 keypress 事件"。

常见 DOM 事件：

| 鼠标事件                          | 键盘事件 | 表单事件 | 文档/窗口事件 |
| :-------------------------------- | :------- | :------- | :------------ |
| click                             | keypress | submit   |               |
| dblclick（双击）                  | keydown  | change   | resize        |
| mouseenter（鼠标穿过，进入元素）  | keyup    | focus    | scroll        |
| mouseleave（鼠标离开）            |          | blur     |               |
| hover（鼠标指针悬停在被选元素上） |          |          |               |

```javascript
//例子:当点击事件在某个 <p> 元素上触发时，隐藏当前的 <p> 元素
$("p").click(function(){
  $(this).hide();
});
```

## Prototype

**Prototype** 是一种库，提供用于执行常见 web 任务的简单 API。

**API** 是应用程序编程接口（Application Programming Interface）的缩写。它是包含属性和方法的库，用于操作 HTML DOM。

Prototype 通过提供类和继承，实现了对 JavaScript 的增强。

## Moo Tools

**MooTools** 也是一个框架，提供了可使常见的 JavaScript 编程更为简单的 API。

MooTools 也含有一些轻量级的效果和动画函数。

# JQuery-AJAX

AJAX 是与服务器交换数据的技术，它在不重载全部页面的情况下，实现了对部分网页的更新.

## load方法

jQuery load() 方法是简单但强大的 AJAX 方法。

load() 方法从服务器加载数据，并把返回的数据放入被选元素中。

### 语法

```javascript
$(selector).load(URL,data,callback);
```

必需的 URL 参数规定您希望加载的 URL。

可选的 data 参数规定与请求一同发送的查询字符串键/值对集合。

可选的 callback 参数是 load() 方法完成后所执行的函数名称。

## Get()和Post()方法

两种在客户端和服务器端进行请求-响应的常用方法是：GET 和 POST。

- *GET* - 从指定的资源请求数据
- *POST* - 向指定的资源提交要处理的数据

GET 基本上用于从服务器获得（取回）数据。注释：GET 方法可能返回缓存数据。

POST 也可用于从服务器获取数据。不过，POST 方法不会缓存数据，并且常用于连同请求一起发送数据。

```javascript
//get方法
$.get(URL,callback);
$.get( URL [, data ] [, callback ] [, dataType ] )
//URL：发送请求的 URL字符串。
//data：可选的，发送给服务器的字符串或 key/value 键值对。
//callback：可选的，请求成功后执行的回调函数。
//dataType：可选的，从服务器返回的数据类型。默认：智能猜测（可以是xml, json, script, 或 html）。

//post方法
$.post(URL,callback);
```

```javascript
$("button").click(function(){
  $.get("demo_test.java",function(data,status){
    alert("数据: " + data + "\n状态: " + status);
  });
});
```

```javascript
$("button").click(function(){
    $.post("/try/ajax/demo_test_post.java",
    {
        name:"菜鸟教程",
        url:"http://www.runoob.com"
    },
    function(data,status){
        alert("数据: \n" + data + "\n状态: " + status);
    });
});
```

**GET 和 POST 方法的区别**：

**1、发送的数据数量**

在 GET 中，只能发送有限数量的数据，因为数据是在 URL 中发送的。

在 POST 中，可以发送大量的数据，因为数据是在正文主体中发送的。

**2、安全性**

GET 方法发送的数据不受保护，因为数据在 URL 栏中公开，这增加了漏洞和黑客攻击的风险。

POST 方法发送的数据是安全的，因为数据未在 URL 栏中公开，还可以在其中使用多种编码技术，这使其具有弹性。

**3、加入书签中**

GET 查询的结果可以加入书签中，因为它以 URL 的形式存在；而 POST 查询的结果无法加入书签中。

**4、编码**

在表单中使用 GET 方法时，数据类型中只接受 ASCII 字符。

在表单提交时，POST 方法不绑定表单数据类型，并允许二进制和 ASCII 字符。

**5、可变大小**

GET 方法中的可变大小约为 2000 个字符。

POST 方法最多允许 8 Mb 的可变大小。

**6、缓存**

GET 方法的数据是可缓存的，而 POST 方法的数据是无法缓存的。

**7、主要作用**

GET 方法主要用于获取信息。而 POST 方法主要用于更新数据。