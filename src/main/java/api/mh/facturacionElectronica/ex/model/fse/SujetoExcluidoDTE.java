package api.mh.facturacionElectronica.ex.model.fse;

import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.DireccionDTE;

public class SujetoExcluidoDTE {

	public String tipoDocumento;
	public String numDocumento;
	public String nombre;
	public String codActividad;
	public String descActividad;
	public DireccionDTE direccion;
	public String telefono;
	public String correo;

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
		return Objects.hash(codActividad, correo, descActividad, direccion, nombre, numDocumento, telefono,
				tipoDocumento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SujetoExcluidoDTE other = (SujetoExcluidoDTE) obj;
		return Objects.equals(codActividad, other.codActividad) && Objects.equals(correo, other.correo)
				&& Objects.equals(descActividad, other.descActividad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(numDocumento, other.numDocumento)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(tipoDocumento, other.tipoDocumento);
	}

	@Override
	public String toString() {
		return "SujeroExcluidoDTE [tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", nombre="
				+ nombre + ", codActividad=" + codActividad + ", descActividad=" + descActividad + ", direccion="
				+ direccion + ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}
