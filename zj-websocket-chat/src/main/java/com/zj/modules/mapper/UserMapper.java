package com.zj.modules.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zj.modules.domain.User;



/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-28
 */
@Mapper
public interface UserMapper {

	/**
	 * 根据id获取
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	@Select("SELECT * FROM zw_user WHERE id = #{id}")
	public User get(@Param("id") int id);

	/**
	 * 获取列表
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	public List<User> queryByPage(User user);
	
	/**
	 * 添加方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	public int add(User user);
	
	/**
	 * 设置无效（ID 为int 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	public int update(User user);
	
	/**
	 * 更新方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	public int setInvalidWithInt(int id);
	
	/**
	 * 设置无效（ID 为string 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-28
	 */
	public int setInvalidWithString(String id);
	
	@Select("select * from zw_user where user_name = #{userName} and password = #{password}")
	public User login(@Param("userName") String userName, @Param("password") String password);
	
	@Select("SELECT * FROM zw_user zu " + 
			"where zu.id != #{userId} and zu.id not in( " + 
			"	select object_id id from zw_friend_user fu " + 
			"	where fu.user_id = #{userId} and type = 1 and fu.valid_flag = 'Y' " + 
//			"	union all " + 
//			"	select user_id id from zw_friend_user fu " + 
//			"	where fu.object_id = #{userId} and type = 1 and fu.valid_flag = 'Y' " + 
			") " + 
			"and zu.valid_flag = 'Y';")
	public List<User> getStranger(int userId);
	
	@Select("select u.* from zw_friend_user fu " + 
			"join zw_user u on u.id = fu.object_id " + 
			"where fu.user_id = #{userId} and u.valid_flag = 'Y' and fu.valid_flag = 'Y'")
	public List<User> getFriends(int userId);
}
