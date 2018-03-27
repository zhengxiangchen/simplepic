package cn.gzitrans.soft.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discuss")
public class DiscussEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "picture_upload_logs_id")
	private Long pictureUploadLogsId;//该评论属于哪个发现的内容
	
	@Column(name = "from_openId")
	private String fromOpenId;//评论人的openid
	
	@Column(name = "discuss_content")
	private String discussContent;
	
	@Column(name = "discuss_time")
	private Timestamp discussTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPictureUploadLogsId() {
		return pictureUploadLogsId;
	}

	public void setPictureUploadLogsId(Long pictureUploadLogsId) {
		this.pictureUploadLogsId = pictureUploadLogsId;
	}

	public String getFromOpenId() {
		return fromOpenId;
	}

	public void setFromOpenId(String fromOpenId) {
		this.fromOpenId = fromOpenId;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Timestamp getDiscussTime() {
		return discussTime;
	}

	public void setDiscussTime(Timestamp discussTime) {
		this.discussTime = discussTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[pictureUploadLogsId] = ");
		sb.append(pictureUploadLogsId);
		sb.append(",[fromOpenId] = ");
		sb.append(fromOpenId);
		sb.append(",[discussContent] = ");
		sb.append(discussContent);
		sb.append(",[discussTime] = ");
		sb.append(discussTime);
		return sb.toString();
	}
	
	

}
