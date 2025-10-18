package api.mh.facturacionElectronica.ex.model.anulacionDTE;

import java.math.BigDecimal;
import java.util.Objects;

public class DocumentoAnulacion {

	public String tipoDte;
	public String codigoGeneracion;
	public String selloRecibido;
	public String numeroControl;
	public String fecEmi;
	public BigDecimal montoIva;
	public String codigoGeneracionR;
	public String tipoDocumento;
	public String numDocumento;
	public String nombre;
	public String telefono;
	public String correo;

	public String getTipoDte() {
		return tipoDte;
	}

	public void setTipoDte(String tipoDte) {
		this.tipoDte = tipoDte;
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

	public String getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}

	public String getFecEmi() {
		return fecEmi;
	}

	public void setFecEmi(String fecEmi) {
		this.fecEmi = fecEmi;
	}

	public BigDecimal getMontoIva() {
		return montoIva;
	}

	public void setMontoIva(BigDecimal montoIva) {
		this.montoIva = montoIva;
	}

	public String getCodigoGeneracionR() {
		return codigoGeneracionR;
	}

	public void setCodigoGeneracionR(String codigoGeneracionR) {
		this.codigoGeneracionR = codigoGeneracionR;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoGeneracion, codigoGeneracionR, correo, fecEmi, montoIva, nombre, numDocumento,
				numeroControl, selloRecibido, telefono, tipoDocumento, tipoDte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoAnulacion other = (DocumentoAnulacion) obj;
		return Objects.equals(codigoGeneracion, other.codigoGeneracion)
				&& Objects.equals(codigoGeneracionR, other.codigoGeneracionR) && Objects.equals(correo, other.correo)
				&& Objects.equals(fecEmi, other.fecEmi) && Objects.equals(montoIva, other.montoIva)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(numDocumento, other.numDocumento)
				&& Objects.equals(numeroControl, other.numeroControl)
				&& Objects.equals(selloRecibido, other.selloRecibido) && Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoDocumento, other.tipoDocumento) && Objects.equals(tipoDte, other.tipoDte);
	}

	@Override
	public String toString() {
		return "DocumentoAnulacion [tipoDte=" + tipoDte + ", codigoGeneracion=" + codigoGeneracion + ", selloRecibido="
				+ selloRecibido + ", numeroControl=" + numeroControl + ", fecEmi=" + fecEmi + ", montoIva=" + montoIva
				+ ", codigoGeneracionR=" + codigoGeneracionR + ", tipoDocumento=" + tipoDocumento + ", numDocumento="
				+ numDocumento + ", nombre=" + nombre + ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}
