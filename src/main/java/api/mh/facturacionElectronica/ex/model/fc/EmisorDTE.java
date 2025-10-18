package api.mh.facturacionElectronica.ex.model.fc;

import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.DireccionDTE;

public class EmisorDTE {

	public String nit;
	public String nrc;
	public String nombre;
	public String codActividad;
	public String descActividad;
	public String nombreComercial;
	public String tipoEstablecimiento;
	public DireccionDTE direccion;
	public String telefono;
	public String correo;
	public String codEstableMH;
	public String codEstable;
	public String codPuntoVentaMH;
	public String codPuntoVenta;

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

	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}

	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
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

	public String getCodEstableMH() {
		return codEstableMH;
	}

	public void setCodEstableMH(String codEstableMH) {
		this.codEstableMH = codEstableMH;
	}

	public String getCodEstable() {
		return codEstable;
	}

	public void setCodEstable(String codEstable) {
		this.codEstable = codEstable;
	}

	public String getCodPuntoVentaMH() {
		return codPuntoVentaMH;
	}

	public void setCodPuntoVentaMH(String codPuntoVentaMH) {
		this.codPuntoVentaMH = codPuntoVentaMH;
	}

	public String getCodPuntoVenta() {
		return codPuntoVenta;
	}

	public void setCodPuntoVenta(String codPuntoVenta) {
		this.codPuntoVenta = codPuntoVenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codActividad, codEstable, codEstableMH, codPuntoVenta, codPuntoVentaMH, correo,
				descActividad, direccion, nit, nombre, nombreComercial, nrc, telefono, tipoEstablecimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmisorDTE other = (EmisorDTE) obj;
		return Objects.equals(codActividad, other.codActividad) && Objects.equals(codEstable, other.codEstable)
				&& Objects.equals(codEstableMH, other.codEstableMH)
				&& Objects.equals(codPuntoVenta, other.codPuntoVenta)
				&& Objects.equals(codPuntoVentaMH, other.codPuntoVentaMH) && Objects.equals(correo, other.correo)
				&& Objects.equals(descActividad, other.descActividad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nit, other.nit) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nombreComercial, other.nombreComercial) && Objects.equals(nrc, other.nrc)
				&& Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoEstablecimiento, other.tipoEstablecimiento);
	}

	@Override
	public String toString() {
		return "EmisorDTE [nit=" + nit + ", nrc=" + nrc + ", nombre=" + nombre + ", codActividad=" + codActividad
				+ ", descActividad=" + descActividad + ", nombreComercial=" + nombreComercial + ", tipoEstablecimiento="
				+ tipoEstablecimiento + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo
				+ ", codEstableMH=" + codEstableMH + ", codEstable=" + codEstable + ", codPuntoVentaMH="
				+ codPuntoVentaMH + ", codPuntoVenta=" + codPuntoVenta + "]";
	}

}
