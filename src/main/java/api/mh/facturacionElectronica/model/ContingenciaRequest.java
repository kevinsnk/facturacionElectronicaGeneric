package api.mh.facturacionElectronica.model;

public class ContingenciaRequest {

	public String nit;
	public String documento;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "ContingenciaRequest [nit=" + nit + ", documento=" + documento + "]";
	}

}
