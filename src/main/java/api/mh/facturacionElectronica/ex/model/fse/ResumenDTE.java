package api.mh.facturacionElectronica.ex.model.fse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.PagosDTE;

public class ResumenDTE {

	public BigDecimal totalCompra;
	public BigDecimal descu;
	public BigDecimal totalDescu;
	public BigDecimal subTotal;
	public BigDecimal ivaRete1;
	public BigDecimal reteRenta;
	public BigDecimal totalPagar;
	public String totalLetras;
	public BigDecimal condicionOperacion;
	public ArrayList<PagosDTE> pagos;
	public String observaciones;

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}

	public BigDecimal getDescu() {
		return descu;
	}

	public void setDescu(BigDecimal descu) {
		this.descu = descu;
	}

	public BigDecimal getTotalDescu() {
		return totalDescu;
	}

	public void setTotalDescu(BigDecimal totalDescu) {
		this.totalDescu = totalDescu;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(condicionOperacion, descu, ivaRete1, observaciones, pagos, reteRenta, subTotal, totalCompra,
				totalDescu, totalLetras, totalPagar);
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
		return condicionOperacion == other.condicionOperacion && Objects.equals(descu, other.descu)
				&& Objects.equals(ivaRete1, other.ivaRete1) && Objects.equals(observaciones, other.observaciones)
				&& Objects.equals(pagos, other.pagos) && Objects.equals(reteRenta, other.reteRenta)
				&& Objects.equals(subTotal, other.subTotal) && Objects.equals(totalCompra, other.totalCompra)
				&& Objects.equals(totalDescu, other.totalDescu) && Objects.equals(totalLetras, other.totalLetras)
				&& Objects.equals(totalPagar, other.totalPagar);
	}

	@Override
	public String toString() {
		return "ResumenDTE [totalCompra=" + totalCompra + ", descu=" + descu + ", totalDescu=" + totalDescu
				+ ", subTotal=" + subTotal + ", ivaRete1=" + ivaRete1 + ", reteRenta=" + reteRenta + ", totalPagar="
				+ totalPagar + ", totalLetras=" + totalLetras + ", condicionOperacion=" + condicionOperacion
				+ ", pagos=" + pagos + ", observaciones=" + observaciones + "]";
	}

}