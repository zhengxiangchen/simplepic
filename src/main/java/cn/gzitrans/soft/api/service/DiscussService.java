package cn.gzitrans.soft.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzitrans.soft.api.dao.DiscussDao;
import cn.gzitrans.soft.api.entity.DiscussEntity;

@Service
public class DiscussService {
	
	@Autowired
	private DiscussDao discussDao;

	
	/**
	 * 根据图片上传记录id获取该记录的所有评论
	 * @param pictureUploadLogsId
	 * @return
	 */
	public ArrayList<DiscussEntity> getListByPictureUploadLogsId(Integer pictureUploadLogsId,int discuss_index_count) {
		return discussDao.getListByPictureUploadLogsId(Long.valueOf(pictureUploadLogsId), discuss_index_count);
	}


	/**
	 * 保存评论
	 * @param discussEntity
	 */
	public void save(DiscussEntity discussEntity) {
		discussDao.save(discussEntity);
	}


	/**
	 * 返回该发现下的所有评论
	 * @return
	 */
	public Integer getAllCount(Integer pictureUploadLogsId) {
		return discussDao.getAllCount(pictureUploadLogsId);
	}


	/**
	 * 获取更多的评论
	 * @param beginId
	 * @param discuss_more_count
	 * @return
	 */
	public ArrayList<DiscussEntity> getMoreDiscuss(int beginId, int discuss_more_count) {
		return discussDao.getMoreDiscuss(beginId,discuss_more_count);
	}

}
