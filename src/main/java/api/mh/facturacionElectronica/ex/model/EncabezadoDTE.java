package api.mh.facturacionElectronica.ex.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class EncabezadoDTE {

	// Campos para llenar informacion en IdentificacionDTE.java
	public String numeroControl;
	public String fecEmi;
	public String horEmi;

	// Codigo del receptor del DTE
	public String codReceptor;

	public BigDecimal iva;

	// Campos para llenar ResumenDTE.java
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
	// public ArrayList<PagosDTE> pagos;
	public String numPagoElectronico;

	// factura de exportación
	public BigDecimal seguro;
	public BigDecimal flete;
	public BigDecimal descuento;
	public String codIncoterms;
	public String descIncoterms;
	public String observaciones;
	public int tipoItem;

	// Valores necesarios para la anulacion del documento
	public int tipoAnulacion;
	public String motivoAnulacion;
	public String nombreResponsable;
	public String tipDocResponsable;
	public String numDocResponsable;
	public String nombreSolicita;
	public String tipDocSolicita;
	public String numDocSolicita;
	public String uECodGene;
	public String uENumCont;
	public String uESellRecep;
	public Date uEFechaHora;

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}

	public String getFecEmi() {
		return fecEmi;
	}

	public void setFecEmi(String fecEmi) {
		this.fecEmi = fecEmi;
	}

	public String getHorEmi() {
		return horEmi;
	}

	public void setHorEmi(String horEmi) {
		this.horEmi = horEmi;
	}

	public String getCodReceptor() {
		return codReceptor;
	}

	public void setCodReceptor(String codReceptor) {
		this.codReceptor = codReceptor;
	}

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

	public String getNumPagoElectronico() {
		return numPagoElectronico;
	}

	public void setNumPagoElectronico(String numPagoElectronico) {
		this.numPagoElectronico = numPagoElectronico;
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

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(int tipoItem) {
		this.tipoItem = tipoItem;
	}

	public ArrayList<TributosDTE> getTributos() {
		return tributos;
	}

	public void setTributos(ArrayList<TributosDTE> tributos) {
		this.tributos = tributos;
	}

	public int getTipoAnulacion() {
		return tipoAnulacion;
	}

	public void setTipoAnulacion(int tipoAnulacion) {
		this.tipoAnulacion = tipoAnulacion;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getTipDocResponsable() {
		return tipDocResponsable;
	}

	public void setTipDocResponsable(String tipDocResponsable) {
		this.tipDocResponsable = tipDocResponsable;
	}

	public String getNumDocResponsable() {
		return numDocResponsable;
	}

	public void setNumDocResponsable(String numDocResponsable) {
		this.numDocResponsable = numDocResponsable;
	}

	public String getNombreSolicita() {
		return nombreSolicita;
	}

	public void setNombreSolicita(String nombreSolicita) {
		this.nombreSolicita = nombreSolicita;
	}

	public String getTipDocSolicita() {
		return tipDocSolicita;
	}

	public void setTipDocSolicita(String tipDocSolicita) {
		this.tipDocSolicita = tipDocSolicita;
	}

	public String getNumDocSolicita() {
		return numDocSolicita;
	}

	public void setNumDocSolicita(String numDocSolicita) {
		this.numDocSolicita = numDocSolicita;
	}

	public String getuECodGene() {
		return uECodGene;
	}

	public void setuECodGene(String uECodGene) {
		this.uECodGene = uECodGene;
	}

	public String getuENumCont() {
		return uENumCont;
	}

	public void setuENumCont(String uENumCont) {
		this.uENumCont = uENumCont;
	}

	public String getuESellRecep() {
		return uESellRecep;
	}

	public void setuESellRecep(String uESellRecep) {
		this.uESellRecep = uESellRecep;
	}

	public Date getuEFechaHora() {
		return uEFechaHora;
	}

	public void setuEFechaHora(Date uEFechaHora) {
		this.uEFechaHora = uEFechaHora;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codIncoterms, codReceptor, condicionOperacion, descIncoterms, descuExenta, descuGravada,
				descuNoSuj, descuento, fecEmi, flete, horEmi, iva, ivaPerci1, ivaRete1, montoTotalOperacion,
				motivoAnulacion, nombreResponsable, nombreSolicita, numDocResponsable, numDocSolicita,
				numPagoElectronico, numeroControl, observaciones, porcentajeDescuento, reteRenta, saldoFavor, seguro,
				subTotal, subTotalVentas, tipDocResponsable, tipDocSolicita, tipoAnulacion, tipoItem, totalDescu,
				totalExenta, totalGravada, totalLetras, totalNoGravado, totalNoSuj, totalPagar, tributos, uECodGene,
				uEFechaHora, uENumCont, uESellRecep);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncabezadoDTE other = (EncabezadoDTE) obj;
		return Objects.equals(codIncoterms, other.codIncoterms) && Objects.equals(codReceptor, other.codReceptor)
				&& Objects.equals(condicionOperacion, other.condicionOperacion)
				&& Objects.equals(descIncoterms, other.descIncoterms) && Objects.equals(descuExenta, other.descuExenta)
				&& Objects.equals(descuGravada, other.descuGravada) && Objects.equals(descuNoSuj, other.descuNoSuj)
				&& Objects.equals(descuento, other.descuento) && Objects.equals(fecEmi, other.fecEmi)
				&& Objects.equals(flete, other.flete) && Objects.equals(horEmi, other.horEmi)
				&& Objects.equals(iva, other.iva) && Objects.equals(ivaPerci1, other.ivaPerci1)
				&& Objects.equals(ivaRete1, other.ivaRete1)
				&& Objects.equals(montoTotalOperacion, other.montoTotalOperacion)
				&& Objects.equals(motivoAnulacion, other.motivoAnulacion)
				&& Objects.equals(nombreResponsable, other.nombreResponsable)
				&& Objects.equals(nombreSolicita, other.nombreSolicita)
				&& Objects.equals(numDocResponsable, other.numDocResponsable)
				&& Objects.equals(numDocSolicita, other.numDocSolicita)
				&& Objects.equals(numPagoElectronico, other.numPagoElectronico)
				&& Objects.equals(numeroControl, other.numeroControl)
				&& Objects.equals(observaciones, other.observaciones)
				&& Objects.equals(porcentajeDescuento, other.porcentajeDescuento)
				&& Objects.equals(reteRenta, other.reteRenta) && Objects.equals(saldoFavor, other.saldoFavor)
				&& Objects.equals(seguro, other.seguro) && Objects.equals(subTotal, other.subTotal)
				&& Objects.equals(subTotalVentas, other.subTotalVentas)
				&& Objects.equals(tipDocResponsable, other.tipDocResponsable)
				&& Objects.equals(tipDocSolicita, other.tipDocSolicita) && tipoAnulacion == other.tipoAnulacion
				&& tipoItem == other.tipoItem && Objects.equals(totalDescu, other.totalDescu)
				&& Objects.equals(totalExenta, other.totalExenta) && Objects.equals(totalGravada, other.totalGravada)
				&& Objects.equals(totalLetras, other.totalLetras)
				&& Objects.equals(totalNoGravado, other.totalNoGravado) && Objects.equals(totalNoSuj, other.totalNoSuj)
				&& Objects.equals(totalPagar, other.totalPagar) && Objects.equals(tributos, other.tributos)
				&& Objects.equals(uECodGene, other.uECodGene) && Objects.equals(uEFechaHora, other.uEFechaHora)
				&& Objects.equals(uENumCont, other.uENumCont) && Objects.equals(uESellRecep, other.uESellRecep);
	}

	@Override
	public String toString() {
		return "EncabezadoDTE [numeroControl=" + numeroControl + ", fecEmi=" + fecEmi + ", horEmi=" + horEmi
				+ ", codReceptor=" + codReceptor + ", iva=" + iva + ", totalNoSuj=" + totalNoSuj + ", totalExenta="
				+ totalExenta + ", totalGravada=" + totalGravada + ", subTotalVentas=" + subTotalVentas
				+ ", descuNoSuj=" + descuNoSuj + ", descuExenta=" + descuExenta + ", descuGravada=" + descuGravada
				+ ", porcentajeDescuento=" + porcentajeDescuento + ", totalDescu=" + totalDescu + ", tributos="
				+ tributos + ", subTotal=" + subTotal + ", ivaPerci1=" + ivaPerci1 + ", ivaRete1=" + ivaRete1
				+ ", reteRenta=" + reteRenta + ", montoTotalOperacion=" + montoTotalOperacion + ", totalNoGravado="
				+ totalNoGravado + ", totalPagar=" + totalPagar + ", totalLetras=" + totalLetras + ", saldoFavor="
				+ saldoFavor + ", condicionOperacion=" + condicionOperacion + ", numPagoElectronico="
				+ numPagoElectronico + ", seguro=" + seguro + ", flete=" + flete + ", descuento=" + descuento
				+ ", codIncoterms=" + codIncoterms + ", descIncoterms=" + descIncoterms + ", observaciones="
				+ observaciones + ", tipoItem=" + tipoItem + ", tipoAnulacion=" + tipoAnulacion + ", motivoAnulacion="
				+ motivoAnulacion + ", nombreResponsable=" + nombreResponsable + ", tipDocResponsable="
				+ tipDocResponsable + ", numDocResponsable=" + numDocResponsable + ", nombreSolicita=" + nombreSolicita
				+ ", tipDocSolicita=" + tipDocSolicita + ", numDocSolicita=" + numDocSolicita + ", uECodGene="
				+ uECodGene + ", uENumCont=" + uENumCont + ", uESellRecep=" + uESellRecep + ", uEFechaHora="
				+ uEFechaHora + "]";
	}

}
