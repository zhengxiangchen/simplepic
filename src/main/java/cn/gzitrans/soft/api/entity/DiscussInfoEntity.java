package cn.gzitrans.soft.api.entity;

public class DiscussInfoEntity {
	
	private Long id;
	
	private String nickName;
	
	private String userHeadPicture;//评论者头像
	
	private String discussContent;//评论内容
	
	private String discussTime;//评论时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserHeadPicture() {
		return userHeadPicture;
	}

	public void setUserHeadPicture(String userHeadPicture) {
		this.userHeadPicture = userHeadPicture;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public String getDiscussTime() {
		return discussTime;
	}

	public void setDiscussTime(String discussTime) {
		this.discussTime = discussTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[nickName] = ");
		sb.append(nickName);
		sb.append(",[userHeadPicture] = ");
		sb.append(userHeadPicture);
		sb.append(",[discussContent] = ");
		sb.append(discussContent);
		sb.append(",[discussTime] = ");
		sb.append(discussTime);
		return sb.toString();
	}
}
