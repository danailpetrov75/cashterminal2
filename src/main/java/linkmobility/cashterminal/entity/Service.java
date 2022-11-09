package linkmobility.cashterminal.entity;


/**
 * 
 * @author Danail Petrov
 * Represents service for which payment is committed
 * 
 * {
    "id": 1,
    "name": "Eletricity",
    "description": "Eletricity",
    "bankId": "52151bbf-bb01-4bbb-a2c5-57eab5761210"
  },
 */
public class Service {
	
	String id;
	
	String name;
	
	String description;
	
	String bankId;

	public Service() {
		
	}
	
	

	public Service(String id, String name, String description, String bankId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.bankId = bankId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", description=" + description + ", bankId=" + bankId + "]";
	}
	
}
