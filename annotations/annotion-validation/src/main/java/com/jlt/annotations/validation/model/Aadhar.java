package com.jlt.annotations.validation.model;

import java.util.Date;

import com.jlt.annotations.validation.annotation.Consistent;
import com.jlt.annotations.validation.annotation.Gender;
import com.jlt.annotations.validation.annotation.Length;
import com.jlt.annotations.validation.annotation.MaxLength;
import com.jlt.annotations.validation.annotation.MinLength;
import com.jlt.annotations.validation.annotation.NotNull;

/**
 * Model class for Aadhaar Document
 * 
 * @author Prabal Ghura
 *
 */
public class Aadhar extends Document{
	
	@Consistent("Full Name")
	@MaxLength(50)
	@MinLength(3)
	@NotNull
	private String fullname;
	
	@Gender
	@NotNull
	private String gender;
	
	@MaxLength(100)
	@NotNull
	private String address;
	
	@Length(12)
	@NotNull
	private String aadharNumber;
	
	@NotNull
	private Date dob;

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}

	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
