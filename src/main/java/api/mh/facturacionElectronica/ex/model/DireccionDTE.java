package api.mh.facturacionElectronica.ex.model;

import java.util.Objects;

public class DireccionDTE {

	public String departamento;
	public String municipio;
	public String complemento;

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complemento, departamento, municipio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DireccionDTE other = (DireccionDTE) obj;
		return Objects.equals(complemento, other.complemento) && Objects.equals(departamento, other.departamento)
				&& Objects.equals(municipio, other.municipio);
	}

	@Override
	public String toString() {
		return "DireccionEmisor [departamento=" + departamento + ", municipio=" + municipio + ", complemento="
				+ complemento + "]";
	}

}
