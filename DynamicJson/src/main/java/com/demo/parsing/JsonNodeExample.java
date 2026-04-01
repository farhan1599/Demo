package com.demo.parsing;

import java.io.File;

import com.demo.binding.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeExample {

	ObjectMapper mapper = new ObjectMapper();

//	Person person = new Person();
//	Details personDetails = new Details();
//	ExtraFields extra = new ExtraFields();
	File json = new File("data.json");

	public void dynamicJson() {

		try {
			 Person person = mapper.readValue(json,Person.class);
			 
			// JsonNode root = mapper.readTree(json);

//			// extract known fields
//			int id = root.get("id").asInt();
//			String name = root.get("name").asText();
//			// extract nested objects
//			JsonNode details = root.get("details");
//			String email = details.get("email").asText();
//			Long phone = details.get("phone").asLong();
//			personDetails.setEmail(email);
//			personDetails.setPhone(phone);

//           
//			// array
//			List<String> hobbies = new ArrayList<>();
//			for (JsonNode hobbyNode : root.get("hobbies")) {
//				hobbies.add(hobbyNode.asText());
//			}
			 
//			// unknown fields
//        
//			JsonNode extraFields = root.get("extraField");
//			String key1 = extraFields.get("key1").asText();
//			String key2 = extraFields.get("key2").asText();
//			extra.setKey1(key1);
//			extra.setKey2(key2);
//
//			person.setId(id);
//			person.setName(name);
//			person.setDetails(personDetails);
//			person.setHobbies(hobbies);
//			person.setExtraFields(extra);

			System.out.println(person);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
