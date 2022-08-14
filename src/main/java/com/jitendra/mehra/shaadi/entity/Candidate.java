package com.jitendra.mehra.shaadi.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CANTIDATE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Candidate {
	
	@Column(name = "ID", nullable = false, unique = true)
	@Id
	private String id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "DOB", nullable = true)
	private Date dob;
	
	@Column(name = "GENDER", nullable = true)
	private String gender;
	
	@Column(name = "OCCUPATION", nullable = true)
	private String occupation;
	
	@Column(name = "EDUCATION", nullable = true)
	private String education;
		
	@Column(name = "SALARY", nullable = true)
	private Double salary;
	
	@Column(name = "MOTHERNAME", nullable = true)
	private String motherName;
	
	@Column(name = "FATHERNAME", nullable = true)
	private String fatherName; 
	
	@Column(name = "FATHEROCCUPATION", nullable = true)
	private String fatherOccupation;
	
	@Column(name = "FATHERSALARY", nullable = true)
	private Double fatherSalary; 
	
	@Column(name = "ADDRESS", nullable = true)
	private String address;
	
	@Column(name = "CONTACTINFO", nullable = false)
	private String contactInfo;
	
	@Column(name = "FATHERGOTRA", nullable = true)
	private String fatherGotra;
	
	@Column(name = "MAMAGOTRA", nullable = true)
	private String mamaGotra;
	
	@Column(name = "NOOFSISTER", nullable = true)
	private int noOfSister;
	
	@Column(name = "NOOFBROTHER", nullable = true)
	private int noOfBrother;
	
	@Column(name = "MOREINFORMATION", nullable = true)
	private String moreInformation;
	
	
	@Column(name = "PROFILECOMPLETED", nullable = true)
	private Boolean isProfileCompleted = Boolean.FALSE;

}
