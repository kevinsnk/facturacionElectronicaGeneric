package api.mh.facturacionElectronica.ex.model;

import java.util.Objects;

public class DatosReceptor {
	public String nit;
	public String nrc;
	public String nombre;
	public String codActividad;
	public String descActividad;
	public String nombreComercial;
	// public String tipoEstablecimiento;
	public DireccionDTE direccion;
	public String telefono;
	public String correo;

	// Variables para Sujeto Excluido
	public String tipoDocumento;
	public String numDocumento;
	public String codPais;
	public String nombrePais;
	public int tipoPersona;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}

	public String getDescActividad() {
		return descActividad;
	}

	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public DireccionDTE getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTE direccion) {
		this.direccion = direccion;
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

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public int getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codActividad, codPais, correo, descActividad, direccion, nit, nombre, nombreComercial,
				nombrePais, nrc, numDocumento, telefono, tipoDocumento, tipoPersona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatosReceptor other = (DatosReceptor) obj;
		return Objects.equals(codActividad, other.codActividad) && Objects.equals(codPais, other.codPais)
				&& Objects.equals(correo, other.correo) && Objects.equals(descActividad, other.descActividad)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(nit, other.nit)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreComercial, other.nombreComercial)
				&& Objects.equals(nombrePais, other.nombrePais) && Objects.equals(nrc, other.nrc)
				&& Objects.equals(numDocumento, other.numDocumento) && Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoDocumento, other.tipoDocumento) && Objects.equals(tipoPersona, other.tipoPersona);
	}

	@Override
	public String toString() {
		return "DatosReceptor [nit=" + nit + ", nrc=" + nrc + ", nombre=" + nombre + ", codActividad=" + codActividad
				+ ", descActividad=" + descActividad + ", nombreComercial=" + nombreComercial + ", direccion="
				+ direccion + ", telefono=" + telefono + ", correo=" + correo + ", tipoDocumento=" + tipoDocumento
				+ ", numDocumento=" + numDocumento + ", codPais=" + codPais + ", nombrePais=" + nombrePais
				+ ", tipoPersona=" + tipoPersona + "]";
	}

}
