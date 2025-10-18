package api.mh.facturacionElectronica.model;

import java.util.Objects;

public class AnulacionDTERequest {

	public String ambiente;
	public int idEnvio;
	public int version;
	public String documento;

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ambiente, documento, idEnvio, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnulacionDTERequest other = (AnulacionDTERequest) obj;
		return Objects.equals(ambiente, other.ambiente) && Objects.equals(documento, other.documento)
				&& idEnvio == other.idEnvio && version == other.version;
	}

	@Override
	public String toString() {
		return "AnulacionDTERequest [ambiente=" + ambiente + ", idEnvio=" + idEnvio + ", version=" + version
				+ ", documento=" + documento + "]";
	}

}
