package JsonManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Model.User_Pojo;

/**
 * Created by kuush on 6/16/2016.
 */
public class Registration_Json {

    public static String RegisterME_Error(String s) {

        String g_Table = null;
        try {
                JSONObject obj = new JSONObject(s);
                g_Table = obj.optString("message");
                return g_Table;

        } catch (JSONException e) {
            e.printStackTrace();
            return g_Table = "Something went wrong.";
        }
    }

    public static User_Pojo Parse_Data_Registration(String s) {

        String g_Table = null;

        User_Pojo USER = null;
        try {

                USER = new User_Pojo();
                JSONObject obj = new JSONObject(s);

            USER.setId(obj.optString("id"));
            USER.setLoginId(obj.optString("loginId"));
            USER.setFullName(obj.optString("fullName"));
            USER.setEmailId(obj.optString("emailId"));
            USER.setDistrictId(obj.optString("districtId"));
            USER.setFlgPasswordUpdate(obj.optString("flgPasswordUpdate"));
            USER.setDistrictName(obj.optString("districtName"));
            USER.setDepartmentId(obj.optString("departmentId"));
            USER.setDepartmentName(obj.optString("departmentName"));
            USER.setDesigName(obj.optString("desigName"));
            USER.setDesigId(obj.optString("desigId"));
            USER.setBlockId(obj.optString("blockId"));
            USER.setPatwarId(obj.optString("patwarId"));
            USER.setPatwarName(obj.optString("patwarName"));
            USER.setRevenuevillageId(obj.optString("revenuevillageId"));
            USER.setRevenuevillageName(obj.optString("revenuevillageName"));
            USER.setRevenuetehsilId(obj.optString("revenuetehsilId"));
            USER.setRevenuetehsilName(obj.optString("revenuetehsilName"));
            USER.setBlockName(obj.optString("blockName"));
            USER.setPanchayatId(obj.optString("panchayatId"));
            USER.setPanchayatName(obj.optString("panchayatName"));
            USER.setVillageId(obj.optString("villageId"));
            USER.setVillageName(obj.optString("villageName"));
            USER.setTehsilId(obj.optString("tehsilId"));
            USER.setTehsilName(obj.optString("tehsilName"));
            USER.setTownId(obj.optString("townId"));
            USER.setTownName(obj.optString("townName"));
            USER.setWardId(obj.optString("wardId"));
            USER.setWardName(obj.optString("wardName"));
            USER.setStateId(obj.optString("stateId"));
            USER.setStateName(obj.optString("stateName"));
            USER.setUserGroup(obj.optString("userGroup"));
            USER.setUserType(obj.optString("userType"));
            USER.setUserTypeObj(obj.optString("userTypeObj"));
            USER.setUserGroupName(obj.optString("userGroupName"));
            USER.setLoginType(obj.optString("loginType"));
            USER.setIsLoggedIn(obj.optString("isLoggedIn"));
            USER.setLastLogin(obj.optString("lastLogin"));
            USER.setPassword(obj.optString("password"));
            USER.setFirstName(obj.optString("firstName"));
            USER.setMiddleName(obj.optString("middleName"));
            USER.setLastName(obj.optString("lastName"));
            USER.setFathersFirstName(obj.optString("fathersFirstName"));
            USER.setFathersMiddleName(obj.optString("fathersMiddleName"));
            USER.setFathersLastName(obj.optString("fathersLastName"));
            USER.setDateOfBirth(obj.optString("dateOfBirth"));
            USER.setMobileNumber(obj.optString("mobileNumber"));
            USER.setAadhaarNo(obj.optString("aadhaarNo"));
            USER.setEpic(obj.optString("epic"));
            USER.setBplNo(obj.optString("bplNo"));
            USER.setPanCard(obj.optString("panCard"));
            USER.setFamilyId(obj.optString("familyId"));
            USER.setEmailIsLogin(obj.optString("emailIsLogin"));
            USER.sethQueId1(obj.optString("hQueId1"));
            USER.setHintAns1(obj.optString("hintAns1"));
            USER.sethQueId2(obj.optString("hQueId2"));
            USER.setHintAns2(obj.optString("hintAns2"));
            USER.setExpirePasswd(obj.optString("expirePasswd"));
            USER.setDisplayName(obj.optString("displayName"));
            USER.setMemberOf(obj.optString("memberOf"));
            USER.setFilter(obj.optString("filter"));
            USER.setDoc(obj.optString("doc"));
            USER.setMimeType(obj.optString("mimeType"));
            USER.setFileName(obj.optString("fileName"));
            USER.setDtCreated(obj.optString("dtCreated"));
            USER.setLinkCreation(obj.optString("linkCreation"));
            USER.setForceLogin(obj.optString("forceLogin"));
            USER.setState(obj.optString("state"));
            USER.setRuralUrban(obj.optString("ruralUrban"));
            USER.setDistrict(obj.optString("district"));
            USER.setTehsil(obj.optString("tehsil"));
            USER.setCity(obj.optString("city"));
            USER.setAddress(obj.optString("address"));
            USER.setBlock(obj.optString("block"));
            USER.setPanchayat(obj.optString("panchayat"));
            USER.setVillage(obj.optString("village"));
            USER.setGuestUser(obj.optString("guestUser"));
            USER.setGender(obj.optString("gender"));
            USER.setShortCode(obj.optString("shortCode"));
            USER.setRowCount(obj.optString("rowCount"));
            USER.setRowData(obj.optString("rowData"));
            USER.setRowIndex(obj.optString("rowIndex"));
            USER.setWrappedData(obj.optString("wrappedData"));
            USER.setRowAvailable(obj.optString("rowAvailable"));


                return USER;

        } catch (JSONException e) {
            e.printStackTrace();
            return USER=null;
        }
    }


}
