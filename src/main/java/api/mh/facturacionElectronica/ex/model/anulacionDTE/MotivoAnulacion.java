package api.mh.facturacionElectronica.ex.model.anulacionDTE;

import java.util.Objects;

public class MotivoAnulacion {

	public int tipoAnulacion;
	public String motivoAnulacion;
	public String nombreResponsable;
	public String tipDocResponsable;
	public String numDocResponsable;
	public String nombreSolicita;
	public String tipDocSolicita;
	public String numDocSolicita;

	public int getTipoAnulacion() {
		return tipoAnulacion;
	}

	public void setTipoAnulacion(int tipoAnulacion) {
		this.tipoAnulacion = tipoAnulacion;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getTipDocResponsable() {
		return tipDocResponsable;
	}

	public void setTipDocResponsable(String tipDocResponsable) {
		this.tipDocResponsable = tipDocResponsable;
	}

	public String getNumDocResponsable() {
		return numDocResponsable;
	}

	public void setNumDocResponsable(String numDocResponsable) {
		this.numDocResponsable = numDocResponsable;
	}

	public String getNombreSolicita() {
		return nombreSolicita;
	}

	public void setNombreSolicita(String nombreSolicita) {
		this.nombreSolicita = nombreSolicita;
	}

	public String getTipDocSolicita() {
		return tipDocSolicita;
	}

	public void setTipDocSolicita(String tipDocSolicita) {
		this.tipDocSolicita = tipDocSolicita;
	}

	public String getNumDocSolicita() {
		return numDocSolicita;
	}

	public void setNumDocSolicita(String numDocSolicita) {
		this.numDocSolicita = numDocSolicita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(motivoAnulacion, nombreResponsable, nombreSolicita, numDocResponsable, numDocSolicita,
				tipDocResponsable, tipDocSolicita, tipoAnulacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotivoAnulacion other = (MotivoAnulacion) obj;
		return Objects.equals(motivoAnulacion, other.motivoAnulacion)
				&& Objects.equals(nombreResponsable, other.nombreResponsable)
				&& Objects.equals(nombreSolicita, other.nombreSolicita)
				&& Objects.equals(numDocResponsable, other.numDocResponsable)
				&& Objects.equals(numDocSolicita, other.numDocSolicita)
				&& Objects.equals(tipDocResponsable, other.tipDocResponsable)
				&& Objects.equals(tipDocSolicita, other.tipDocSolicita) && tipoAnulacion == other.tipoAnulacion;
	}

	@Override
	public String toString() {
		return "MotivoAnulacion [tipoAnulacion=" + tipoAnulacion + ", motivoAnulacion=" + motivoAnulacion
				+ ", nombreResponsable=" + nombreResponsable + ", tipDocResponsable=" + tipDocResponsable
				+ ", numDocResponsable=" + numDocResponsable + ", nombreSolicita=" + nombreSolicita
				+ ", tipDocSolicita=" + tipDocSolicita + ", numDocSolicita=" + numDocSolicita + "]";
	}

}
