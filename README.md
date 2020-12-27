# Tclass班级事务管理系统

一个基于Vue.js和Spring Boot的前后端分离项目。

## 系统功能组成

### 登录模块

登录模块中有两种角色，一种为管理员，一种为普通用户，两种角色都使用学号和密码登录。不同角色登录后所加载的模块有所不同。普通用户登录后只能查看通知和作业内容、提交作业和修改个人信息。管理员登录系统后可以对通知、作业、用户进行管理，如：编辑、发布、删除等。

### 首页显示模块

实现显示当前最新发布的通知和作业，内容包括发布时间、编辑时间、作业文件命名格式等。

### 用户信息管理模块

实现管理员对用户信息的编辑、修改、删除，以及复杂信息的高级搜索，普通用户对个人资料的修改，用户信息管理还包括用户的批量导入和导出、编辑用户信息、重置密码等。

###  通知内容显示和管理模块

管理员可以使用Markdown语法对通知内容进行发布或者编辑，同时还可以对历史通知的内容、标题等进行编辑、删除、查询等。

普通用户可以根据时间或者标题查询某一条通知并进行查阅。

### 作业内容显示和管理模块

管理员可以发布作业，作业内容包括文件命名格式、截止提交时间等、可以对作业进行添加、删除、编辑等管理。

普通用户可以根据作业标题、发布时间对作业进行查阅。

### 作业的上传下载模块

管理员可以查看各个作业提交的情况，并对作业进行打包下载。

普通用户可以上传提交自己的作业文件。

## 架构

项目采用B/S架构，前后端通讯采用RESTful API，数据格式使用Json。

身份认证使用Spring Security。

部署推荐使用Docker进行部署。当然也可以使用命令行的方式运行jar进行部署。

前端：Vue.js

后端：Spring Boot

数据库：MySQL

缓存：Redis

ORM：MyBatis-Plus 

### 具体技术栈如下：

#### 后端使用的技术栈

1. Spring Boot
2. Spring Security
3. MySql
4. Redis
5. Spring Cache
6. MyBatis-Plus 
7. ...

#### 前端使用的技术栈

1. Vue.js
2. axios
3. ElementUI
4. vue-router
5. vuex
6. tui-editor
7. ...

## 编译

1. 克隆项目

   ```bash
   git clone https://github.com/Teoan/TClass.git
   ```

2. 编译前端项目可以使用Vscode或其他IDE打开tclassclient文件夹。编译前需安装依赖：

   ```bash
   # 切换当前目录
   cd tclassclient
   # 安装依赖
   npm install
   # 启动项目
   npm run serve
   ```

3. 编译后端项目可以使用IntelliJ IDEA或其他IDE打开项目目录tclasserver。

## 获取发布版本

### Releases

[获取release版本](https://github.com/Teoan/TClass/releases)

运行jar

```bash
java -jar tclass-web-1.0.0-BETA.jar --spring.datasource.url=jdbc:mysql://MYSQL_URL/tclass --spring.datasource.username=MYSQL_USERNAME --spring.datasource.password=MYSQL_PASSWORD --spring.redis.host=REDIS_HOST --spring.redis.port=REDIS_PORT --spring.redis.password=REDIS_PASSWORD --file.upload.url=UPLOAD_PATH
```

### Docker

拉取并运行镜像

```bash
sudo docker run  -d -p TCLASS_PORT:8080 -v UPLOAD_PATH:/home/root/tclass/upload --name tclass -e MYSQL_URL=MYSQL_URL -e MYSQL_USERNAME=MYSQL_USERNAME -e MYSQL_PASSWORD=MYSQL_PASSWORD -e REDIS_HOST=REDIS_HOST -e REDIS_PORT=REDIS_PORT -e REDIS_PASSWORD=REDIS_PASSWORD registry.cn-guangzhou.aliyuncs.com/teoan/tclass-server
```

| 参数           | 含义               |
| -------------- | ------------------ |
| MYSQL_URL      | mysql服务地址      |
| MYSQL_USERNAME | mysql用户名        |
| MYSQL_PASSWORD | mysql密码          |
| REDIS_HOST     | redis服务地址      |
| REDIS_PORT     | redis服务端口      |
| REDIS_PASSWORD | redis密码          |
| UPLOAD_PATH    | 上传文件保存的路径 |

例如使用jar部署：

```bash
java -jar tclass-web-1.0.0-BETA.jar --spring.datasource.url=jdbc:mysql://172.17.0.1:3306/tclass --spring.datasource.username=Teoan --spring.datasource.password=123456 --spring.redis.host=172.17.0.2 --spring.redis.port=6379 --spring.redis.password=123456 --file.upload.url=~/tclass/upload
```

例如使用Docker部署：

```bash
sudo docker run  -d -p 127.0.0.1:8080:8080 -v ~/tclass/upload:/home/root/tclass/upload --name tclass -e MYSQL_URL=172.17.0.1:3306 -e MYSQL_USERNAME=Teoan -e MYSQL_PASSWORD=123456 -e REDIS_HOST=172.17.0.2 -e REDIS_PORT=6379 -e REDIS_PASSWORD=123456 registry.cn-guangzhou.aliyuncs.com/teoan/tclass-server
```

注意：运行前需创建好tclass数据库，默认管理员账号1724111400，密码123456。

部署完毕以后浏览器打开：http://localhost:8080/index.html 即可访问本项目。

## 表字段属性

### 部门属性（Department）

| 属性 | 类型    | 说明         | 约束       |
| ---- | ------- | ------------ | ---------- |
| id   | int     | 部门唯一标识 | 非空，主键 |
| name | varchar | 部门名称     | 非空       |

### 文件扩展名属性（Extension）

| 属性 | 类型    | 说明           | 约束       |
| ---- | ------- | -------------- | ---------- |
| id   | int     | 扩展名唯一标识 | 非空，主键 |
| name | varchar | 扩展名名称     | 非空       |

### 民族属性（Nation）

| 属性 | 类型    | 说明         | 约束       |
| ---- | ------- | ------------ | ---------- |
| id   | int     | 民族唯一标识 | 非空，主键 |
| name | varchar | 民族名称     | 非空       |

### 政治面貌属性（Politicsstatus）

| 属性 | 类型    | 说明             | 约束       |
| ---- | ------- | ---------------- | ---------- |
| id   | int     | 政治面貌唯一标识 | 非空，主键 |
| name | varchar | 政治面貌名称     | 非空       |

### 班级职务属性（Nation）

| 属性 | 类型    | 说明         | 约束       |
| ---- | ------- | ------------ | ---------- |
| id   | int     | 民族唯一标识 | 非空，主键 |
| name | varchar | 民族名称     | 非空       |

### 权限属性（Role）

| 属性    | 类型    | 说明         | 约束       |
| ------- | ------- | ------------ | ---------- |
| id      | int     | 权限唯一标识 | 非空，主键 |
| name    | varchar | 权限英文名称 | 非空       |
| zh_name | varchar | 权限中文名称 | 非空       |

### 通知属性（Notice）

| 属性        | 类型     | 说明           | 约束       |
| ----------- | -------- | -------------- | ---------- |
| id          | int      | 扩展名唯一标识 | 非空，主键 |
| s_id        | int      | 通知发起人id   | 非空       |
| edit_s_id   | int      | 通知编辑人id   |            |
| deleted     | tinyint  | 逻辑删除       |            |
| title       | varchar  | 通知标题       | 非空       |
| content     | text     | 通知内容       | 非空       |
| create_time | datetime | 创建时间       | 非空       |
| update_time | datetime | 更新时间       | 非空       |

### 学生用户属性（Student）

| 属性         | 类型     | 说明             | 约束       |
| ------------ | -------- | ---------------- | ---------- |
| id           | int      | 学生用户唯一标识 | 非空，主键 |
| name         | varchar  | 学生用户名称     | 非空       |
| role_id      | int      | 学生权限标识     | 非空       |
| password     | varchar  | 用户密码         | 非空       |
| gender       | varchar  | 用户性别         | 非空       |
| nation_id    | int      | 民族标识         | 非空       |
| native_place | varchar  | 用户籍贯         | 非空       |
| politic_id   | int      | 政治面貌标识     |            |
| email        | varchar  | 用户邮箱         |            |
| phone        | varchar  | 用户手机         |            |
| address      | varchar  | 用户家庭住址     |            |
| pos_id       | int      | 班级职务标识     |            |
| create_time  | datetime | 创建时间         |            |
| update_time  | datetime | 更新时间         |            |
| login_time   | datetime | 最近登录时间     |            |
| avatar_url   | varchar  | 头像链接         |            |
| deleted      | tinyint  | 逻辑删除         |            |

### 上传文件属性（Upload）

| 属性        | 类型     | 说明         | 约束 |
| ----------- | -------- | ------------ | ---- |
| s_id        | int      | 文件所有者id | 非空 |
| w_id        | int      | 作业id       | 非空 |
| file_type   | varchar  | 文件类型     | 非空 |
| file_name   | varchar  | 文件名       | 非空 |
| size        | bigint   | 文件大小     | 非空 |
| create_time | datetime | 创建时间     |      |
| update_time | datetime | 更新时间     |      |

### 作业属性（Work）

| 属性                  | 类型     | 说明           | 约束       |
| --------------------- | -------- | -------------- | ---------- |
| id                    | int      | 扩展名唯一标识 | 非空，主键 |
| s_id                  | int      | 作业发起人id   | 非空       |
| edit_s_id             | int      | 作业编辑人id   |            |
| deleted               | tinyint  | 逻辑删除       |            |
| name                  | varchar  | 作业名称       | 非空       |
| extension_id          | int      | 文件扩展名标识 | 非空       |
| file_name_format_enum | int      | 文件名命名枚举 | 非空       |
| last_time             | datetime | 最晚提交时间   | 非空       |
| create_time           | datetime | 创建时间       | 非空       |
| update_time           | datetime | 更新时间       | 非空       |



## 接口

> 统一响应格式:
>
> Content-Type: application/json
>
> 状态吗:
>
> 400 请求参数错误
>
> 401 用户未登陆
>
> 403 用户权限不足
>
> 404 请求资源不存在
>
> 405 不支持该请求方法
>
> 500 服务器内部出错
>
> 503 服务不可用

> Json格式规范:
>
> ```json
> {
>     "code":"业务错误码",
>     "msg":"描述",
>     "data":"结果集"
> }
> ```

### 公共接口

**登录**

请求方法 POST

请求路径 **/login**

| 参数     | 说明                       |
| -------- | -------------------------- |
| username | 用户名                     |
| password | 密码                       |
| code     | 验证码                     |
| remember | Spring Security 记住我参数 |

响应状态（登录成功）

```json
{
    "code": 0
	"data": {id: 1724111400, roleId: 1, name: "王涛", password: null, gender: "男", nationId: 53,…}
	"msg": "登录成功！"
}
```

**获取验证码**

请求方法 GET

请求路径 **/verifyCode.jpg**

响应状态：返回验证码文件流

**退出登录**

请求方法 GET

请求路径 **/logout**

响应状态（注销成功）

```json
{
	"code":0,
    "data":"王涛",
    "msg":"注销成功！"
}
```

### 普通用户接口

**更新用户信息**

请求方法 PUT

请求路径 **/student/**

请求体

```json
{
    "address": "海南省海口市美兰区",
    "departmentIdList": [9, 8],
    "email": "chenjing@qq.com",
    "gender": "女",
    "id": 1724111401,
    "name": "陈静",
    "nationId": 44,
    "nativePlace": "广东省,广州市,天河区",
    "phone": "18795556693",
    "politicId": 11,
}
```

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**获取民族列表**

请求方法 GET

请求路径 **/student/nations**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "汉族"
	}, {
		"id": 2,
		"name": "蒙古族"
	}, {
		"id": 3,
		"name": "回族"
	}...],
	"msg": "执行成功"
}
```

**获取部门列表**

请求方法 GET

请求路径 **/student/departments**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "组织部"
	}, {
		"id": 2,
		"name": "实践部"
	}, {
		"id": 3,
		"name": "外联部"
	}...],
	"msg": "执行成功"
}
```

**获取政治面貌列表**

请求方法 GET

请求路径 **/student/political**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "中共党员"
	}, {
		"id": 2,
		"name": "中共预备党员"
	}, {
		"id": 3,
		"name": "共青团员"
	}...],
	"msg": "执行成功"
}
```

**获取班级职位列表**

请求方法 GET

请求路径 **/student/political**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "班长"
	}, {
		"id": 2,
		"name": "纪委"
	}, {
		"id": 3,
		"name": "学习委员"
	}...],
	"msg": "执行成功"
}
```

**获取权限列表**

请求方法 GET

请求路径 **/student/roles**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "ROLE_admin",
		"zhName": "管理员"
	}, {
		"id": 2,
		"name": "ROLE_student",
		"zhName": "普通用户"
	}],
	"msg": "执行成功"
}
```

**上传用户头像**

请求方法 POST

请求路径 **/student/avatar**

| 参数 |   说明   |
| :--: | :------: |
| file | 头像文件 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**获取用户头像**

请求方法 GET

请求路径 **/student/avatar/{photoPath}**

|   参数    |     说明     |
| :-------: | :----------: |
| photoPath | 头像图片路径 |

响应状态：返回头像文件流

**获取作业列表**

请求方法 GET

请求路径 **/work/**

|    参数    |     说明     |
| :--------: | :----------: |
|  current   |   当前页数   |
|    size    |    页大小    |
| createTime | 作业创建时间 |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"records": [{
			"id": 3,
			"editSId": null,
			"editorName": null,
			"deleted": false,
			"name": "医保凭证",
			"fileNameFormatEnum": 2,
			"createTime": "2020-12-19 12:58:38",
			"updateTime": "2020-12-19 12:58:38",
			"lastTime": "2020-12-31 00:00:00",
			"remarks": "请同学们将医保凭证上传到此处",
			"extensionId": 11,
			"extensionName": ".jpeg",
			"uploadFileCount": 0,
			"sid": 1724111400,
			"sname": "王涛"
		}],
		"total": 3,
		"size": 6,
		"current": 1,
		"orders": [],
		"optimizeCountSql": true,
		"hitCount": false,
		"searchCount": true,
		"pages": 1
	},
	"msg": "执行成功"
}
```

**获取作业信息**

请求方法 GET

请求路径 **/work/{id}**

| 参数 |  说明  |
| :--: | :----: |
|  id  | 作业id |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"id": 3,
		"editSId": null,
		"editorName": null,
		"deleted": false,
		"name": "医保凭证",
		"fileNameFormatEnum": 2,
		"createTime": "2020-12-19 12:58:38",
		"updateTime": "2020-12-19 12:58:38",
		"lastTime": "2020-12-31 00:00:00",
		"remarks": "请同学们将医保凭证上传到此处",
		"extensionId": 11,
		"extensionName": ".jpeg",
		"uploadFileCount": 1,
		"sid": 1724111400,
		"sname": "王涛"
	},
	"msg": "执行成功"
}
```

**获取扩展名列表**

请求方法 GET

请求路径 **/work/extensions**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": [{
		"id": 1,
		"name": "无"
	}, {
		"id": 3,
		"name": ".html"
	}...],
	"msg": "执行成功"
}
```

**上传学生作业**

请求方法 POST

请求路径 **/work/{wId}**

| 参数 |    说明    |
| :--: | :--------: |
| wId  |   作业id   |
| file | 上传的文件 |
| sId  | 学生用户id |

响应状态（请求成功）

```json
{
    "code":0
    ,"data":true,
    "msg":"上传成功！"
}
```

**查询是否已上传作业**

请求方法 GET

请求路径 **/work/isUpload**

| 参数 |    说明    |
| :--: | :--------: |
| wId  |   作业id   |
| sId  | 学生用户id |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {	//若已上传，返回文件信息，否则返回null
		"fileType": "application/octet-stream",
		"fileName": "1724111400_王涛_Java作业.java",
		"size": 1170,
		"createTime": "2020-12-22 20:36:39",
		"updateTime": "2020-12-22 20:36:39",
		"sname": "王涛",
		"sid": 1724111400,
		"wid": 2
	},
	"msg": "执行成功"
}
```

**删除已上传的文件**

请求方法 DELETE

请求路径 **/work/deleteFile**

|   参数   |    说明    |
| :------: | :--------: |
|   wId    |   作业id   |
|   sId    | 学生用户id |
| filename |   文件名   |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**获取通知列表**

请求方法 GET

请求路径 **/notice/**

|    参数    |   说明   |
| :--------: | :------: |
|  current   |  当前页  |
|    size    |  页大小  |
|   title    | 通知标题 |
| createTime | 创建时间 |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"records": [{
			"id": 3,
			"editSId": null,
			"editorName": null,
			"deleted": false,
			"name": "医保凭证",
			"fileNameFormatEnum": 2,
			"createTime": "2020-12-19 12:58:38",
			"updateTime": "2020-12-19 12:58:38",
			"lastTime": "2020-12-31 00:00:00",
			"remarks": "请同学们将医保凭证上传到此处",
			"extensionId": 11,
			"extensionName": ".jpeg",
			"uploadFileCount": 1,
			"sname": "王涛",
			"sid": 1724111400
		}...],
		"total": 2,
		"size": 6,
		"current": 1,
		"orders": [],
		"optimizeCountSql": true,
		"hitCount": false,
		"searchCount": true,
		"pages": 1
	},
	"msg": "执行成功"
}
```

**获取通知**

请求方法 GET

请求路径 **/notice/{id}**

| 参数 |  说明  |
| :--: | :----: |
|  id  | 通知id |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"id": 33,
		"editSId": 1724111400,
		"editSName": "王涛",
		"deleted": false,
		"title": "关于英语四六级通知",
		"content": "# 英语四六级通知\n1. 报考资格\n    本次补报考生应满足唯一条件：已参加2020年9月延考且当次英语四级（CET4）笔试成绩大于等于425分的考生。\n2. 报名方式及时间\n    1. 本次补报科目仅为英语六级（CET6）笔试，其他科目不予补报。\n    2. 本次补报时间为11月9日9:00-11日17:00，过时不再予以补报。\n    3. 符合补报资格的考生，请自行登录报名系统http://cet-bm.neea.edu.cn完成CET6笔试补报及缴费。",
		"createTime": "2020-12-19 12:49:30",
		"updateTime": "2020-12-19 12:51:27",
		"sname": "王涛",
		"sid": 1724111400
	},
	"msg": "执行成功"
}
```

**获取当前最新通知**

请求方法 GET

请求路径 **/notice/current**

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"id": 33,
		"editSId": 1724111400,
		"editSName": "王涛",
		"deleted": false,
		"title": "关于英语四六级通知",
		"content": "# 英语四六级通知\n1. 报考资格\n    本次补报考生应满足唯一条件：已参加2020年9月延考且当次英语四级（CET4）笔试成绩大于等于425分的考生。\n2. 报名方式及时间\n    1. 本次补报科目仅为英语六级（CET6）笔试，其他科目不予补报。\n    2. 本次补报时间为11月9日9:00-11日17:00，过时不再予以补报。\n    3. 符合补报资格的考生，请自行登录报名系统http://cet-bm.neea.edu.cn完成CET6笔试补报及缴费。",
		"createTime": "2020-12-19 12:49:30",
		"updateTime": "2020-12-19 12:51:27",
		"sname": "王涛",
		"sid": 1724111400
	},
	"msg": "执行成功"
}
```

### 管理员接口

**分页查询学生数据**

请求方法 GET

请求路径 **/admin/student/**

|    参数     |     说明     |
| :---------: | :----------: |
|   current   |    当前页    |
|    size     |    页大小    |
|    name     | 学生用户名字 |
|  nationId   |   民族标识   |
| nativePlace |     籍贯     |
|  politicId  |   政治面貌   |
|    posId    |   班级职务   |
|   gender    |     性别     |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"records": [{
			"id": 1724111400,
			"roleId": 1,
			"name": "王涛",
			"password": "e10adc3949ba59abbe56e057f20f883e",
			"gender": "男",
			"nationId": 53,
			"nativePlace": "广东省,广州市,天河区",
			"politicId": 1,
			"email": "wang@qq.com",
			"phone": "15644442252",
			"address": "陕西西安新城区",
			"avatarUrl": "/student/avatar/1724111400.jpg",
			"posId": 1,
			"createTime": "2020-07-23 16:32:47",
			"updateTime": "2020-12-22 20:29:53",
			"loginTime": "2020-12-22 20:29:53",
			"deleted": false,
			"role": {
				"id": 1,
				"name": "ROLE_admin",
				"zhName": "管理员"
			},
			"nation": {
				"id": 53,
				"name": "赫哲族"
			},
			"politicsstatus": {
				"id": 1,
				"name": "中共党员"
			},
			"position": {
				"id": 1,
				"name": "班长"
			},
			"departmentIdList": [4, 2, 7, 8, 9]
		}, {
			"id": 1724111401,
			"roleId": 2,
			"name": "陈静",
			"password": "e10adc3949ba59abbe56e057f20f883e",
			"gender": "女",
			"nationId": 44,
			"nativePlace": "广东省,广州市,天河区",
			"politicId": 11,
			"email": "chenjing@qq.com",
			"phone": "18795556693",
			"address": "海南省海口市美兰区",
			"avatarUrl": "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
			"posId": 11,
			"createTime": "2020-07-23 16:38:45",
			"updateTime": "2020-12-14 22:41:13",
			"loginTime": "2020-12-14 22:11:45",
			"deleted": false,
			"role": {
				"id": 2,
				"name": "ROLE_student",
				"zhName": "普通用户"
			},
			"nation": {
				"id": 44,
				"name": "俄罗斯族"
			},
			"politicsstatus": {
				"id": 11,
				"name": "台盟盟员"
			},
			"position": {
				"id": 11,
				"name": "无"
			},
			"departmentIdList": [9, 8]
		}...],
		"total": 42,
		"size": 10,
		"current": 1,
		"orders": [],
		"optimizeCountSql": true,
		"hitCount": false,
		"searchCount": true,
		"pages": 5
	},
	"msg": "执行成功"
}
```

**修改用户数据**

请求方法 PUT

请求路径 **/admin/student/**

请求体

```json
{
	"id": 1724111400,
	"name": "王涛",
	"gender": "女",
	"nationId": 53,
	"politicId": 1,
	"email": "wang@qq.com",
	"phone": "15644442252",
	"address": "陕西西安新城区",
	"nativePlace": "广东省,广州市,天河区",
	"posId": 1,
	"roleId": 1,
	"departmentIdList": [4, 2, 7, 8, 9]
}
```

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除用户数据**

请求方法 DELETE

请求路径 **/admin/student/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 用户的id列表 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**批量导出学生数据**

请求方法 GET

请求路径 **/admin/student/export**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 用户的id列表 |

响应状态：返回excel文件流

**批量导入学生数据**

请求方法 POST

请求路径 **/admin/student/import**

| 参数 |      说明       |
| :--: | :-------------: |
| file | 导入的excel文件 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"已成功导入n条数据!"
}
```

**获取导入模板**

请求方法 GET

请求路径 **/admin/student/template**

响应状态：返回excel文件流

**获取作业数据**

请求方法 GET

请求路径 **/admin/work/{id}**

| 参数 |  说明  |
| :--: | :----: |
|  id  | 作业id |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"id": 3,
		"editSId": null,
		"editorName": null,
		"deleted": false,
		"name": "医保凭证",
		"fileNameFormatEnum": 2,
		"createTime": "2020-12-19 12:58:38",
		"updateTime": "2020-12-19 12:58:38",
		"lastTime": "2020-12-31 00:00:00",
		"remarks": "请同学们将医保凭证上传到此处",
		"extensionId": 11,
		"extensionName": ".jpeg",
		"uploadFileCount": 1,
		"sname": "王涛",
		"sid": 1724111400
	},
	"msg": "执行成功"
}
```

**发布作业**

请求方法 POST

请求路径 **/admin/work/**

请求体

```json
{
	"sid": 1724111400,
	"fileNameFormatEnum": 4,//文件命名枚举
	"extensionId": 3,//扩展名标识
	"name": "test",
	"lastTime": "2020-12-24 00:00:00",
	"remarks": "添加数据",
}
```

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**更新作业数据**

请求方法 PUT

请求路径 **/admin/work/**

请求体

```json
{
	"id": 4,
	"fileNameFormatEnum": 4,
	"extensionId": 3,
	"name": "修改test",
	"lastTime": "2020-12-24 00:00:00",
	"remarks": "添加数据",
	"editSId": 1724111400
}
```

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除作业数据**

请求方法 DELETE

请求路径  **/admin/work/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 作业的id列表 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**下载作业文件**

请求方法 GET

请求路径 **/admin/work/download**

|   参数   |  说明  |
| :------: | :----: |
|   wId    | 作业id |
| fileName | 文件名 |

响应状态：返回请求对应的文件流

**打包下载作业文件**

请求方法 GET

请求路径 **/admin/work/download/{wId}**

|   参数   |  说明  |
| :------: | :----: |
|   wId    | 作业id |

响应状态：返回打包zip文件流

**分页获取上传文件信息列表**

请求方法 GET

请求路径 **/admin/work/upload/{wId}**

|  参数   |  说明  |
| :-----: | :----: |
|   wId   | 作业id |
| current | 当前页 |
|  size   | 页大小 |

响应状态（请求成功）

```json
{
	"code": 0,
	"data": {
		"records": [{
			"fileType": "image/jpeg",
			"fileName": "1724111400_王涛_医保凭证.jpeg",
			"size": 5430113,
			"createTime": "2020-12-20 16:23:32",
			"updateTime": "2020-12-20 16:23:32",
			"sname": "王涛",
			"sid": 1724111400,
			"wid": 3
		}...],
		"total": 1,
		"size": 12,
		"current": 1,
		"orders": [],
		"optimizeCountSql": true,
		"hitCount": false,
		"searchCount": true,
		"pages": 1
	},
	"msg": "执行成功"
}
```

**批量删除已提交的作业文件**

请求方法 DELETE

请求路径  **/admin/work/upload**

|        参数        |             说明             |
| :----------------: | :--------------------------: |
|        wId         |            作业id            |
|   deleteSIdList    | 需要删除文件对应的用户id列表 |
| deleteFileNameList |     需要删除的文件名列表     |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"删除成功！"
}
```

**添加文件扩展名**

请求方法 POST

请求路径  **/admin/work/extensions**

| 参数 |  说明  |
| :--: | :----: |
| name | 扩展名 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**删除文件扩展名**

请求方法 DELETE

请求路径  **/admin/work/extensions**

| 参数 |   说明   |
| :--: | :------: |
| eId  | 扩展名id |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**发布通知**

请求方法 POST

请求路径 **/admin/notice/**

|  参数   |   说明   |
| :-----: | :------: |
|   sId   | 发布者id |
|  title  | 通知标题 |
| content | 通知内容 |

响应状态（请求成功）

```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**编辑通知**

请求方法 PUT

请求路径 **/admin/notice/**

|  参数   |      说明      |
| :-----: | :------------: |
|   id    |     通知id     |
| editSId | 编辑者的用户id |
|   sId   | 发布者的用户id |
|  title  |    通知标题    |
| content |    通知内容    |

响应状态（请求成功）
```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除通知**

请求方法 DELETE

请求路径 **/admin/notice/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 通知的id列表 |

响应状态（请求成功）
```json
{
    "code":0,
    "data":true,
    "msg":"执行成功"
}
```

## 项目效果图

**登录界面**

![](https://i.jpg.dog/img/080311f80e6fc06eeb8e87cd90439239.png)

**项目首页**

![](https://i.jpg.dog/img/4e253150b76c96c9e2b3bca825bfcc0e.png)

**管理员导航栏**

![](https://i.jpg.dog/img/8d801c473ed2efe47f84bf5cd74bf3a8.png)

**普通用户导航栏**

![](https://i.jpg.dog/img/785d04ef2c110d351de3a64aa280450d.png)

**用户管理**

![](https://i.jpg.dog/img/48b4e10e1980cc0f0ffbb4dac01223c3.png)

**用户数据导入导出**

![](https://i.jpg.dog/img/a44f10e91fbdf0118fb73b3356362ace.png)

**用户批量管理**

![](https://i.jpg.dog/img/a49b7ab6bb52aa05516064423d98705b.png)

**用户高级搜索**

![](https://i.jpg.dog/img/bb399aada376a0f2af06de0ecfebcd81.png)

**编辑个人资料**

![](https://i.jpg.dog/img/0d1a3f001472c73374563ff608260497.png)

**编辑用户资料**

![](https://i.jpg.dog/img/3520837b11869a2bb59551fb9f3d737f.png)

**查看作业**

![](https://i.jpg.dog/img/d0ad436b4a8adda6ef3b892872aa6544.png)

**查看提交的文件**

![](https://i.jpg.dog/img/6bea3b9a34791e39a65141528f70f846.png)

**提交作业**

![](https://i.jpg.dog/img/aff6afd90b9fce1d017151a3920faa96.png)

**作业管理**

![](https://i.jpg.dog/img/0e6449737c35f98c72f7ef2c92056a22.png)

**发布作业**

![](https://i.jpg.dog/img/d18f85a319fba7e9ec635801ad08499b.png)

**编辑作业**

![](https://i.jpg.dog/img/8a508ef9c58da7ab47c32247097e46a5.png)

**编辑扩展名列表**

![](https://i.jpg.dog/img/2775b8c05df30c7ba0159b998113e39c.png)

**查看用户提交的作业文件**

![](https://i.jpg.dog/img/2493e00c040d0ea68c44b608e706f1ea.png)

**查看通知**

![](https://i.jpg.dog/img/369d358a431e6224ec4843dd67fda032.png)

**查看通知详情**

![](https://i.jpg.dog/img/8bd139820746bad1e10cb0c5c8206f5e.png)

**通知管理**

![](https://i.jpg.dog/img/f73b260fe25c4c109821865c531ec414.png)

**编辑通知**

![](https://i.jpg.dog/img/c9e497a82f2200a85c0ebbb1cf5c7ada.png)

**发布通知**

![](https://i.jpg.dog/img/82952aa7dbbe013a2d62b58287e3b924.png)