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
import cn.gzitrans.soft.api.entity.UserLikePictureLogsEntity;
import cn.gzitrans.soft.api.service.PictureUploadLogsService;
import cn.gzitrans.soft.api.service.UserLikePictureLogsService;
import cn.gzitrans.soft.api.service.UserService;
/**
 * 我的点赞记录控制器
 * 创建人：Jarvan   
 * 创建时间：2018年3月13日 上午9:13:49
 */
@RestController
@RequestMapping("${basepath}/mylike")
public class MyLikeController {
public static Logger logger = LogManager.getLogger(MyLikeController.class);
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLikePictureLogsService userLikePictureLogsService;
	
	
	@Value("${static_path}")
	private String staticPath;
	
	
	/**
	 * 获取我的点赞记录列表
	 * @param request
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/getMyLike", method = RequestMethod.GET)
	public ArrayList<DiscoverEntity> getMyLike(HttpServletRequest request, @RequestParam String openId){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<DiscoverEntity> returnList = new ArrayList<DiscoverEntity>();
		DiscoverEntity discover;
		ArrayList<UserLikePictureLogsEntity> likeLogsList = userLikePictureLogsService.getMyLikes(openId);
		for(int i = 0; i < likeLogsList.size(); i++){
			discover = new DiscoverEntity();
			UserLikePictureLogsEntity likeEntity = likeLogsList.get(i);
			Long uploadId = likeEntity.getPictureUploadLogsId();
			PictureUploadLogsEntity uploadLog = pictureUploadLogsService.findOne(uploadId.intValue());
			discover.setId(uploadLog.getId());
			discover.setPictureUrl(staticPath + uploadLog.getThumbnailPictureUrl());
			discover.setUploadTime(format.format(uploadLog.getUploadTime()));
			
			UserEntity uploadUser = userService.findUserByOpenId(uploadLog.getOpenId());
			discover.setNickName(uploadUser.getNickName());
			
			returnList.add(discover);
		}
		return returnList;
	}

}
