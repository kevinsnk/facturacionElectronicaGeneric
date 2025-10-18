package api.mh.facturacionElectronica.ex.model.nr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.TributosDTE;

public class ResumenDTE {

	public BigDecimal totalNoSuj;
	public BigDecimal totalExenta;
	public BigDecimal totalGravada;
	public BigDecimal subTotalVentas;
	public BigDecimal descuNoSuj;
	public BigDecimal descuExenta;
	public BigDecimal descuGravada;
	public BigDecimal porcentajeDescuento;
	public BigDecimal totalDescu;
	public ArrayList<TributosDTE> tributos;
	public BigDecimal subTotal;
	public BigDecimal montoTotalOperacion;
	public String totalLetras;

	public BigDecimal getTotalNoSuj() {
		return totalNoSuj;
	}

	public void setTotalNoSuj(BigDecimal totalNoSuj) {
		this.totalNoSuj = totalNoSuj;
	}

	public BigDecimal getTotalExenta() {
		return totalExenta;
	}

	public void setTotalExenta(BigDecimal totalExenta) {
		this.totalExenta = totalExenta;
	}

	public BigDecimal getTotalGravada() {
		return totalGravada;
	}

	public void setTotalGravada(BigDecimal totalGravada) {
		this.totalGravada = totalGravada;
	}

	public BigDecimal getSubTotalVentas() {
		return subTotalVentas;
	}

	public void setSubTotalVentas(BigDecimal subTotalVentas) {
		this.subTotalVentas = subTotalVentas;
	}

	public BigDecimal getDescuNoSuj() {
		return descuNoSuj;
	}

	public void setDescuNoSuj(BigDecimal descuNoSuj) {
		this.descuNoSuj = descuNoSuj;
	}

	public BigDecimal getDescuExenta() {
		return descuExenta;
	}

	public void setDescuExenta(BigDecimal descuExenta) {
		this.descuExenta = descuExenta;
	}

	public BigDecimal getDescuGravada() {
		return descuGravada;
	}

	public void setDescuGravada(BigDecimal descuGravada) {
		this.descuGravada = descuGravada;
	}

	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public BigDecimal getTotalDescu() {
		return totalDescu;
	}

	public void setTotalDescu(BigDecimal totalDescu) {
		this.totalDescu = totalDescu;
	}

	public ArrayList<TributosDTE> getTributos() {
		return tributos;
	}

	public void setTributos(ArrayList<TributosDTE> tributos) {
		this.tributos = tributos;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getMontoTotalOperacion() {
		return montoTotalOperacion;
	}

	public void setMontoTotalOperacion(BigDecimal montoTotalOperacion) {
		this.montoTotalOperacion = montoTotalOperacion;
	}

	public String getTotalLetras() {
		return totalLetras;
	}

	public void setTotalLetras(String totalLetras) {
		this.totalLetras = totalLetras;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descuExenta, descuGravada, descuNoSuj, montoTotalOperacion, porcentajeDescuento, subTotal,
				subTotalVentas, totalDescu, totalExenta, totalGravada, totalLetras, totalNoSuj, tributos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResumenDTE other = (ResumenDTE) obj;
		return Objects.equals(descuExenta, other.descuExenta) && Objects.equals(descuGravada, other.descuGravada)
				&& Objects.equals(descuNoSuj, other.descuNoSuj)
				&& Objects.equals(montoTotalOperacion, other.montoTotalOperacion)
				&& Objects.equals(porcentajeDescuento, other.porcentajeDescuento)
				&& Objects.equals(subTotal, other.subTotal) && Objects.equals(subTotalVentas, other.subTotalVentas)
				&& Objects.equals(totalDescu, other.totalDescu) && Objects.equals(totalExenta, other.totalExenta)
				&& Objects.equals(totalGravada, other.totalGravada) && Objects.equals(totalLetras, other.totalLetras)
				&& Objects.equals(totalNoSuj, other.totalNoSuj) && Objects.equals(tributos, other.tributos);
	}

	@Override
	public String toString() {
		return "ResumenDTE [totalNoSuj=" + totalNoSuj + ", totalExenta=" + totalExenta + ", totalGravada="
				+ totalGravada + ", subTotalVentas=" + subTotalVentas + ", descuNoSuj=" + descuNoSuj + ", descuExenta="
				+ descuExenta + ", descuGravada=" + descuGravada + ", porcentajeDescuento=" + porcentajeDescuento
				+ ", totalDescu=" + totalDescu + ", tributos=" + tributos + ", subTotal=" + subTotal
				+ ", montoTotalOperacion=" + montoTotalOperacion + ", totalLetras=" + totalLetras + "]";
	}

}