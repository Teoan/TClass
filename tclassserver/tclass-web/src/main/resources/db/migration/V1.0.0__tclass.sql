/*
 Navicat Premium Data Transfer

 Source Server         : Teoan
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : tclass

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 19/12/2020 13:31:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '组织部');
INSERT INTO `department` VALUES (2, '实践部');
INSERT INTO `department` VALUES (3, '外联部');
INSERT INTO `department` VALUES (4, '学习部');
INSERT INTO `department` VALUES (5, '文体部');
INSERT INTO `department` VALUES (6, '英语协会');
INSERT INTO `department` VALUES (7, '心理协会');
INSERT INTO `department` VALUES (8, '计算机协会');
INSERT INTO `department` VALUES (9, '乒乓球协会');
INSERT INTO `department` VALUES (10, '摄影协会');

-- ----------------------------
-- Table structure for extension
-- ----------------------------
DROP TABLE IF EXISTS `extension`;
CREATE TABLE `extension`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of extension
-- ----------------------------
INSERT INTO `extension` VALUES (1, '无');
INSERT INTO `extension` VALUES (3, '.html');
INSERT INTO `extension` VALUES (4, '.txt');
INSERT INTO `extension` VALUES (5, '.sql');
INSERT INTO `extension` VALUES (6, '.docx');
INSERT INTO `extension` VALUES (7, '.xlsx');
INSERT INTO `extension` VALUES (8, '.pptx');
INSERT INTO `extension` VALUES (9, '.7z');
INSERT INTO `extension` VALUES (10, '.java');
INSERT INTO `extension` VALUES (11, '.jpeg');

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
                                          `installed_rank` int(11) NOT NULL,
                                          `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                          `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                          `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                          `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                          `checksum` int(11) NULL DEFAULT NULL,
                                          `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                          `installed_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                          `execution_time` int(11) NOT NULL,
                                          `success` tinyint(1) NOT NULL,
                                          PRIMARY KEY (`installed_rank`) USING BTREE,
                                          INDEX `flyway_schema_history_s_idx`(`success`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1.0.0', 'tclass', 'SQL', 'V1.0.0__tclass.sql', -2051240447, 'Teoan', '2020-12-17 22:24:59', 1899, 1);

-- ----------------------------
-- Table structure for nation
-- ----------------------------
DROP TABLE IF EXISTS `nation`;
CREATE TABLE `nation`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nation
-- ----------------------------
INSERT INTO `nation` VALUES (1, '汉族');
INSERT INTO `nation` VALUES (2, '蒙古族');
INSERT INTO `nation` VALUES (3, '回族');
INSERT INTO `nation` VALUES (4, '藏族');
INSERT INTO `nation` VALUES (5, '维吾尔族');
INSERT INTO `nation` VALUES (6, '苗族');
INSERT INTO `nation` VALUES (7, '彝族');
INSERT INTO `nation` VALUES (8, '壮族');
INSERT INTO `nation` VALUES (9, '布依族');
INSERT INTO `nation` VALUES (10, '朝鲜族');
INSERT INTO `nation` VALUES (11, '满族');
INSERT INTO `nation` VALUES (12, '侗族');
INSERT INTO `nation` VALUES (13, '瑶族');
INSERT INTO `nation` VALUES (14, '白族');
INSERT INTO `nation` VALUES (15, '土家族');
INSERT INTO `nation` VALUES (16, '哈尼族');
INSERT INTO `nation` VALUES (17, '哈萨克族');
INSERT INTO `nation` VALUES (18, '傣族');
INSERT INTO `nation` VALUES (19, '黎族');
INSERT INTO `nation` VALUES (20, '傈僳族');
INSERT INTO `nation` VALUES (21, '佤族');
INSERT INTO `nation` VALUES (22, '畲族');
INSERT INTO `nation` VALUES (23, '高山族');
INSERT INTO `nation` VALUES (24, '拉祜族');
INSERT INTO `nation` VALUES (25, '水族');
INSERT INTO `nation` VALUES (26, '东乡族');
INSERT INTO `nation` VALUES (27, '纳西族');
INSERT INTO `nation` VALUES (28, '景颇族');
INSERT INTO `nation` VALUES (29, '柯尔克孜族');
INSERT INTO `nation` VALUES (30, '土族');
INSERT INTO `nation` VALUES (31, '达斡尔族');
INSERT INTO `nation` VALUES (32, '仫佬族');
INSERT INTO `nation` VALUES (33, '羌族');
INSERT INTO `nation` VALUES (34, '布朗族');
INSERT INTO `nation` VALUES (35, '撒拉族');
INSERT INTO `nation` VALUES (36, '毛难族');
INSERT INTO `nation` VALUES (37, '仡佬族');
INSERT INTO `nation` VALUES (38, '锡伯族');
INSERT INTO `nation` VALUES (39, '阿昌族');
INSERT INTO `nation` VALUES (40, '普米族');
INSERT INTO `nation` VALUES (41, '塔吉克族');
INSERT INTO `nation` VALUES (42, '怒族');
INSERT INTO `nation` VALUES (43, '乌孜别克族');
INSERT INTO `nation` VALUES (44, '俄罗斯族');
INSERT INTO `nation` VALUES (45, '鄂温克族');
INSERT INTO `nation` VALUES (46, '崩龙族');
INSERT INTO `nation` VALUES (47, '保安族');
INSERT INTO `nation` VALUES (48, '裕固族');
INSERT INTO `nation` VALUES (49, '京族');
INSERT INTO `nation` VALUES (50, '塔塔尔族');
INSERT INTO `nation` VALUES (51, '独龙族');
INSERT INTO `nation` VALUES (52, '鄂伦春族');
INSERT INTO `nation` VALUES (53, '赫哲族');
INSERT INTO `nation` VALUES (54, '门巴族');
INSERT INTO `nation` VALUES (55, '珞巴族');
INSERT INTO `nation` VALUES (56, '基诺族');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `s_id` int(11) NOT NULL COMMENT '通知发起人',
                           `edit_s_id` int(11) NULL DEFAULT NULL COMMENT '编辑人',
                           `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
                           `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
                           `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知内容',
                           `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                           `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `sId`(`s_id`) USING BTREE,
                           CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (20, 1724111400, 1724111400, 0, '学校通知', '为更好处理毕业生相关工作，学院将于本周六（8月8日）下午4点在腾讯会议召开准毕业生会议。主持人：史婉怡  主讲人：张文台\n会议内容：\n1. 关于实习实训处理。\n2. 论文事情怎么处理好。\n3. 工作什么时候出去找合适，以及现在毕业生就业情况。\n4. 大四需要处理的事情。5.问答 有疑问可以询问。\n\n会议 ID：404 401 142     16：00准时开始，因为房间人数有限，参加的同学尽快进入。这是大家毕业找工作前最大一次会议，大家不要错过。', '2020-08-10 14:28:09', '2020-12-14 20:55:25');
INSERT INTO `notice` VALUES (29, 1724111400, NULL, 0, '学校通知', '12月12日（周六）上午10点市人社在鼎湖的人力资源服务产业园举行开园活动及2021届毕业生招聘会专场（10点－11点半）。\n请有意参与招聘会的同学于12月8日上午12点（本周二前）向本班就业指导员报名\n报名的同学于12月12日上午9点45分在国砚广场路边上车。', '2020-12-19 12:14:56', '2020-12-19 12:14:56');
INSERT INTO `notice` VALUES (30, 1724111400, NULL, 0, '项目申报系统开放的通知', '2021年度广东省科技创新战略专项资金（大学生科技创新培育） 项目申报系统已开放。【系统操作及相关参考文件】已附于2021年申报平台首页，请有意参加的老师指导好项目组学生完成相应的申报工作。以下几个方面请注意：\n\n1.  学生注册账号及申报时间为：2020 年 11 月 25 日-12 月 9 日。请大家合理安排时间，以免影响下一阶段审核工作。\n2.  申报阶段相关细则请具体参照《广东省科技创新战略专项资金（大学生科技创新培育）管理办法》和《2021年广东省科技创新战略专项资金（大学生科技创新培育）项目申报指南》要求推动相关工作。请转发班群，学院老师有收到该通知了，如有想法及时跟学院老师沟通与申请。', '2020-12-19 12:43:20', '2020-12-19 12:43:20');
INSERT INTO `notice` VALUES (31, 1724111400, NULL, 0, '学校通知', '第七届“助学·筑梦·铸人”主题作品征集活动，同学们可以看看，征集时间在2020年11月2日-2020年12月31日，现在已开通报名，有意向参加活动的同学可以参加啦，参加活动的同学记得提前跟我说一声哈，最后提交作品后记得截图证明，以及将作品发我一份，参与活动的同学可以获得2分综测分！', '2020-12-19 12:44:19', '2020-12-19 12:44:19');
INSERT INTO `notice` VALUES (32, 1724111400, 1724111400, 0, '学校通知', '学校有关对特困新生及受灾受疫情影响学生等的资助，具体资助及相关要求如下\n1. 2020级特困新生学费资助:\n    对象:经学校认定的20级特困新生\n2. 受疫情影响学生资助\n    对象:在校学生\n    申请条件：\n    思想品德良好，无违纪违法行为；\n    本人或家人感染新冠肺炎或受疫情影响而造成家庭经济困难的学生。\n3. 受灾学生资助:\n对象:在校学生\n申请条件:\n    ​1. 无违纪违规行为\n    2. 因低温雨雪冰冻、超强台风等自然灾害造成的人员伤亡、主要居住房屋倒塌、作为生活来源的农作物绝收或财产经济受到重大损失的情况；\n4. 来自受灾地区家庭，有当地村委或政府部门的受灾证明等材料。（证明材料可先拍照作证，再将原件寄回学校提交；若是开学申请时已提交，请在表格说明）', '2020-12-19 12:47:45', '2020-12-19 13:29:12');
INSERT INTO `notice` VALUES (33, 1724111400, 1724111400, 0, '关于英语四六级通知', '# 英语四六级通知\n1. 报考资格\n    本次补报考生应满足唯一条件：已参加2020年9月延考且当次英语四级（CET4）笔试成绩大于等于425分的考生。\n2. 报名方式及时间\n    1. 本次补报科目仅为英语六级（CET6）笔试，其他科目不予补报。\n    2. 本次补报时间为11月9日9:00-11日17:00，过时不再予以补报。\n    3. 符合补报资格的考生，请自行登录报名系统http://cet-bm.neea.edu.cn完成CET6笔试补报及缴费。', '2020-12-19 12:49:30', '2020-12-19 12:51:27');

-- ----------------------------
-- Table structure for politicsstatus
-- ----------------------------
DROP TABLE IF EXISTS `politicsstatus`;
CREATE TABLE `politicsstatus`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT,
                                   `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of politicsstatus
-- ----------------------------
INSERT INTO `politicsstatus` VALUES (1, '中共党员');
INSERT INTO `politicsstatus` VALUES (2, '中共预备党员');
INSERT INTO `politicsstatus` VALUES (3, '共青团员');
INSERT INTO `politicsstatus` VALUES (4, '民革党员');
INSERT INTO `politicsstatus` VALUES (5, '民盟盟员');
INSERT INTO `politicsstatus` VALUES (6, '民建会员');
INSERT INTO `politicsstatus` VALUES (7, '民进会员');
INSERT INTO `politicsstatus` VALUES (8, '农工党党员');
INSERT INTO `politicsstatus` VALUES (9, '致公党党员');
INSERT INTO `politicsstatus` VALUES (10, '九三学社社员');
INSERT INTO `politicsstatus` VALUES (11, '台盟盟员');
INSERT INTO `politicsstatus` VALUES (12, '无党派民主人士');
INSERT INTO `politicsstatus` VALUES (13, '普通公民');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (1, '班长');
INSERT INTO `position` VALUES (2, '纪委');
INSERT INTO `position` VALUES (3, '学习委员');
INSERT INTO `position` VALUES (4, '信息委员');
INSERT INTO `position` VALUES (5, '心理委员');
INSERT INTO `position` VALUES (6, '学习委员');
INSERT INTO `position` VALUES (7, '文娱委员');
INSERT INTO `position` VALUES (8, '宣传委员');
INSERT INTO `position` VALUES (9, '生活委员');
INSERT INTO `position` VALUES (10, '组织委员');
INSERT INTO `position` VALUES (11, '无');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         `zh_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_admin', '管理员');
INSERT INTO `role` VALUES (2, 'ROLE_student', '普通用户');

-- ----------------------------
-- Table structure for stu_dep_ref
-- ----------------------------
DROP TABLE IF EXISTS `stu_dep_ref`;
CREATE TABLE `stu_dep_ref`  (
                                `sId` int(11) NULL DEFAULT NULL,
                                `dId` int(11) NULL DEFAULT NULL,
                                INDEX `sId`(`sId`) USING BTREE,
                                INDEX `dId`(`dId`) USING BTREE,
                                CONSTRAINT `stu_dep_ref_ibfk_1` FOREIGN KEY (`sId`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `stu_dep_ref_ibfk_2` FOREIGN KEY (`dId`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu_dep_ref
-- ----------------------------
INSERT INTO `stu_dep_ref` VALUES (1724111408, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111402, 5);
INSERT INTO `stu_dep_ref` VALUES (1724111403, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111404, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111405, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111406, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111407, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111408, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111409, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111413, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111414, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111416, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111417, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111418, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111423, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111424, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111425, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111426, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111427, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111428, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111429, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111430, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111431, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111432, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111433, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111434, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111435, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111436, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111437, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111438, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111439, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111440, 1);
INSERT INTO `stu_dep_ref` VALUES (1724111441, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111442, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111443, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111444, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111445, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111403, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111402, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111405, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111404, 1);
INSERT INTO `stu_dep_ref` VALUES (1724111407, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111406, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111413, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111409, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111416, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111428, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111414, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111417, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111418, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111432, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111423, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111424, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111425, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111426, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111427, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111442, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111429, 10);
INSERT INTO `stu_dep_ref` VALUES (1724111430, 3);
INSERT INTO `stu_dep_ref` VALUES (1724111431, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111438, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111433, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111434, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111435, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111436, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111437, 3);
INSERT INTO `stu_dep_ref` VALUES (1724111440, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111439, 1);
INSERT INTO `stu_dep_ref` VALUES (1724111441, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111443, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111444, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111445, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111421, 3);
INSERT INTO `stu_dep_ref` VALUES (1724111421, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111421, 5);
INSERT INTO `stu_dep_ref` VALUES (1724111421, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111419, 1);
INSERT INTO `stu_dep_ref` VALUES (1724111419, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111419, 6);
INSERT INTO `stu_dep_ref` VALUES (1724111419, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111422, 3);
INSERT INTO `stu_dep_ref` VALUES (1724111422, 3);
INSERT INTO `stu_dep_ref` VALUES (1724111420, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111420, 5);
INSERT INTO `stu_dep_ref` VALUES (1724111401, 9);
INSERT INTO `stu_dep_ref` VALUES (1724111401, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111400, 4);
INSERT INTO `stu_dep_ref` VALUES (1724111400, 2);
INSERT INTO `stu_dep_ref` VALUES (1724111400, 7);
INSERT INTO `stu_dep_ref` VALUES (1724111400, 8);
INSERT INTO `stu_dep_ref` VALUES (1724111400, 9);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学号',
                            `role_id` int(11) NOT NULL COMMENT '权限',
                            `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生名字',
                            `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                            `gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
                            `nation_id` int(8) NOT NULL COMMENT '民族',
                            `native_place` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '籍贯',
                            `politic_id` int(8) NULL DEFAULT 13 COMMENT '政治面貌',
                            `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                            `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
                            `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
                            `pos_id` int(11) NULL DEFAULT 11 COMMENT '班级职位',
                            `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                            `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                            `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近登录时间',
                            `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像链接',
                            `deleted` tinyint(1) NULL DEFAULT 0,
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `roleId`(`role_id`) USING BTREE,
                            INDEX `id`(`id`) USING BTREE,
                            INDEX `nationId`(`nation_id`) USING BTREE,
                            INDEX `politicId`(`politic_id`) USING BTREE,
                            INDEX `posId`(`pos_id`) USING BTREE,
                            CONSTRAINT `student_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                            CONSTRAINT `student_ibfk_2` FOREIGN KEY (`nation_id`) REFERENCES `nation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                            CONSTRAINT `student_ibfk_3` FOREIGN KEY (`politic_id`) REFERENCES `politicsstatus` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                            CONSTRAINT `student_ibfk_4` FOREIGN KEY (`pos_id`) REFERENCES `position` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1724111446 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1724111400, 1, '王涛', 'e10adc3949ba59abbe56e057f20f883e', '男', 53, '广东省,广州市,天河区', 1, 'wang@qq.com', '15644442252', '陕西西安新城区', 1, '2020-07-23 16:32:47', '2020-12-19 12:42:22', '2020-12-19 12:42:23', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111401, 2, '陈静', 'e10adc3949ba59abbe56e057f20f883e', '女', 44, '广东省,广州市,天河区', 11, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', 11, '2020-07-23 16:38:45', '2020-12-14 22:41:13', '2020-12-14 22:11:45', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111402, 2, '赵琳浩', 'e10adc3949ba59abbe56e057f20f883e', '男', 35, '广东省,广州市,天河区', 10, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', 3, '2020-07-23 16:38:45', '2020-12-14 22:41:13', '2020-11-29 17:25:00', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111403, 2, '鹿存亮', 'e10adc3949ba59abbe56e057f20f883e', '男', 30, '广东省,广州市,天河区', 9, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', 4, '2020-07-23 16:38:45', '2020-12-14 22:41:13', '2020-11-21 21:55:06', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111404, 2, '姚森', 'e10adc3949ba59abbe56e057f20f883e', '男', 27, '广东省,广州市,天河区', 8, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', 5, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111405, 2, '云星', 'e10adc3949ba59abbe56e057f20f883e', '女', 10, '广东省,广州市,天河区', 4, 'yunxing@qq.com', '15644442252', '陕西西安新城区', 6, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111406, 2, '贾旭明', 'e10adc3949ba59abbe56e057f20f883e', '男', 46, '广东省,广州市,天河区', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', 7, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111407, 2, '张黎明', 'e10adc3949ba59abbe56e057f20f883e', '男', 40, '广东省,广州市,天河区', 12, 'zhangliming@qq.com', '18979994478', '广东珠海', 8, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111408, 2, '薛磊', 'e10adc3949ba59abbe56e057f20f883e', '男', 5, '广东省,广州市,天河区', 12, 'xuelei@qq.com', '15648887741', '西安市雁塔区', 9, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111409, 2, '张洁', 'e10adc3949ba59abbe56e057f20f883e', '女', 11, '广东省,广州市,天河区', 4, 'zhangjie@qq.com', '13695557742', '海口市美兰区', 10, '2020-07-23 16:38:45', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111413, 2, '王涛1', 'e10adc3949ba59abbe56e057f20f883e', '男', 14, '广东省,广州市,天河区', 6, 'wang@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:02:20', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111414, 2, '陈静1', 'e10adc3949ba59abbe56e057f20f883e', '女', 21, '广东省,广州市,天河区', 8, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', 11, '2020-07-23 20:02:20', '2020-12-14 22:41:13', '2020-07-29 12:10:29', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111416, 2, '王涛1', 'e10adc3949ba59abbe56e057f20f883e', '男', 41, '广东省,广州市,天河区', 9, 'wang@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111417, 2, '陈静1', 'e10adc3949ba59abbe56e057f20f883e', '女', 56, '广东省,广州市,天河区', 1, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111418, 2, '赵琳浩1', 'e10adc3949ba59abbe56e057f20f883e', '男', 14, '广东省,广州市,天河区', 2, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', '2020-08-09 10:57:55', 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111419, 2, '鹿存亮1', 'e10adc3949ba59abbe56e057f20f883e', '男', 36, '湖北省,十堰市,茅箭区', 13, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111420, 2, '姚森', 'e10adc3949ba59abbe56e057f20f883e', '男', 46, '湖北省,宜昌市,点军区', 4, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111421, 2, '云星1', 'e10adc3949ba59abbe56e057f20f883e', '女', 34, '广东省,广州市,天河区', 5, 'yunxing@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111422, 2, '贾旭明', 'e10adc3949ba59abbe56e057f20f883e', '男', 48, '广东省,广州市,天河区', 4, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111423, 2, '张黎明1', 'e10adc3949ba59abbe56e057f20f883e', '男', 33, '广东省,广州市,天河区', 4, 'zhangliming@qq.com', '18979994478', '广东珠海', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111424, 2, '薛磊1', 'e10adc3949ba59abbe56e057f20f883e', '男', 17, '广东省,广州市,天河区', 12, 'xuelei@qq.com', '15648887741', '西安市雁塔区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111425, 2, '张洁1', 'e10adc3949ba59abbe56e057f20f883e', '女', 19, '广东省,广州市,天河区', 2, 'zhangjie@qq.com', '13695557742', '海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111426, 2, '王涛2', 'e10adc3949ba59abbe56e057f20f883e', '男', 23, '广东省,广州市,天河区', 11, 'wang@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111427, 2, '陈静2', 'e10adc3949ba59abbe56e057f20f883e', '女', 46, '广东省,广州市,天河区', 8, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111428, 2, '赵琳浩2', 'e10adc3949ba59abbe56e057f20f883e', '男', 29, '广东省,广州市,天河区', 11, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111429, 2, '鹿存亮2', 'e10adc3949ba59abbe56e057f20f883e', '男', 11, '广东省,广州市,天河区', 10, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111430, 2, '姚森2', 'e10adc3949ba59abbe56e057f20f883e', '男', 4, '广东省,广州市,天河区', 2, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111431, 2, '云星2', 'e10adc3949ba59abbe56e057f20f883e', '女', 15, '广东省,广州市,天河区', 2, 'yunxing@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111432, 2, '贾旭明2', 'e10adc3949ba59abbe56e057f20f883e', '男', 27, '广东省,广州市,天河区', 3, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111433, 2, '张黎明2', 'e10adc3949ba59abbe56e057f20f883e', '男', 27, '广东省,广州市,天河区', 11, 'zhangliming@qq.com', '18979994478', '广东珠海', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111434, 2, '薛磊2', 'e10adc3949ba59abbe56e057f20f883e', '男', 29, '广东省,广州市,天河区', 3, 'xuelei@qq.com', '15648887741', '西安市雁塔区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111435, 2, '张洁2', 'e10adc3949ba59abbe56e057f20f883e', '女', 22, '广东省,广州市,天河区', 5, 'zhangjie@qq.com', '13695557742', '海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111436, 2, '王涛3', 'e10adc3949ba59abbe56e057f20f883e', '男', 32, '广东省,广州市,天河区', 11, 'wang@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111437, 2, '陈静3', 'e10adc3949ba59abbe56e057f20f883e', '女', 16, '广东省,广州市,天河区', 13, 'chenjing@qq.com', '18795556693', '海南省海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111438, 2, '赵琳浩3', 'e10adc3949ba59abbe56e057f20f883e', '男', 54, '广东省,广州市,天河区', 13, 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111439, 2, '鹿存亮3', 'e10adc3949ba59abbe56e057f20f883e', '男', 38, '广东省,广州市,天河区', 9, 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111440, 2, '姚森3', 'e10adc3949ba59abbe56e057f20f883e', '男', 9, '广东省,广州市,天河区', 12, 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111441, 2, '云星3', 'e10adc3949ba59abbe56e057f20f883e', '女', 42, '广东省,广州市,天河区', 3, 'yunxing@qq.com', '15644442252', '陕西西安新城区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111442, 2, '贾旭明3', 'e10adc3949ba59abbe56e057f20f883e', '男', 29, '广东省,广州市,天河区', 2, 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111443, 2, '张黎明3', 'e10adc3949ba59abbe56e057f20f883e', '男', 56, '广东省,广州市,天河区', 9, 'zhangliming@qq.com', '18979994478', '广东珠海', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111444, 2, '薛磊3', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '广东省,广州市,天河区', 5, 'xuelei@qq.com', '15648887741', '西安市雁塔区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);
INSERT INTO `student` VALUES (1724111445, 2, '张洁3', 'e10adc3949ba59abbe56e057f20f883e', '女', 32, '广东省,广州市,天河区', 12, 'zhangjie@qq.com', '13695557742', '海口市美兰区', 11, '2020-07-23 20:04:49', '2020-12-14 22:41:13', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png', 0);

-- ----------------------------
-- Table structure for upload
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload`  (
                           `s_id` int(11) NOT NULL COMMENT '学生编号',
                           `w_id` int(11) NOT NULL COMMENT '作业编号',
                           `file_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
                           `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
                           `size` bigint(11) NOT NULL COMMENT '文件大小',
                           `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '上传时间',
                           `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                           INDEX `sId`(`s_id`) USING BTREE,
                           INDEX `wId`(`w_id`) USING BTREE,
                           CONSTRAINT `upload_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                           CONSTRAINT `upload_ibfk_2` FOREIGN KEY (`w_id`) REFERENCES `work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `s_id` int(11) NOT NULL COMMENT '发布者',
                         `edit_s_id` int(11) NULL DEFAULT NULL COMMENT '最近编辑者',
                         `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                         `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '作业启用状态',
                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作业名称',
                         `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                         `last_time` datetime(0) NULL DEFAULT NULL COMMENT '最晚提交时间',
                         `extension_id` int(11) NOT NULL DEFAULT 1 COMMENT '文件扩展名',
                         `file_name_format_enum` int(11) NULL DEFAULT NULL COMMENT '文件名命名枚举',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `id`(`id`) USING BTREE,
                         INDEX `sId`(`s_id`) USING BTREE,
                         CONSTRAINT `work_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (1, 1724111423, 1724111400, '数据库存储过程作业收集', 0, '数据库作业', '2020-10-17 22:19:37', '2020-12-19 12:54:59', '2020-11-27 22:20:00', 5, 4);
INSERT INTO `work` VALUES (2, 1724111400, NULL, 'Java算法作业', 0, 'Java作业', '2020-12-19 12:54:35', '2020-12-19 12:54:35', '2020-12-31 00:00:00', 10, 2);
INSERT INTO `work` VALUES (3, 1724111400, NULL, '请同学们将医保凭证上传到此处', 0, '医保凭证', '2020-12-19 12:58:38', '2020-12-19 12:58:38', '2020-12-31 00:00:00', 11, 2);

-- ----------------------------
-- Procedure structure for addref
-- ----------------------------
DROP PROCEDURE IF EXISTS `addref`;
delimiter ;;
CREATE PROCEDURE `addref`()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=42 DO
            INSERT INTO `tclass`.`stu_dep_ref`() VALUES ();
            SET i=i+1;

        END WHILE;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for addStudent
-- ----------------------------
DROP PROCEDURE IF EXISTS `addStudent`;
delimiter ;;
CREATE PROCEDURE `addStudent`(num INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=num DO

            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('王涛',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '广东广州', ROUND((RAND()*13)), 'wang@qq.com', '15644442252', '陕西西安新城区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('陈静',i), 'e10adc3949ba59abbe56e057f20f883e', '女', ROUND((RAND()*56)), '海南', ROUND((RAND()*13)), 'chenjing@qq.com', '18795556693', '海南省海口市美兰区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('赵琳浩',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '陕西', ROUND((RAND()*13)), 'zhao@qq.com', '15698887795', '陕西省西安市莲湖区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('鹿存亮',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '陕西',ROUND((RAND()*13)), 'zhao@qq.com', '15612347795', '陕西省西安市莲湖区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('姚森',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '河南', ROUND((RAND()*13)), 'yaosen@qq.com', '14785559936', '河南洛阳人民大道58号');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('云星',i), 'e10adc3949ba59abbe56e057f20f883e', '女', ROUND((RAND()*56)), '陕西西安', ROUND((RAND()*13)), 'yunxing@qq.com', '15644442252', '陕西西安新城区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('贾旭明',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '广东广州', ROUND((RAND()*13)), 'jiaxuming@qq.com', '15644441234', '广东省广州市天河区冼村路');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('张黎明',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '广东', ROUND((RAND()*13)), 'zhangliming@qq.com', '18979994478', '广东珠海');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('薛磊',i), 'e10adc3949ba59abbe56e057f20f883e', '男', ROUND((RAND()*56)), '陕西西安', ROUND((RAND()*13)), 'xuelei@qq.com', '15648887741', '西安市雁塔区');
            INSERT INTO student(roleId,name,password,gender,nationId,nativePlace,politicId,email,phone,address) VALUES (2, CONCAT('张洁',i), 'e10adc3949ba59abbe56e057f20f883e', '女', ROUND((RAND()*56)), '海南', ROUND((RAND()*13)), 'zhangjie@qq.com', '13695557742', '海口市美兰区');

            SET i=i+1;

        END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
