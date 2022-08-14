package com.jitendra.mehra.shaadi.dto;

import org.apache.commons.lang3.StringUtils;
import org.js.autenticationclient.bean.Authentication;

import com.jitendra.mehra.shaadi.utils.StringHelper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MehraAuthentication extends Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mobileNumber;
	private String gender;
	private String name;

	public MehraAuthentication build(String mobileNumber) {
		MehraAuthentication authentication = new MehraAuthenticationBuilder().mobileNumber(mobileNumber).build();
		authentication.setUserId(getUserId());
		authentication.setUserName(getUserName());

		return authentication;
	}

	public MehraAuthentication build(String mobileNumber, String gender) {
		MehraAuthentication authentication = new MehraAuthenticationBuilder().mobileNumber(mobileNumber).gender(gender)
				.build();
		authentication.setUserId(getUserId());
		authentication.setUserName(getUserName());

		return authentication;
	}

	public MehraAuthentication(Authentication authentication) {
		this.setUserId(authentication.getUserId());
		this.setUserName(authentication.getUserName());
	}
	
	public void afterPropertiesSet() {
		 this.setName(StringHelper.upperCaseWords(this.name));
		 this.setGender(StringUtils.capitalize(this.getGender()));	}

	
	@Override
	public String toString() {
		return "MehraAuthentication [mobileNumber=" + mobileNumber + ", gender=" + gender + ", name=" + name
				+ ", getUserName()=" + getUserName() + ", getPassward()=" + getPassward() + ", getUserId()="
				+ getUserId() + ", getAppId()=" + getAppId() + ", getToken()=" + getToken() + ", getExpaireDay()="
				+ getExpaireDay() + ", getCreationDate()=" + getCreationDate() + ", getLastLogin()=" + getLastLogin()
				+ ", getIsLogout()=" + getIsLogout() + ", getIsActive()=" + getIsActive() + ", toString()="
				+ super.toString() + "]";
	}

	
	
}
