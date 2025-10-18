package api.mh.facturacionElectronica.model;

import java.util.Arrays;
import java.util.Objects;

public class RecepcionDTEResponse {

	public int version;
	public String ambiente;
	public int versionApp;
	public String estado;
	public String codigoGeneracion;
	public String selloRecibido;
	public String fhProcesamiento;
	public String clasificaMsg;
	public String codigoMsg;
	public String descripcionMsg;
	public String[] observaciones;

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

	public int getVersionApp() {
		return versionApp;
	}

	public void setVersionApp(int versionApp) {
		this.versionApp = versionApp;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoGeneracion() {
		return codigoGeneracion;
	}

	public void setCodigoGeneracion(String codigoGeneracion) {
		this.codigoGeneracion = codigoGeneracion;
	}

	public String getSelloRecibido() {
		return selloRecibido;
	}

	public void setSelloRecibido(String selloRecibido) {
		this.selloRecibido = selloRecibido;
	}

	public String getFhProcesamiento() {
		return fhProcesamiento;
	}

	public void setFhProcesamiento(String fhProcesamiento) {
		this.fhProcesamiento = fhProcesamiento;
	}

	public String getClasificaMsg() {
		return clasificaMsg;
	}

	public void setClasificaMsg(String clasificaMsg) {
		this.clasificaMsg = clasificaMsg;
	}

	public String getCodigoMsg() {
		return codigoMsg;
	}

	public void setCodigoMsg(String codigoMsg) {
		this.codigoMsg = codigoMsg;
	}

	public String getDescripcionMsg() {
		return descripcionMsg;
	}

	public void setDescripcionMsg(String descripcionMsg) {
		this.descripcionMsg = descripcionMsg;
	}

	public String[] getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String[] observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(observaciones);
		result = prime * result + Objects.hash(ambiente, clasificaMsg, codigoGeneracion, codigoMsg, descripcionMsg,
				estado, fhProcesamiento, selloRecibido, version, versionApp);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecepcionDTEResponse other = (RecepcionDTEResponse) obj;
		return Objects.equals(ambiente, other.ambiente) && Objects.equals(clasificaMsg, other.clasificaMsg)
				&& Objects.equals(codigoGeneracion, other.codigoGeneracion)
				&& Objects.equals(codigoMsg, other.codigoMsg) && Objects.equals(descripcionMsg, other.descripcionMsg)
				&& Objects.equals(estado, other.estado) && Objects.equals(fhProcesamiento, other.fhProcesamiento)
				&& Arrays.equals(observaciones, other.observaciones)
				&& Objects.equals(selloRecibido, other.selloRecibido) && version == other.version
				&& versionApp == other.versionApp;
	}

	@Override
	public String toString() {
		return "RecepcionDTEResponse [version=" + version + ", ambiente=" + ambiente + ", versionApp=" + versionApp
				+ ", estado=" + estado + ", codigoGeneracion=" + codigoGeneracion + ", selloRecibido=" + selloRecibido
				+ ", fhProcesamiento=" + fhProcesamiento + ", clasificaMsg=" + clasificaMsg + ", codigoMsg=" + codigoMsg
				+ ", descripcionMsg=" + descripcionMsg + ", observaciones=" + Arrays.toString(observaciones) + "]";
	}

}
