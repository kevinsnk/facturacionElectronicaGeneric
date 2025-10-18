package api.mh.facturacionElectronica.model;

import java.util.Objects;

public class RecepcionDTERequest {

	public String ambiente;
	public int idEnvio;
	public int version;
	public String tipoDte;
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

	public String getTipoDte() {
		return tipoDte;
	}

	public void setTipoDte(String tipoDte) {
		this.tipoDte = tipoDte;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ambiente, documento, idEnvio, tipoDte, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecepcionDTERequest other = (RecepcionDTERequest) obj;
		return Objects.equals(ambiente, other.ambiente) && Objects.equals(documento, other.documento)
				&& idEnvio == other.idEnvio && Objects.equals(tipoDte, other.tipoDte) && version == other.version;
	}

	@Override
	public String toString() {
		return "RecepcionDTERequest [ambiente=" + ambiente + ", idEnvio=" + idEnvio + ", version=" + version
				+ ", tipoDte=" + tipoDte + ", documento=" + documento + "]";
	}

}
