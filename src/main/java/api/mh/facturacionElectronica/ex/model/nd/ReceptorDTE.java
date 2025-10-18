package api.mh.facturacionElectronica.ex.model.nd;

import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.DireccionDTE;

public class ReceptorDTE {

	public String nit;
	public String nrc;
	public String nombre;
	public String codActividad;
	public String descActividad;
	public String nombreComercial;
	public DireccionDTE direccion;
	public String telefono;
	public String correo;

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

	@Override
	public int hashCode() {
		return Objects.hash(codActividad, correo, descActividad, direccion, nit, nombre, nombreComercial, nrc,
				telefono);
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
		return Objects.equals(codActividad, other.codActividad) && Objects.equals(correo, other.correo)
				&& Objects.equals(descActividad, other.descActividad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nit, other.nit) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nombreComercial, other.nombreComercial) && Objects.equals(nrc, other.nrc)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "ReceptorDTE [nit=" + nit + ", nrc=" + nrc + ", nombre=" + nombre + ", codActividad=" + codActividad
				+ ", descActividad=" + descActividad + ", nombreComercial=" + nombreComercial + ", direccion="
				+ direccion + ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}
