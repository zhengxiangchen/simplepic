package cn.gzitrans.soft.api.controller;

import java.sql.Timestamp;
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
import cn.gzitrans.soft.api.entity.DiscussEntity;
import cn.gzitrans.soft.api.entity.DiscussInfoEntity;
import cn.gzitrans.soft.api.entity.UserEntity;
import cn.gzitrans.soft.api.service.DiscussService;
import cn.gzitrans.soft.api.service.UserService;

/**
 * 小程序评论控制器
 * 创建人：Jarvan   
 * 创建时间：2018年3月12日 上午10:32:16
 */
@RestController
@RequestMapping("${basepath}/discuss")
public class DiscussController {
	
	public static Logger logger = LogManager.getLogger(DiscussController.class);
	
	@Autowired
	private DiscussService discussService;
	
	@Autowired
	private UserService userService;
	
	@Value("${discuss_more_count}")
	private int discuss_more_count;
	
	@Value("${static_path}")
	private String staticPath;
	

	/**
	 * 小程序发送评论信息，后台进行保存
	 * @param request
	 * @param pictureUploadLogsId
	 * @param openId
	 * @param discussContent
	 * @return
	 */
	@RequestMapping(value = "/receiveDiscuss", method = RequestMethod.GET)
	public String receiveDiscuss(HttpServletRequest request, 
			@RequestParam Integer pictureUploadLogsId, @RequestParam String openId, @RequestParam String discussContent){
		
		DiscussEntity discussEntity = new DiscussEntity();
		discussEntity.setPictureUploadLogsId(Long.valueOf(pictureUploadLogsId));
		discussEntity.setFromOpenId(openId);
		discussEntity.setDiscussContent(discussContent);
		discussEntity.setDiscussTime(new Timestamp(System.currentTimeMillis()));
		discussService.save(discussEntity);
		return "success";
	}
	
	
	
	/**
	 * 加载更多的评论
	 * @param request
	 * @param itemsLength
	 * @return
	 */
	@RequestMapping(value = "/getMoreDiscuss", method = RequestMethod.GET)
	public ArrayList<DiscussInfoEntity> getMoreDiscuss(HttpServletRequest request, 
			@RequestParam Integer pictureUploadLogsId, @RequestParam String itemsLength){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<DiscussInfoEntity> returnList = new ArrayList<DiscussInfoEntity>();
		
		Integer allCount = discussService.getAllCount(pictureUploadLogsId);
		if(Integer.parseInt(itemsLength) == allCount){
			
		}else{
			DiscussEntity discussEntity;
			DiscussInfoEntity discussInfo;
			int beginId = allCount - Integer.parseInt(itemsLength);
			ArrayList<DiscussEntity> list = discussService.getMoreDiscuss(beginId,discuss_more_count);
			for(int i = 0; i < list.size(); i++){
				discussInfo = new DiscussInfoEntity();
				discussEntity = list.get(i);
				discussInfo.setId(discussEntity.getId());
				discussInfo.setDiscussContent(discussEntity.getDiscussContent());
				discussInfo.setDiscussTime(format.format(discussEntity.getDiscussTime()));
				
				String openid = discussEntity.getFromOpenId();
				UserEntity discussUser = userService.findUserByOpenId(openid);
				
				discussInfo.setNickName(discussUser.getNickName());
				discussInfo.setUserHeadPicture(discussUser.getAvatarUrl());
				returnList.add(discussInfo);
			}
		}
		return returnList;
	}
}
