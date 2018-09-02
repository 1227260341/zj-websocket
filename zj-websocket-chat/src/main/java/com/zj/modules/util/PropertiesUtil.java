package com.zj.modules.util;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;

//import org.apache.commons.codec.binary.Base64;


/**
 * 获取 .properties配置  及判断null "" .... 
 *name 及 properties文件名
 * @author zj
 * 
 * 2017年9月25日
 */
public class PropertiesUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 获取配置文件的 value
	 * zj
	 * 2017年10月24日
	 */
	public static String getValue(String key, String name){
		if (name ==null || "".equals(name)) {
			name = "baseconfig";
		}
		Properties prop = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/" + name + ".properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	/**
	 * 验证 string类型 
	 * zj
	 * 2017年10月24日
	 * 
	 */
	public static boolean verifyString(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证string 判断 ‘null’ 的字符串
	 * zj
	 * 2017年11月29日
	 */
	public static boolean verifyStringNull(String str) {
		if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证list
	 * zj
	 * 2017年10月24日
	 */
	public static boolean verifyList(List list) {
		if (list == null || list.size() < 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证对象
	 * zj
	 * 2017年10月24日
	 */
	public static boolean verifyObject(Object obj) {
		if (obj == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证 string类型
	 * zj
	 * 2017年10月24日
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证list
	 * zj
	 * 2017年10月24日
	 */
	public static boolean isEmptyList(List list) {
		if (list == null || list.size() < 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证对象
	 * zj
	 * 2017年10月24日
	 */
	public static boolean isEmptyObject(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyStrings(String[] str) {
		if (str == null || str.length < 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证多参数，是否是 null 或者 "",如果是 则 返回 true
	 * zj
	 * 2017年11月17日
	 */
	public static boolean existEmptyParams(String... params) {
		if (params == null || params.length < 1) {
			return true;
		}
		
		boolean isEmpty = false;
		for (int i = 0; i < params.length; i ++) {
			String param = params[i];
			if (isEmptyString(param)) {
				isEmpty = true;
				return isEmpty;
			}
		}
		return isEmpty;
	}
	
	/**
	 * 验证多参数，是否是 null 或 'null'字符串 或者 "",如果是 则 返回 true
	 * zj
	 * 2018年1月31日
	 */
	public static boolean existEmptyOrNullParams(String... params) {
		if (params == null || params.length < 1) {
			return true;
		}
		
		boolean isEmpty = false;
		for (int i = 0; i < params.length; i ++) {
			String param = params[i];
			if (!verifyStringNull(param)) {
				isEmpty = true;
				return isEmpty;
			}
		}
		return isEmpty;
	}
	
	/**
	 * 验证Object多参数，是否是 null 或 'null'字符串 或者 "",如果是 则 返回 true
	 * zj
	 * 2018年5月30日
	 */
	public static boolean existEmptyOrNullObject(Object... objects) {
		if (objects == null || objects.length < 1) {
			return true;
		}
		
		boolean isEmpty = false;
		for (int i = 0; i < objects.length; i ++) {
			Object param = objects[i];
			if (param == null || "".equals(param) || "null".equalsIgnoreCase(param.toString())) {
				isEmpty = true;
				return isEmpty;
			}
		}
		return isEmpty;
	}
	
	/**
	 * 获取请求的ip
	 * zj
	 * 2018年1月17日
	 */
	public static String getIpAddr(HttpServletRequest request) {
	     String ipAddress = null;
	     //ipAddress = this.getRequest().getRemoteAddr();
	     ipAddress = request.getHeader("x-forwarded-for");
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	      ipAddress = request.getHeader("Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	         ipAddress = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
		     ipAddress = request.getRemoteAddr();
		     if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
		       //根据网卡取本机配置的IP
		       InetAddress inet=null;
			    try {
			     inet = InetAddress.getLocalHost();
			    } catch (UnknownHostException e) {
			     e.printStackTrace();
			    }
			    ipAddress= inet.getHostAddress();
		     }
	     }

	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
	         if(ipAddress.indexOf(",")>0){
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
	         }
	     }
	     return ipAddress; 
	}
	
	
	/**
	 * 保存cookies加密值 信息 zj
	 */
	public static boolean setCookiesEncryptValue(String cookieName, String cookieValue, HttpServletResponse response) {
		
		if (PropertiesUtil.existEmptyParams(cookieName, cookieValue)) {
			log.info("设置cookie接口传入的参数有误@!");
			return false;
		}
		String value = new String(Base64.encodeBase64(cookieValue.getBytes()));
		log.info("加密后的值：" + value);
		String deValue = new String(Base64.decodeBase64(value));
		log.info("解密后的值：" + deValue);
		Cookie cookieLoginToken = new Cookie(cookieName, value);
		cookieLoginToken.setMaxAge(3 * 3600 * 24);
		cookieLoginToken.setPath("/");
		//加入cookie中
		response.addCookie(cookieLoginToken);
		
		return true;
	}
	
	
	/**
	 * zj
	 * 获取cookie 中对应的解密值
	 */
	public static String getCookiesDecript(HttpServletRequest request, String cookieName) {
		if (PropertiesUtil.isEmptyString(cookieName)) {
			log.info("获取cookie方法请传入正确的参数");
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				String value = cookie.getValue();
				if (PropertiesUtil.isEmptyString(value)) {
					return null;
				}
				String loginToken = new String(Base64.decodeBase64(value));
				return loginToken;
			}
		}
		return null;
	}
	
	/**
	 * 保存 cookie 
	 * zj
	 * 2018年2月25日
	 */
	public static boolean setCookies(String cookieName, String cookieValue, int timeOut ,HttpServletResponse response) {
		
		if (PropertiesUtil.existEmptyParams(cookieName, cookieValue)) {
			log.info("设置cookie接口传入的参数有误@!");
			return false;
		}
		
		Cookie cookieLoginToken = new Cookie(cookieName, cookieValue);
		cookieLoginToken.setMaxAge(timeOut);//秒
		cookieLoginToken.setPath("/");
		//加入cookie中
		response.addCookie(cookieLoginToken);
		
		return true;
	}
	
	/**
	 * 获取对应cookie值
	 * zj
	 * 2018年2月25日
	 */
	public static String getCookies(HttpServletRequest request, String cookieName) {
		if (PropertiesUtil.isEmptyString(cookieName)) {
			log.info("获取cookie方法请传入正确的参数");
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				String value = cookie.getValue();
				return value;
			}
		}
		return null;
	}
	
	/**
	 * 删除cookie 对应的值
	 * cookieName 需要删除的cookie对应name
	 * zj
	 * 2018年1月23日
	 */
	public static boolean delCookies(HttpServletRequest reuqest, HttpServletResponse response, String cookieName) {
		Cookie[] cookies = reuqest.getCookies();
		if (cookies == null) {
			log.info("清除cookie失败！");
			return false;
		}
		String cookieValue = null;
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				cookieValue = cookie.getValue();
			}
		}
		
		if (PropertiesUtil.verifyString(cookieValue)) {//说名cookie值还存在，则进行清除操作
			Cookie cookieClean = new Cookie(cookieName, null);
			cookieClean.setMaxAge(0);
			cookieClean.setPath("/");
			response.addCookie(cookieClean);
		}
		
		return true;
	}
	
	/**
	 * 判断两个double是否相等
	 * zj
	 * 2018年4月27日
	 */
	public static boolean equalsDouble(double a, double b) {
		
		if ((a - b > -0.000001) && (a-b) < 0.00001 ) {
			return true;
		}
		return false;
	}

	/**
	 * 四舍五入保留两位小数方法
	 * zj
	 * 2018年8月23日
	 */
	public static double doubleKeepTwo(double doubleValue) {
		
		BigDecimal bd = new BigDecimal(doubleValue);
		doubleValue = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return doubleValue;
	}
}