# Ubuntu的安装java

安装openjdk-11-jdk

```bash
sudo apt-get -y install openjdk-11-jdk
java --version
```

若修改默认版本，可以使用如下命令

```sql
sudo update-alternatives --config java
```

# ubuntu安装mysql

sudo apt update
sudo apt install -y  mysql-server

sudo mysql -uroot

设置新秘密

alter user 'root'@'localhost' identified with mysql_native_password by 'your_new _password';

授权远程使用

update user set host='%' where user='root';

flush privileges;

![1666337562904](image/Ubuntu安装java/1666337562904.png)

flush privileges;
