package api.mh.facturacionElectronica.model;

import java.util.Arrays;

public class ContingenciaResponse {

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
	public String toString() {
		return "ContingenciaResponse [version=" + version + ", ambiente=" + ambiente + ", versionApp=" + versionApp
				+ ", estado=" + estado + ", codigoGeneracion=" + codigoGeneracion + ", selloRecibido=" + selloRecibido
				+ ", fhProcesamiento=" + fhProcesamiento + ", clasificaMsg=" + clasificaMsg + ", codigoMsg=" + codigoMsg
				+ ", descripcionMsg=" + descripcionMsg + ", observaciones=" + Arrays.toString(observaciones) + "]";
	}

}
