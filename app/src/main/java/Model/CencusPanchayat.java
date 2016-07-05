package Model;

import java.io.Serializable;

public class CencusPanchayat implements Serializable {

	private Long id;

	private String name;

	private String cencus;
	
	private String stateCode;
	
	private String districtCode;
	
	private String tehsilCode;
	
	private String blockCode;
	
	private String cencusOne;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCencus() {
		return cencus;
	}

	public void setCencus(String cencus) {
		this.cencus = cencus;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCencusOne() {
		return cencusOne;
	}

	public void setCencusOne(String cencusOne) {
		this.cencusOne = cencusOne;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getTehsilCode() {
		return tehsilCode;
	}

	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

}
