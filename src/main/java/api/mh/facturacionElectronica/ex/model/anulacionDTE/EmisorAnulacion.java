package api.mh.facturacionElectronica.ex.model.anulacionDTE;

import java.util.Objects;

public class EmisorAnulacion {

	public String nit;
	public String nombre;
	public String tipoEstablecimiento;
	public String nomEstablecimiento;
	public String codEstableMH;
	public String codEstable;
	public String codPuntoVentaMH;
	public String codPuntoVenta;
	public String telefono;
	public String correo;

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

	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}

	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
	}

	public String getNomEstablecimiento() {
		return nomEstablecimiento;
	}

	public void setNomEstablecimiento(String nomEstablecimiento) {
		this.nomEstablecimiento = nomEstablecimiento;
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
		return Objects.hash(codEstable, codEstableMH, codPuntoVenta, codPuntoVentaMH, correo, nit, nomEstablecimiento,
				nombre, telefono, tipoEstablecimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmisorAnulacion other = (EmisorAnulacion) obj;
		return Objects.equals(codEstable, other.codEstable) && Objects.equals(codEstableMH, other.codEstableMH)
				&& Objects.equals(codPuntoVenta, other.codPuntoVenta)
				&& Objects.equals(codPuntoVentaMH, other.codPuntoVentaMH) && Objects.equals(correo, other.correo)
				&& Objects.equals(nit, other.nit) && Objects.equals(nomEstablecimiento, other.nomEstablecimiento)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono)
				&& Objects.equals(tipoEstablecimiento, other.tipoEstablecimiento);
	}

	@Override
	public String toString() {
		return "EmisorAnulacion [nit=" + nit + ", nombre=" + nombre + ", tipoEstablecimiento=" + tipoEstablecimiento
				+ ", nomEstablecimiento=" + nomEstablecimiento + ", codEstableMH=" + codEstableMH + ", codEstable="
				+ codEstable + ", codPuntoVentaMH=" + codPuntoVentaMH + ", codPuntoVenta=" + codPuntoVenta
				+ ", telefono=" + telefono + ", correo=" + correo + "]";
	}

}
