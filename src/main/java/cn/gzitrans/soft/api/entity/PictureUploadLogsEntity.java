package cn.gzitrans.soft.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "picture_upload_logs")
public class PictureUploadLogsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "openid")
	private String openId;
	
	@Column(name = "upload_picture_url")
	private String uploadPictureUrl;//上传的原图在服务器的地址
	
	@Column(name = "simplify_picture_url")
	private String simplifyPictureUrl;//简化后的图片在服务器的地址
	
	@Column(name = "thumbnail_picture_url")
	private String thumbnailPictureUrl;//简化后的图片的缩略图在服务器的地址
	
	@Column(name = "upload_time")
	private Timestamp uploadTime;
	
	@Column(name = "like_number")
	private Integer likeNumber;
	
	@Column(name = "share_number")
	private Integer shareNumber;
	
	@Column(name = "is_delete")
	private Integer isDelete;//是否删除的标记0正常,1已删除

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

	public String getUploadPictureUrl() {
		return uploadPictureUrl;
	}

	public void setUploadPictureUrl(String uploadPictureUrl) {
		this.uploadPictureUrl = uploadPictureUrl;
	}

	public String getSimplifyPictureUrl() {
		return simplifyPictureUrl;
	}

	public void setSimplifyPictureUrl(String simplifyPictureUrl) {
		this.simplifyPictureUrl = simplifyPictureUrl;
	}

	public String getThumbnailPictureUrl() {
		return thumbnailPictureUrl;
	}

	public void setThumbnailPictureUrl(String thumbnailPictureUrl) {
		this.thumbnailPictureUrl = thumbnailPictureUrl;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(Integer likeNumber) {
		this.likeNumber = likeNumber;
	}

	public Integer getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[openId] = ");
		sb.append(openId);
		sb.append(",[uploadPictureUrl] = ");
		sb.append(uploadPictureUrl);
		sb.append(",[simplifyPictureUrl] = ");
		sb.append(simplifyPictureUrl);
		sb.append(",[thumbnailPictureUrl] = ");
		sb.append(thumbnailPictureUrl);
		sb.append(",[uploadTime] = ");
		sb.append(uploadTime);
		sb.append(",[likeNumber] = ");
		sb.append(likeNumber);
		sb.append(",[shareNumber] = ");
		sb.append(shareNumber);
		sb.append(",[isDelete] = ");
		if(isDelete == 0){
			sb.append("正常");
		}else if(isDelete == 1){
			sb.append("已删除");
		}else{
			sb.append(isDelete);
		}
		return sb.toString();
	}
	
}
