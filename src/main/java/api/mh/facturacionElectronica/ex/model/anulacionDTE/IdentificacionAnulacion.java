package api.mh.facturacionElectronica.ex.model.anulacionDTE;

import java.util.Objects;

public class IdentificacionAnulacion {

	public int version;
	public String ambiente;
	public String codigoGeneracion;
	public String fecAnula;
	public String horAnula;
	
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
	public String getFecAnula() {
		return fecAnula;
	}
	public void setFecAnula(String fecAnula) {
		this.fecAnula = fecAnula;
	}
	public String getHorAnula() {
		return horAnula;
	}
	public void setHorAnula(String horAnula) {
		this.horAnula = horAnula;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ambiente, codigoGeneracion, fecAnula, horAnula, version);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentificacionAnulacion other = (IdentificacionAnulacion) obj;
		return Objects.equals(ambiente, other.ambiente) && Objects.equals(codigoGeneracion, other.codigoGeneracion)
				&& Objects.equals(fecAnula, other.fecAnula) && Objects.equals(horAnula, other.horAnula)
				&& Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return "IdentificacionAnulacion [version=" + version + ", ambiente=" + ambiente + ", codigoGeneracion="
				+ codigoGeneracion + ", fecAnula=" + fecAnula + ", horAnula=" + horAnula + "]";
	}
	
	
}
