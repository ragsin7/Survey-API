package com.propsur.api.project.dao;

import com.propsur.api.project.bean.TmUsersBean;
import com.propsur.api.project.entity.TmUsers;

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
public class JwtResponse {

	private String jwtToken;
	
	private String username;
	
	private TmUsers tmUsers;
	
	private TmUsersBean tmUsersBean;
}
