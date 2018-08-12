package com.zj.modules.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zj.modules.domain.Group;
import com.zj.modules.domain.GroupUser;
import com.zj.modules.domain.User;


/**
 * <p>
 * 群用户表 Mapper 接口
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-31
 */
@Mapper
public interface GroupUserMapper {

	/**
	 * 根据id获取
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	@Select("SELECT * FROM zw_group_user WHERE id = #{id}")
	public GroupUser get(@Param("id") int id);

	/**
	 * 获取列表
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public List<GroupUser> queryByPage(GroupUser groupUser);
	
	/**
	 * 添加方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int add(GroupUser groupUser);
	
	/**
	 * 设置无效（ID 为int 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int update(GroupUser groupUser);
	
	/**
	 * 更新方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int setInvalidWithInt(int id);
	
	/**
	 * 设置无效（ID 为string 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int setInvalidWithString(String id);
	
	@Select("SELECT g.* FROM zw_group_user gu " + 
			"join zw_group g on g.id = gu.group_id " + 
			"WHERE gu.user_id = #{userId} and gu.valid_flag = 'Y' " + 
			"and g.valid_flag = 'Y' order by g.make_time;")
	public List<Group> getGroup(@Param("userId") int userId);
	
	@Select("select * from zw_user u " + 
			"where u.valid_flag = 'Y' and u.id not in ( " + 
			"select gu.user_id from zw_group g " + 
			"join zw_group_user gu on gu.group_id = g.id " + 
			"where g.id = #{groupId} and g.valid_flag = 'Y' and gu.valid_flag = 'Y')")
	public List<User> getStrangerGroupUser(@Param("groupId") int groupId);
	
	@Select("select u.* from zw_group g " + 
			"join zw_group_user gu on gu.group_id = g.id " + 
			"join zw_user u on u.id = gu.user_id " + 
			"where g.id = #{groupId} and g.valid_flag = 'Y' and gu.valid_flag = 'Y' and u.valid_flag = 'Y'")
	public List<User> getGroupUser(@Param("groupId") int groupId);
	
	
	@Select("update zw_group_user set valid_flag = 'N' " + 
			"where group_id = #{groupId} and user_id = #{userId}")
	public void deleteGroupUser(@Param("groupId") int groupId, @Param("userId") int userId);
}
