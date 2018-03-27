package cn.gzitrans.soft.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_like_picture_logs")
public class UserLikePictureLogsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "openid")
	private String openId;//当前用户
	
	@Column(name = "picture_upload_logs_id")
	private Long pictureUploadLogsId;//某一个上传记录
	
	@Column(name = "like_time")
	private Timestamp likeTime;//点赞时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getPictureUploadLogsId() {
		return pictureUploadLogsId;
	}

	public void setPictureUploadLogsId(Long pictureUploadLogsId) {
		this.pictureUploadLogsId = pictureUploadLogsId;
	}

	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[openId] = ");
		sb.append(openId);
		sb.append(",[pictureUploadLogsId] = ");
		sb.append(pictureUploadLogsId);
		sb.append(",[likeTime] = ");
		sb.append(likeTime);
		return sb.toString();
	}

}
