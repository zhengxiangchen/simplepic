package cn.gzitrans.soft.api.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.gzitrans.soft.api.entity.DiscoverEntity;
import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;
import cn.gzitrans.soft.api.entity.UserEntity;
import cn.gzitrans.soft.api.service.PictureUploadLogsService;
import cn.gzitrans.soft.api.service.UserService;

/**
 * 获取其他用户信息控制器
 * 创建人：Jarvan   
 * 创建时间：2018年4月12日 上午10:26:37
 */
@RestController
@RequestMapping("${basepath}/people")
public class PeopleController {
	
	public static Logger logger = LogManager.getLogger(DiscoverController.class);
	
	@Value("${static_path}")
	private String staticPath;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	
	/**
	 * 获取其他用户信息
	 * @param request
	 * @param peopleOpenid
	 * @return
	 */
	@RequestMapping(value = "/getPeopleInfo", method = RequestMethod.GET)
	public UserEntity getPeopleInfo(HttpServletRequest request, @RequestParam String peopleOpenId){
		UserEntity user = userService.findUserByOpenId(peopleOpenId);
		return user;
	}
	
	
	/**
	 * 获取用户作品的总点赞数和总分享数
	 * @param request
	 * @param peopleOpenId
	 * @return
	 */
	@RequestMapping(value = "/getLikeAndShare", method = RequestMethod.GET)
	public Map<String, Integer> getLikeAndShare(HttpServletRequest request, @RequestParam String peopleOpenId){
		Integer likeNumber = 0;
		Integer shareNumber = 0;
		ArrayList<PictureUploadLogsEntity> uploadList = pictureUploadLogsService.getMyUploadLogsList(peopleOpenId);
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
	
	
	/**
	 * 获取其他人的上传历史
	 * @param request
	 * @param peopleOpenId
	 * @return
	 */
	@RequestMapping(value = "/getPeopleUploadList", method = RequestMethod.GET)
	public ArrayList<DiscoverEntity> getPeopleUploadList(HttpServletRequest request, @RequestParam String peopleOpenId){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DiscoverEntity discover;
		ArrayList<DiscoverEntity> returnList = new ArrayList<DiscoverEntity>();
		
		ArrayList<PictureUploadLogsEntity> list = pictureUploadLogsService.getMyUploadLogsList(peopleOpenId);
		for(int i = 0; i < list.size(); i++){
			discover = new DiscoverEntity();
			PictureUploadLogsEntity entity = list.get(i);
			discover.setId(entity.getId());
			discover.setPictureUrl(staticPath + entity.getThumbnailPictureUrl());
			discover.setUploadTime(format.format(entity.getUploadTime()));
			String openid = entity.getOpenId();
			UserEntity user = userService.findUserByOpenId(openid);
			discover.setNickName(user.getNickName());
			returnList.add(discover);
		}
		return returnList;
	}

}
