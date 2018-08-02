package com.zj.modules.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zj.modules.domain.Group;


/**
 * <p>
 * 群表 Mapper 接口
 * </p>
 *
 * @author zhouzhenjang123
 * @since 2018-07-31
 */
@Mapper
public interface GroupMapper {

	/**
	 * 根据id获取
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	@Select("SELECT * FROM zw_group WHERE id = #{id}")
	public Group get(@Param("id") int id);

	/**
	 * 获取列表
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public List<Group> queryByPage(Group group);
	
	/**
	 * 添加方法
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int add(Group group);
	
	/**
	 * 设置无效（ID 为int 类型）
	 *
	 * @author zhouzhenjang123
	 * @since 2018-07-31
	 */
	public int update(Group group);
	
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
