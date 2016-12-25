use test;
create table ds_userlogin(
  id  bigint(19),
  userId  bigint(19),
  userName  varchar(255),
  lastLoginTime  datetime,
  loginIp  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_userlogin add primary key(id);
alter table ds_userlogin change id id bigint(19) auto_increment;

create table ds_user(
  id  bigint(19),
  username  varchar(255),
  password  varchar(255),
  address  varchar(255),
  phone  varchar(255),
  enabled  tinyint,
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_user add primary key(id);
alter table ds_user change id id bigint(19) auto_increment;



create table ds_codeinfo(
  id  bigint(19),
  bizGroup  varchar(255),
  typeCode  varchar(255),
  typeName  varchar(255),
  value  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table ds_codeinfo add primary key(id);
alter table ds_codeinfo change id id bigint(19) auto_increment;

create table ds_product(
  id  bigint(19),
  name  varchar(255),
  lowestCategoryId  bigint(19),
  picUrls  varchar(255),
  price  varchar(255),
  discount  varchar(255),
  marketPrice  varchar(255),
  brandId  bigint(19),
  status  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_product add primary key(id);
alter table ds_product change id id bigint(19) auto_increment;


create table ds_sku(
  id  bigint(19),
  barcode  varchar(255),
  productId  bigint(19),
  marketPrice  varchar(255),
  salePrice  varchar(255),
  picUrls  varchar(255),
  orginalStock  int,
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_sku add primary key(id);
alter table ds_sku change id id bigint(19) auto_increment;


create table ds_Category(
  id  bigint(19),
  name  varchar(255),
  parentId  bigint(19),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_Category add primary key(id);
alter table ds_Category change id id bigint(19) auto_increment;


create table ds_Product_Param(
  id  bigint(19),
  belongId  bigint(19),
  typeCode  varchar(255),
  codeInfoId  bigint(19),
  codeInfoValue  varchar(255),
  extraValue  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_Product_Param add primary key(id);
alter table ds_Product_Param change id id bigint(19) auto_increment;


create table ds_SkuAttribute(
  id  bigint(19),
  belongId  bigint(19),
  typeCode  varchar(255),
  codeInfoId  bigint(19),
  codeInfoValue  varchar(255),
  extraValue  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_SkuAttribute add primary key(id);
alter table ds_SkuAttribute change id id bigint(19) auto_increment;

create table ds_skustock(
  id  bigint(19),
  skuId  bigint(19),
  count  bigint(19),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_skustock add primary key(id);
alter table ds_skustock change id id bigint(19) auto_increment;

create table ds_promotion_record(
  id  bigint(19),
  name  varchar(255),
  startTime  datetime,
  endTime  datetime,
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_promotion_record add primary key(id);
alter table ds_promotion_record change id id bigint(19) auto_increment;

create table ds_promotion_condition(
  id  bigint(19),
  belongPromotionId  bigint(19),
  promotionConditionType  varchar(255),
  moneyStart  varchar(255),
  moneyEnd  varchar(255),
  productIds  varchar(255),
  isAndCondition  tinyint,
  isExclusion  tinyint,
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_promotion_condition add primary key(id);
alter table ds_promotion_condition change id id bigint(19) auto_increment;


create table ds_promotion_discount(
  id  bigint(19),
  belongPromotionId  bigint(19),
  promotionDiscountType  varchar(255),
  promotionDiscountTypeCalTarget  varchar(255),
  money  varchar(255),
  discount  varchar(255),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_promotion_discount add primary key(id);
alter table ds_promotion_discount change id id bigint(19) auto_increment;

create table ds_promotion_target(
  id  bigint(19),
  belongPromotionId  bigint(19),
  userId  bigint(19),
  creator_Id  bigint(19),
  creator_name  varchar(255),
  updator_Id  bigint(19),
  updator_name  varchar(255),
  created_Time  datetime,
  updated_Time  datetime,
  re_mark  varchar(255),
  lock_version  bigint(19)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table ds_promotion_target add primary key(id);
alter table ds_promotion_target change id id bigint(19) auto_increment;
