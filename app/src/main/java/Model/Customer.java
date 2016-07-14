package Model;


import java.io.Serializable;

/** @hibernate.class table="UM_USERS" */
public class Customer implements Serializable {

	private String loginId;
	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fathersFirstName;
	private String fathersMiddleName;
	private String fathersLastName;
	private String dateOfBirth;
	private String mobileNumber;
	private String aadhaarNo;
	private String familyId;
	private String hQueId1;
	private String hintAns1;
	private String hQueId2;
	private String hintAns2;
	private String address;
	private String gender;
	private String state;
	private String district;
	private String tehsil;
	private String block;
	private String panchayat;
	private String village;
	private String town;
	private String muncipality;
	private String ward;
	private String photoProofData;
	private String photoProofMimeType;
	private String photoProofFileName;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
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

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getMuncipality() {
		return muncipality;
	}

	public void setMuncipality(String muncipality) {
		this.muncipality = muncipality;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getPhotoProofData() {
		return photoProofData;
	}

	public void setPhotoProofData(String photoProofData) {
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
