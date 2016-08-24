package jhj.scanner.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "scanresult")
public class Address {
	@Field
	private String addressId="";
	@Field
	private String addressValue ="";
	
		
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressValue() {
		return addressValue;
	}
	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}
	
	
	

}
