package cn.gzitrans.soft.api.entity;

public class DiscoverEntity {
	
	private Long id;
	
	private String pictureUrl;
	
	private String nickName;
	
	private String uploadTime;
	
	private String likeNumber;
	
	private String shareNumber;
	
	private String avatarUrl;

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
	
	public String getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(String likeNumber) {
		this.likeNumber = likeNumber;
	}

	public String getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(String shareNumber) {
		this.shareNumber = shareNumber;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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
		sb.append(",[likeNumber] = ");
		sb.append(likeNumber);
		sb.append(",[shareNumber] = ");
		sb.append(shareNumber);
		return sb.toString();
	}

}
