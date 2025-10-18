package api.mh.facturacionElectronica.ex.model.contingencia;

import java.util.Objects;

public class Motivo {

	public String fInicio;
	public String fFin;
	public String hInicio;
	public String hFin;
	public int tipoContingencia;
	public String motivoContingencia;

	public String getfInicio() {
		return fInicio;
	}

	public void setfInicio(String fInicio) {
		this.fInicio = fInicio;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	public String gethInicio() {
		return hInicio;
	}

	public void sethInicio(String hInicio) {
		this.hInicio = hInicio;
	}

	public String gethFin() {
		return hFin;
	}

	public void sethFin(String hFin) {
		this.hFin = hFin;
	}

	public int getTipoContingencia() {
		return tipoContingencia;
	}

	public void setTipoContingencia(int tipoContingencia) {
		this.tipoContingencia = tipoContingencia;
	}

	public String getMotivoContingencia() {
		return motivoContingencia;
	}

	public void setMotivoContingencia(String motivoContingencia) {
		this.motivoContingencia = motivoContingencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fFin, fInicio, hFin, hInicio, motivoContingencia, tipoContingencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motivo other = (Motivo) obj;
		return Objects.equals(fFin, other.fFin) && Objects.equals(fInicio, other.fInicio)
				&& Objects.equals(hFin, other.hFin) && Objects.equals(hInicio, other.hInicio)
				&& Objects.equals(motivoContingencia, other.motivoContingencia)
				&& tipoContingencia == other.tipoContingencia;
	}

	@Override
	public String toString() {
		return "Motivo [fInicio=" + fInicio + ", fFin=" + fFin + ", hInicio=" + hInicio + ", hFin=" + hFin
				+ ", tipoContingencia=" + tipoContingencia + ", motivoContingencia=" + motivoContingencia + "]";
	}

}
