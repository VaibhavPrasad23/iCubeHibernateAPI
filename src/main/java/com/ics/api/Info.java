package com.ics.api;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Info 
{
	private Integer id;
	private String username;
	private String password;
}