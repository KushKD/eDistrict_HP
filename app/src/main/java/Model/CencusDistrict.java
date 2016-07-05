package Model;


import java.io.Serializable;

public class CencusDistrict implements Serializable {

	private Long id;

	private String name;

	private String code;

	private String cencus;

	private String stateCode;

	private String cencusOne;

	public CencusDistrict() {
		super();
	}

	public CencusDistrict(Long id, String name, String code, String cencus,
			String stateCode, String cencusOne) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.cencus = cencus;
		this.stateCode = stateCode;
		this.cencusOne = cencusOne;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
