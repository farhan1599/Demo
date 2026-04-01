package com.demo.parsing;

import java.io.File;
import com.demo.binding.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParsingExample {

	ObjectMapper mapper = new ObjectMapper();
	User user = new User();
	String json = "{ \"id\":101, \"name\":\"Farhan\", \"skills\":[\"Java\",\"Spring\",\"SQL\"] }";
	File file = new File("User.json");
	public void jsonToObj() {
		try {
			
			user = mapper.readValue(file, User.class);
			System.out.println(user);
		//	System.out.println(user.getName());
			//System.out.println(user.getSkills());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ObjToJson() {
		User newUser = new User();
		newUser.setId(user.getId()+1);
		newUser.setName(user.getName());
		newUser.setSkills(user.getSkills());
		try {
			mapper.writeValue(file,newUser);
			// System.out.println(writeValueAsString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
