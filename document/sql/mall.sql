-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
drop table if exists `ums_admin`;
create table `ums_admin`(
    `id` bigint(20) not null comment '记录id' auto_increment primary key,
    `username` varchar(64) not null comment '用户名',
    `password` varchar(64) not null comment '密码',
    `icon` varchar(500) comment '头像',
    `email` varchar(100) comment '邮箱',
    `nick_name` varchar(200) comment '昵称',
    `note` varchar(500) comment '备注信息',
    `create_time` datetime not null comment '创建时间',
    `login_time` datetime comment '最后登录时间',
    `status` int(1) default 1 comment '账号启用状态：0->禁用；1->启用'
) engine=InnoDB auto_increment=1 default charset=utf8 comment='后台用户表';

# 商品模块管理
-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS pms_product_category;
CREATE TABLE pms_product_category(
     tenant_id bigint(20)    COMMENT '租户号' ,
     revision bigint(20)    COMMENT '乐观锁' ,
     created_by bigint(20)    COMMENT '创建人' ,
     created_time DATETIME NOT NULL   COMMENT '创建时间' ,
     updated_by bigint(20)    COMMENT '更新人' ,
     updated_time DATETIME    COMMENT '更新时间' ,
     id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键ID' ,
     parent_id bigint(20) NOT NULL   COMMENT '父主键ID;无父主键值为0' ,
     name VARCHAR(64) NOT NULL   COMMENT '分类名称' ,
     level INT(1) NOT NULL   COMMENT '分类级别' ,
     product_count INT NOT NULL  DEFAULT 0 COMMENT '商品数量' ,
     product_unit VARCHAR(64) NOT NULL   COMMENT '商品单位' ,
     nav_status INT(1) NOT NULL  DEFAULT 0 COMMENT '是否显示在导航栏;0->不显示；1->显示' ,
     show_status INT(1) NOT NULL  DEFAULT 0 COMMENT '显示状态;0->不显示；1->显示' ,
     icon VARCHAR(255)    COMMENT '图标' ,
     description TEXT    COMMENT '描述' ,
     PRIMARY KEY (id)
)  COMMENT = '商品分类表';

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS pms_product;
CREATE TABLE pms_product(
        tenant_id bigint(20)    COMMENT '租户号' ,
        revision bigint(20)    COMMENT '乐观锁' ,
        created_by bigint(20)    COMMENT '创建人' ,
        created_time DATETIME    COMMENT '创建时间' ,
        updated_by bigint(20)    COMMENT '更新人' ,
        updated_time DATETIME    COMMENT '更新时间' ,
        id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
        product_category_id bigint(20) NOT NULL   COMMENT '商品分类id' ,
        brand_id bigint(20) NOT NULL   COMMENT '品牌id' ,
        freight_template_id bigint(20)    COMMENT '商品运费模版id' ,
        name VARCHAR(64) NOT NULL   COMMENT '商品名称' ,
        delete_status INT(1) NOT NULL   COMMENT '删除状态;0->未删除；1->已删除' ,
        publish_status INT(1) NOT NULL   COMMENT '上架状态;0->下架；1->上架' ,
        new_status INT(1) NOT NULL   COMMENT '新品状态;0->不是新品；1->新品' ,
        verify_status INT(1) NOT NULL   COMMENT '审核状态;0->未审核；1->审核通过' ,
        sort bigint(11) NOT NULL   COMMENT '排序' ,
        price DECIMAL(10,2) NOT NULL   COMMENT '价格' ,
        PRIMARY KEY (id)
)  COMMENT = '商品表';


-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS pms_brand;
CREATE TABLE pms_brand(
      tenant_id bigint(20)    COMMENT '租户号' ,
      revision bigint(20)    COMMENT '乐观锁' ,
      created_by bigint(20)    COMMENT '创建人' ,
      created_time DATETIME NOT NULL   COMMENT '创建时间' ,
      updated_by bigint(20)    COMMENT '更新人' ,
      updated_time DATETIME    COMMENT '更新时间' ,
      id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
      name VARCHAR(64) NOT NULL   COMMENT '品牌名称' ,
      big_pic VARCHAR(255)    COMMENT '专区大图' ,
      brand_story TEXT    COMMENT '品牌故事' ,
      PRIMARY KEY (id)
)  COMMENT = '商品品牌表';

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS pms_product_attribute_category;
CREATE TABLE pms_product_attribute_category(
       tenant_id bigint(20)    COMMENT '租户号' ,
       revision bigint(20)    COMMENT '乐观锁' ,
       created_by bigint(20)    COMMENT '创建人' ,
       created_time DATETIME NOT NULL   COMMENT '创建时间' ,
       updated_by bigint(20)    COMMENT '更新人' ,
       updated_time DATETIME    COMMENT '更新时间' ,
       id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
       name VARCHAR(64) NOT NULL   COMMENT '分类名称' ,
       attribute_count INT NOT NULL  DEFAULT 0 COMMENT '属性数量' ,
       param_count INT NOT NULL  DEFAULT 0 COMMENT '参数数量' ,
       PRIMARY KEY (id)
)  COMMENT = '商品属性分类表';

-- ----------------------------
-- Table structure for pms_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS pms_product_attribute;
CREATE TABLE pms_product_attribute(
      tenant_id bigint(20)    COMMENT '租户号' ,
      revision bigint(20)    COMMENT '乐观锁' ,
      created_by bigint(20)    COMMENT '创建人' ,
      created_time DATETIME NOT NULL   COMMENT '创建时间' ,
      updated_by bigint(20)    COMMENT '更新人' ,
      updated_time DATETIME    COMMENT '更新时间' ,
      id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
      product_attribute_category_id bigint(20) NOT NULL   COMMENT '商品属性分类id' ,
      name VARCHAR(64) NOT NULL   COMMENT '名称' ,
      select_type INT(1) NOT NULL   COMMENT '属性选择类型;0->唯一；1->单选；2->多选；对应属性和参数意义不同' ,
      input_type INT(1) NOT NULL   COMMENT '属性录入方式;0->手工录入；1->从列表中选取' ,
      input_list VARCHAR(255) NOT NULL   COMMENT '可选值列表;以逗号隔开' ,
      sort INT NOT NULL   COMMENT '排序字段' ,
      filter_type INT(1)    COMMENT '分类筛选样式;1->普通；1->颜色' ,
      search_type INT(1)    COMMENT '搜索类型;0->不需要进行检索；1->关键字检索；2->范围检索' ,
      related_status INT(1)    COMMENT '相同属性产品是否关联;0->不关联；1->关联' ,
      hand_add_status INT(1)    COMMENT '是否支持手动新增;0->不支持；1->支持' ,
      type INT(1) NOT NULL   COMMENT '属性的类型;0->规格；1->参数' ,
      PRIMARY KEY (id)
)  COMMENT = '商品属性表';

-- ----------------------------
-- Table structure for pms_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS pms_product_attribute_value;
CREATE TABLE pms_product_attribute_value(
        tenant_id bigint(20)    COMMENT '租户号' ,
        revision bigint(20)    COMMENT '乐观锁' ,
        created_by bigint(20)    COMMENT '创建人' ,
        created_time DATETIME NOT NULL   COMMENT '创建时间' ,
        updated_by bigint(20)    COMMENT '更新人' ,
        updated_time DATETIME    COMMENT '更新时间' ,
        id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
        product_id bigint(20) NOT NULL   COMMENT '商品id' ,
        product_attribute_id bigint(20) NOT NULL   COMMENT '商品属性id' ,
        value VARCHAR(255) NOT NULL   COMMENT '手动添加规格或参数的值;参数单值，规格有多个时以逗号隔开' ,
        PRIMARY KEY (id)
)  COMMENT = '商品属性值表';

