package cn.gzitrans.soft.api.entity;

public class DiscoverEntity {
	
	private Long id;
	
	private String pictureUrl;
	
	private String nickName;
	
	private String uploadTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[pictureUrl] = ");
		sb.append(pictureUrl);
		sb.append(",[nickName] = ");
		sb.append(nickName);
		sb.append(",[uploadTime] = ");
		sb.append(uploadTime);
		return sb.toString();
	}

}
