package api.mh.facturacionElectronica.ex.model.contingencia;

import java.util.Objects;

public class IdentificacionDTE {

	public int version;
	public String ambiente;
	public String codigoGeneracion;
	public String fTransmision;
	public String hTransmision;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getCodigoGeneracion() {
		return codigoGeneracion;
	}

	public void setCodigoGeneracion(String codigoGeneracion) {
		this.codigoGeneracion = codigoGeneracion;
	}

	public String getfTransmision() {
		return fTransmision;
	}

	public void setfTransmision(String fTransmision) {
		this.fTransmision = fTransmision;
	}

	public String gethTransmision() {
		return hTransmision;
	}

	public void sethTransmision(String hTransmision) {
		this.hTransmision = hTransmision;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ambiente, codigoGeneracion, fTransmision, hTransmision, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentificacionDTE other = (IdentificacionDTE) obj;
		return Objects.equals(ambiente, other.ambiente) && Objects.equals(codigoGeneracion, other.codigoGeneracion)
				&& Objects.equals(fTransmision, other.fTransmision) && Objects.equals(hTransmision, other.hTransmision)
				&& Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return "IdentificacionDTE [version=" + version + ", ambiente=" + ambiente + ", codigoGeneracion="
				+ codigoGeneracion + ", fTransmision=" + fTransmision + ", hTransmision=" + hTransmision + "]";
	}
}
