package api.mh.facturacionElectronica.ex.model.contingencia;

import java.util.Objects;

public class EmisorDTE {

	public String nit;
	public String nombre;
	public String nombreResponsable;
	public String tipoDocResponsable;
	public String numeroDocResponsable;
	public String tipoEstablecimiento;
	public String telefono;
	public String correo;
	public String codEstableMH;
	public String codPuntoVenta;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getTipoDocResponsable() {
		return tipoDocResponsable;
	}

	public void setTipoDocResponsable(String tipoDocResponsable) {
		this.tipoDocResponsable = tipoDocResponsable;
	}

	public String getNumeroDocResponsable() {
		return numeroDocResponsable;
	}

	public void setNumeroDocResponsable(String numeroDocResponsable) {
		this.numeroDocResponsable = numeroDocResponsable;
	}

	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}

	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
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

	public String getCodPuntoVenta() {
		return codPuntoVenta;
	}

	public void setCodPuntoVenta(String codPuntoVenta) {
		this.codPuntoVenta = codPuntoVenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEstableMH, codPuntoVenta, correo, nit, nombre, nombreResponsable, numeroDocResponsable,
				telefono, tipoDocResponsable, tipoEstablecimiento);
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
		return Objects.equals(codEstableMH, other.codEstableMH) && Objects.equals(codPuntoVenta, other.codPuntoVenta)
				&& Objects.equals(correo, other.correo) && Objects.equals(nit, other.nit)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nombreResponsable, other.nombreResponsable)
				&& Objects.equals(numeroDocResponsable, other.numeroDocResponsable)
				&& Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoDocResponsable, other.tipoDocResponsable)
				&& Objects.equals(tipoEstablecimiento, other.tipoEstablecimiento);
	}

	@Override
	public String toString() {
		return "EmisorDTE [nit=" + nit + ", nombre=" + nombre + ", nombreResponsable=" + nombreResponsable
				+ ", tipoDocResponsable=" + tipoDocResponsable + ", numeroDocResponsable=" + numeroDocResponsable
				+ ", tipoEstablecimiento=" + tipoEstablecimiento + ", telefono=" + telefono + ", correo=" + correo
				+ ", codEstableMH=" + codEstableMH + ", codPuntoVenta=" + codPuntoVenta + "]";
	}

}
