# 三大特性

## 封装

封装为一个类

```java
public class User {
    private int ID;
    private String username;
    private String userpass;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(String username, String userpass, String email) {
        this.username = username;
        this.userpass = userpass;
        this.email = email;
    }
    public User(int ID, String username, String userpass, String email) {
        this.ID = ID;
        this.username = username;
        this.userpass = userpass;
        this.email = email;
    }

    public User() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

## 继承

​	就是父类和子类，子类对象必须能够替换掉所有父类对象。子类可以继承父类的所以非private的对象。

​	父类也可以引用子类的对象。这称位向上转型

### 访问权限

Java 中有三个访问权限修饰符: private、protected 以及 public，如果不加访问修饰符，表示包级可见。

可以对类或类中的成员(字段以及方法)加上访问修饰符。

- 类可见表示其它类可以用这个类创建实例对象。
- 成员可见表示其它类可以用这个类的实例对象访问到该成员；

protected 用于修饰成员，表示在继承体系中成员对于子类可见，但是这个访问修饰符对于类没有意义。

如果子类的方法重写了父类的方法，那么子类中该方法的访问级别不允许低于父类的访问级别。这是为了确保可以使用父类实例的地方都可以使用子类实例，也就是确保满足里氏替换原则。

大多数使用private修饰符，然后通过setter和getter方法来替换共有字段。

### 抽象类与接口

#### 1.抽象类

抽象类和抽象方法都使用 abstract 关键字来进行声明。抽象类一般会包含抽象方法，抽象方法一定位于抽象类中。

抽象类不能被实例化，需要子类继承抽象类，才能将其实例化

#### 2.接口

接口无法被实例化，但是可以被实现。一个实现接口的类，必须实现接口内所描述的所有方法，否则就必须声明为抽象类。接口的实现使用implements关键字

```java
interface Animal {
   public void eat();
   public void travel();
}

public class MammalInt implements Animal{
 //实现接口就需要实现接口的所有方法
   public void eat(){
      System.out.println("Mammal eats");
   }
 
   public void travel(){
      System.out.println("Mammal travels");
   } 
 
   public int noOfLegs(){
      return 0;
   }
 
   public static void main(String args[]){
      MammalInt m = new MammalInt();
      m.eat();
      m.travel();
   }
}
```

接口的成员(字段 + 方法)默认都是 public 的，并且不允许定义为 private 或者 protected。

接口的字段默认都是 static 和 final 的。

#### 3.比较

- 从设计层面上看，抽象类提供了一种 IS-A 关系，那么就必须满足里式替换原则，即子类对象必须能够替换掉所有父类对象。而接口更像是一种 LIKE-A 关系，它只是提供一种方法实现契约，并不要求接口和实现接口的类具有 IS-A 关系。
- 从使用上来看，一个类可以实现多个接口，但是不能继承多个抽象类。
- 接口的字段只能是 static 和 final 类型的，而抽象类的字段没有这种限制。
- 接口的成员只能是 public 的，而抽象类的成员可以有多种访问权限。

#### 4.使用选择

使用接口:

- 需要让不相关的类都实现一个方法，例如不相关的类都可以实现 Compareable 接口中的 compareTo() 方法；
- 需要使用多重继承。

使用抽象类:

- 需要在几个相关的类中共享代码。
- 需要能控制继承来的成员的访问权限，而不是都为 public。
- 需要继承非静态和非常量字段。

在很多情况下，**接口优先于抽象类**，因为接口没有抽象类严格的类层次结构要求，可以灵活地为一个类添加行为。并且从 Java 8 开始，接口也可以有默认的方法实现，使得修改接口的成本也变的很低。

### super

访问父类的构造函数: 可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。

访问父类的成员: 如果子类重写了父类的中某个方法的实现，可以通过使用 super 关键字来引用父类的方法实现。

```java
public class SuperExample {
    protected int x;
    protected int y;

    public SuperExample(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void func() {
        System.out.println("SuperExample.func()");
    }
}

//子类继承父类
public class SuperExtendExample extends SuperExample {
    private int z;

    public SuperExtendExample(int x, int y, int z) {
        super(x, y); //引用父类的方法
        this.z = z;
    }

    @Override
    public void func() {
        super.func();
        System.out.println("SuperExtendExample.func()");
    }
}


```

### 重写与重载

**1. 重写(Override)**

存在于继承体系中，指子类实现了一个与父类在方法声明上完全相同的一个方法。

为了满足里式替换原则，重写有以下两个限制:

- 子类方法的访问权限必须大于等于父类方法；
- 子类方法的返回类型必须是父类方法返回类型或为其子类型。

使用 @Override 注解，可以让编译器帮忙检查是否满足上面的两个限制条件。

**2. 重载(Overload)**

存在于同一个类中，指一个方法与已经存在的方法名称上相同，但是参数类型、个数、顺序至少有一个不同。（例如类中的构造函数就是可以重载，无参构造和其他的构造函数）

应该注意的是，返回值不同，其它都相同不算是重载。

## 多态

多态分为运行时多态和编译时多态

运行时多态是程序定义的对象在运行才确定类型

编译多态是指方法的重载

运行时多态有三个条件：

- 继承

- 重写

- 向上转型

```java
public class Instrument {
    public void play() {
        System.out.println("Instrument is playing...");
    }
}

public class Wind extends Instrument {
    public void play() {
        System.out.println("Wind is playing...");
    }
}

public class Percussion extends Instrument {
    public void play() {
        System.out.println("Percussion is playing...");
    }
}

public class Music {
    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Wind());
        instruments.add(new Percussion());
        for(Instrument instrument : instruments) {
            instrument.play();
        }
    }
}
  
```

# 基础知识点

## 数据类型

### 基本类型

- boolean/1
- byte/8
- char/16
- short/16
- int/32    包装类型是Ingeter
- float/32
- long/64
- double/64

基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成

```java
Integer x = 2;     // 装箱  装箱就是自动将基本数据类型转换为包装器类型
int y = x;         // 拆箱  拆箱就是自动将包装器类型转换为基本数据类型。
```

### 引用类型

类、接口类型、数组类型、枚举类型、注解类型。

### 区别

基本数据类型在被创建时，在栈上给其划分一块内存，将数值直接存储在栈上。
引用数据类型在被创建时，首先要在栈上给其引用（句柄）分配一块内存，而对象的具体信息都存储在堆内存上，然后由栈上面的引用指向堆中对象的地址。

静态区： 保存自动全局变量和 static 变量（包括 static 全局和局部变量）。静态区的内容在总个程序的生命周期内都存在，由编译器在编译的时候分配。
堆区： 一般由程序员分配释放，由 malloc 系列函数或 new 操作符分配的内存，其生命周期由 free 或 delete 决定。在没有释放之前一直存在，直到程序结束，由OS释放。其特点是使用灵活，空间比较大，但容易出错
栈区： 由编译器自动分配释放，保存局部变量，栈上的内容只在函数的范围内存在，当函数运行结束，这些内容也会自动被销毁，其特点是效率高，但空间大小有限
文字常量区： 常量字符串就是放在这里的。 程序结束后由系统释放。

## 缓存池

new Integer(12)和Intege.valueOf(12)的区别：

- new Integer(12)每次都会创建一个新的对象
- Intege.valueOf(12)会使用缓存池中的对象，每次调用都会取得同一个对象的引用

```java
Integer x = new Integer(123);
Integer y = new Integer(123);
System.out.println(x == y);    // false
Integer z = Integer.valueOf(123);
Integer k = Integer.valueOf(123);
System.out.println(z == k);   // true
```

valueOf()的实现是先判断值是否在缓存池中，如果是就返回缓存池中的内容

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

编译器会**在缓冲池范围内的基本类型**自动装箱过程调用 valueOf() 方法，因此多个 Integer 实例使用自动装箱来创建并且值相同，那么就会引用相同的对象。

## String

String 被声明为 final，因此它不可被继承。

内部使用 char 数组存储数据，该数组被声明为 final，这意味着 value 数组初始化之后就不能再引用其它数组。并且 String 内部没有改变 value 数组的方法，因此可以保证 String 不可变。

不可变的好处：

1. 可以缓存hash值

   因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。

2. String Pool的需要

   如果一个String对象已经被创建过了，那么就会从String Pool中取得应用

3. 安全性

   String 经常作为参数，String 不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变，改变 String 对象的那一方以为现在连接的是其它主机，而实际情况却不一定是。

   还有数据库，String不变可保证数据库名，密码，url等不变。

4. 线程安全

   可在多个线程安全使用。

### String, StringBuffer and StringBuilder

1. 可变性

   - String不可变

   - StringBuffer和StringBuilder可变

2. 线程安全

   - String不可变，是线程安全的
   - StringBuilder不是线程安全的
   - StringBuffer是线程安全的，内部使用 synchronized 进行同步

StringBuilder也是一个类，但是它能够存储可变长度的字符串！

```java
StringBuilder builder = new StringBuilder();
builder
       .append("a")
       .append("bc")
       .append("d");   //链式调用
String str = builder.toString();
System.out.println(str);
```

### String.intern

使用 String.intern() 可以保证相同内容的字符串变量引用同一的内存对象。

```java
String s1 = new String("aaa");
String s2 = new String("aaa");
System.out.println(s1 == s2);           // false
String s3 = s1.intern();
System.out.println(s1.intern() == s3);  // true
//双引号会直接将对象放入String Pool中
String s4 = "bbb";
String s5 = "bbb";
System.out.println(s4 == s5);  // true
```

## 运算

### 参数传递

java的参数是以值传递的形式传入方法中，而不是引用传递。将对象的地址以值的方式传递到形参中。

### 运算符号

```java
short i = 10;
short j = 10;
i += 10;  //对
i = i+10;//报错

short k =i + j;//会报错，因为10为int类型的数据需要强制转换
short k = (short)i+j;//对

```



### float和double

1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。Java 不能隐式执行向下转型，因为这会使得精度降低。1.1f 字面量才是 float 类型。

```java
float f=1.1f;
//float f = 1.1  错误的，1.1是double类型，这是向下转型，精度会降低
```

### 隐式类型转换

精度低的向精度高的才可以进行隐式类型转换

```java
short s1 = 1;  //short 16
s1 += 1;
就是
s1 = (short) (s1 + 1);
```

### switch

可在switch中对字符串进行判断，但是不能对long类型进行判断

```java
String s = "a";
switch (s) {
    case "a":
        System.out.println("aaa");
        break;
    case "b":
        System.out.println("bbb");
        break;
}

```

## Object 通用方法

object类是所有类的父类

```java
public final native Class<?> getClass()

public native int hashCode()

public boolean equals(Object obj)
    /**
    等价关系，判断两个值是否相同。
    **/

protected native Object clone() throws CloneNotSupportedException

public String toString()

public final native void notify()

public final native void notifyAll()

public final native void wait(long timeout) throws InterruptedException

public final void wait(long timeout, int nanos) throws InterruptedException

public final void wait() throws InterruptedException

protected void finalize() throws Throwable {}

```

### 1.equals

(1)等价关系

- 自反性

  ```java
  x.equals(x); // true
  ```

- 对称性

  ```java
  x.equals(y) == y.equals(x); // true
  ```

- 传递性

  ```java
  if (x.equals(y) && y.equals(z))
      x.equals(z); // true;
  ```

- 一致性

  多次调用 equals() 方法结果不变

  ```java
  x.equals(y) == x.equals(y); // true
  ```

  

- 与null比较

  ```java
  //对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false
  x.equals(null); // false;
  ```

（2）equals()和==

- 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
- 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。

（3）实现

- 检查是否为同一个对象的引用，如果是直接返回 true；

- 检查是否是同一个类型，如果不是，直接返回 false；

- 将 Object 对象进行转型；

- 判断每个关键域是否相等。

  ```java
  public class EqualExample {
      private int x;
      private int y;
      private int z;
  
      public EqualExample(int x, int y, int z) {
          this.x = x;
          this.y = y;
          this.z = z;
      }
  
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;//检查是否是同一个对象
          if (o == null || getClass() != o.getClass()) return false;
  			//检查是否是同一个类型
          EqualExample that = (EqualExample) o;
  
          if (x != that.x) return false;
          if (y != that.y) return false;
          return z == that.z;
      }
  }
  ```

### 2.hashcode

hashcode()返回散列值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价。

hashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的；

如果两个对象相同，就是适用于equals(java.lang.Object) 方法，那么这两个对象的hashCode一定要相同；

两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals(java.lang.Object) 方法，只能够说明这两个对象在散列存储结构中，如Hashtable，他们 **“存放在同一个篮子里”** 。

 **hashCode是用于查找使用的**，而 **equals是用于比较两个对象的是否相等的**。

理想的散列函数应当具有均匀性，即不相等的对象应当均匀分布到所有可能的散列值上。这就要求了散列函数要把所有域的值都考虑进来，可以将每个域都当成 R 进制的某一位，然后组成一个 R 进制的整数。R 一般取 31(  或者7 )，因为它是一个奇素数，如果是偶数的话，当出现乘法溢出，信息就会丢失，因为与 2 相乘相当于向左移一位。

一个数与 31 相乘可以转换成移位和减法: `31*x == (x<<5)-x`，编译器会自动进行这个优化

```java
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    result = 31 * result + z;
    return result;
}
```

### 3.toString()

默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

在数据库的entity，实体类中，要重写toString，不然select出来的是地址，重写toString就是查询的内容。

## 关键字

### final

**1.数据**

声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。

- 对于基本类型，final 使数值不变；
- 对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。

**2. 方法**

声明方法不能被子类重写。

private 方法隐式地被指定为 final，如果在子类中定义的方法和基类中的一个 private 方法签名相同，此时子类的方法不是重写基类方法，而是在子类中定义了一个新的方法。

**3. 类**

声明类不允许被继承。

### static

**1. 静态变量**

- 静态变量: 又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它；静态变量在内存中只存在一份。
- 实例变量: 每创建一个实例就会产生一个实例变量，它与该实例同生共死。

**2. 静态方法**

静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法(abstract)。

只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字。

**3. 静态语句块**

静态语句块在类初始化时运行一次。

```java
public class A {
    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
    }
}
//结果：123
```

**4. 静态内部类**

非静态内部类依赖于外部类的实例，而静态内部类不需要。

```java
public class OuterClass {
    class InnerClass {
    }

    static class StaticInnerClass {
    }

    public static void main(String[] args) {
        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
```

**6. 初始化顺序**

静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。

存在继承的情况下，初始化顺序为:

- 父类(静态变量、静态语句块)
- 子类(静态变量、静态语句块)
- 父类(实例变量、普通语句块)
- 父类(构造函数)
- 子类(实例变量、普通语句块)
- 子类(构造函数)

## 执行顺序

静态代码块>构造代码块>构造方法

原因 ：

- 静态代码块（**static{}**）在类加载的时候执行一次，是最早被执行的。
- 构造代码块（**{}内的部分**）在每一次创建对象时执行，始终在构造方法前执行。
- 构造方法在新建对象时调用（ 就是new的时候 ）。

静态代码块在类加载的时候就执行，所以它的优先级高于入口的main方法，当三种形式不止一次出现，同优先级是按照先后顺序执行

（1）执行程序入口main方法时，首先加载类StaticTest
注意：加载类时并不会调用构造块和构造方法，只有静态域会执行
（2）加载类StaticTest，执行静态域的第一个静态变量，StaticTest t1 = new StaticTest();，输出构造块和构造方法。
（3）由于每次new对象时，会执行一次构造块和构造方法，构造块总是在构造方法前执行，（当然，第一次new时，会先执行静态域，静态域〉构造块〉构造方法）所以执行StaticTest t1 = new StaticTest();时，执行了下面代码

### 静态域

静态域分为静态变量，静态方法，静态块，当执行到静态域的时候，按照静态域的顺序加载，并且静态域只在类第一次加载的时候执行

注意 加载了静态域的一部分，这个时候就不能加载另一个静态域了，静态域必需当成一个整体来对待

## 反射

JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

### **反射基础**

RTIT（Run-Time Type Identification）运行时类型识别。在《Thinking in Java》一书第十四章中有提到，其作用是在运行时识别一个对象的类型和类的信息。主要有两种方式：一种是“传统的”RTTI，它假定我们在编译时已经知道了所有的类型；另一种是“反射”机制，它允许我们在运行时发现和使用类的信息。

反射就是把java类中的各种成分映射成一个个的Java对象

例如：一个类有：成员变量、方法、构造方法、包等等信息，利用反射技术可以对一个类进行解剖，把个个组成部分映射成一个个对象。

### **class类**

Class类，Class类也是一个实实在在的类，存在于JDK的java.lang包中。Class类的实例表示java应用运行时的类(class ans enum)或接口(interface and annotation)（每个java类运行时都在JVM里表现为一个class对象，可通过类名.class、类型.getClass()、Class.forName("类名")等方法获取class对象）。数组同样也被映射为class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。基本类型boolean，byte，char，short，int，long，float，double和关键字void同样表现为 class  对象。

到这我们也就可以得出以下几点信息：

- Class类也是类的一种，与class关键字是不一样的。
- 手动编写的类被编译后会产生一个Class对象，其表示的是创建的类的类型信息，而且这个Class对象保存在同名.class的文件中(字节码文件)
- 每个通过关键字class标识的类，在内存中有且只有一个与之对应的Class对象来描述其类型信息，无论创建多少个实例对象，其依据的都是用一个Class对象。
- Class类只存私有构造函数，因此对应Class对象只能有JVM创建和加载
- Class类的对象作用是运行时提供或获得某个对象的类型信息，这点对于反射技术很重要(关于反射稍后分析)。

### 类加载

1. 类加载机制流程

2.类的加载

### 反射的使用

基类的加载我们如何通过反射获取Class类对象以及类中的成员变量、方法、构造方法等。

在Java中，Class类与java.lang.reflect类库一起对反射技术进行了全力的支持。在反射包中，我们常用的类主要有

- Constructor类表示的是Class 对象所表示的类的构造方法，利用它可以在运行时动态创建对象。

- Field表示Class对象所表示的类的成员变量，通过它可以在运行时动态修改成员变量的属性值(包含private）

- Method表示Class对象所表示的类的成员方法，通过它可以动态调用对象的方法(包含private)，下面将对这几个重要类进行分别说明。

### Class类对象的获取

在类加载的时候，jvm会创建一个class对象

class对象是可以说是反射中最常用的，获取class对象的方式的主要有三种

- 根据类名：类名.class
- 根据对象：对象.getClass()
- 根据全限定类名：Class.forName(全限定类名)

```java
    @Test
    public void classTest() throws Exception {
        // 获取Class对象的三种方式
        logger.info("根据类名:  \t" + User.class);
        logger.info("根据对象:  \t" + new User().getClass());
        logger.info("根据全限定类名:\t" + Class.forName("com.test.User"));
        // 常用的方法
        logger.info("获取全限定类名:\t" + userClass.getName());
        logger.info("获取类名:\t" + userClass.getSimpleName());
        logger.info("实例化:\t" + userClass.newInstance());
    }

    // ...
    package com.test;

    public class User {
        private String name = "init";
        private int age;
        public User() {}
        public User(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
        private String getName() {
            return name;
        }
        private void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "User [name=" + name + ", age=" + age + "]";
        }
    }
/**
输出结果：
根据类名:  	class com.test.User
根据对象:  	class com.test.User
根据全限定类名:	class com.test.User
获取全限定类名:	com.test.User
获取类名:	User
实例化:	User [name=init, age=0]
**/
```

class类的方法

 forName()     (1)获取Class对象的一个引用，但引用的类还没有加载(该类的第一个对象没有生成)就加载了这个类。 (2)为了产生Class引用，forName()立即就进行了初始化。 

 Object-getClass()   获取Class对象的一个引用，返回表示该对象的实际类型的Class引用。 getName() 取全限定的类名(包括包名)，即类的完整名字。 

getSimpleName() 	获取类名(不包括包名) 

getCanonicalName() 	获取全限定的类名(包括包名) isInterface() 判断Class对象是否是表示一个接口 getInterfaces() 返回Class对象数组，表示Class对象所引用的类所实现的所有接口。

 getSupercalss() 	返回Class对象，表示Class对象所引用的类所继承的直接基类。应用该方法可在运行时发现一个对象完整的继承结构。 

newInstance() 	返回一个Oject对象，是实现“虚拟构造器”的一种途径。使用该方法创建的类，必须带有无参的构造器。 

getFields() 	获得某个类的所有的公共（public）的字段，包括继承自父类的所有公共字段。 类似的还有getMethods和getConstructors。

 getDeclaredFields 	获得某个类的自己声明的字段，即包括public、private和proteced，默认但是不包括父类声明的任何字段。类似的还有getDeclaredMethods和getDeclaredConstructors。

### Constructor类及其用法

Constructor类存在于反射包(java.lang.reflect)中，反映的是Class 对象所表示的类的构造方法。

获取Constructor对象是通过Class类中的方法获取的，Class类与Constructor相关的主要方法如下：

static Class<?> forName(String className) 返回与带有给定字符串名的类或接口相关联的 Class 对象。 

Constructor getConstructor(Class<?>... parameterTypes) 返回指定参数类型、具有public访问权限的构造函数对象 

Constructor<?>[] getConstructors() 返回所有具有public访问权限的构造函数的Constructor对象数组 

Constructor getDeclaredConstructor(Class<?>... parameterTypes) 返回指定参数类型、所有声明的（包括private）构造函数对象 

Constructor<?>[] getDeclaredConstructor() 返回所有声明的（包括private）构造函数对象 

T newInstance() 调用无参构造器创建此 Class 对象所表示的类的一个新实例。

### Field类及其用法

Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。

Field getDeclaredField(String name) 获取指定name名称的(包含private修饰的)字段，不包括继承的字段

 Field[] getDeclaredFields() 获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段 

Field getField(String name) 获取指定name名称、具有public修饰的字段，包含继承字段 

Field[] getFields() 获取修饰符为public的字段，包含继承字段

### Method类及其用法

Method 提供关于类或接口上单独某个方法（以及如何访问该方法）的信息，所反映的方法可能是类方法或实例方法（包括抽象方法）。

Method getDeclaredMethod(String name, Class<?>... parameterTypes) 返回一个指定参数的Method对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。 

Method[] getDeclaredMethod() 返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。 Method getMethod(String name, Class<?>... parameterTypes) 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。 

Method[] getMethods() 返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法。

### 反射机制执行的流程

反射类及反射方法的获取，都是通过从列表中搜寻查找匹配的方法，所以查找性能会随类的大小方法多少而变化；

每个类都会有一个与之对应的Class实例，从而每个类都可以获取method反射方法，并作用到其他实例身上；

反射也是考虑了线程安全的，放心使用；

反射使用软引用relectionData缓存class信息，避免每次重新从jvm获取带来的开销；

反射调用多次生成新代理Accessor, 而通过字节码生存的则考虑了卸载功能，所以会使用独立的类加载器；

当找到需要的方法，都会copy一份出来，而不是使用原来的实例，从而保证数据隔离；

调度反射方法，最终是由jvm执行invoke0()执行；

## 泛型

**泛型类**

```java
class Point<T>{         // 此处可以随便写标识符号，T是type的简称  
    private T var ;     // var的类型由T指定，即：由外部指定  
    public T getVar(){  // 返回值的类型由外部决定  
        return var ;  
    }  
    public void setVar(T var){  // 设置的类型也由外部决定  
        this.var = var ;  
    }  
}  
public class GenericsDemo06{  
    public static void main(String args[]){  
        Point<String> p = new Point<String>() ;     // 里面的var类型为String类型  
        p.setVar("it") ;                            // 设置字符串  
        System.out.println(p.getVar().length()) ;   // 取得字符串的长度  
    }  
}
```

- 多元泛型

```java
class Notepad<K,V>{       // 此处指定了两个泛型类型  
    private K key ;     // 此变量的类型由外部决定  
    private V value ;   // 此变量的类型由外部决定  
    public K getKey(){  
        return this.key ;  
    }  
    public V getValue(){  
        return this.value ;  
    }  
    public void setKey(K key){  
        this.key = key ;  
    }  
    public void setValue(V value){  
        this.value = value ;  
    }  
} 
public class GenericsDemo09{  
    public static void main(String args[]){  
        Notepad<String,Integer> t = null ;        // 定义两个泛型类型的对象  
        t = new Notepad<String,Integer>() ;       // 里面的key为String，value为Integer  
        t.setKey("汤姆") ;        // 设置第一个内容  
        t.setValue(20) ;            // 设置第二个内容  
        System.out.print("姓名；" + t.getKey()) ;      // 取得信息  
        System.out.print("，年龄；" + t.getValue()) ;       // 取得信息  
  
    }  
}
```

**泛型接口**

```java
interface Info<T>{        // 在接口上定义泛型  
    public T getVar() ; // 定义抽象方法，抽象方法的返回值就是泛型类型  
}  
class InfoImpl<T> implements Info<T>{   // 定义泛型接口的子类  
    private T var ;             // 定义属性  
    public InfoImpl(T var){     // 通过构造方法设置属性内容  
        this.setVar(var) ;    
    }  
    public void setVar(T var){  
        this.var = var ;  
    }  
    public T getVar(){  
        return this.var ;  
    }  
} 
public class GenericsDemo24{  
    public static void main(String arsg[]){  
        Info<String> i = null;        // 声明接口对象  
        i = new InfoImpl<String>("汤姆") ;  // 通过子类实例化对象  
        System.out.println("内容：" + i.getVar()) ;  
    }  
}  
```

**泛型方法**

在调用方法的时候指明泛型的具体类型。

![java-basic-generic-4](D:\desk\java-basic-generic-4.png)

```java
public <T> T getObject(Class<T> c){
    T t = c.newInstance;
    return t;
}
```

泛型类要在实例化的时候就指明类型，如果想换一种类型，不得不重新new一次，可能不够灵活；而泛型方法可以在调用的时候指明类型，更加灵活。

**泛型的上下限**

上限

```java
class Info<T extends Number>{    // 此处泛型只能是数字类型
    private T var ;        // 定义泛型变量
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }
    public String toString(){    // 直接打印
        return this.var.toString() ;
    }
}
public class demo1{
    public static void main(String args[]){
        Info<Integer> i1 = new Info<Integer>() ;        // 声明Integer的泛型对象
    }
}
```

下限

```java
class Info<T>{
    private T var ;        // 定义泛型变量
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }
    public String toString(){    // 直接打印
        return this.var.toString() ;
    }
}
public class GenericsDemo21{
    public static void main(String args[]){
        Info<String> i1 = new Info<String>() ;        // 声明String的泛型对象
        Info<Object> i2 = new Info<Object>() ;        // 声明Object的泛型对象
        i1.setVar("hello") ;
        i2.setVar(new Object()) ;
        fun(i1) ;
        fun(i2) ;
    }
    public static void fun(Info<? super String> temp){    // 只能接收String或Object类型的泛型，String类的父类只有Object类
        System.out.print(temp + ", ") ;
    }
}
```

<?> 无限制通配符
<? extends E> extends 关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
<? super E> super 关键字声明了类型的下界，表示参数化的类型可能是指定的类型，或者是此类型的父类

// 使用原则《Effictive Java》
// 为了获得最大限度的灵活性，要在表示 生产者或者消费者 的输入参数上使用通配符，使用的规则就是：生产者有上限、消费者有下限
1. 如果参数化类型表示一个 T 的生产者，使用 < ? extends T>;

2. 如果它表示一个 T 的消费者，就使用 < ? super T>；

3. 如果既是生产又是消费，那使用通配符就没什么意义了，因为你需要的是精确的参数类型。

   

## 垃圾回收机制

## 注解

用于对代码进行说明，可以对包、类、接口、字段、方法参数、局部变量等进行注解。它主要的作用有以下四方面：

- 生成文档，通过代码里标识的元数据生成javadoc文档。
- 编译检查，通过代码里标识的元数据让编译器在编译期间进行检查验证。
- 编译时动态处理，编译时通过代码里标识的元数据动态处理，例如动态生成代码。
- 运行时动态处理，运行时通过代码里标识的元数据动态处理，例如使用反射注入实例。

这么来说是比较抽象的，我们具体看下注解的常见分类：

- **Java自带的标准注解**，包括`@Override`、`@Deprecated`和`@SuppressWarnings`，分别用于标明重写某个方法、标明某个类或方法过时、标明要忽略的警告，用这些注解标明后编译器就会进行检查。
- **元注解**，元注解是用于定义注解的注解，包括`@Retention`、`@Target`、`@Inherited`、`@Documented`，`@Retention`用于标明注解被保留的阶段，`@Target`用于标明注解使用的范围，`@Inherited`用于标明注解可继承，`@Documented`用于标明是否生成javadoc文档。
- **自定义注解**，可以根据自己的需求定义注解，并可用元注解对自定义注解进行注解。



Java 1.5开始自带的标准注解，包括`@Override`、`@Deprecated`和`@SuppressWarnings`：

- `@Override`：表示当前的方法定义将覆盖父类中的方法
- `@Deprecated`：表示代码被弃用，如果使用了被@Deprecated注解的代码则编译器将发出警告
- `@SuppressWarnings`：表示关闭编译器警告信息

### 自定义注解

```java
package com.pdai.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethodAnnotation {

    public String title() default "";

    public String description() default "";

}
```

## Collection类

collection，容器，就是可以容纳其他Java对象的对象。

java容器里只能放对象，对于基本类型(int, long, float, double等)，需要将其包装成对象类型后(Integer, Long, Float, Double等)才能放到容器里。很多时候拆包装和解包装能够自动完成。这虽然会导致额外的性能和空间开销，但简化了设计和编程。

容器主要包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对(两个对象)的映射表。

### Set

set集合中不包含重复元素。

#### TreeSet

基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如 HashSet，HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。TreeSet是基于TreeMap的一个封装。

####  HashSet

基于哈希表实现，支持快速查找，但不支持有序性操作。并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。HashSet是队HashMap的一个封装。

#### LinkedHashSet

具有 HashSet 的查找效率，且内部使用双向链表维护元素的插入顺序。

### List

#### ArrayList

基于动态数组实现，支持随机访问。

ArrayList实现了List接口，是顺序容器，即元素存放的数据于放进去的顺序相同。允许放入null元素。底层采用数组实现。每个ArrayList都有一个容量（capacity），表示底层数组大小。当容器容量不足，会自动增大底层数组的大小。List采用泛型可以保存任何类型的对象。

ArrayList允许为null，允许重复数据，有序，线程不安全

size(), isEmpty(), get(), set()方法均能在常数时间内完成，add()方法的时间开销跟插入位置有关，addAll()方法的时间开销跟添加元素的个数成正比。其余方法大都是线性时间。

为追求效率，ArrayList没有实现同步(synchronized)，如果需要多个线程并发访问，用户可以手动同步，也可使用Vector替代。

```java
   //arrayList
        List<Integer> list = new ArrayList<>();
        //直接插入
        list.add(1);list.add(23);
        //根据索引插入，底层会先移动元素，在完成插入操作
        list.add(0,2);
        //截取List，不包含最后一位,使用subList会影响原理的List，subList后在对原List操作会报错
        List<Integer> list1 = list.subList(0,2);
        //新增list，新的list元素默认排在原来元素的后面 ,此时list1就不能再使用了
        list.addAll(2,list1);
        //替换元素。索引，元素
        list.set(0,10);
        System.out.println(list);
        //元素的长度
        System.out.println(list.size());
        //指定元素在动态数组中第一次出现的位置。
        System.out.println(list.indexOf(23));
        //指定元素在动态数组中最后一次出现的位置。
        System.out.println(list.lastIndexOf(1));
        //判断是否为空
        System.out.println(list.isEmpty());

        list.add(100);
        //移除一个元素，指定元素的索引
        list.remove(5);
        System.out.println(list);
        //移除list中的指定的所有数据
        List<Integer> newlist = new ArrayList<>();
        newlist.add(1);
        newlist.add(2);
//        list.removeAll(newlist);
//        System.out.println(list);

        //判断是否包含某个元素,对对象使用需要重写equals方法
        System.out.println(list.contains(1));
        System.out.println(list.containsAll(newlist));

        //删除不在指定集合中的元素
//        list.retainAll(list1);

        //使用迭代器循环遍历List
        Iterator<Integer> iterator =  list.iterator();
        while ( iterator.hasNext() ){
            Integer str = iterator.next();
            System.out.print(str);
        }
        System.out.println();
        //使用foreach的方式遍历list
        for (int i:list) {
            System.out.print(i);
        }
```



#### Vector

和 ArrayList 类似，但它是线程安全的。

####  LinkedList

基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。不仅如此，LinkedList 还可以用作栈、顺序容器和双向队列。

需要使用栈或者队列时，可以考虑使用LinkedList。

LinkedList允许为null，允许重复数据，有序，线程不安全。

*LinkedList*的实现方式决定了所有跟下标相关的操作都是线性时间，而在首段或者末尾删除元素只需要常数时间。为追求效率*LinkedList*没有实现同步(synchronized)；

```java
    LinkedList<Integer> list = new LinkedList<>();
        //添加数据
        /**
         * LinkedList添加元素的方法有四种，分别是：add(element)、addFirst()、addLast()、add(index,element)。
         * 其中add(element)方法和addLast()方法的实现逻辑一样，
         * 唯一不同的是addLast()没有返回值，而add(element)方法会返回true。
         * add(index,element)方法是通过指定索引的方式添加新元素。
         */
        list.add(1);
        list.add(2);
        //在头部添加元素
        list.addFirst(100);
        //在尾部添加元素
        list.addLast(20);
        //修改元素
        list.set(0,0);
        //用对应的索引查看元素
        System.out.println(list.get(2));
        //用对应的索引删除元素
        list.remove(2);
        print(list);
```



### Queue

####  LinkedList

可以用它来实现双向队列。

#### PriorityQueue

基于堆结构实现，可以用它来实现优先队列。

实现可以使用ArrayDeque和LinkedList

1. ArrayDeque不是线程安全的；

2. 当ArrayDeque被当作栈使用时比Stack快，当作队列使用时比LinkedList快。

   ArrayDeque有三个基本属性。首先就是存储元素的数组，其次就是首尾指针。

   ```java
   /**
    * 在java中需要使用栈的时候，推荐使用高效的Queue
    *
    *              抛出异常    返回值（没有返回null）
    * 插入操作       add       offer
    * 删除操作      remove     poll
    * 检查操作      element    peek
    *
    * 对列除了ArrayDeque，先进先出
    * 还有Deque，双向队列，可以对队头和队尾都进行操作，ArrayDeque和LinkedList是Deque的两个通用实现
    *
    */
   ArrayDeque<Integer> queue = new ArrayDeque<>();
   //添加元素
   queue.add(1);
   queue.add(2);
   //队头插入
   queue.addFirst(4);
   //队尾插入
   queue.addLast(7);
   //插入,有返回值 boolean
   queue.offer(5);
   //对头插入,有返回值 boolean
   queue.offerFirst(3);
   //队尾插入,有返回值 boolean
   queue.offerLast(8);
   //remove会抛出异常，使用poll，,有返回值 boolean，队列位空返回null
   //删除对头元素
   queue.pollFirst();
   //删除队尾元素
   queue.pollLast();
   //判断大小
   System.out.println(queue.size());
   //判断是否位空
   System.out.println(queue.isEmpty());
   //判断是否包含哪个元素
   System.out.println(queue.contains(2));
   System.out.println(queue.contains(22));
   //返回对头元素，element会抛出异常，peek会返回null
   System.out.println(queue.peek());
   System.out.println(queue);
   /**
    * ArrayDeque实现Stack（栈）
    * push 入栈 pop 出栈
    */
   ArrayDeque<Integer> stack = new ArrayDeque<>();
   stack.push(1);
   stack.push(2);
   //栈弹出一个
   stack.pop();
   System.out.println(stack);
   ```

## Map

### TreeMap

基于红黑树实现的。

TreeMap中的数据是有序的，按照升键。

```java
TreeMap<Integer,Integer> map = new TreeMap<>();
TreeMap<Integer, Integer> map1 = new TreeMap<>();
//添加数据
map.put(1,1);
map.put(2,22);
map.put(3,1341);
map1.put(1,23);
//添加一个集合map到Treemap中,对与相同的key会进行替换
map.putAll(map1);
//删除元素，clear删除所有，remove删除指定key的value
map.remove(3);
//修改元素,replace替换指定key的value，或者当指定key的value为定值时，在替换
map.replace(2,2);
map.replace(1,1);
map.replace(1,1,100);
//根具key计算value的地址，后取值
System.out.println(map.get(2));
//判断map中是否包含指定key
System.out.println(map.containsKey(1));
//判断map中是否包含指定value
System.out.println(map.containsValue(1));
//返回Tree中第一个映射firstEntry(),
System.out.println(map.firstEntry());
//返回Tree中第一个key,firstKey()
//返回Tree中最后一个个映射，lastEntry()
//返回Tree中最后一个映射，lastKey()
//返回该TreeMap中小于指定key的映射集合
System.out.println(map.headMap(2));
//返回该TreeMap中指定范围的映射集合（大于等于fromKey，小于toKey）
System.out.println(map.subMap(1,2));
//返回TreeMap实例的浅拷贝
Object clone = map.clone();
System.out.println(clone);
//返回map的大小
System.out.println(map.size());
//遍历map的value
for (int i:map.values()) {
    System.out.println(i);
}
//Map遍历键值
for (Map.Entry<Integer, Integer> i:map.entrySet()) {
    System.out.println(i);
}
System.out.println(map);
```

### HashMap

基于哈希表实现。

利用了红黑树。由数组+链表+红黑树组成，查找的时候，顺着链表一个一个比较，当链表中的元素达到了 8 个时，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)。

使用方法和TreeMap一样。但是允许存放空值null。顺序会被打散

###  HashTable

和 HashMap 类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入 HashTable 并且不会导致数据不一致。它是遗留类，不应该去使用它。现在可以使用 ConcurrentHashMap 来支持线程安全，并且 ConcurrentHashMap 的效率会更高，因为 ConcurrentHashMap 引入了分段锁。

###  LinkedHashMap

使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用(LRU)顺序。用法和HashMap一样，就是使用双向列表来来实现HashMap。

允许放入空值null。可以看作LinkedList增强的HashMap。迭代顺序为插入顺序。

## Comparable接口

Comparable接口是java提供的一个进行排序比较规则。

所有可以 “排序” 的类都实现了java.lang.Comparable接口，Comparable接口中只有一个方法。
public int compareTo(Object obj) ;该方法：
返回 0 表示 this == obj
返回整数表示 this > obj 
返回负数表示 this < ob 
实现了 Comparable 接口的类通过实现 comparaTo 方法从而确定该类对象的排序方式。
该接口强行对实现它的每个类的对象进行整体排序。此排序被称为该类的自然排序 ，类的 compareTo 方法被称为它的自然比较方法 。实现此接口的对象列表（和数组）可以通过 Collections.sort （和 Arrays.sort ）进行自动排序。如Jdk 中 Long 根据 value 大小进行排序

## 数组



### 静态初始化数组

```java
int[] arr =new int[]{12,23,45,35,135,15,32,41,534,232};
int[] arr = {12,23,45,35,135,15,32,41,534,232};
```

数组变量名存储的是数组在内存中的地址，数组是引用类型。

使用length来获取数组长度

```java
  System.out.println(arr.length);
```

### 动态初始化数组

定义数组的时候只确定元素的类型和数组的长度，之后再存入具体的数据，默认都为0.

```java
int[] arr =new int[3];
```

### 数组遍历

```java
 for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
```

### 数组排序

冒泡排序，选择排序，快速排序，插入排序

### 数组搜索

二分搜索，分块查找，哈希表查找

### 可变长参数

可变长参数其实就是数组的一种应用，我们可以指定方法的形参为一个可变长参数，要求实参可以根据情况动态填入0个或多个，而不是固定的数量

```java
public static void main(String[] args) {
     test("AAA", "BBB", "CCC");    //可变长，最后都会被自动封装成一个数组
}
    
private static void test(String... test){
     System.out.println(test[0]);    //其实参数就是一个数组
}
```

### 多位数组

![image-20220914225034517](C:\Users\李焱军\AppData\Roaming\Typora\typora-user-images\image-20220914225034517.png)

## 方法定义

### 格式

```java
    public static void function(){
        //方法体
    }
```

### 注意事项

1.方法标内嵌套定义

2.void表示无返回值，可以省略return，也可以单独书写return，后面不加数据。

### 方法重载

方法名一样，但是方法中的参数不一致。（例如类中的结构函数）

## 静态变量和方法

使用static关键字来声明一个变量或一个方法为静态变量或静态方法。旦被声明为静态，那么通过这个类创建的所有对象，操作的都是同一个目标，也就是说，对象再多，也只有这一个静态的变量或方法。那么，一个对象改变了静态变量的值，那么其他的对象读取的就是被改变的值。

不推荐使用对象来调用，被标记为静态的内容，可以直接通过`类名.xxx`的形式访问。

```java
public class Student {
    static int a;
}

public static void main(String[] args) {
	Student s1 = new Student();
	s1.a = 10;
	Student s2 = new Student();
	System.out.println(s2.a);
    
    Student.a = 10;
   System.out.println(Student.a);
}
```

### 类加载机制

类并不是在一开始就全部加载好，而是在需要时才会去加载（提升速度）以下情况会加载类：

- 访问类的静态变量，或者为静态变量赋值
- new 创建类的实例（隐式加载）
- 调用类的静态方法
- 子类初始化时
- 其他的情况会在讲到反射时介绍

所有被标记为静态的内容，会在类刚加载的时候就分配，而不是在对象创建的时候分配，所以说静态内容一定会在第一个对象初始化之前完成加载。

```java
public class Student {
    static int a = test();  //直接调用静态方法，只能调用静态方法

    Student(){
        System.out.println("构造类对象");
    }

    static int test(){   //静态方法刚加载时就有了
        System.out.println("初始化变量a");
        return 1;
    }
}
```

### 代码块和静态代码块

代码块在对象创建时执行，也是属于类的内容，但是它在构造方法执行之前执行（和成员变量初始值一样），且每创建一个对象时，只执行一次！（相当于构造之前的准备工作）

```java
public class Student {
    {
        System.out.println("我是代码块");
    }

    Student(){
        System.out.println("我是构造方法");
    }
}
```

静态代码块和上面的静态方法和静态变量一样，在类刚加载时就会调用；

```java
public class Student {
    static int a;

    static {
        a = 10;
    }
    
    public static void main(String[] args) {
        System.out.println(Student.a);
    }
}
```

