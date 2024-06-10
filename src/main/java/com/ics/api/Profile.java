package com.ics.api;

import java.sql.Date;

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
public class Profile {
	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date created;

}
