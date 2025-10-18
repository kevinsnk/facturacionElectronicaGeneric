package api.mh.facturacionElectronica.ex.model.fex;

import java.math.BigDecimal;
import java.util.Objects;

public class IdentificacionDTE {

	public int version;
	public String ambiente;
	public String tipoDte;
	public String numeroControl;
	public String codigoGeneracion;
	public int tipoModelo;
	public int tipoOperacion;
	public BigDecimal tipoContingencia;
	public String motivoContigencia;
	public String fecEmi;
	public String horEmi;
	public String tipoMoneda;

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

	public String getTipoDte() {
		return tipoDte;
	}

	public void setTipoDte(String tipoDte) {
		this.tipoDte = tipoDte;
	}

	public String getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}

	public String getCodigoGeneracion() {
		return codigoGeneracion;
	}

	public void setCodigoGeneracion(String codigoGeneracion) {
		this.codigoGeneracion = codigoGeneracion;
	}

	public int getTipoModelo() {
		return tipoModelo;
	}

	public void setTipoModelo(int tipoModelo) {
		this.tipoModelo = tipoModelo;
	}

	public int getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public BigDecimal getTipoContingencia() {
		return tipoContingencia;
	}

	public void setTipoContingencia(BigDecimal tipoContingencia) {
		this.tipoContingencia = tipoContingencia;
	}

	public String getMotivoContingencia() {
		return motivoContigencia;
	}

	public void setMotivoContingencia(String motivoContigencia) {
		this.motivoContigencia = motivoContigencia;
	}

	public String getFecEmi() {
		return fecEmi;
	}

	public void setFecEmi(String fecEmi) {
		this.fecEmi = fecEmi;
	}

	public String getHorEmi() {
		return horEmi;
	}

	public void setHorEmi(String horEmi) {
		this.horEmi = horEmi;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ambiente, codigoGeneracion, fecEmi, horEmi, motivoContigencia, numeroControl,
				tipoContingencia, tipoDte, tipoModelo, tipoMoneda, tipoOperacion, version);
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
				&& Objects.equals(fecEmi, other.fecEmi) && Objects.equals(horEmi, other.horEmi)
				&& Objects.equals(motivoContigencia, other.motivoContigencia)
				&& Objects.equals(numeroControl, other.numeroControl) && tipoContingencia == other.tipoContingencia
				&& Objects.equals(tipoDte, other.tipoDte) && tipoModelo == other.tipoModelo
				&& Objects.equals(tipoMoneda, other.tipoMoneda) && tipoOperacion == other.tipoOperacion
				&& version == other.version;
	}

	@Override
	public String toString() {
		return "IdentificacionDTE [version=" + version + ", ambiente=" + ambiente + ", tipoDte=" + tipoDte
				+ ", numeroControl=" + numeroControl + ", codigoGeneracion=" + codigoGeneracion + ", tipoModelo="
				+ tipoModelo + ", tipoOperacion=" + tipoOperacion + ", tipoContingencia=" + tipoContingencia
				+ ", motivoContin=" + motivoContigencia + ", fecEmi=" + fecEmi + ", horEmi=" + horEmi + ", tipoMoneda="
				+ tipoMoneda + "]";
	}

}
