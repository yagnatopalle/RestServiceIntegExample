package au.com.interview.ing.model;

import javax.validation.constraints.NotNull;
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

	@NotNull
	@Size(max = 100)
	@JsonProperty("street")
	private String street;

	@NotNull
	@Size(max = 50)
	@JsonProperty("city")
	private String city;

	@NotNull
	@Size(max = 20)
	@JsonProperty("state")
	private String state;

	@NotNull
	@Size(max = 4)
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
