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
import cn.gzitrans.soft.api.entity.DiscoverEntity;
import cn.gzitrans.soft.api.entity.DiscoverInfoEntity;
import cn.gzitrans.soft.api.entity.DiscussEntity;
import cn.gzitrans.soft.api.entity.DiscussInfoEntity;
import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;
import cn.gzitrans.soft.api.entity.UserEntity;
import cn.gzitrans.soft.api.entity.UserLikePictureLogsEntity;
import cn.gzitrans.soft.api.service.DiscussService;
import cn.gzitrans.soft.api.service.PictureUploadLogsService;
import cn.gzitrans.soft.api.service.UserLikePictureLogsService;
import cn.gzitrans.soft.api.service.UserService;
/**
 * 小程序发现的控制器
 * 创建人：Jarvan   
 * 创建时间：2018年3月12日 上午10:31:59
 */
@RestController
@RequestMapping("${basepath}/discover")
public class DiscoverController {
	
	public static Logger logger = LogManager.getLogger(DiscoverController.class);
	
	@Autowired
	private PictureUploadLogsService pictureUploadLogsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiscussService discussService;
	
	@Autowired
	private UserLikePictureLogsService userLikePictureLogsService;
	
	//第一次加载评论的数量
	@Value("${discuss_index_count}")
	private int discuss_index_count;
	
	//第一次加载发现的数量
	@Value("${discover_index_count}")
	private int discover_index_count;
	
	@Value("${discover_more_count}")
	private int discover_more_count;
	
	@Value("${static_path}")
	private String staticPath;
	
	
	/**
	 * 给小程序提供首页的列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getDiscoverList", method = RequestMethod.GET)
	public ArrayList<DiscoverEntity> getDiscoverList(HttpServletRequest request){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DiscoverEntity discover;
		ArrayList<DiscoverEntity> returnList = new ArrayList<DiscoverEntity>();
		ArrayList<PictureUploadLogsEntity> list = pictureUploadLogsService.getPictureUploadLogsList(discover_index_count);
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
	
	
	/**
	 * 加载更多的发现
	 * @param request
	 * @param itemsLength
	 * @return
	 */
	@RequestMapping(value = "/getMoreDiscover", method = RequestMethod.GET)
	public ArrayList<DiscoverEntity> getMoreDiscover(HttpServletRequest request, @RequestParam String itemsLength){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DiscoverEntity discover;
		ArrayList<DiscoverEntity> returnList = new ArrayList<DiscoverEntity>();
		Long allCount = pictureUploadLogsService.getAllCount();
		if(Integer.parseInt(itemsLength) == allCount.intValue()){
			
		}else{
			int beginId = allCount.intValue() - Integer.parseInt(itemsLength);
			ArrayList<PictureUploadLogsEntity> list = pictureUploadLogsService.getMoreLogsList(beginId,discover_more_count);
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
		}
		return returnList;
	}
	
	
	/**
	 * 点击小程序发现栏目下的每一个项目,获取对应id的发现的内容以及评价
	 * @param request
	 * @param discoverId
	 * @return
	 */
	@RequestMapping(value = "/getDiscoverInfo", method = RequestMethod.GET)
	public DiscoverInfoEntity getDiscoverInfo(HttpServletRequest request, @RequestParam Integer pictureUploadLogsId){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		PictureUploadLogsEntity picLogs = pictureUploadLogsService.findOne(pictureUploadLogsId);
		DiscoverInfoEntity discoverInfo = new DiscoverInfoEntity();
		discoverInfo.setId(picLogs.getId());
		discoverInfo.setPictureUrl(staticPath + picLogs.getUploadPictureUrl());
		discoverInfo.setSimplifyPictureUrl(staticPath + picLogs.getSimplifyPictureUrl());
		discoverInfo.setUploadTime(format.format(picLogs.getUploadTime()));
		discoverInfo.setLikeNumber(picLogs.getLikeNumber());
		discoverInfo.setShareNumber(picLogs.getShareNumber());
		
		UserEntity user = userService.findUserByOpenId(picLogs.getOpenId());
		discoverInfo.setNickName(user.getNickName());
		discoverInfo.setUserHeadPicture(user.getAvatarUrl());
		
		ArrayList<DiscussEntity> discussList = discussService.getListByPictureUploadLogsId(pictureUploadLogsId,discuss_index_count);
		DiscussEntity discussEntity;
		DiscussInfoEntity discussInfo;
		ArrayList<DiscussInfoEntity> discussInfoList = new ArrayList<DiscussInfoEntity>();
		for(int i = 0; i < discussList.size(); i++){
			discussInfo = new DiscussInfoEntity();
			discussEntity = discussList.get(i);
			discussInfo.setId(discussEntity.getId());
			discussInfo.setDiscussContent(discussEntity.getDiscussContent());
			discussInfo.setDiscussTime(format.format(discussEntity.getDiscussTime()));
			
			String openid = discussEntity.getFromOpenId();
			UserEntity discussUser = userService.findUserByOpenId(openid);
			
			discussInfo.setNickName(discussUser.getNickName());
			discussInfo.setUserHeadPicture(discussUser.getAvatarUrl());
			discussInfoList.add(discussInfo);
		}
		
		discoverInfo.setDiscussInfo(discussInfoList);
		
		return discoverInfo;
	}
	
	
	/**
	 * 根据用户openid和上传记录id查找点赞记录表中是否有该条数据
	 * @param request
	 * @param openId
	 * @param pictureUploadLogsId
	 * @return
	 */
	@RequestMapping(value = "/toCheckLike", method = RequestMethod.GET)
	public boolean toCheckLike(HttpServletRequest request, @RequestParam String openId, @RequestParam Integer pictureUploadLogsId){
		boolean ret;
		UserLikePictureLogsEntity userLike = userLikePictureLogsService.findOnEntity(openId,pictureUploadLogsId);
		if(userLike != null){
			ret = true;
		}else{
			ret = false;
		}
		return ret;
	}
	
	
	/**
	 * 小程序改变点赞状态
	 * @param request
	 * @param openId
	 * @param pictureUploadLogsId
	 * @return
	 */
	@RequestMapping(value = "/toChangeLike", method = RequestMethod.GET)
	public Integer toChangeLike(HttpServletRequest request, @RequestParam String openId, @RequestParam Integer pictureUploadLogsId){
		
		UserLikePictureLogsEntity userLike = userLikePictureLogsService.findOnEntity(openId,pictureUploadLogsId);
		PictureUploadLogsEntity pictureUploadLog = pictureUploadLogsService.findOne(pictureUploadLogsId);
		Integer likeNumber = pictureUploadLog.getLikeNumber();
		if(likeNumber == null){
			likeNumber = 0;
		}
		Integer nowLikeNumber;
		//如果存在则删除该条点赞记录,将点赞数减1
		//如果不存在则新建点赞记录,将点赞数加1
		if(userLike != null){
			userLikePictureLogsService.delete(userLike.getId());
			nowLikeNumber = likeNumber - 1;
			pictureUploadLogsService.updateLikeNumber(nowLikeNumber,pictureUploadLogsId);
		}else{
			userLike = new UserLikePictureLogsEntity();
			userLike.setOpenId(openId);
			userLike.setPictureUploadLogsId(Long.valueOf(pictureUploadLogsId));
			userLike.setLikeTime(new Timestamp(System.currentTimeMillis()));
			userLikePictureLogsService.save(userLike);
			
			nowLikeNumber = likeNumber + 1;
			pictureUploadLogsService.updateLikeNumber(nowLikeNumber,pictureUploadLogsId);
		}
		return nowLikeNumber;
	}
	
	
	
	/**
	 * 小程序在发现页面分享成功后记录的图片分享次数加1
	 * @param request
	 * @param pictureUploadLogsId
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public Integer share(HttpServletRequest request, @RequestParam Integer pictureUploadLogsId){
		PictureUploadLogsEntity entity = pictureUploadLogsService.findOne(pictureUploadLogsId);
		Integer beforeNumber = entity.getShareNumber();
		Integer afterNumber = beforeNumber + 1;
		pictureUploadLogsService.updateShareNumber(afterNumber, pictureUploadLogsId);
		return afterNumber;
	}

}
