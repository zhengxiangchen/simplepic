package cn.gzitrans.soft.api.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
 * 我的历史上传记录控制器
 * 创建人：Jarvan   
 * 创建时间：2018年3月12日 下午1:58:52
 */
@RestController
@RequestMapping("${basepath}/history")
public class HistoryController {
	public static Logger logger = LogManager.getLogger(HistoryController.class);
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	@Autowired
	private UserService userService;
	
	
	@Value("${static_path}")
	private String staticPath;
	
	/**
	 * 获取我的上传历史
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/getMyHistory", method = RequestMethod.GET)
	public ArrayList<DiscoverEntity> getMyHistory(HttpServletRequest request, @RequestParam String openId){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DiscoverEntity discover;
		ArrayList<DiscoverEntity> returnList = new ArrayList<DiscoverEntity>();
		
		ArrayList<PictureUploadLogsEntity> list = pictureUploadLogsService.getMyUploadLogsList(openId);
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
