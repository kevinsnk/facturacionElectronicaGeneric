package api.mh.facturacionElectronica.ex.model.fse;

import java.math.BigDecimal;
import java.util.Objects;

public class CuerpoDocumentoDTE {

	public int numItem;
	public int tipoItem;
	public int cantidad;
	public String codigo;
	public int uniMedida;
	public String descripcion;
	public BigDecimal precioUni;
	public BigDecimal montoDescu;
	public BigDecimal compra;

	public int getNumItem() {
		return numItem;
	}

	public void setNumItem(int numItem) {
		this.numItem = numItem;
	}

	public int getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(int tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(int uniMedida) {
		this.uniMedida = uniMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioUni() {
		return precioUni;
	}

	public void setPrecioUni(BigDecimal precioUni) {
		this.precioUni = precioUni;
	}

	public BigDecimal getMontoDescu() {
		return montoDescu;
	}

	public void setMontoDescu(BigDecimal montoDescu) {
		this.montoDescu = montoDescu;
	}

	public BigDecimal getCompra() {
		return compra;
	}

	public void setCompra(BigDecimal compra) {
		this.compra = compra;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codigo, compra, descripcion, montoDescu, numItem, precioUni, tipoItem, uniMedida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuerpoDocumentoDTE other = (CuerpoDocumentoDTE) obj;
		return cantidad == other.cantidad && Objects.equals(codigo, other.codigo)
				&& Objects.equals(compra, other.compra) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(montoDescu, other.montoDescu) && numItem == other.numItem
				&& Objects.equals(precioUni, other.precioUni) && tipoItem == other.tipoItem
				&& uniMedida == other.uniMedida;
	}

	@Override
	public String toString() {
		return "CuerpoDocumentoDTE [numItem=" + numItem + ", tipoItem=" + tipoItem + ", cantidad=" + cantidad
				+ ", codigo=" + codigo + ", uniMedida=" + uniMedida + ", descripcion=" + descripcion + ", precioUni="
				+ precioUni + ", montoDescu=" + montoDescu + ", compra=" + compra + "]";
	}

}