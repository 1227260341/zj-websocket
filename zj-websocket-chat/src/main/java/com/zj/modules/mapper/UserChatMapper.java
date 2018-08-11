package com.zj.modules.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zj.modules.domain.UserChat;


/**
 * <p>
 * 聊天记录表 Mapper 接口
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-08-11
 */
@Mapper
public interface UserChatMapper {

	/**
	 * 根据id获取
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	@Select("SELECT * FROM zw_user_chat WHERE id = #{id}")
	public UserChat get(@Param("id") int id);

	/**
	 * 获取列表
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	public List<UserChat> queryByPage(UserChat userChat);
	
	/**
	 * 添加方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	public int add(UserChat userChat);
	
	/**
	 * 设置无效（ID 为int 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	public int update(UserChat userChat);
	
	/**
	 * 更新方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	public int setInvalidWithInt(int id);
	
	/**
	 * 设置无效（ID 为string 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-08-11
	 */
	public int setInvalidWithString(String id);
	
	//public List<UserChat> getMessageRecord(int id);
	
}
