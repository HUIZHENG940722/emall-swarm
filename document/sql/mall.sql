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
     sort bigint(11) NOT NULL  DEFAULT 0 COMMENT '排序' ,
     icon VARCHAR(255)    COMMENT '图标' ,
     keywords VARCHAR(255)    COMMENT '搜索关键字' ,
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
    product_attribute_category_id bigint(20) NOT NULL   COMMENT '商品属性分类id' ,
    name VARCHAR(64) NOT NULL   COMMENT '商品名称' ,
    pic VARCHAR(255)    COMMENT '商品大图标' ,
    product_sn VARCHAR(64) NOT NULL   COMMENT '货号' ,
    delete_status INT(1) NOT NULL  DEFAULT 0 COMMENT '删除状态;0->未删除；1->已删除' ,
    publish_status INT(1) NOT NULL  DEFAULT 1 COMMENT '上架状态;0->下架；1->上架' ,
    new_status INT(1) NOT NULL  DEFAULT 1 COMMENT '新品状态;0->不是新品；1->新品' ,
    recommend_status INT(1) NOT NULL  DEFAULT 0 COMMENT '推荐状态;0->不推荐；1->推荐' ,
    verify_status INT(1) NOT NULL  DEFAULT 0 COMMENT '审核状态;0->未审核；1->审核通过' ,
    sort bigint(11) NOT NULL   COMMENT '排序' ,
    sale bigint(11) NOT NULL  DEFAULT 0 COMMENT '销量' ,
    price DECIMAL(10,2) NOT NULL   COMMENT '价格' ,
    promotion_price DECIMAL(10,2)    COMMENT '促销价格' ,
    gift_growth bigint(11) NOT NULL  DEFAULT 0 COMMENT '赠送的成长值' ,
    gift_point bigint(11) NOT NULL  DEFAULT 0 COMMENT '赠送的积分' ,
    use_point_limit bigint(11)    COMMENT '限制使用积分数' ,
    sub_title VARCHAR(64)    COMMENT '副标题' ,
    description TEXT    COMMENT '商品描述' ,
    original_price DECIMAL(10,2)    COMMENT '市场价' ,
    stock bigint(11) NOT NULL   COMMENT '库存' ,
    low_stock bigint(11)    COMMENT '库存预警' ,
    unit VARCHAR(64) NOT NULL   COMMENT '单位' ,
    weight DECIMAL(10,2) NOT NULL   COMMENT '商品重量;默认为克' ,
    preview_status INT(1) NOT NULL   COMMENT '是否为预告商品;0->不是；1->是' ,
    service_ids VARCHAR(64)    COMMENT '产品服务;以逗号分隔，包括：1->无忧退货；2->快速退款；3->免费包邮' ,
    keywords VARCHAR(255)    COMMENT '搜索关键字' ,
    note VARCHAR(255)    COMMENT '备注' ,
    album_pics VARCHAR(255)    COMMENT '画册图片;连产品图片限制为5张，以逗号分割' ,
    detail_title VARCHAR(255)    COMMENT '商品详情标题' ,
    detail_desc TEXT    COMMENT '商品详情描述' ,
    detail_html TEXT    COMMENT '商品详情网页内容' ,
    promotion_start_time DATETIME    COMMENT '促销开始时间' ,
    promotion_end_time DATETIME    COMMENT '促销结束时间' ,
    promotion_per_limit bigint(11)    COMMENT '活动限购数量' ,
    promotion_type INT(1)    COMMENT '促销类型;0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购' ,
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
      first_letter VARCHAR(64)    COMMENT '首字母' ,
      sort bigint(11)    COMMENT '排序' ,
      factory_status INT(1) NOT NULL  DEFAULT 0 COMMENT '是否为品牌制造商;0->不是；1->是；' ,
      show_status INT(1) NOT NULL  DEFAULT 0 COMMENT '显示状态;0->不显示；1->显示；' ,
      product_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '商品数量' ,
      product_comment_count bigint(11)   DEFAULT 0 COMMENT '商品评论数量' ,
      logo VARCHAR(255) NOT NULL   COMMENT '品牌logo' ,
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
)  COMMENT = '商品类型表';

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

-- ----------------------------
-- Records of pms_product_ladder
-- ----------------------------
DROP TABLE IF EXISTS pms_product_ladder;
CREATE TABLE pms_product_ladder(
       tenant_id bigint(20)    COMMENT '租户号' ,
       revision bigint(20)    COMMENT '乐观锁' ,
       created_by bigint(20)    COMMENT '创建人' ,
       created_time DATETIME NOT NULL   COMMENT '创建时间' ,
       updated_by bigint(20)    COMMENT '更新人' ,
       updated_time DATETIME    COMMENT '更新时间' ,
       id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
       product_id bigint(20) NOT NULL   COMMENT '商品id' ,
       count bigint(11) NOT NULL   COMMENT '满足的商品数量' ,
       discount DECIMAL(10,2) NOT NULL   COMMENT '折扣' ,
       price DECIMAL(10,2) NOT NULL   COMMENT '折扣后的价格' ,
       PRIMARY KEY (id)
)  COMMENT = '商品阶梯价格表(只针对同商品)';

-- ----------------------------
-- Table structure for pms_product_full_reduction
-- ----------------------------
DROP TABLE IF EXISTS pms_product_full_reduction;
CREATE TABLE pms_product_full_reduction(
       tenant_id bigint(20)    COMMENT '租户号' ,
       revision bigint(20)    COMMENT '乐观锁' ,
       created_by bigint(20)    COMMENT '创建人' ,
       created_time DATETIME NOT NULL   COMMENT '创建时间' ,
       updated_by bigint(20)    COMMENT '更新人' ,
       updated_time DATETIME    COMMENT '更新时间' ,
       id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
       product_id bigint(20) NOT NULL   COMMENT '商品id' ,
       full_price DECIMAL(10,2) NOT NULL   COMMENT '商品满足金额' ,
       reduce_price DECIMAL(10,2) NOT NULL   COMMENT '商品减少金额' ,
       PRIMARY KEY (id)
)  COMMENT = '商品满减表(只针对同商品)';

-- ----------------------------
-- Records of pms_member_price
-- ----------------------------
DROP TABLE IF EXISTS pms_member_price;
CREATE TABLE pms_member_price(
     tenant_id bigint(20)    COMMENT '租户号' ,
     revision bigint(20)    COMMENT '乐观锁' ,
     created_by bigint(20)    COMMENT '创建人' ,
     created_time DATETIME NOT NULL   COMMENT '创建时间' ,
     updated_by bigint(20)    COMMENT '更新人' ,
     updated_time DATETIME    COMMENT '更新时间' ,
     id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
     product_id bigint(20) NOT NULL   COMMENT '商品id' ,
     member_level_id bigint(20) NOT NULL   COMMENT '会员等级id' ,
     member_price DECIMAL(10,2) NOT NULL   COMMENT '会员价格' ,
     member_level_name VARCHAR(255) NOT NULL   COMMENT '会员等级名称' ,
     PRIMARY KEY (id)
)  COMMENT = '会员价格表';

-- ----------------------------
-- Records of pms_sku_stock
-- ----------------------------
DROP TABLE IF EXISTS pms_sku_stock;
CREATE TABLE pms_sku_stock(
      tenant_id bigint(20)    COMMENT '租户号' ,
      revision bigint(20)    COMMENT '乐观锁' ,
      created_by bigint(20)    COMMENT '创建人' ,
      created_time DATETIME NOT NULL   COMMENT '创建时间' ,
      updated_by bigint(20)    COMMENT '更新人' ,
      updated_time DATETIME    COMMENT '更新时间' ,
      id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
      product_id bigint(20) NOT NULL   COMMENT '商品id' ,
      sku_code VARCHAR(64) NOT NULL   COMMENT 'sku编码' ,
      price DECIMAL(10,2) NOT NULL   COMMENT '价格' ,
      stock bigint(11) NOT NULL   COMMENT '库存' ,
      low_stock bigint(11) NOT NULL   COMMENT '预警库存' ,
      pic VARCHAR(255)    COMMENT '展示图片' ,
      sale bigint(11) NOT NULL   COMMENT '销量' ,
      PRIMARY KEY (id)
)  COMMENT = 'sku库存';

-- ----------------------------
-- Records of cms_subject_product_relation
-- ----------------------------
DROP TABLE IF EXISTS cms_subject_product_relation;
CREATE TABLE cms_subject_product_relation(
     tenant_id bigint(20)    COMMENT '租户号' ,
     revision bigint(20)    COMMENT '乐观锁' ,
     created_by bigint(20)    COMMENT '创建人' ,
     created_time DATETIME NOT NULL   COMMENT '创建时间' ,
     updated_by bigint(20)    COMMENT '更新人' ,
     updated_time DATETIME    COMMENT '更新时间' ,
     id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
     subject_id bigint(20) NOT NULL   COMMENT '专题id' ,
     product_id bigint(20) NOT NULL   COMMENT '商品id' ,
     PRIMARY KEY (id)
)  COMMENT = '专题商品关联表';

-- ----------------------------
-- Records of cms_preference_area_product_relation
-- ----------------------------
DROP TABLE IF EXISTS cms_preference_area_product_relation;
CREATE TABLE cms_preference_area_product_relation(
     tenant_id bigint(20)    COMMENT '租户号' ,
     revision bigint(20)    COMMENT '乐观锁' ,
     created_by bigint(20)    COMMENT '创建人' ,
     created_time DATETIME NOT NULL   COMMENT '创建时间' ,
     updated_by bigint(20)    COMMENT '更新人' ,
     updated_time DATETIME    COMMENT '更新时间' ,
     id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
     preference_area_id bigint(20) NOT NULL   COMMENT '优选专区主键id' ,
     product_id bigint(20) NOT NULL   COMMENT '商品id' ,
     PRIMARY KEY (id)
)  COMMENT = '优选专区与产品关系表';

-- ----------------------------
-- Table structure for pms_product_category_attribute_relation
-- ----------------------------
DROP TABLE IF EXISTS pms_product_category_attribute_relation;
CREATE TABLE pms_product_category_attribute_relation(
    tenant_id bigint(20)    COMMENT '租户号' ,
    revision bigint(20)    COMMENT '乐观锁' ,
    created_by bigint(20)    COMMENT '创建人' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间' ,
    updated_by bigint(20)    COMMENT '更新人' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
    product_category_id bigint(20) NOT NULL   COMMENT '商品分类id' ,
    product_attribute_id bigint(20) NOT NULL   COMMENT '商品类型id' ,
    PRIMARY KEY (id)
)  COMMENT = '商品分类类型关联表';

-- ----------------------------
-- Table structure for cms_subject
-- ----------------------------
DROP TABLE IF EXISTS cms_subject;
CREATE TABLE cms_subject(
    tenant_id bigint(20)    COMMENT '租户号' ,
    revision bigint(20)    COMMENT '乐观锁' ,
    created_by bigint(20)    COMMENT '创建人' ,
    created_time DATETIME NOT NULL   COMMENT '创建时间' ,
    updated_by bigint(20)    COMMENT '更新人' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
    category_id bigint(20) NOT NULL   COMMENT '分类id' ,
    title VARCHAR(255) NOT NULL   COMMENT '标题' ,
    pic VARCHAR(255)    COMMENT '专区主图' ,
    product_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '关联的商品数量' ,
    recommend_status INT(1)    COMMENT '' ,
    collect_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '' ,
    read_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '' ,
    comment_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '' ,
    album_pics TEXT    COMMENT '画册图片;用分号隔开' ,
    description TEXT    COMMENT '专题描述' ,
    show_status INT(1) NOT NULL   COMMENT '显示状态;0->不显示；1->显示' ,
    content TEXT    COMMENT '' ,
    forward_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '转发数' ,
    category_name VARCHAR(64)    COMMENT '专题分类名称' ,
    PRIMARY KEY (id)
)  COMMENT = '专题表';

# 权限模块
-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS ums_admin;
CREATE TABLE ums_admin(
      tenant_id bigint(20)    COMMENT '租户号' ,
      revision bigint(20)    COMMENT '乐观锁' ,
      created_by bigint(20)    COMMENT '创建人' ,
      created_time DATETIME NOT NULL   COMMENT '创建时间' ,
      updated_by bigint(20)    COMMENT '更新人' ,
      updated_time DATETIME    COMMENT '更新时间' ,
      id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
      username VARCHAR(64) NOT NULL   COMMENT '用户名' ,
      password VARCHAR(64) NOT NULL   COMMENT '密码' ,
      icon VARCHAR(255)    COMMENT '头像' ,
      email VARCHAR(64)    COMMENT '邮箱' ,
      nick_name VARCHAR(64)    COMMENT '昵称' ,
      note TEXT    COMMENT '备注' ,
      login_time DATETIME    COMMENT '登录时间' ,
      status INT(1) NOT NULL  DEFAULT 0 COMMENT '账号启用状态;0->未启用；1->已启用' ,
      PRIMARY KEY (id)
)  COMMENT = '后台用户表';

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS ums_role;
CREATE TABLE ums_role(
     tenant_id bigint(20)    COMMENT '租户号' ,
     revision bigint(20)    COMMENT '乐观锁' ,
     created_by bigint(20)    COMMENT '创建人' ,
     created_time DATETIME NOT NULL   COMMENT '创建时间' ,
     updated_by bigint(20)    COMMENT '更新人' ,
     updated_time DATETIME    COMMENT '更新时间' ,
     id bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键id' ,
     name VARCHAR(64) NOT NULL   COMMENT '角色名称' ,
     description VARCHAR(255)    COMMENT '角色描述' ,
     admin_count bigint(11) NOT NULL  DEFAULT 0 COMMENT '后台用户数量' ,
     status INT(1) NOT NULL  DEFAULT 1 COMMENT '启用状态;0->禁用；1->启用' ,
     sort bigint(11) NOT NULL  DEFAULT 0 COMMENT '排序' ,
     PRIMARY KEY (id)
)  COMMENT = '后台用户角色表';