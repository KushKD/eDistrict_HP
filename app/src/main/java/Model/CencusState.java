package Model;


import java.io.Serializable;

public class CencusState implements Serializable {

	private Long id;

	private String name;

	private String cencus;

	private String code;

	public CencusState() {
		super();
	}

	public CencusState(Long id, String name, String cencus, String code) {
		super();
		this.id = id;
		this.name = name;
		this.cencus = cencus;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
