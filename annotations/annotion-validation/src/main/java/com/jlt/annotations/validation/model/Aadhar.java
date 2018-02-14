package com.jlt.annotations.validation.model;

import java.util.Date;

import com.jlt.annotations.validation.annotation.CField;
import com.jlt.annotations.validation.annotation.MVField;
import com.jlt.annotations.validation.annotation.VField;
import com.jlt.annotations.validation.validator.VMaxLength;
import com.jlt.annotations.validation.validator.VNull;
import com.jlt.annotations.validation.validator.VGender;

/**
 * Model class for Aadhaar Document
 * 
 * @author Prabal Ghura
 *
 */
public class Aadhar extends Document{
	
	@MVField(validators = {
		@VField(type=VNull.class),
		@VField(type=VMaxLength.class, params={"length=50"}) })
	@CField(name="Full Name")
	private String fullname;
	
	@MVField(validators= {
		@VField(type=VNull.class),
		@VField(type=VGender.class)})
	private String gender;
	
	@MVField(validators= {
			@VField(type=VNull.class),
			@VField(type=VMaxLength.class, params={"length=100"})})
	private String address;
	
	@MVField(validators= {
			@VField(type=VNull.class),
			@VField(type=VMaxLength.class, params={"length=12"})})
	private String aadharNumber;
	
	private Date dob;

	/**
	 * @param fullname
	 * @param gender
	 * @param address
	 * @param aadharNumber
	 * @param dob
	 */
	public Aadhar(String fullname, String gender, String address, String aadharNumber, Date dob) {
		super();
		this.fullname = fullname;
		this.gender = gender;
		this.address = address;
		this.aadharNumber = aadharNumber;
		this.dob = dob;
	}

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
