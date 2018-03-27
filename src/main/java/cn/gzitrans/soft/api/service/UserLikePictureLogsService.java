package cn.gzitrans.soft.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzitrans.soft.api.dao.UserLikePictureLogsDao;
import cn.gzitrans.soft.api.entity.UserLikePictureLogsEntity;

@Service
public class UserLikePictureLogsService {
	
	@Autowired
	private UserLikePictureLogsDao userLikePictureLogsDao;

	/**
	 * 根据用户openid和上传记录id查找点赞表中是否有该条记录
	 * @param openId
	 * @param pictureUploadLogsId
	 * @return
	 */
	public UserLikePictureLogsEntity findOnEntity(String openId, Integer pictureUploadLogsId) {
		return userLikePictureLogsDao.findOnEntity(openId, Long.valueOf(pictureUploadLogsId));
	}

	/**
	 * 按id删除记录
	 * @param id
	 */
	public void delete(Long id) {
		userLikePictureLogsDao.delete(id);
	}

	/**
	 * 保存
	 * @param userLike
	 */
	public void save(UserLikePictureLogsEntity userLike) {
		userLikePictureLogsDao.save(userLike);
	}

	/**
	 * 获取我的点赞记录
	 * @param openId
	 * @return
	 */
	public ArrayList<UserLikePictureLogsEntity> getMyLikes(String openId) {
		return userLikePictureLogsDao.getMyLikes(openId);
	}

}
