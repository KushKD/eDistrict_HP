package Model;

import java.io.Serializable;

/** @hibernate.class table="MD_WARDS" */
public class Ward implements Serializable {

	/** identifier field */
	private Long id;

	/** persistent field */
	private Long stateId;

	private String stateName;

	/** persistent field */
	private Long districtId;

	private String districtName;

	/** persistent field */
	private Long townId;

	private String townName;

	/** persistent field */
	private Long tehsilId;

	private String tehsilName;

	/** persistent field */
	private String name;

	/** persistent field */
	private String nameHindi;

	/** persistent field */
	private String code;

	/** persistent field */
	private String censusCode;

	/** persistent field */
	private String nicCode;
	
	
	private Long townCode;

	/**
	 * @hibernate.id generator-class="identity" column="ID"
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="STATE_ID"
	 */
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @hibernate.property column="DISTRICT_ID"
	 */
	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	/**
	 * @hibernate.property column="TOWN_ID"
	 */
	public Long getTownId() {
		return townId;
	}

	public void setTownId(Long townId) {
		this.townId = townId;
	}

	/**
	 * @hibernate.property column="TEHSIL_ID"
	 */
	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	/**
	 * @hibernate.property column="WARD_NAME" length="255"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="WARD_NAME_HINDI" length="500"
	 */
	public String getNameHindi() {
		return nameHindi;
	}

	public void setNameHindi(String nameHindi) {
		this.nameHindi = nameHindi;
	}

	/**
	 * @hibernate.property column="WARD_CODE" length="10"
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @hibernate.property column="CENSUS_CODE" length="10"
	 */
	public String getCensusCode() {
		return censusCode;
	}

	public void setCensusCode(String censusCode) {
		this.censusCode = censusCode;
	}

	/**
	 * @hibernate.property column="NIC_CODE" length="10"
	 */
	public String getNicCode() {
		return nicCode;
	}

	public void setNicCode(String nicCode) {
		this.nicCode = nicCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public Long getTownCode() {
		return townCode;
	}

	public void setTownCode(Long townCode) {
		this.townCode = townCode;
	}

}
