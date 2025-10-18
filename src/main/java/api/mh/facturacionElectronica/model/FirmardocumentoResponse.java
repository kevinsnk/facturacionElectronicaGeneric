package api.mh.facturacionElectronica.model;

import java.util.Objects;

public class FirmardocumentoResponse {

	public String status;
	public String body;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
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
		FirmardocumentoResponse other = (FirmardocumentoResponse) obj;
		return Objects.equals(body, other.body) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "FirmardocumentoResponse [status=" + status + ", body=" + body + "]";
	}

}
