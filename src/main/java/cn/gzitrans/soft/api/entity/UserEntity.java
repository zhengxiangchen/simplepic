package cn.gzitrans.soft.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wx_user")
public class UserEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "openid")
	private String openId;//用户唯一标识
	
	@Column(name = "nick_name")
	private String nickName;//用户昵称
	
	@Column(name = "avatar_url")
	private String avatarUrl;//用户头像
	
	@Column(name = "gender")
	private String gender;//性别
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "language")
	private String language;


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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[openId] = ");
		sb.append(openId);
		sb.append(",[nickName] = ");
		sb.append(nickName);
		sb.append(",[avatarUrl] = ");
		sb.append(avatarUrl);
		sb.append(",[gender] = ");
		sb.append(gender);
		sb.append(",[city] = ");
		sb.append(city);
		sb.append(",[province] = ");
		sb.append(province);
		sb.append(",[country] = ");
		sb.append(country);
		sb.append(",[language] = ");
		sb.append(language);
		return sb.toString();
	}
	
	

}
