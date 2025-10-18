package api.mh.facturacionElectronica.ex.model.ccf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.PagosDTE;
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
	public BigDecimal ivaPerci1;
	public BigDecimal ivaRete1;
	public BigDecimal reteRenta;
	public BigDecimal montoTotalOperacion;
	// public BigDecimal totalIva;
	public BigDecimal totalNoGravado;
	public BigDecimal totalPagar;
	public String totalLetras;
	public BigDecimal saldoFavor;
	public BigDecimal condicionOperacion;
	public ArrayList<PagosDTE>  pagos;
	public String numPagoElectronico;

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

	public BigDecimal getIvaPerci1() {
		return ivaPerci1;
	}

	public void setIvaPerci1(BigDecimal ivaPerci1) {
		this.ivaPerci1 = ivaPerci1;
	}

	public BigDecimal getIvaRete1() {
		return ivaRete1;
	}

	public void setIvaRete1(BigDecimal ivaRete1) {
		this.ivaRete1 = ivaRete1;
	}

	public BigDecimal getReteRenta() {
		return reteRenta;
	}

	public void setReteRenta(BigDecimal reteRenta) {
		this.reteRenta = reteRenta;
	}

	public BigDecimal getMontoTotalOperacion() {
		return montoTotalOperacion;
	}

	public void setMontoTotalOperacion(BigDecimal montoTotalOperacion) {
		this.montoTotalOperacion = montoTotalOperacion;
	}

//	public BigDecimal getTotalIva() {
//		return totalIva;
//	}
//
//	public void setTotalIva(BigDecimal totalIva) {
//		this.totalIva = totalIva;
//	}

	public BigDecimal getTotalNoGravado() {
		return totalNoGravado;
	}

	public void setTotalNoGravado(BigDecimal totalNoGravado) {
		this.totalNoGravado = totalNoGravado;
	}

	public BigDecimal getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(BigDecimal totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getTotalLetras() {
		return totalLetras;
	}

	public void setTotalLetras(String totalLetras) {
		this.totalLetras = totalLetras;
	}

	public BigDecimal getSaldoFavor() {
		return saldoFavor;
	}

	public void setSaldoFavor(BigDecimal saldoFavor) {
		this.saldoFavor = saldoFavor;
	}

	public BigDecimal getCondicionOperacion() {
		return condicionOperacion;
	}

	public void setCondicionOperacion(BigDecimal condicionOperacion) {
		this.condicionOperacion = condicionOperacion;
	}

	public ArrayList<PagosDTE> getPagos() {
		return pagos;
	}

	public void setPagos(ArrayList<PagosDTE> pagos) {
		this.pagos = pagos;
	}

	public String getNumPagoElectronico() {
		return numPagoElectronico;
	}

	public void setNumPagoElectronico(String numPagoElectronico) {
		this.numPagoElectronico = numPagoElectronico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condicionOperacion, descuExenta, descuGravada, descuNoSuj, ivaPerci1, ivaRete1,
				montoTotalOperacion, numPagoElectronico, pagos, porcentajeDescuento, reteRenta, saldoFavor, subTotal,
				subTotalVentas, totalDescu, totalExenta, totalGravada, totalLetras, totalNoGravado, totalNoSuj,
				totalPagar, tributos);
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
		return Objects.equals(condicionOperacion, other.condicionOperacion)
				&& Objects.equals(descuExenta, other.descuExenta) && Objects.equals(descuGravada, other.descuGravada)
				&& Objects.equals(descuNoSuj, other.descuNoSuj) && Objects.equals(ivaPerci1, other.ivaPerci1)
				&& Objects.equals(ivaRete1, other.ivaRete1)
				&& Objects.equals(montoTotalOperacion, other.montoTotalOperacion)
				&& Objects.equals(numPagoElectronico, other.numPagoElectronico) && Objects.equals(pagos, other.pagos)
				&& Objects.equals(porcentajeDescuento, other.porcentajeDescuento)
				&& Objects.equals(reteRenta, other.reteRenta) && Objects.equals(saldoFavor, other.saldoFavor)
				&& Objects.equals(subTotal, other.subTotal) && Objects.equals(subTotalVentas, other.subTotalVentas)
				&& Objects.equals(totalDescu, other.totalDescu) && Objects.equals(totalExenta, other.totalExenta)
				&& Objects.equals(totalGravada, other.totalGravada) && Objects.equals(totalLetras, other.totalLetras)
				&& Objects.equals(totalNoGravado, other.totalNoGravado) && Objects.equals(totalNoSuj, other.totalNoSuj)
				&& Objects.equals(totalPagar, other.totalPagar) && Objects.equals(tributos, other.tributos);
	}

	@Override
	public String toString() {
		return "ResumenDTE [totalNoSuj=" + totalNoSuj + ", totalExenta=" + totalExenta + ", totalGravada="
				+ totalGravada + ", subTotalVentas=" + subTotalVentas + ", descuNoSuj=" + descuNoSuj + ", descuExenta="
				+ descuExenta + ", descuGravada=" + descuGravada + ", porcentajeDescuento=" + porcentajeDescuento
				+ ", totalDescu=" + totalDescu + ", tributos=" + tributos + ", subTotal=" + subTotal + ", ivaPerci1="
				+ ivaPerci1 + ", ivaRete1=" + ivaRete1 + ", reteRenta=" + reteRenta + ", montoTotalOperacion="
				+ montoTotalOperacion + ", totalNoGravado=" + totalNoGravado + ", totalPagar=" + totalPagar
				+ ", totalLetras=" + totalLetras + ", saldoFavor=" + saldoFavor + ", condicionOperacion="
				+ condicionOperacion + ", pagos=" + pagos + ", numPagoElectronico=" + numPagoElectronico + "]";
	}

}
