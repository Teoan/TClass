# Tclass班级事务管理系统

[主分支](https://github.com/Teoan/TClass/tree/master)后端采用Spring Boot开发，现已停止维护。

**cloud**分支版本正在开发中，后端采用Spring Cloud进行改造。

## 系统架构

项目采用B/S架构，前后端通讯采用RESTful API，数据格式使用Json，认证Token格式采用JWT。

身份认证使用Spring Security Oauth2。

前端：Vue.js

后端：Spring Cloud

数据库：MySQL

缓存：Redis

文件存储：FastDFS

ORM：MyBatis-Plus 

### 具体技术栈如下：

#### 后端使用的技术栈

1. Spring Cloud
2. Spring Security
3. FastDFS
4. MySql
5. Redis
6. Spring Cache
7. MyBatis-Plus 
8. ...

#### 前端使用的技术栈

1. Vue.js
2. axios
3. ElementUI
4. vue-router
5. vuex
6. tui-editor
7. ...

## 各个模块简要介绍

| 模块名          | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| tclass-common   | 公共模块，存放一些通用的配置和类如：oauth资源服务器的配置，FastDFS文件服务等 |
| tclass-config   | Spring Cloud配置中心，统一微服务的配置管理                   |
| tclass-gateway  | Spring Cloud网关，负责将对应请求转发到对应的微服务模块       |
| tclass-oauth    | Spring Cloud认证中心，负责权限统一的认证，提供登录接口以及发放JWT类型的Token |
| tclass-registry | Spring Cloud注册中心，负责微服务的统一治理。                 |
| tclass-notice   | 微服务通知模块，负责通知内容的CRUD                           |
| tclass-user     | 微服务用户模块，负责用户的CRUD                               |
| tclass-work     | 微服务作业模块，负责作业文件的持久化和作业内容的CRUD         |

## 项目中使用Spring Cloud相关组件介绍

| 技术栈               | 描述                                                         |
| -------------------- | ------------------------------------------------------------ |
| Spring Cloud Config  | 微服务配置的统一管理，实时更新，存储的位置可以为Git仓库、SVN仓库、本地文件等 |
| Spring Cloud OAuth2  | Spring Cloud 体系对OAuth2协议的实现，可以用来做多个微服务的统一认证 (验证身份合法性)授权(验证权限) |
| Spring Cloud Eureka  | 负责服务的注册于发现，Eureka体系包括：服务注册中心、服务提供者、服务消费者。 |
| Spring Cloud Ribbon  | 负责服务间调用的负载均衡                                     |
| Spring Cloud Feign   | 简化微服务之间相互调用，只需要一个接口的注解，就可以实现服务之间的HTTP调用 |
| Spring Cloud Hystrix | 微服务熔断器，当服务不可用时，防止请求服务的积压，导致系统过载 |

## 部署

由于系统使用Spring Cloud架构，部署时一般配合Nginx将请求转发到网关模块。部署时需修改对应注册中心和配置中心的地址。

服务启动顺序如下：

1. tclass-config
2. tclass-registry
3. tclass-oauth
4. tclass-gateway
5. 其他业务模块

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

### 班级职务属性（Position）

| 属性 | 类型    | 说明         | 约束       |
| ---- | ------- | ------------ | ---------- |
| id   | int     | 职务唯一标识 | 非空，主键 |
| name | varchar | 职务名称     | 非空       |

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
| file_path   | varchar  | 文件保存路径 | 非空 |
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
> 状态码:
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
>  "code":"业务错误码",
>  "msg":"描述",
>  "data":"结果集",
>  "timeStamp":"时间戳"
> }
> ```

### 公共接口

**登录**

请求方法 POST

请求路径 **/oauth/login**

| 参数       | 说明           |
| ---------- | -------------- |
| username   | 用户名         |
| password   | 密码           |
| code       | 验证码         |
| grant_type | Oauth2认证类型 |

响应状态（登录成功）

```json
{
    "code": 200,
    "data": {
        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjQ5NDkxOTAsInVzZXJfbmFtZSI6IjE3MjQxMTE0MDAiLCJhdXRob3JpdGllcyI6WyJST0xFX2FkbWluIl0sImp0aSI6IjQ1MmQ2Y2VkLWJjZWItNDkzOC05NzIyLTk2NzAxOTkzYzIzOCIsImNsaWVudF9pZCI6IndlYmFwcCIsInNjb3BlIjpbInNlcnZlciJdfQ.CW5PB4zZfx7aAUl9I4U6M2KLZGql_AKMjh1V9U9IafY",
        "token_type": "bearer",
        "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxNzI0MTExNDAwIiwic2NvcGUiOlsic2VydmVyIl0sImF0aSI6IjQ1MmQ2Y2VkLWJjZWItNDkzOC05NzIyLTk2NzAxOTkzYzIzOCIsImV4cCI6MTYyNDk0NzY5MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9hZG1pbiJdLCJqdGkiOiI4MDMxZjEyMS0xOGEwLTRjMjktYTMzZS03NzU5NzBjNDc5NTMiLCJjbGllbnRfaWQiOiJ3ZWJhcHAifQ.9VsKTXvtj9tpLROYNVMzvNnN7gCGd7lSAiCHm1xEYM0",
        "expires_in": 1798,
        "scope": "server",
        "jti": "452d6ced-bceb-4938-9722-96701993c238"
    },
    "msg": "登录成功",
    "timeStamp": 1624947390207
}
```

**获取验证码**

请求方法 GET

请求路径 **/oauth/verifyCode.jpg**

响应状态：返回验证码文件流


### 普通用户接口

**更新用户信息**

请求方法 PUT

请求路径 **/user/**

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
	"code":200,
	"data":true,
	"msg":"执行成功"
}
```

**获取民族列表**

请求方法 GET

请求路径 **/user/nations**

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/user/departments**

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/user/political**

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/user/political**

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/user/roles**

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/user/avatar**

| 参数 |   说明   |
| :--: | :------: |
| file | 头像文件 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**获取用户头像**

请求方法 GET

请求路径 **/user/avatar/{photoPath}**

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
	"code": 200,
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
	"code": 200,
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
	"code": 200,
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
    "code":200,
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
	"code": 200,
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
    "code":200,
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
	"code": 200,
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
	"code": 200,
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
	"code": 200,
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

请求路径 **/user/admin/**

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
	"code": 200,
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

请求路径 **/user/admin/**

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
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除用户数据**

请求方法 DELETE

请求路径 **/user/admin/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 用户的id列表 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**批量导出学生数据**

请求方法 GET

请求路径 **/user/admin/export**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 用户的id列表 |

响应状态：返回excel文件流

**批量导入学生数据**

请求方法 POST

请求路径 **/user/admin/import**

| 参数 |      说明       |
| :--: | :-------------: |
| file | 导入的excel文件 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"已成功导入n条数据!"
}
```

**获取导入模板**

请求方法 GET

请求路径 **/user/admin/template**

响应状态：返回excel文件流

**获取作业数据**

请求方法 GET

请求路径 **/work/admin/{id}**

| 参数 |  说明  |
| :--: | :----: |
|  id  | 作业id |

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径 **/work/admin/**

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
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**更新作业数据**

请求方法 PUT

请求路径 **/work/admin/**

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
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除作业数据**

请求方法 DELETE

请求路径  **/work/admin/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 作业的id列表 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**下载作业文件**

请求方法 GET

请求路径 **/work/admin/download**

|   参数   |  说明  |
| :------: | :----: |
|   wId    | 作业id |
| fileName | 文件名 |

响应状态：返回请求对应的文件流

**打包下载作业文件**

请求方法 GET

请求路径 **/work/admin/download/{wId}**

|   参数   |  说明  |
| :------: | :----: |
|   wId    | 作业id |
| fileName | 文件名 |

响应状态：返回打包zip文件流

**分页获取上传文件信息列表**

请求方法 GET

请求路径 **/work/admin/upload/{wId}**

|  参数   |  说明  |
| :-----: | :----: |
|   wId   | 作业id |
| current | 当前页 |
|  size   | 页大小 |

响应状态（请求成功）

```json
{
	"code": 200,
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

请求路径  **/work/admin/upload**

|        参数        |             说明             |
| :----------------: | :--------------------------: |
|        wId         |            作业id            |
|   deleteSIdList    | 需要删除文件对应的用户id列表 |
| deleteFileNameList |     需要删除的文件名列表     |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"删除成功！"
}
```

**添加文件扩展名**

请求方法 POST

请求路径  **/work/admin/extensions**

| 参数 |  说明  |
| :--: | :----: |
| name | 扩展名 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**删除文件扩展名**

请求方法 DELETE

请求路径  **/work/admin/extensions**

| 参数 |   说明   |
| :--: | :------: |
| eId  | 扩展名id |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**发布通知**

请求方法 POST

请求路径 **/notice/admin/**

|  参数   |   说明   |
| :-----: | :------: |
|   sId   | 发布者id |
|  title  | 通知标题 |
| content | 通知内容 |

响应状态（请求成功）

```json
{
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**编辑通知**

请求方法 PUT

请求路径 **/notice/admin/**

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
    "code":200,
    "data":true,
    "msg":"执行成功"
}
```

**批量删除通知**

请求方法 DELETE

请求路径 **/notice/admin/**

|  参数  |     说明     |
| :----: | :----------: |
| idList | 通知的id列表 |

响应状态（请求成功）
```json
{
    "code":200,
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
