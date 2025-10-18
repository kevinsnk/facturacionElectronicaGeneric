package api.mh.facturacionElectronica.ex.model.nr;

import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.DireccionDTE;

public class ReceptorDTE {

	public String tipoDocumento;
	public String numDocumento;
	public String nrc;
	public String nombre;
	public String codActividad;
	public String descActividad;
	public String nombreComercial;
	public DireccionDTE direccion;
	public String telefono;
	public String correo;
	public String bienTitulo;

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

	public String getBienTitulo() {
		return bienTitulo;
	}

	public void setBienTitulo(String bienTitulo) {
		this.bienTitulo = bienTitulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bienTitulo, codActividad, correo, descActividad, direccion, nombre, nombreComercial, nrc,
				numDocumento, telefono, tipoDocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceptorDTE other = (ReceptorDTE) obj;
		return Objects.equals(bienTitulo, other.bienTitulo) && Objects.equals(codActividad, other.codActividad)
				&& Objects.equals(correo, other.correo) && Objects.equals(descActividad, other.descActividad)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nombreComercial, other.nombreComercial) && Objects.equals(nrc, other.nrc)
				&& Objects.equals(numDocumento, other.numDocumento) && Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoDocumento, other.tipoDocumento);
	}

	@Override
	public String toString() {
		return "ReceptorDTE [tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", nrc=" + nrc
				+ ", nombre=" + nombre + ", codActividad=" + codActividad + ", descActividad=" + descActividad
				+ ", nombreComercial=" + nombreComercial + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", correo=" + correo + ", bienTitulo=" + bienTitulo + "]";
	}

}
