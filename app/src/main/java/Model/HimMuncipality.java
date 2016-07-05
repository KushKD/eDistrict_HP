package Model;

import java.io.Serializable;

public class HimMuncipality implements Serializable {

	private Integer id;
	public String officeName;
	public String officeNameHindi;
	public String officeType;
	public String location;
	public String districtCode;
	public String subDivision;
	private Long townCode;
	public String isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeNameHindi() {
		return officeNameHindi;
	}

	public void setOfficeNameHindi(String officeNameHindi) {
		this.officeNameHindi = officeNameHindi;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}


	public Long getTownCode() {
		return townCode;
	}

	public void setTownCode(Long townCode) {
		this.townCode = townCode;
	}

}
