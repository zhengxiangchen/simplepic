package cn.gzitrans.soft.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;
import cn.gzitrans.soft.api.service.PictureUploadLogsService;
import cn.gzitrans.soft.api.utils.FileUtils;
import cn.gzitrans.soft.api.utils.HttpAccess;
import cn.gzitrans.soft.api.utils.ThumbnailUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@RestController
@RequestMapping("${basepath}/picture")
public class PictureController {
	
	public static Logger logger = LogManager.getLogger(PictureController.class);
	
	@Value("${upload_path}")
	private String filePath;
	
	@Value("${simplify_path}")
	private String simplifyFilePath;
	
	@Value("${thumbnail_path}")
	private String thumbnailPicturePath;
	
	@Value("${python_path}")
	private String pythonPath;
	
	@Value("${static_path}")
	private String staticPath;
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	/**
	 * 接收小程序传过来的图片信息
	 * 第一步：存储图片在服务器本地
	 * 第二步：调用简化图片的接口并接受回传
	 * 第三步：存储简化后的图片在服务器本地
	 * 第四步：生成简化图的缩略图存储在服务器本地
	 * 第五步：保存上传简化记录到数据库
	 * @param request
	 * @param picture
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam MultipartFile picture, @RequestParam String openid){
		
		PictureUploadLogsEntity picUploadLog = new PictureUploadLogsEntity();
		picUploadLog.setOpenId(openid);
		//存储上传的原图到服务器本地
        String fileName = Math.random()*1000 + picture.getOriginalFilename();
        try {
            FileUtils.uploadFile(picture.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
        
        //调用接口简化图片
        //模拟接收到的简化图片为原图
        //保存简化后的图片到服务器本地
        String simplifyFileName = "simplify" + Math.random()*1000 + picture.getOriginalFilename();
        try {
            FileUtils.uploadFile(picture.getBytes(), simplifyFilePath, simplifyFileName);
        } catch (Exception e) {
        }
        
        //生成缩略图并保存在服务器本地
        String thumbnailPictureName = ThumbnailUtils.cutImage(simplifyFilePath + simplifyFileName, thumbnailPicturePath, 375, 375);
        
        picUploadLog.setUploadPictureUrl(fileName);
        picUploadLog.setSimplifyPictureUrl(simplifyFileName);
        picUploadLog.setThumbnailPictureUrl(thumbnailPictureName);
        picUploadLog.setUploadTime(new Timestamp(System.currentTimeMillis()));
        picUploadLog.setLikeNumber(0);
        picUploadLog.setShareNumber(0);
        pictureUploadLogsService.save(picUploadLog);
		return "success";
	}
	
	
	
	/**
	 * 接收小程序传过来的图片信息
	 * 第一步：存储图片在服务器本地
	 * 第二步：调用简化图片的接口并接受回传
	 * 第三步：存储简化后的图片在服务器本地
	 * 第四步：生成简化图的缩略图存储在服务器本地
	 * 第五步：保存上传简化记录到数据库
	 * @param request
	 * @param picture
	 * @return
	 */
	@RequestMapping(value = "/upload_test", method = RequestMethod.POST)
	public String upload_test(HttpServletRequest request, @RequestParam MultipartFile picture, @RequestParam String openid){
		
		PictureUploadLogsEntity picUploadLog = new PictureUploadLogsEntity();
		picUploadLog.setOpenId(openid);
		//存储上传的原图到服务器本地
        String fileName = Math.random()*1000 + picture.getOriginalFilename();
        try {
            FileUtils.uploadFile(picture.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }
        
        String picture64 = imageToBase64(filePath + fileName);
        
        String url = "http://192.168.1.205:8081/api_v1/lab/picture/receive";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("picture64", picture64);
        
        String ret = HttpAccess.postNameValuePairRequest(url, map, "utf-8", "wx");
        
        String simplePicPath = base64ToImage(ret);
        if(simplePicPath == null || simplePicPath.length() <= 0){
        	return "error";
        }
        
        String simplifyFilePath = simplePicPath.split(";")[0];
        String simplifyFileName = simplePicPath.split(";")[1];
        //生成缩略图并保存在服务器本地
        String thumbnailPictureName = ThumbnailUtils.cutImage(simplifyFilePath + simplifyFileName, thumbnailPicturePath, 375, 375);
        
        picUploadLog.setUploadPictureUrl(fileName);
        picUploadLog.setSimplifyPictureUrl(simplifyFileName);
        picUploadLog.setThumbnailPictureUrl(thumbnailPictureName);
        picUploadLog.setUploadTime(new Timestamp(System.currentTimeMillis()));
        picUploadLog.setLikeNumber(0);
        picUploadLog.setShareNumber(0);
        pictureUploadLogsService.save(picUploadLog);
		return staticPath + simplifyFileName;
	}
	
	
	//base64字符串转化成图片 
	public String base64ToImage(String imgStr) {
		//对字节数组字符串进行Base64解码并生成图片 
		if (imgStr == null) //图像数据为空 
		  return ""; 
		BASE64Decoder decoder = new BASE64Decoder(); 
		try {
		  //Base64解码 
		  byte[] b = decoder.decodeBuffer(imgStr); 
		  for(int i=0;i<b.length;++i){
		    if(b[i]<0){//调整异常数据 
		      b[i]+=256; 
		    } 
		  } 
		  //生成jpeg图片
		  String simplifyFileName = "simplify" + Math.random()*1000 + ".jpg";
		  String absPath = simplifyFilePath + simplifyFileName;
		  
		  File targetFile = new File(simplifyFilePath);  
		  if(!targetFile.exists()){    
            targetFile.mkdirs();    
		  }
		  
	      OutputStream out = new FileOutputStream(absPath);   
	      out.write(b); 
	      out.flush(); 
	      out.close();
	      return simplifyFilePath + ";" + simplifyFileName;
	    }catch (Exception e) {
	      logger.error(e.getMessage());
	      return null; 
	    } 
	}
	
	
	public String imageToBase64(String picPath){
		//将图片文件转化为字节数组字符串，并对其进行Base64编码处理 
	    InputStream in = null; 
	    byte[] data = null; 
	    //读取图片字节数组 
	    try 
	    { 
	      in = new FileInputStream(picPath);     
	      data = new byte[in.available()]; 
	      in.read(data); 
	      in.close(); 
	    }  
	    catch (IOException e)  
	    { 
	    	logger.error(e.getMessage());
	    } 
	    //对字节数组Base64编码 
	    BASE64Encoder encoder = new BASE64Encoder(); 
	    return encoder.encode(data);//返回Base64编码过的字节数组字符串 
	  }
	
	
	

}
