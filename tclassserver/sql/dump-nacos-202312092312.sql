-- MySQL dump 10.13  Distrib 8.2.0, for macos14.0 (arm64)
--
-- Host: mysql    Database: nacos
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info`
(
    `id`                 bigint                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`            varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
    `group_id`           varchar(128) COLLATE utf8mb3_bin          DEFAULT NULL,
    `content`            longtext COLLATE utf8mb3_bin     NOT NULL COMMENT 'content',
    `md5`                varchar(32) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COLLATE utf8mb3_bin COMMENT 'source user',
    `src_ip`             varchar(50) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'source ip',
    `app_name`           varchar(128) COLLATE utf8mb3_bin          DEFAULT NULL,
    `tenant_id`          varchar(128) COLLATE utf8mb3_bin          DEFAULT '' COMMENT '租户字段',
    `c_desc`             varchar(256) COLLATE utf8mb3_bin          DEFAULT NULL,
    `c_use`              varchar(64) COLLATE utf8mb3_bin           DEFAULT NULL,
    `effect`             varchar(64) COLLATE utf8mb3_bin           DEFAULT NULL,
    `type`               varchar(64) COLLATE utf8mb3_bin           DEFAULT NULL,
    `c_schema`           text COLLATE utf8mb3_bin,
    `encrypted_data_key` text COLLATE utf8mb3_bin         NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`, `group_id`, `tenant_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 39
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info`
    DISABLE KEYS */;
INSERT INTO `config_info`
VALUES (8, 'tclass-gateway', 'DEFAULT_GROUP',
        'server:\n  port: 6666\nspring:\n  application:\n    name: tclass-gateway #指定应用的唯一标识/服务名\n    #网关配置中心\n  cloud:\n    gateway:\n      discovery:\n        locator:  #路由访问方式：http://Gateway_HOST:Gateway_PORT/大写的serviceId/**，其中微服务应用名默认大写访问。\n          enabled: true #是否与服务发现组件进行结合，通过 serviceId(必须设置成大写) 转发到具体的服务实例。默认为false，设为true便开启通过服务中心的自动根据 serviceId 创建路由的功能。\n          lower-case-service-id: true #允许通过模块名小写代理\n      routes:\n        - id: tclass-login-server\n          uri: lb://tclass-login-server #网关路由到springcloud-client模块，lb指向内部注册模块\n          predicates: #转发谓词，用于设置匹配路由的规则\n            - Path=/login/** #通过请求路径匹配\n        - id: tclass-notice-server\n          uri: lb://tclass-notice-server\n          predicates:\n            - Path=/notice/**\n        - id: tclass-user-server\n          uri: lb://tclass-user-server\n          predicates:\n            - Path=/user/**\n        - id: tclass-work-server\n          uri: lb://tclass-work-server\n          predicates:\n            - Path=/work/**\n        - id: tclass-oauth-server\n          uri: lb://tclass-oauth-server\n          predicates:\n            - Path=/oauth/**\n        #            - Method=GET #通过请求方式匹配\n        #            - RemoteAddr=127.0.0.1/25 #通过请求id匹配，只有在某个 ip 区间号段的请求才会匹配路由，其中/后的是子网掩码\n        #            - After=2018-01-20T06:06:06+08:00[Asia/Shanghai] #根据时间进行匹配，在指定时间之后才会匹配路由\n        #            - Before=2018-01-20T06:06:06+08:00[Asia/Shanghai] #根据时间进行匹配，在指定时间之前才会匹配路由\n        #            - Between=2018-01-20T06:06:06+08:00[Asia/Shanghai], 2019-01-20T06:06:06+08:00[Asia/Shanghai] #根据时间段进行匹配，处于指定时间段才会匹配路由\n  main:\n    allow-bean-definition-overriding: true #是否允许同Bean覆盖',
        '94dbcdec4d8ba8c88c67a69c138ddb7e', '2023-04-01 02:58:25', '2023-04-01 03:38:58', 'nacos', '172.28.0.1', '', '',
        '', '', '', 'yaml', '', ''),
       (10, 'tclass-oauth-server', 'DEFAULT_GROUP', 'server:\n  port: 9999', 'fe570bc58c90735b10202cbaf95ed44c',
        '2023-04-01 03:33:17', '2023-12-09 17:11:23', NULL, '172.19.0.1', '', '', '', '', '', 'yaml', '', ''),
       (15, 'tclass-notice-server', 'DEFAULT_GROUP', 'server:\n  port: 8002\n  servlet:\n    context-path: \"/notice\"',
        '24e23879d53c04511c473459babe2343', '2023-04-01 04:20:09', '2023-12-09 17:03:07', NULL, '172.19.0.1', '', '',
        '', '', '', 'yaml', '', ''),
       (19, 'tclass-user-server', 'DEFAULT_GROUP', 'server:\n  port: 8003\n  servlet:\n    context-path: \"/user\"',
        '0978e7de448fe3dbe4a7c266db0b52e9', '2023-04-01 04:42:56', '2023-12-09 17:02:39', NULL, '172.19.0.1', '', '',
        '', '', '', 'yaml', '', ''),
       (21, 'tclass-work-server', 'DEFAULT_GROUP', 'server:\n  port: 8004\n  servlet:\n    context-path: \"/work\"',
        '1650728cb983f01bed08f33bfc66d19c', '2023-04-01 04:49:51', '2023-12-09 17:02:19', NULL, '172.19.0.1', '', '',
        '', '', '', 'yaml', '', ''),
       (38, 'tclass-public.yaml', 'DEFAULT_GROUP',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    password: teoan\n  elasticsearch:\n    uris: localhost:9200 \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量  ',
        '5aa87ed14c12186d2b4dc70ca2c5f35f', '2023-12-09 17:16:05', '2023-12-09 17:16:05', NULL, '172.19.0.1', '', '',
        NULL, NULL, NULL, 'yaml', NULL, '');
/*!40000 ALTER TABLE `config_info`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_aggr`
(
    `id`           bigint                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
    `datum_id`     varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
    `content`      longtext COLLATE utf8mb3_bin     NOT NULL COMMENT '内容',
    `gmt_modified` datetime                         NOT NULL COMMENT '修改时间',
    `app_name`     varchar(128) COLLATE utf8mb3_bin DEFAULT NULL,
    `tenant_id`    varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`, `group_id`, `tenant_id`, `datum_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_beta`
(
    `id`                 bigint                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`            varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
    `group_id`           varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
    `app_name`           varchar(128) COLLATE utf8mb3_bin          DEFAULT NULL COMMENT 'app_name',
    `content`            longtext COLLATE utf8mb3_bin     NOT NULL COMMENT 'content',
    `beta_ips`           varchar(1024) COLLATE utf8mb3_bin         DEFAULT NULL COMMENT 'betaIps',
    `md5`                varchar(32) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'md5',
    `gmt_create`         datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`       datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`           text COLLATE utf8mb3_bin COMMENT 'source user',
    `src_ip`             varchar(50) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'source ip',
    `tenant_id`          varchar(128) COLLATE utf8mb3_bin          DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text COLLATE utf8mb3_bin         NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`, `group_id`, `tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_info_tag`
(
    `id`           bigint                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `data_id`      varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
    `group_id`     varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
    `tenant_id`    varchar(128) COLLATE utf8mb3_bin          DEFAULT '' COMMENT 'tenant_id',
    `tag_id`       varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
    `app_name`     varchar(128) COLLATE utf8mb3_bin          DEFAULT NULL COMMENT 'app_name',
    `content`      longtext COLLATE utf8mb3_bin     NOT NULL COMMENT 'content',
    `md5`          varchar(32) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'md5',
    `gmt_create`   datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `src_user`     text COLLATE utf8mb3_bin COMMENT 'source user',
    `src_ip`       varchar(50) COLLATE utf8mb3_bin           DEFAULT NULL COMMENT 'source ip',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`, `group_id`, `tenant_id`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config_tags_relation`
(
    `id`        bigint                           NOT NULL COMMENT 'id',
    `tag_name`  varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
    `tag_type`  varchar(64) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT 'tag_type',
    `data_id`   varchar(255) COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
    `group_id`  varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
    `tenant_id` varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
    `nid`       bigint                           NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`nid`),
    UNIQUE KEY `uk_configtagrelation_configidtag` (`id`, `tag_name`, `tag_type`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_capacity`
(
    `id`                bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `group_id`          varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
    `quota`             int unsigned                     NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int unsigned                     NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int unsigned                     NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int unsigned                     NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
    `max_aggr_size`     int unsigned                     NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int unsigned                     NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `his_config_info`
(
    `id`                 bigint unsigned                  NOT NULL,
    `nid`                bigint unsigned                  NOT NULL AUTO_INCREMENT,
    `data_id`            varchar(255) COLLATE utf8mb3_bin NOT NULL,
    `group_id`           varchar(128) COLLATE utf8mb3_bin NOT NULL,
    `app_name`           varchar(128) COLLATE utf8mb3_bin          DEFAULT NULL COMMENT 'app_name',
    `content`            longtext COLLATE utf8mb3_bin     NOT NULL,
    `md5`                varchar(32) COLLATE utf8mb3_bin           DEFAULT NULL,
    `gmt_create`         datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`       datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `src_user`           text COLLATE utf8mb3_bin,
    `src_ip`             varchar(50) COLLATE utf8mb3_bin           DEFAULT NULL,
    `op_type`            char(10) COLLATE utf8mb3_bin              DEFAULT NULL,
    `tenant_id`          varchar(128) COLLATE utf8mb3_bin          DEFAULT '' COMMENT '租户字段',
    `encrypted_data_key` text COLLATE utf8mb3_bin         NOT NULL COMMENT '秘钥',
    PRIMARY KEY (`nid`),
    KEY `idx_gmt_create` (`gmt_create`),
    KEY `idx_gmt_modified` (`gmt_modified`),
    KEY `idx_did` (`data_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 45
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info`
    DISABLE KEYS */;
INSERT INTO `his_config_info`
VALUES (10, 28, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名\n  main:\n    allow-bean-definition-overriding: true\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password: \nmybatis-plus:\n  global-config:\n    banner: false\n\noauth2:\n  resource:\n    permit-all-paths: /oauth/login,/oauth/verifyCode.jpg #配置认证放行路径',
        '8304b57aff16bb4be993cb3deefa386e', '2023-12-05 22:56:27', '2023-12-05 08:56:24', 'nacos', '172.28.0.1', 'U',
        '', ''),
       (10, 29, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名\n  main:\n    allow-bean-definition-overriding: true\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password: \nmybatis-plus:\n  global-config:\n    banner: false\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量    \n\noauth2:\n  resource:\n    permit-all-paths: /oauth/login,/oauth/verifyCode.jpg #配置认证放行路径',
        '68b601abe1cc0b039c896706d9bb55b4', '2023-12-05 22:57:39', '2023-12-05 08:57:40', 'nacos', '172.28.0.1', 'U',
        '', ''),
       (10, 30, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名\n  main:\n    allow-bean-definition-overriding: true\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password:\n  elasticsearch:\n    uris: localhost:9200 \nmybatis-plus:\n  global-config:\n    banner: false\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量    \n\noauth2:\n  resource:\n    permit-all-paths: /oauth/login,/oauth/verifyCode.jpg #配置认证放行路径',
        'c2f2bd1add0505d0eb462a9bb7d7ecfc', '2023-12-05 22:58:22', '2023-12-05 08:58:23', 'nacos', '172.28.0.1', 'U',
        '', ''),
       (10, 31, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名\n  main:\n    allow-bean-definition-overriding: true\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password:\n  elasticsearch:\n    uris: localhost:9200 \nmybatis-plus:\n  global-config:\n    banner: false\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量    \n\noauth2:\n  resource:\n    permit-all-paths: /oauth/login,/oauth/verifyCode.jpg #配置认证放行路径',
        'c2f2bd1add0505d0eb462a9bb7d7ecfc', '2023-12-09 16:27:49', '2023-12-09 16:27:49', NULL, '172.19.0.1', 'U', '',
        ''),
       (19, 32, 'tclass-user-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8003\n  servlet:\n    context-path: \"/user\"\nspring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: localhost\n    database: 0\n    port: 6379\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\n# 注册中心配置\neureka:\n  instance:\n    prefer-ip-address: true #优先使用IP地址注册\n  client:\n    service-url:\n      defaultZone: http://teoan:277077411@localhost:8761/eureka/ #eureka的注册地址\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - www.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths: /avatar/get/**,/admin/template #配置认证放行路径',
        'c804f00626efbf32e6ed9ab7c070f1d6', '2023-12-09 16:29:19', '2023-12-09 16:29:19', NULL, '172.19.0.1', 'U', '',
        ''),
       (0, 33, 'tclass-public', 'DEFAULT_GROUP', '', 'aa: aa ', '641e6f7708540ca39fd82318bd2339be',
        '2023-12-09 16:48:13', '2023-12-09 16:48:14', NULL, '172.19.0.1', 'I', '', ''),
       (28, 34, 'tclass-public', 'DEFAULT_GROUP', '', 'aa: aa ', '641e6f7708540ca39fd82318bd2339be',
        '2023-12-09 17:02:13', '2023-12-09 17:02:13', NULL, '172.19.0.1', 'U', '', ''),
       (21, 35, 'tclass-work-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8004\n  servlet:\n    context-path: \"/work\"\nspring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    # password: 277077411\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径',
        '28e8f1fdee269cde6f973c2f5988cbc5', '2023-12-09 17:02:19', '2023-12-09 17:02:19', NULL, '172.19.0.1', 'U', '',
        ''),
       (19, 36, 'tclass-user-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8003\n  servlet:\n    context-path: \"/user\"\nspring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: localhost\n    database: 0\n    port: 6379\n    password: teoan\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\n# 注册中心配置\neureka:\n  instance:\n    prefer-ip-address: true #优先使用IP地址注册\n  client:\n    service-url:\n      defaultZone: http://teoan:277077411@localhost:8761/eureka/ #eureka的注册地址\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - www.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths: /avatar/get/**,/admin/template #配置认证放行路径',
        'b41f77aedbb4df4ae357a688c37ee853', '2023-12-09 17:02:38', '2023-12-09 17:02:39', NULL, '172.19.0.1', 'U', '',
        ''),
       (15, 37, 'tclass-notice-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 8002\n  servlet:\n    context-path: \"/notice\"\nspring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password: \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\n# 注册中心配置\neureka:\n  instance:\n    prefer-ip-address: true #优先使用IP地址注册\n  client:\n    service-url:\n      defaultZone: http://teoan:277077411@localhost:8761/eureka/ #eureka的注册地址\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\nmybatis-plus:\n  global-config:\n    banner: false',
        '806d2e2846b5f47f235c657ed2a49209', '2023-12-09 17:03:06', '2023-12-09 17:03:07', NULL, '172.19.0.1', 'U', '',
        ''),
       (28, 38, 'tclass-public', 'DEFAULT_GROUP', '',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    # password: 277077411\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径',
        '09c3e158558b7d6d5a31bb7892184211', '2023-12-09 17:03:30', '2023-12-09 17:03:30', NULL, '172.19.0.1', 'U', '',
        ''),
       (28, 39, 'tclass-public', 'DEFAULT_GROUP', '',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    # password: 277077411\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量  ',
        '08813901ec92dc818121325c772211f4', '2023-12-09 17:04:40', '2023-12-09 17:04:40', NULL, '172.19.0.1', 'U', '',
        ''),
       (10, 40, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名\n  main:\n    allow-bean-definition-overriding: true\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: redis\n    database: 0\n    port: 6379\n    password: teoan\n  elasticsearch:\n    uris: localhost:9200 \nmybatis-plus:\n  global-config:\n    banner: false\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量    \n\noauth2:\n  resource:\n    permit-all-paths: /oauth/login,/oauth/verifyCode.jpg #配置认证放行路径',
        'f9e0147a89078fb7300cafc64247127a', '2023-12-09 17:04:52', '2023-12-09 17:04:52', NULL, '172.19.0.1', 'U', '',
        ''),
       (10, 41, 'tclass-oauth-server', 'DEFAULT_GROUP', '',
        'server:\n  port: 9999\nspring:\n  application:\n    name: tclass-oauth-server #指定应用的唯一标识/服务名',
        '7645fc96a7e1e9e527d0891e058e3f00', '2023-12-09 17:11:23', '2023-12-09 17:11:23', NULL, '172.19.0.1', 'U', '',
        ''),
       (28, 42, 'tclass-public', 'DEFAULT_GROUP', '',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    password: teoan\n  elasticsearch:\n    uris: localhost:9200 \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量  ',
        '5aa87ed14c12186d2b4dc70ca2c5f35f', '2023-12-09 17:12:45', '2023-12-09 17:12:45', NULL, '172.19.0.1', 'U', '',
        ''),
       (28, 43, 'tclass-public', 'DEFAULT_GROUP', '',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    password: teoan\n  elasticsearch:\n    uris: localhost:9200 \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量  ',
        '5aa87ed14c12186d2b4dc70ca2c5f35f', '2023-12-09 17:15:34', '2023-12-09 17:15:34', NULL, '172.19.0.1', 'D', '',
        ''),
       (0, 44, 'tclass-public.yaml', 'DEFAULT_GROUP', '',
        'spring:\n  datasource: #数据库连接配置\n    type: com.alibaba.druid.pool.DruidDataSource\n    username: root\n    password: teoan_pwd+-\n    url: jdbc:mysql://mysql:3306/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\n    druid:\n      stat-view-servlet:\n        login-username: Teoan\n        login-password: 277077411\n  redis:\n    host: mysql\n    database: 0\n    port: 6379\n    password: teoan\n  elasticsearch:\n    uris: localhost:9200 \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n  #配置文件上传大小\n  servlet:\n    multipart:\n      max-request-size: 30MB\n      max-file-size: 50MB\n  #将前端传来的String解析为Data\n  mvc:\n    format:\n      date: yyyy-MM-dd HH:mm:ss\nmybatis-plus:\n  global-config:\n    banner: false\nfeign:\n  hystrix:\n    enabled: true\n  compression:\n    request:\n      #是否启用请求GZIP压缩，true：启用，false：不启用\n      enabled: true\n      #压缩支持的MIME TYPE\n      mime-types: text/xml,application/xml,application/json\n      #压缩数据的最小值\n      min-request-size: 2048\n    response:\n      #是否启用响应GZIP压缩，true：启用，false：不启用\n      enabled: true\n  client:\n    config:\n      #feign全局配置\n      default:\n        #指定日志级别，none：不记录任何日志，basic：仅记录请求方法、URL、响应状态代码以及执行时间（适合生产环境）\n        #headers：在basic基础上，记录请求和响应的header，full：记录请求和响应的header、body和元数据，默认none\n        loggerLevel: headers\n      #feign指定客户端配置，即仅对指定调用的服务生效\n      eureka-client:\n        loggerLevel: full\nhystrix:\n  command:\n    default:\n      execution:\n        timeout:\n          enabled: true\n        isolation:\n          strategy: SEMAPHORE\n#fastdfs 配置\nfdfs:\n  # 读取时间\n  so-timeout: 1500\n  # 连接超时时间\n  connect-timeout: 600\n  # 缩略图\n  thumb-image:\n    width: 150\n    height: 150\n  # Tracker服务\n  tracker-list:\n    - qexw.xumumi.com:22122\noauth2:\n  resource:\n    permit-all-paths:  #配置认证放行路径\nteoan:\n  log:\n    batch: 20                #普通日志批处理数量\n    enabled:\n      elasticsearch: true    #是否启用ES日志处理\n      mongodb: true          #是否启用mongoDB日志处理\n      print: true            #是否启用TeoanLog注解普通日志打印\n    task:\n      core: 5                #日志处理线程数量\n      max: 20                #日志处理线程最大数量\n      queue: 500             #日志处理队列最大数量  ',
        '5aa87ed14c12186d2b4dc70ca2c5f35f', '2023-12-09 17:16:05', '2023-12-09 17:16:05', NULL, '172.19.0.1', 'I', '',
        '');
/*!40000 ALTER TABLE `his_config_info`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions`
(
    `role`     varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `resource` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `action`   varchar(8) COLLATE utf8mb4_unicode_ci   NOT NULL,
    UNIQUE KEY `uk_role_permission` (`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions`
    DISABLE KEYS */;
INSERT INTO `permissions`
VALUES ('ROLE_ADMIN', ':*:*', 'rw');
/*!40000 ALTER TABLE `permissions`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles`
(
    `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    `role`     varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    UNIQUE KEY `idx_user_role` (`username`, `role`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles`
    DISABLE KEYS */;
INSERT INTO `roles`
VALUES ('nacos', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_capacity`
(
    `id`                bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id`         varchar(128) COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
    `quota`             int unsigned                     NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
    `usage`             int unsigned                     NOT NULL DEFAULT '0' COMMENT '使用量',
    `max_size`          int unsigned                     NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
    `max_aggr_count`    int unsigned                     NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
    `max_aggr_size`     int unsigned                     NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    `max_history_count` int unsigned                     NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
    `gmt_create`        datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`      datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant_info`
(
    `id`            bigint                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `kp`            varchar(128) COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
    `tenant_id`     varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
    `tenant_name`   varchar(128) COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_name',
    `tenant_desc`   varchar(256) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tenant_desc',
    `create_source` varchar(32) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT 'create_source',
    `gmt_create`    bigint                           NOT NULL COMMENT '创建时间',
    `gmt_modified`  bigint                           NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`, `tenant_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_info`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `username` varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL,
    `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
    `enabled`  tinyint(1)                              NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
    DISABLE KEYS */;
INSERT INTO `users`
VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
/*!40000 ALTER TABLE `users`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nacos'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-12-09 23:12:43
