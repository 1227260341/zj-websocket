package com.zj.modules.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.MidiDevice.Info;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zj.modules.websocket.WebSocketController;

public class FileUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 上传文件至本地
	 * @param file
	 * @param filePath
	 * @return 对应的完整的地址
	 * @throws Exception
	 */
	public static String uploadFileToLocal(MultipartFile file, String foldName,
			HttpServletRequest request) throws Exception { 
        
		String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
		
        String filePath = getProjectPath(request) +  "/files/" + foldName;
        
		File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        
        filePath += "/" + fileName;
        
        FileOutputStream out = new FileOutputStream(filePath);
        out.write(file.getBytes());
        out.flush();
        out.close();
        
        log.info(filePath);
        String path = foldName + "/" + fileName;
        
        return path;
    }
	
	/**
	 * 获取工程根目录（据对路径）
	 * @return
	 */
	public static String getProjectPath(HttpServletRequest request) {
		
		String path = System.getProperty("user.dir");
		if (path == null || "".equals(path)) {
			path = request.getSession().getServletContext().getRealPath("");
		}
		// 根目录路径,可以指定相对路径
		//String rootUrl = ServletActionContext.getRequest().getContextPath() + "/upload/";
		
		path = path.replaceAll("\\\\", "/");
		log.info("getProjectPath = " + path);
		return path;
	}
	
	/**
	 * 获取金泰文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getResourcePath() throws FileNotFoundException {
		String path = ResourceUtils.getURL("classpath:").getPath();
		log.info("getResourcePath = " + path);
		return path;
	}
	
	
	
}
