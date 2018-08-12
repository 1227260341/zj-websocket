alter table zw_user add column head varchar(200) default null comment '用户头像' after type; 
alter table zw_group_user add column type int(2) default 0 comment '1为群主' after user_id;