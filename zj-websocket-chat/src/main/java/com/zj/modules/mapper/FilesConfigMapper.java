package com.zj.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zj.modules.domain.FilesConfig;


/**
 * 系统编码信息
 */
@Mapper
public interface FilesConfigMapper {

	
//	@Select("select * from sys_code where codeType='Grade' and codeName = #{grade}")
//	public Syscode getGrade(@Param("grade") java.lang.String grade);
	
	
	@Select("SELECT * FROM files_config WHERE id = #{id}")
	public FilesConfig get(@Param("id") java.lang.Integer id);
	
	public void save(FilesConfig filesConfig);
	
	//public void update(FilesConfig filesConfig);

	
	public void deleteById(@Param("id") java.lang.Integer id);
	
	@Update("UPDATE files_config SET validFlag = 'N' WHERE id = #{id}")
	public void setInvalid(@Param("id") java.lang.Integer id);
	
	@Select("SELECT CONCAT(name,'.',type) name FROM files_config WHERE id = #{id}")
	public FilesConfig getFileName(@Param("id") java.lang.Integer id);
}