package Model;

import java.io.Serializable;
import java.util.Date;

/** @hibernate.class table="UM_USERS" */
public class User implements Serializable {

	/** identifier field */
	private Long id;

	/** persistent field */
	private String loginId;

	private String fullName;

	private String emailId;
	/** persistent field */
	private Long districtId;
	private Integer flgPasswordUpdate = 1;
	private String districtName;
	private Long departmentId;
	private String departmentName;
	private String desigName;
	private Long desigId;
	/** persistent field */
	private Long blockId;

	private Long patwarId;

	private String patwarName;

	private Long revenuevillageId;

	private String revenuevillageName;

	private Long revenuetehsilId;

	private String revenuetehsilName;

	private String blockName;

	/** persistent field */
	private Long panchayatId;

	private String panchayatName;

	/** persistent field */
	private Long villageId;

	private String villageName;

	/** persistent field */
	private Long tehsilId;

	private String tehsilName;

	/** persistent field */
	private Long townId;

	private String townName;

	/** persistent field */
	private Long wardId;

	private String wardName;

	/** persistent field */
	private Long stateId;

	/** persistent field */
	private String stateName;

	/** persistent field */
	private Long userGroup;
	private String userType;

	private String userTypeObj;
	private String userGroupName;
	private String loginType;
	/** persistent field */
	private Integer isLoggedIn;

	/** persistent field */
	private Date lastLogin;

	/** persistent field */
	private String password;

	private String firstName;

	private String middleName;

	private String lastName;

	private String fathersFirstName;

	private String fathersMiddleName;

	private String fathersLastName;

	private Date dateOfBirth;

	private String mobileNumber;

	private String aadhaarNo;

	private String epic;

	private String bplNo;

	private String panCard;

	private String familyId;

	private String emailIsLogin;

	private String hQueId1;

	private String hintAns1;

	private String hQueId2;

	private String hintAns2;

	private boolean expirePasswd = true;

	private String displayName;

	private String memberOf;

	private String filter;

	private byte[] doc;
	private String mimeType;
	private String fileName;

	private Date dtCreated;
	private Date linkCreation;

	private Integer forceLogin = 1;

	private String address;

	private boolean guestUser;

	private boolean lmkUser;

	private String gender;
		
	private CencusState state;
	
	private CencusDistrict district;
	
	private CencusTehsil tehsil;
	
	private CencusBlock block;
	
	private CencusPanchayat panchayat;
	
	private CencusVillage village;
	
	private Town town;
	
	private HimMuncipality muncipality;
	
	private Ward ward;
	
	private byte[] photoProofData;
	
	private String photoProofMimeType;
	
	private String photoProofFileName;
	
	
	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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
	 * @hibernate.property column="LOGIN_ID" length="250"
	 */
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * @hibernate.property column="BLOCK_ID"
	 */
	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	/**
	 * @hibernate.property column="PANCHAYAT_ID"
	 */
	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	/**
	 * @hibernate.property column="VILLAGE_ID"
	 */
	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
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
	 * @hibernate.property column="TOWN_ID"
	 */
	public Long getTownId() {
		return townId;
	}

	public void setTownId(Long townId) {
		this.townId = townId;
	}

	/**
	 * @hibernate.property column="WARD_ID"
	 */
	public Long getWardId() {
		return wardId;
	}

	public void setWardId(Long wardId) {
		this.wardId = wardId;
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
	 * @hibernate.property column="USER_GROUP"
	 */
	public Long getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(Long userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @hibernate.property column="IS_LOGGEDIN"
	 */
	public Integer getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Integer isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * @hibernate.property column="LAST_LOGIN"
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @hibernate.property column="USER_PASSWORD" length="500"
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getDesigId() {
		return desigId;
	}

	public void setDesigId(Long desigId) {
		this.desigId = desigId;
	}

	/**
	 * @hibernate.property column="DEPARTMENT_ID" length="250"
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getPatwarId() {
		return patwarId;
	}

	public void setPatwarId(Long patwarId) {
		this.patwarId = patwarId;
	}

	public Long getRevenuevillageId() {
		return revenuevillageId;
	}

	public void setRevenuevillageId(Long revenuevillageId) {
		this.revenuevillageId = revenuevillageId;
	}

	public Long getRevenuetehsilId() {
		return revenuetehsilId;
	}

	public void setRevenuetehsilId(Long revenuetehsilId) {
		this.revenuetehsilId = revenuetehsilId;
	}

	public String getRevenuevillageName() {
		return revenuevillageName;
	}

	public void setRevenuevillageName(String revenuevillageName) {
		this.revenuevillageName = revenuevillageName;
	}

	public String getRevenuetehsilName() {
		return revenuetehsilName;
	}

	public void setRevenuetehsilName(String revenuetehsilName) {
		this.revenuetehsilName = revenuetehsilName;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDesigName() {
		return desigName;
	}

	public void setDesigName(String desigName) {
		this.desigName = desigName;
	}

	public String getPatwarName() {
		return patwarName;
	}

	public void setPatwarName(String patwarName) {
		this.patwarName = patwarName;
	}

	public Integer getFlgPasswordUpdate() {
		return flgPasswordUpdate;
	}

	public void setFlgPasswordUpdate(Integer flgPasswordUpdate) {
		this.flgPasswordUpdate = flgPasswordUpdate;
	}

	public String getFathersFirstName() {
		return fathersFirstName;
	}

	public void setFathersFirstName(String fathersFirstName) {
		this.fathersFirstName = fathersFirstName;
	}

	public String getFathersMiddleName() {
		return fathersMiddleName;
	}

	public void setFathersMiddleName(String fathersMiddleName) {
		this.fathersMiddleName = fathersMiddleName;
	}

	public String getFathersLastName() {
		return fathersLastName;
	}

	public void setFathersLastName(String fathersLastName) {
		this.fathersLastName = fathersLastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getEpic() {
		return epic;
	}

	public void setEpic(String epic) {
		this.epic = epic;
	}

	public String getBplNo() {
		return bplNo;
	}

	public void setBplNo(String bplNo) {
		this.bplNo = bplNo;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getEmailIsLogin() {
		return emailIsLogin;
	}

	public void setEmailIsLogin(String emailIsLogin) {
		this.emailIsLogin = emailIsLogin;
	}

	public String gethQueId1() {
		return hQueId1;
	}

	public void sethQueId1(String hQueId1) {
		this.hQueId1 = hQueId1;
	}

	public String getHintAns1() {
		return hintAns1;
	}

	public void setHintAns1(String hintAns1) {
		this.hintAns1 = hintAns1;
	}

	public String gethQueId2() {
		return hQueId2;
	}

	public void sethQueId2(String hQueId2) {
		this.hQueId2 = hQueId2;
	}

	public String getHintAns2() {
		return hintAns2;
	}

	public void setHintAns2(String hintAns2) {
		this.hintAns2 = hintAns2;
	}

	public boolean isExpirePasswd() {
		return expirePasswd;
	}

	public void setExpirePasswd(boolean expirePasswd) {
		this.expirePasswd = expirePasswd;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	public String getUserTypeObj() {
		return userTypeObj;
	}

	public void setUserTypeObj(String userTypeObj) {
		this.userTypeObj = userTypeObj;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFullName() {
		this.fullName = (this.firstName != null ? this.firstName : "") + " "
				+ (this.middleName != null ? this.middleName : "") + " "
				+ (this.lastName != null ? this.lastName : "");
		return fullName;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	private String shortCode;

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getLinkCreation() {
		return linkCreation;
	}

	public void setLinkCreation(Date linkCreation) {
		this.linkCreation = linkCreation;
	}

	public Integer getForceLogin() {
		return forceLogin;
	}

	public void setForceLogin(Integer forceLogin) {
		this.forceLogin = forceLogin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGuestUser() {
		return guestUser;
	}

	public void setGuestUser(boolean guestUser) {
		this.guestUser = guestUser;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isLmkUser() {
		return lmkUser;
	}

	public void setLmkUser(boolean lmkUser) {
		this.lmkUser = lmkUser;
	}

	public CencusState getState() {
		return state;
	}

	public void setState(CencusState state) {
		this.state = state;
	}

	public CencusDistrict getDistrict() {
		return district;
	}

	public void setDistrict(CencusDistrict district) {
		this.district = district;
	}

	public CencusTehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(CencusTehsil tehsil) {
		this.tehsil = tehsil;
	}

	public CencusBlock getBlock() {
		return block;
	}

	public void setBlock(CencusBlock block) {
		this.block = block;
	}

	public CencusPanchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(CencusPanchayat panchayat) {
		this.panchayat = panchayat;
	}

	public CencusVillage getVillage() {
		return village;
	}

	public void setVillage(CencusVillage village) {
		this.village = village;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public HimMuncipality getMuncipality() {
		return muncipality;
	}

	public void setMuncipality(HimMuncipality muncipality) {
		this.muncipality = muncipality;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}


	public byte[] getPhotoProofData() {
		return photoProofData;
	}

	public void setPhotoProofData(byte[] photoProofData) {
		this.photoProofData = photoProofData;
	}

	public String getPhotoProofMimeType() {
		return photoProofMimeType;
	}

	public void setPhotoProofMimeType(String photoProofMimeType) {
		this.photoProofMimeType = photoProofMimeType;
	}

	public String getPhotoProofFileName() {
		return photoProofFileName;
	}

	public void setPhotoProofFileName(String photoProofFileName) {
		this.photoProofFileName = photoProofFileName;
	}



}
