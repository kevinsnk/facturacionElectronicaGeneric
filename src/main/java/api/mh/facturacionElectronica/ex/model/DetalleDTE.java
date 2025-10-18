package api.mh.facturacionElectronica.ex.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DetalleDTE {
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
	public BigDecimal psv;
	public BigDecimal noGravado;
	public BigDecimal ivaItem;
	//Variable para validar si documento esta exento
	public String taxCode;

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

	public BigDecimal getPsv() {
		return psv;
	}

	public void setPsv(BigDecimal psv) {
		this.psv = psv;
	}

	public BigDecimal getNoGravado() {
		return noGravado;
	}

	public void setNoGravado(BigDecimal noGravado) {
		this.noGravado = noGravado;
	}

	public BigDecimal getIvaItem() {
		return ivaItem;
	}

	public void setIvaItem(BigDecimal ivaItem) {
		this.ivaItem = ivaItem;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Override
	public String toString() {
		return "DetalleDTE [numItem=" + numItem + ", tipoItem=" + tipoItem + ", numeroDocumento=" + numeroDocumento
				+ ", codigo=" + codigo + ", codTributo=" + codTributo + ", descripcion=" + descripcion + ", cantidad="
				+ cantidad + ", uniMedida=" + uniMedida + ", precioUni=" + precioUni + ", montoDescu=" + montoDescu
				+ ", ventaNoSuj=" + ventaNoSuj + ", ventaExenta=" + ventaExenta + ", ventaGravada=" + ventaGravada
				+ ", tributos=" + tributos + ", psv=" + psv + ", noGravado=" + noGravado + ", ivaItem=" + ivaItem + "]";
	}
}
