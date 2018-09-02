package com.zj.modules.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zj.modules.domain.FilesConfig;
import com.zj.modules.domain.User;
import com.zj.modules.mapper.FilesConfigMapper;
import com.zj.modules.service.FilesConfigService;
import com.zj.modules.util.FileUtil;
import com.zj.modules.util.PropertiesUtil;


@Service("FilesConfigService")
public class FilesConfigServiceImpl implements FilesConfigService {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	@Autowired
	private HttpServletRequest request;
	@Resource
	private FilesConfigMapper filesConfigMapper;
	
	/**
	 * 上传
	 */
	@Override
	public Integer upload(MultipartFile file, String foldName, HttpServletRequest request) {

		String filePath = "";
		try {
			// 获取文件后缀名
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			
			//filePath = FileManager.upload(fastFile, meta_list);
			filePath = FileUtil.uploadFileToLocal(file, foldName, request);
			
			//上传成功保存至 文件配置表
			String name = "";
			String type = "";
			String size = "0KB";
			String makeUser = "";
			String fileName = file.getOriginalFilename();
			if (fileName != null && !"".equals(fileName)) {
				String values[] = fileName.split("\\.");
				if (values != null && values.length > 0) {
					name = values[0];
					type = values[1];
				}
			}
			
			size = getFileSize(file.getSize());
			HttpSession session = request.getSession();
			if (session != null) {
				User loginUser = (User) session.getAttribute("loginUser");
				if (loginUser != null) {
					makeUser = loginUser.getUserName();
				}
			}
			
			FilesConfig filesConfig = new FilesConfig();
			filesConfig.setName(name);
			filesConfig.setType(type);
			filesConfig.setSize(size);
			filesConfig.setPath(filePath);
			filesConfig.setMakeUser(makeUser);
			
			try {
				filesConfigMapper.save(filesConfig);
				return filesConfig.getId();
			} catch (Exception e) {
//				e.printStackTrace();
				log.error(e);
				log.debug("执行filesConfigMapper.save（）方法发生错误" + e);
			}
		} catch (Exception e) {
			log.error(e);
//			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	/**
	 * 获取完整路径
	 */
	@Override
	public String getFullPath(String path) {
//		String protocol = PropertiesUtil.getValue("protocol", null);
//        String addr = PropertiesUtil.getValue("tracker.ngnix.addr", null);
//        String separator = PropertiesUtil.getValue("separator", null);
//        path = protocol + addr + path;
		return path;
	}
	
	/**
	 * 根据图片id 获取 完整路径
	 */
	@Override
	public String getFullPathById(Integer id) {
		FilesConfig filesConfig = filesConfigMapper.get(id);
		if (filesConfig == null) {
			return "";
		}
		String path = filesConfig.getPath();
		if (path == null || "".equals(path)) {
			return "";
		}
		String fullPath = getFullPath(path);
		return fullPath;
	}
	
	
	
	
	
	/**
	 * 
	 * zj
	 * 2017年9月26日
	 */
	public static String getFileSize(long fileSize){
		String size = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00"); 
			if (fileSize < 1024) {
				size = df.format((double) fileSize) + "BT";
			} else if (fileSize < 1048576) {
				size = df.format((double) fileSize / 1024) + "KB";
			} else if (fileSize < 1073741824) {
				size = df.format((double) fileSize / 1048576) + "MB";
			} else {
				size = df.format((double) fileSize / 1073741824) +"GB";
			}
		} catch (Exception e) {
			e.printStackTrace();
			size = "0KB";
		}
		return size;
	}

	/**
	 * 设置无效
	 */
	@Override
	public void setInvalid(Integer id) {
		try {
			filesConfigMapper.setInvalid(id);
		} catch (Exception e) {
			log.debug("setInvalid(String id) find bug, filesConfigMapper.setInvalid(id) appear a error");
			e.printStackTrace();
		}
		
	}

	/**
	 * zj
	 * 获取加密文件下载的url
	 */
	@Override
	public String downEncryptFileUrl(String path) {
		/*if (path == null) {
			return "";
		}
		
		//下载文件加密处理 
		// 配置到配置文件 MgToPwVLhZuyu
//		String key = "MgToPwVLhZuyu";
		String key = PropertiesUtil.getValue("tracker.ngnix.key", null);
		String expire = String.valueOf(System.currentTimeMillis() / 1000 + 6000);
		//String e = "1505472787";
		//String url = "/group1/M00/00/03/wKgDD1myUW-AERQXAC413_Xg2Vg295.zip";
		String base64Str = Base64.encodeBase64URLSafeString(DigestUtils.md5(key + path + expire));
		String strReplace = base64Str.replace("+", "-");
		String strReplace2 = strReplace.replace("/", "_");

		String getStr = strReplace2.replaceAll("=", "");
		String protocol = PropertiesUtil.getValue("protocol", null);
        String addr = PropertiesUtil.getValue("tracker.ngnix.addr", null);
		//String fileUrl = "http://files.21cnjy.com";
        String fileUrl = protocol + addr;
		String downFileUrl = fileUrl + path + "?st=" + getStr
				+ "&e=" + expire;

		System.out.println("-------" + downFileUrl);*/
		
		return "";
	}
	
	/**
	 * 根据id获取完整文件名
	 */
	@Override
	public String getFileNameById(Integer id) {
		FilesConfig filesConfig = filesConfigMapper.getFileName(id);
		if (filesConfig != null) {
			return filesConfig.getName();
		}
		return "";
	}
	
	/**
	 * 根据id获取加密路径
	 */
	@Override
	public String downEncryptFileUrlById(Integer id) {
		FilesConfig filesConfig = filesConfigMapper.get(id);
		if (filesConfig == null) {
			return "";
		}
		String path = filesConfig.getPath();
		if (path == null || "".equals(path)) {
			return "";
		}
		String fullPath = downEncryptFileUrl(path);
		return fullPath;
	}
}
