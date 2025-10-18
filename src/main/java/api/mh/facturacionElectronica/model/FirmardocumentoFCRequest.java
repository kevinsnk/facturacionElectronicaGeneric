package api.mh.facturacionElectronica.model;

import api.mh.facturacionElectronica.ex.model.fc.DTE;

public class FirmardocumentoFCRequest {

	public String nit;
	public Boolean activo;
	public String passwordPri;
	public DTE dteJson;

	public FirmardocumentoFCRequest() {

	}

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
		return "FirmardocumentoRequest [nit=" + nit + ", activo=" + activo + ", passwordPri=" + passwordPri
				+ ", dteJson=" + dteJson + "]";
	}

}
