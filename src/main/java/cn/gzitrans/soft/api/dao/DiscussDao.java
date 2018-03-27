package cn.gzitrans.soft.api.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import cn.gzitrans.soft.api.entity.DiscussEntity;

public interface DiscussDao extends CrudRepository<DiscussEntity, Long> {

	@Query(value = "select * from discuss where picture_upload_logs_id =? order by id desc limit ?", nativeQuery=true)
	ArrayList<DiscussEntity> getListByPictureUploadLogsId(Long pictureUploadLogsId, int discuss_index_count);

	@Query(value = "select count(*) from discuss where picture_upload_logs_id =?", nativeQuery=true)
	Integer getAllCount(Integer pictureUploadLogsId);

	@Query(value = "select * from discuss where id <=? order by id desc limit ?", nativeQuery=true)
	ArrayList<DiscussEntity> getMoreDiscuss(int beginId, int discuss_more_count);

}
