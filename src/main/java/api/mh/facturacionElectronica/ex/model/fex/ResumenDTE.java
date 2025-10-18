package api.mh.facturacionElectronica.ex.model.fex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import api.mh.facturacionElectronica.ex.model.PagosDTE;

public class ResumenDTE {

	public BigDecimal totalGravada;
	public BigDecimal descuento;
	public BigDecimal porcentajeDescuento;
	public BigDecimal totalDescu;
	public BigDecimal seguro;
	public BigDecimal flete;
	public BigDecimal montoTotalOperacion;
	public BigDecimal totalNoGravado;
	public BigDecimal totalPagar;
	public String totalLetras;
	public BigDecimal condicionOperacion;
	public ArrayList<PagosDTE> pagos;
	public String codIncoterms;
	public String descIncoterms;
	public String numPagoElectronico;
	public String observaciones;

	public BigDecimal getTotalGravada() {
		return totalGravada;
	}

	public void setTotalGravada(BigDecimal totalGravada) {
		this.totalGravada = totalGravada;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public BigDecimal getSeguro() {
		return seguro;
	}

	public void setSeguro(BigDecimal seguro) {
		this.seguro = seguro;
	}

	public BigDecimal getFlete() {
		return flete;
	}

	public void setFlete(BigDecimal flete) {
		this.flete = flete;
	}

	public BigDecimal getMontoTotalOperacion() {
		return montoTotalOperacion;
	}

	public void setMontoTotalOperacion(BigDecimal montoTotalOperacion) {
		this.montoTotalOperacion = montoTotalOperacion;
	}

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

	public String getCodIncoterms() {
		return codIncoterms;
	}

	public void setCodIncoterms(String codIncoterms) {
		this.codIncoterms = codIncoterms;
	}

	public String getDescIncoterms() {
		return descIncoterms;
	}

	public void setDescIncoterms(String descIncoterms) {
		this.descIncoterms = descIncoterms;
	}

	public String getNumPagoElectronico() {
		return numPagoElectronico;
	}

	public void setNumPagoElectronico(String numPagoElectronico) {
		this.numPagoElectronico = numPagoElectronico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codIncoterms, condicionOperacion, descIncoterms, descuento, flete, montoTotalOperacion,
				numPagoElectronico, observaciones, pagos, porcentajeDescuento, seguro, totalDescu, totalGravada,
				totalLetras, totalNoGravado, totalPagar);
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
		return Objects.equals(codIncoterms, other.codIncoterms)
				&& Objects.equals(condicionOperacion, other.condicionOperacion)
				&& Objects.equals(descIncoterms, other.descIncoterms) && Objects.equals(descuento, other.descuento)
				&& Objects.equals(flete, other.flete) && Objects.equals(montoTotalOperacion, other.montoTotalOperacion)
				&& Objects.equals(numPagoElectronico, other.numPagoElectronico)
				&& Objects.equals(observaciones, other.observaciones) && Objects.equals(pagos, other.pagos)
				&& Objects.equals(porcentajeDescuento, other.porcentajeDescuento)
				&& Objects.equals(seguro, other.seguro) && Objects.equals(totalDescu, other.totalDescu)
				&& Objects.equals(totalGravada, other.totalGravada) && Objects.equals(totalLetras, other.totalLetras)
				&& Objects.equals(totalNoGravado, other.totalNoGravado) && Objects.equals(totalPagar, other.totalPagar);
	}

	@Override
	public String toString() {
		return "ResumenDTE [totalGravada=" + totalGravada + ", descuento=" + descuento + ", porcentajeDescuento="
				+ porcentajeDescuento + ", totalDescu=" + totalDescu + ", seguro=" + seguro + ", flete=" + flete
				+ ", montoTotalOperacion=" + montoTotalOperacion + ", totalNoGravado=" + totalNoGravado
				+ ", totalPagar=" + totalPagar + ", totalLetras=" + totalLetras + ", condicionOperacion="
				+ condicionOperacion + ", pagos=" + pagos + ", codIncoterms=" + codIncoterms + ", descIncoterms="
				+ descIncoterms + ", numPagoElectronico=" + numPagoElectronico + ", observaciones=" + observaciones
				+ "]";
	}

}