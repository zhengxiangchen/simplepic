package cn.gzitrans.soft.api.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.gzitrans.soft.api.dao.PictureUploadLogsDao;
import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;

@Service
public class PictureUploadLogsService {
	
	@Autowired
	private PictureUploadLogsDao pictureUploadLogsDao;

	
	/**
	 * 保存上传图片记录
	 * @param picUploadLog
	 */
	public void save(PictureUploadLogsEntity picUploadLog) {
		pictureUploadLogsDao.save(picUploadLog);
	}


	/**
	 * 取得部分用户简化的缩略图用于小程序的展示
	 * @return
	 */
	public ArrayList<PictureUploadLogsEntity> getPictureUploadLogsList(int discover_index_count) {
		return pictureUploadLogsDao.getPictureUploadLogsList(discover_index_count);
	}

	
	/**
	 * 获取数据库中上传图片的总数
	 * @return
	 */
	public Long getAllCount() {
		return pictureUploadLogsDao.count();
	}


	/**
	 * 再次获取部分用户缩略图数据
	 * @param discover_index_count
	 * @return
	 */
	public ArrayList<PictureUploadLogsEntity> getMoreLogsList(int beginId, int discover_more_count) {
		return pictureUploadLogsDao.getMoreLogsList(beginId, discover_more_count);
	}
	
	
	/**
	 * 根据id得到对象
	 * @param pictureUploadLogsId
	 * @return
	 */
	public PictureUploadLogsEntity findOne(Integer pictureUploadLogsId){
		return pictureUploadLogsDao.findOne(Long.valueOf(pictureUploadLogsId));
	}

	/**
	 * 修改点赞数
	 * @param pictureUploadLogsId
	 * @param nowLikeNumber
	 */
	public void updateLikeNumber(Integer nowLikeNumber, Integer pictureUploadLogsId) {
		pictureUploadLogsDao.updateLikeNumber(nowLikeNumber, Long.valueOf(pictureUploadLogsId));
	}


	/**
	 * 获取我的上传历史记录
	 * @param openId
	 * @return
	 */
	public ArrayList<PictureUploadLogsEntity> getMyUploadLogsList(String openId) {
		return pictureUploadLogsDao.getMyUploadLogsList(openId);
	}


	/**
	 * 修改分享次数
	 * @param afterNumber
	 * @param pictureUploadLogsId
	 */
	public void updateShareNumber(Integer afterNumber, Integer pictureUploadLogsId) {
		pictureUploadLogsDao.updateShareNumber(afterNumber, Long.valueOf(pictureUploadLogsId));
	}

}
