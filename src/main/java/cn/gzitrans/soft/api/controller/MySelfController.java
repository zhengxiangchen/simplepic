package cn.gzitrans.soft.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;
import cn.gzitrans.soft.api.service.PictureUploadLogsService;

@RestController
@RequestMapping("${basepath}/myself")
public class MySelfController {
	
	public static Logger logger = LogManager.getLogger(MySelfController.class);
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	
	/**
	 * 获取我作品的点赞数和分享数
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/getLikeAndShare", method = RequestMethod.GET)
	public Map<String, Integer> getLikeAndShare(HttpServletRequest request, @RequestParam String openId){
		Integer likeNumber = 0;
		Integer shareNumber = 0;
		ArrayList<PictureUploadLogsEntity> uploadList = pictureUploadLogsService.getMyUploadLogsList(openId);
		for(int i = 0; i < uploadList.size(); i++){
			PictureUploadLogsEntity entity = uploadList.get(i);
			likeNumber = likeNumber + entity.getLikeNumber();
			shareNumber = shareNumber + entity.getShareNumber();
		}
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		returnMap.put("likeNumber", likeNumber);
		returnMap.put("shareNumber", shareNumber);
		return returnMap;
	}

}
