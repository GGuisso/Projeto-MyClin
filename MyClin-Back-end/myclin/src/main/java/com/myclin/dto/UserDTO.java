package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Integer id;
	private Integer idClinica;
	private String username;
	private String password;
	
}
