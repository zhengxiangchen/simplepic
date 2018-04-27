package cn.gzitrans.soft.api.dao;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import cn.gzitrans.soft.api.entity.PictureUploadLogsEntity;

public interface PictureUploadLogsDao extends CrudRepository<PictureUploadLogsEntity, Long> {

	@Query(value = "select * from picture_upload_logs where is_delete = 0 order by id desc limit ?", nativeQuery=true)
	ArrayList<PictureUploadLogsEntity> getPictureUploadLogsList(int discover_index_count);

	
	
	@Query(value = "select * from picture_upload_logs where is_delete = 0 and id <=? order by id desc limit ?", nativeQuery=true)
	ArrayList<PictureUploadLogsEntity> getMoreLogsList(int beginId, int discover_more_count);



	@Modifying
	@Transactional
	@Query("update PictureUploadLogsEntity set likeNumber = ? where id = ?")
	void updateLikeNumber(Integer nowLikeNumber, Long pictureUploadLogsId);


	
	@Query("from PictureUploadLogsEntity where is_delete = 0 and openId = ? order by id desc")
	ArrayList<PictureUploadLogsEntity> getMyUploadLogsList(String openId);


	@Modifying
	@Transactional
	@Query("update PictureUploadLogsEntity set shareNumber = ? where id = ?")
	void updateShareNumber(Integer afterNumber, Long valueOf);


	@Modifying
	@Transactional
	@Query("update PictureUploadLogsEntity set isDelete = ? where id = ?")
	void updateIsDelete(Integer i, Long pictureUploadLogsId);

}
