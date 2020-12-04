package au.com.interview.ing.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request object for PUT or to modify user or address details.
 *
 * @author Yagna
 *
 */
@Validated
public class RequestData {

	@Size(max = 20, message = "Title can be of max 20 characters.")
	@JsonProperty("title")
	private String title;

	@Size(max = 50, message = "First Name can be of max 50 characters.")
	@JsonProperty("firstname")
	private String firstname;

	@Size(max = 50, message = "Last Name can be of max 50 characters.")
	@JsonProperty("lastname")
	private String lastname;

	@Size(max = 20, message = "Gender can be of max 20 characters.")
	@JsonProperty("gender")
	private String gender;

	@Valid
	@JsonProperty("address")
	private Address address;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
