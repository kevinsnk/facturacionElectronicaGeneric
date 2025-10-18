package api.mh.facturacionElectronica.ex.model;

import java.util.Objects;

public class DocumentoRelacionado {

	public String tipoDocumento;
	public int tipoGeneracion;
	public String numeroDocumento;
	public String fechaEmision;

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getTipoGeneracion() {
		return tipoGeneracion;
	}

	public void setTipoGeneracion(int tipoGeneracion) {
		this.tipoGeneracion = tipoGeneracion;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaEmision, numeroDocumento, tipoDocumento, tipoGeneracion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoRelacionado other = (DocumentoRelacionado) obj;
		return Objects.equals(fechaEmision, other.fechaEmision)
				&& Objects.equals(numeroDocumento, other.numeroDocumento)
				&& Objects.equals(tipoDocumento, other.tipoDocumento) && tipoGeneracion == other.tipoGeneracion;
	}

	@Override
	public String toString() {
		return "DocumentoRelacionado [tipoDocumento=" + tipoDocumento + ", tipoGeneracion=" + tipoGeneracion
				+ ", numeroDocumento=" + numeroDocumento + ", fechaEmision=" + fechaEmision + "]";
	}

}
