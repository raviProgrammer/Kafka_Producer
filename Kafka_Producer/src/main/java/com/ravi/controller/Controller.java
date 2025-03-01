package com.ravi.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.entity.Feedback;
import com.ravi.entity.Public;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class Controller {

	@Value("${producer.topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkatemp;
	
	@PostMapping("/getSinglaFeedback")
	public String getFeedback(@RequestBody Feedback<Public> feedback) {
		
		Public pb = feedback.getFeedback();
		pb.setId(UUID.randomUUID().toString());
		pb.setDate(new Date());
		kafkatemp.send(topicName, pb);
		return "Successfully gone feedback message";
	}
	
	@PostMapping("/getMultipleFeedback")
	public String getMultipleFeedback(@RequestBody Feedback<Public> feedback) {
		
		Public pb = feedback.getFeedback();
		for(int i=0; i<500; i++) {
			pb.setId(UUID.randomUUID().toString());
			pb.setDate(new Date());
			kafkatemp.send(topicName, pb);
		}
		return "Successfully gone feedback message";
	}
}
