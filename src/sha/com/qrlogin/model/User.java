package sha.com.qrlogin.model;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import sha.com.qrlogin.utils.EnumUtil.UserProfileType;


@Entity
@Table(name="qr_user")
public class User implements IModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6026195619895984291L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", columnDefinition="serial")
	private Long id;
	
	@Column(name="sso_id")
	private String ssoId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="is_admin", columnDefinition = "TINYINT(1)")
	private Boolean admin;
	
	@Column(name="is_active", columnDefinition = "TINYINT(1)")
	private Boolean active;
	
	@Column(name="is_confirm", columnDefinition = "TINYINT(1)")
	private Boolean confirm;
	
	@Column(name="is_remember_me", columnDefinition = "TINYINT(1)")
	private Boolean rememberMe;
	
	@Column(name="unique_id")
	private String uniqueId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_profile_type")
	private UserProfileType enumProfileType;
	
	@Version
    @Column(name = "version")
    private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSsoId() {
		if(this.ssoId==null || this.ssoId.trim().equals("")){
			return null;
		}
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		if(this.email==null || this.email.trim().equals("")){
			return null;
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public UserProfileType getEnumProfileType() {
		return enumProfileType;
	}

	public void setEnumProfileType(UserProfileType enumProfileType) {
		this.enumProfileType = enumProfileType;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public boolean isAdmin(){
		if(UserProfileType.ADMIN.equals(this.enumProfileType)){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
