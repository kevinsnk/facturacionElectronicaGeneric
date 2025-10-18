package api.mh.facturacionElectronica.model;

import java.util.Objects;

public class AutentificacionRequest {
	String user;
	String pwd;
	String token;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pwd, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutentificacionRequest other = (AutentificacionRequest) obj;
		return Objects.equals(pwd, other.pwd) && Objects.equals(user, other.user);
	}

}
