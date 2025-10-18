package api.mh.facturacionElectronica.model;

import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.AutentificacionResponseBody;

public class AutentificacionResponse {

	public String status;
	public AutentificacionResponseBody body;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AutentificacionResponseBody getBody() {
		return body;
	}

	public void setBody(AutentificacionResponseBody body) {
		this.body = body;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutentificacionResponse other = (AutentificacionResponse) obj;
		return Objects.equals(body, other.body) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "AutentificacionResponse [status=" + status + ", body=" + body + "]";
	}

}
