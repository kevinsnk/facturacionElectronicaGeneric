package api.mh.facturacionElectronica.ex.model;

import java.util.Date;
import java.util.Objects;

public class FeContingencia {

	public int code;
	public int uContingencia;
	public String uDesContingencia;
	public String uMotivo;
	public Date uFechaIni;
	public String uHoraIni;
	public Date uFechaFin;
	public String uHoraFin;
	public String uESelleRecepcion;
	public String uJsonContingencia;
	public String uEstado;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getuContingencia() {
		return uContingencia;
	}

	public void setuContingencia(int uContingencia) {
		this.uContingencia = uContingencia;
	}

	public String getuDesContingencia() {
		return uDesContingencia;
	}

	public void setuDesContingencia(String uDesContingencia) {
		this.uDesContingencia = uDesContingencia;
	}

	public String getuMotivo() {
		return uMotivo;
	}

	public void setuMotivo(String uMotivo) {
		this.uMotivo = uMotivo;
	}

	public Date getuFechaIni() {
		return uFechaIni;
	}

	public void setuFechaIni(Date uFechaIni) {
		this.uFechaIni = uFechaIni;
	}

	public String getuHoraIni() {
		return uHoraIni;
	}

	public void setuHoraIni(String uHoraIni) {
		this.uHoraIni = uHoraIni;
	}

	public Date getuFechaFin() {
		return uFechaFin;
	}

	public void setuFechaFin(Date uFechaFin) {
		this.uFechaFin = uFechaFin;
	}

	public String getuHoraFin() {
		return uHoraFin;
	}

	public void setuHoraFin(String uHoraFin) {
		this.uHoraFin = uHoraFin;
	}

	public String getuESelleRecepcion() {
		return uESelleRecepcion;
	}

	public void setuESelleRecepcion(String uESelleRecepcion) {
		this.uESelleRecepcion = uESelleRecepcion;
	}

	public String getuJsonContingencia() {
		return uJsonContingencia;
	}

	public void setuJsonContingencia(String uJsonContingencia) {
		this.uJsonContingencia = uJsonContingencia;
	}

	public String getuEstado() {
		return uEstado;
	}

	public void setuEstado(String uEstado) {
		this.uEstado = uEstado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, uContingencia, uDesContingencia, uESelleRecepcion, uEstado, uFechaFin, uFechaIni,
				uHoraFin, uHoraIni, uJsonContingencia, uMotivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeContingencia other = (FeContingencia) obj;
		return code == other.code && uContingencia == other.uContingencia
				&& Objects.equals(uDesContingencia, other.uDesContingencia)
				&& Objects.equals(uESelleRecepcion, other.uESelleRecepcion) && Objects.equals(uEstado, other.uEstado)
				&& Objects.equals(uFechaFin, other.uFechaFin) && Objects.equals(uFechaIni, other.uFechaIni)
				&& Objects.equals(uHoraFin, other.uHoraFin) && Objects.equals(uHoraIni, other.uHoraIni)
				&& Objects.equals(uJsonContingencia, other.uJsonContingencia) && Objects.equals(uMotivo, other.uMotivo);
	}

	@Override
	public String toString() {
		return "FeContingencia [code=" + code + ", uContingencia=" + uContingencia + ", uDesContingencia="
				+ uDesContingencia + ", uMotivo=" + uMotivo + ", uFechaIni=" + uFechaIni + ", uHoraIni=" + uHoraIni
				+ ", uFechaFin=" + uFechaFin + ", uHoraFin=" + uHoraFin + ", uESelleRecepcion=" + uESelleRecepcion
				+ ", uJsonContingencia=" + uJsonContingencia + ", uEstado=" + uEstado + "]";
	}

}
