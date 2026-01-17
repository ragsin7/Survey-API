package com.propsur.api.project.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {

	private String username;
	
	private String password;
	
	private String appversion;
	
	private Long userId;
	
	private Character currLoginOutFlag;
	
	private String osVersion;
}
