package Model;


import java.io.Serializable;

/** @hibernate.class table="MD_TOWNS" */
public class Town implements Serializable {

	/** identifier field */
	private Long id;

	/** persistent field */
	private Long stateId;

	private String stateName;

	/** persistent field */
	private Long districtId;

	/** persistent field */
	private Long tehsilId;

	private String districtName;

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

	private Long muncipalityCode;

	private String tehsilCode;

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
	 * @hibernate.property column="TOWN_NAME" length="255"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="TOWN_NAME_HINDI" length="500"
	 */
	public String getNameHindi() {
		return nameHindi;
	}

	public void setNameHindi(String nameHindi) {
		this.nameHindi = nameHindi;
	}

	/**
	 * @hibernate.property column="TOWN_CODE" length="10"
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

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public Long getMuncipalityCode() {
		return muncipalityCode;
	}

	public void setMuncipalityCode(Long muncipalityCode) {
		this.muncipalityCode = muncipalityCode;
	}

	public String getTehsilCode() {
		return tehsilCode;
	}

	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

}
