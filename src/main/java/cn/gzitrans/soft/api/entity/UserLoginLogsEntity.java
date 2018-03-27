package cn.gzitrans.soft.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_login_logs")
public class UserLoginLogsEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "openid")
	private String openId;
	
	@Column(name = "login_time")
	private Timestamp loginTime;

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

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[id] = ");
		sb.append(id);
		sb.append(",[openId] = ");
		sb.append(openId);
		sb.append(",[loginTime] = ");
		sb.append(loginTime);
		return sb.toString();
	}

}
