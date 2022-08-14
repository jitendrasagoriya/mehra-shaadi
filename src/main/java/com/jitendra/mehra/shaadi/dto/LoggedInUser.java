package com.jitendra.mehra.shaadi.dto;

import org.apache.commons.lang3.BooleanUtils;

import com.jitendra.mehra.shaadi.entity.Candidate;

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
public class LoggedInUser {
	private String userId;
	private String displayName;
	private String email;
	private String mobileNumber;
	private String token;
	private String gender;
	private boolean isProfileCompleted;
	
	
	public static LoggedInUser build(MehraAuthentication authentication) {
		return  LoggedInUser.builder()
					.userId(authentication.getUserId())
					.displayName(authentication.getName())
					.mobileNumber(authentication.getMobileNumber())
					.email(authentication.getUserName())
					.gender(authentication.getGender())
				.build();
	}
	
	public static LoggedInUser build(Candidate candidate) {
		return  LoggedInUser.builder()
					.userId(candidate.getId())
					.displayName(candidate.getName())
					.mobileNumber(candidate.getContactInfo())
					.email(candidate.getEmail())
					.gender(candidate.getGender())
					.isProfileCompleted(BooleanUtils.isTrue(candidate.getIsProfileCompleted()))
				.build();
	}
	
	

}
