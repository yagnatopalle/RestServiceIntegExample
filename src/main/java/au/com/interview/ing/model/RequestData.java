package au.com.interview.ing.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class RequestData {

	@NotNull
	@Size(max=20) 
	@JsonProperty("title")
	private String title;

	@NotNull
	@Size(max=50) 
	@JsonProperty("firstname")
	private String firstname;

	@NotNull
	@Size(max=50) 
	@JsonProperty("lastname")
	private String lastname;

	@NotNull
	@Size(max=20) 
	@JsonProperty("gender")
	private String gender;

	@NotNull
	@Valid
	@JsonProperty("address")
	private Address address;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

