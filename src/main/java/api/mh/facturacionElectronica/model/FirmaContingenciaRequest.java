package api.mh.facturacionElectronica.model;

import api.mh.facturacionElectronica.ex.model.contingencia.DTE;

public class FirmaContingenciaRequest {

	public String nit;
	public Boolean activo;
	public String passwordPri;
	public DTE dteJson;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getPasswordPri() {
		return passwordPri;
	}

	public void setPasswordPri(String passwordPri) {
		this.passwordPri = passwordPri;
	}

	public DTE getDteJson() {
		return dteJson;
	}

	public void setDteJson(DTE dteJson) {
		this.dteJson = dteJson;
	}

	@Override
	public String toString() {
		return "FirmaContingenciaRequest [nit=" + nit + ", activo=" + activo + ", passwordPri=" + passwordPri
				+ ", dteJson=" + dteJson + "]";
	}

}
