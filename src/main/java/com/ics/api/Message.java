package com.ics.api;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Message {
	private Integer id;
	private String message;
	private String author;
	

	
	
}

