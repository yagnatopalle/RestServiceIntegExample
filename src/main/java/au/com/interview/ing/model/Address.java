package au.com.interview.ing.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;


@Validated
public class Address {

	@NotNull
	@Size(max=100) 
	@JsonProperty("street")
	private String street;

	@NotNull
	@Size(max=50) 
	@JsonProperty("city")
	private String city;

	@NotNull
	@Size(max=20) 
	@JsonProperty("state")
	private String state;

	@NotNull
	@Size(max=4) 
	@JsonProperty("postcode")
	private Integer postcode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}	
}

