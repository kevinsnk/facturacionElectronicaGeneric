package api.mh.facturacionElectronica.ex.model.fex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class CuerpoDocumentoDTE {

	public int numItem;
	public int cantidad;
	public String codigo;
	public int uniMedida;
	public String descripcion;
	public BigDecimal precioUni;
	public BigDecimal montoDescu;
	public BigDecimal ventaGravada;
	public ArrayList<String> tributos;
	public BigDecimal noGravado;

	public int getNumItem() {
		return numItem;
	}

	public void setNumItem(int numItem) {
		this.numItem = numItem;
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

	public BigDecimal getVentaGravada() {
		return ventaGravada;
	}

	public void setVentaGravada(BigDecimal ventaGravada) {
		this.ventaGravada = ventaGravada;
	}

	public ArrayList<String> getTributos() {
		return tributos;
	}

	public void setTributos(ArrayList<String> tributos) {
		this.tributos = tributos;
	}

	public BigDecimal getNoGravado() {
		return noGravado;
	}

	public void setNoGravado(BigDecimal noGravado) {
		this.noGravado = noGravado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codigo, descripcion, montoDescu, noGravado, numItem, precioUni, tributos,
				uniMedida, ventaGravada);
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
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(montoDescu, other.montoDescu)
				&& Objects.equals(noGravado, other.noGravado) && numItem == other.numItem
				&& Objects.equals(precioUni, other.precioUni) && Objects.equals(tributos, other.tributos)
				&& uniMedida == other.uniMedida && Objects.equals(ventaGravada, other.ventaGravada);
	}

	@Override
	public String toString() {
		return "CuerpoDocumentoDTE [numItem=" + numItem + ", cantidad=" + cantidad + ", codigo=" + codigo
				+ ", uniMedida=" + uniMedida + ", descripcion=" + descripcion + ", precioUni=" + precioUni
				+ ", montoDescu=" + montoDescu + ", ventaGravada=" + ventaGravada + ", tributos=" + tributos
				+ ", noGravado=" + noGravado + "]";
	}

}