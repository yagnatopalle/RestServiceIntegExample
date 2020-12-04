package au.com.interview.ing.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Address object
 *
 * @author Yagna
 *
 */

@Validated
public class Address {

	@Size(max = 100, message = "Street can be of max 100 characters.")
	@JsonProperty("street")
	private String street;

	@Size(max = 50, message = "City can be of max 50 characters.")
	@JsonProperty("city")
	private String city;

	@Size(max = 25, message = "State can be of max 25 characters.")
	@JsonProperty("state")
	private String state;

	@Min(value = 999, message = "Postcode must be a 4 digit number.")
	@Max(value = 10000, message = "Postcode must be a 4 digit number.")
	@JsonProperty("postcode")
	private Integer postcode;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postcode
	 */
	public Integer getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

}
