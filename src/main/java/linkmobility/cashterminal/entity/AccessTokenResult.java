package linkmobility.cashterminal.entity;

import java.util.Arrays;

public class AccessTokenResult {

	Boolean success;
	
	String[] errors;
	
	String accessToken;
	
	String expiresUtc;
	
	String tokenType;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresUtc() {
		return expiresUtc;
	}

	public void setExpiresUtc(String expiresUtc) {
		this.expiresUtc = expiresUtc;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		return "AccessTokenResult [success=" + success + ", errors=" + Arrays.toString(errors) + ", accessToken="
				+ accessToken + ", expiresUtc=" + expiresUtc + ", tokenType=" + tokenType + "]";
	}
	
	
}
