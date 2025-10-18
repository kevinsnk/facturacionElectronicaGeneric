package api.mh.facturacionElectronica.ex.model.nc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class CuerpoDocumentoDTE {

	public int numItem;
	public int tipoItem;
	public String numeroDocumento;
	public String codigo;
	public String codTributo;
	public String descripcion;
	public int cantidad;
	public int uniMedida;
	public BigDecimal precioUni;
	public BigDecimal montoDescu;
	public BigDecimal ventaNoSuj;
	public BigDecimal ventaExenta;
	public BigDecimal ventaGravada;
	public ArrayList<String> tributos;

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

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(String codTributo) {
		this.codTributo = codTributo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(int uniMedida) {
		this.uniMedida = uniMedida;
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

	public BigDecimal getVentaNoSuj() {
		return ventaNoSuj;
	}

	public void setVentaNoSuj(BigDecimal ventaNoSuj) {
		this.ventaNoSuj = ventaNoSuj;
	}

	public BigDecimal getVentaExenta() {
		return ventaExenta;
	}

	public void setVentaExenta(BigDecimal ventaExenta) {
		this.ventaExenta = ventaExenta;
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

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codTributo, codigo, descripcion, montoDescu, numItem, numeroDocumento, precioUni,
				tipoItem, tributos, uniMedida, ventaExenta, ventaGravada, ventaNoSuj);
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
		return cantidad == other.cantidad && Objects.equals(codTributo, other.codTributo)
				&& Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(montoDescu, other.montoDescu) && numItem == other.numItem
				&& Objects.equals(numeroDocumento, other.numeroDocumento) && Objects.equals(precioUni, other.precioUni)
				&& tipoItem == other.tipoItem && Objects.equals(tributos, other.tributos)
				&& uniMedida == other.uniMedida && Objects.equals(ventaExenta, other.ventaExenta)
				&& Objects.equals(ventaGravada, other.ventaGravada) && Objects.equals(ventaNoSuj, other.ventaNoSuj);
	}

	@Override
	public String toString() {
		return "CuerpoDocumentoDTE [numItem=" + numItem + ", tipoItem=" + tipoItem + ", numeroDocumento="
				+ numeroDocumento + ", codigo=" + codigo + ", codTributo=" + codTributo + ", descripcion=" + descripcion
				+ ", cantidad=" + cantidad + ", uniMedida=" + uniMedida + ", precioUni=" + precioUni + ", montoDescu="
				+ montoDescu + ", ventaNoSuj=" + ventaNoSuj + ", ventaExenta=" + ventaExenta + ", ventaGravada="
				+ ventaGravada + ", tributos=" + tributos + "]";
	}

}