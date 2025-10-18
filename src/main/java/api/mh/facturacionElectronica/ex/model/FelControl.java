package api.mh.facturacionElectronica.ex.model;

import java.util.Date;
import java.util.Objects;

public class FelControl {

	public String uTipo;
	public String uNumero;
	public Date uFechaIn;
	public Date uFechaPro;
	public String uCertificado;
	public String uEnviado;
	public String uECodGene;
	public String uENumCont;
	public String uESellRecep;
	public String uDocEntry;
	public String uEstado;
	public String uJson;
	public String uFirma;
	public String uJsonEnviado;
	public String cardCode;
	public String uEModFact;
	public String uETipoTrans;
	public String uContingencia;
	public String uNumContingencia;

	public String uTipoDocAnul;

	public int noItem;

	public String getuTipo() {
		return uTipo;
	}

	public void setuTipo(String uTipo) {
		this.uTipo = uTipo;
	}

	public String getuNumero() {
		return uNumero;
	}

	public void setuNumero(String uNumero) {
		this.uNumero = uNumero;
	}

	public Date getuFechaIn() {
		return uFechaIn;
	}

	public void setuFechaIn(Date uFechaIn) {
		this.uFechaIn = uFechaIn;
	}

	public Date getuFechaPro() {
		return uFechaPro;
	}

	public void setuFechaPro(Date uFechaPro) {
		this.uFechaPro = uFechaPro;
	}

	public String getuCertificado() {
		return uCertificado;
	}

	public void setuCertificado(String uCertificado) {
		this.uCertificado = uCertificado;
	}

	public String getuEnviado() {
		return uEnviado;
	}

	public void setuEnviado(String uEnviado) {
		this.uEnviado = uEnviado;
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

	public String getuDocEntry() {
		return uDocEntry;
	}

	public void setuDocEntry(String uDocEntry) {
		this.uDocEntry = uDocEntry;
	}

	public String getuEstado() {
		return uEstado;
	}

	public void setuEstado(String uEstado) {
		this.uEstado = uEstado;
	}

	public String getuJson() {
		return uJson;
	}

	public void setuJson(String uJson) {
		this.uJson = uJson;
	}

	public String getuFirma() {
		return uFirma;
	}

	public void setuFirma(String uFirma) {
		this.uFirma = uFirma;
	}

	public String getuJsonEnviado() {
		return uJsonEnviado;
	}

	public void setuJsonEnviado(String uJsonEnviado) {
		this.uJsonEnviado = uJsonEnviado;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getuEModFact() {
		return uEModFact;
	}

	public void setuEModFact(String uEModFact) {
		this.uEModFact = uEModFact;
	}

	public String getuETipoTrans() {
		return uETipoTrans;
	}

	public void setuETipoTrans(String uETipoTrans) {
		this.uETipoTrans = uETipoTrans;
	}

	public String getuTipoDocAnul() {
		return uTipoDocAnul;
	}

	public void setuTipoDocAnul(String uTipoDocAnul) {
		this.uTipoDocAnul = uTipoDocAnul;
	}

	public String getuContingencia() {
		return uContingencia;
	}

	public void setuContingencia(String uContingencia) {
		this.uContingencia = uContingencia;
	}

	public String getuNumContingencia() {
		return uNumContingencia;
	}

	public void setuNumContingencia(String uNumContingencia) {
		this.uNumContingencia = uNumContingencia;
	}

	public int getNoItem() {
		return noItem;
	}

	public void setNoItem(int noItem) {
		this.noItem = noItem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardCode, noItem, uCertificado, uContingencia, uDocEntry, uECodGene, uEModFact, uENumCont,
				uESellRecep, uETipoTrans, uEnviado, uEstado, uFechaIn, uFechaPro, uFirma, uJson, uJsonEnviado,
				uNumContingencia, uNumero, uTipo, uTipoDocAnul);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FelControl other = (FelControl) obj;
		return Objects.equals(cardCode, other.cardCode) && noItem == other.noItem
				&& Objects.equals(uCertificado, other.uCertificado)
				&& Objects.equals(uContingencia, other.uContingencia) && Objects.equals(uDocEntry, other.uDocEntry)
				&& Objects.equals(uECodGene, other.uECodGene) && Objects.equals(uEModFact, other.uEModFact)
				&& Objects.equals(uENumCont, other.uENumCont) && Objects.equals(uESellRecep, other.uESellRecep)
				&& Objects.equals(uETipoTrans, other.uETipoTrans) && Objects.equals(uEnviado, other.uEnviado)
				&& Objects.equals(uEstado, other.uEstado) && Objects.equals(uFechaIn, other.uFechaIn)
				&& Objects.equals(uFechaPro, other.uFechaPro) && Objects.equals(uFirma, other.uFirma)
				&& Objects.equals(uJson, other.uJson) && Objects.equals(uJsonEnviado, other.uJsonEnviado)
				&& Objects.equals(uNumContingencia, other.uNumContingencia) && Objects.equals(uNumero, other.uNumero)
				&& Objects.equals(uTipo, other.uTipo) && Objects.equals(uTipoDocAnul, other.uTipoDocAnul);
	}

	@Override
	public String toString() {
		return "FelControl [uTipo=" + uTipo + ", uNumero=" + uNumero + ", uFechaIn=" + uFechaIn + ", uFechaPro="
				+ uFechaPro + ", uCertificado=" + uCertificado + ", uEnviado=" + uEnviado + ", uECodGene=" + uECodGene
				+ ", uENumCont=" + uENumCont + ", uESellRecep=" + uESellRecep + ", uDocEntry=" + uDocEntry
				+ ", uEstado=" + uEstado + ", uJson=" + uJson + ", uFirma=" + uFirma + ", uJsonEnviado=" + uJsonEnviado
				+ ", cardCode=" + cardCode + ", uEModFact=" + uEModFact + ", uETipoTrans=" + uETipoTrans
				+ ", uContingencia=" + uContingencia + ", uNumContingencia=" + uNumContingencia + ", uTipoDocAnul="
				+ uTipoDocAnul + ", noItem=" + noItem + "]";
	}

}
