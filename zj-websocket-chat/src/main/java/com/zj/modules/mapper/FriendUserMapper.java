package com.zj.modules.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zj.modules.domain.FriendUser;


/**
 * <p>
 * 用户好友或群表 Mapper 接口
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-31
 */
@Mapper
public interface FriendUserMapper {

	/**
	 * 根据id获取
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	@Select("SELECT * FROM zw_friend_user WHERE id = #{id}")
	public FriendUser get(@Param("id") int id);

	/**
	 * 获取列表
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public List<FriendUser> queryByPage(FriendUser friendUser);
	
	/**
	 * 添加方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int add(FriendUser friendUser);
	
	/**
	 * 设置无效（ID 为int 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int update(FriendUser friendUser);
	
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
	
}
