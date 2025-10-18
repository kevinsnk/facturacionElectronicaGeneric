package api.mh.facturacionElectronica.ex.model.fex;

import java.util.Objects;

public class ReceptorDTE {

	public String nombre;
	public String tipoDocumento;
	public String numDocumento;
	public String nombreComercial;
	public String codPais;
	public String nombrePais;
	public String complemento;
	public int tipoPersona;
	public String descActividad;
	public String telefono;
	public String correo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getDescActividad() {
		return descActividad;
	}

	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
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
		return Objects.hash(codPais, complemento, correo, descActividad, nombre, nombreComercial, nombrePais,
				numDocumento, telefono, tipoDocumento, tipoPersona);
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
		return Objects.equals(codPais, other.codPais) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(correo, other.correo) && Objects.equals(descActividad, other.descActividad)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreComercial, other.nombreComercial)
				&& Objects.equals(nombrePais, other.nombrePais) && Objects.equals(numDocumento, other.numDocumento)
				&& Objects.equals(telefono, other.telefono) && Objects.equals(tipoDocumento, other.tipoDocumento)
				&& tipoPersona == other.tipoPersona;
	}

	@Override
	public String toString() {
		return "ReceptorDTE [nombre=" + nombre + ", tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento
				+ ", nombreComercial=" + nombreComercial + ", codPais=" + codPais + ", nombrePais=" + nombrePais
				+ ", complemento=" + complemento + ", tipoPersona=" + tipoPersona + ", descActividad=" + descActividad
				+ ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}