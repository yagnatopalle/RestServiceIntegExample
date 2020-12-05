package au.com.interview.ing.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class UserEntity {

	@Id
	@Column(name = "empid", updatable = false)
	private long empId;

	@Column(name = "addressid", nullable = false)
	private Long addressId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "createddatetime")
	private Timestamp createdDateTime;

	@Column(name = "updateddatetime")
	private Timestamp updatedDateTime;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressid", referencedColumnName = "id", insertable = false, updatable = false)
	private AddressEntity address;

	public long getEmpId() {
		return empId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public AddressEntity getAddress() {
		return address;
	}

	@PrePersist
	public void setTimeStamps() {
		this.createdDateTime = Timestamp.valueOf(LocalDateTime.now());
		this.updatedDateTime = Timestamp.valueOf(LocalDateTime.now());
	}

	@PreUpdate
	public void setUpdatedTimeStamps() {
		this.updatedDateTime = Timestamp.valueOf(LocalDateTime.now());
	}

}
