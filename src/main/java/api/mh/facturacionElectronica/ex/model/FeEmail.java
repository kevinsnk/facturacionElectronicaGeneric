package api.mh.facturacionElectronica.ex.model;

import java.util.Objects;

public class FeEmail {

	public String uParametro;
	public String uDescripcion;
	public String uValor;

	public String getuParametro() {
		return uParametro;
	}

	public void setuParametro(String uParametro) {
		this.uParametro = uParametro;
	}

	public String getuDescripcion() {
		return uDescripcion;
	}

	public void setuDescripcion(String uDescripcion) {
		this.uDescripcion = uDescripcion;
	}

	public String getuValor() {
		return uValor;
	}

	public void setuValor(String uValor) {
		this.uValor = uValor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uDescripcion, uParametro, uValor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeEmail other = (FeEmail) obj;
		return Objects.equals(uDescripcion, other.uDescripcion) && Objects.equals(uParametro, other.uParametro)
				&& Objects.equals(uValor, other.uValor);
	}

	@Override
	public String toString() {
		return "FeEmail [uParametro=" + uParametro + ", uDescripcion=" + uDescripcion + ", uValor=" + uValor + "]";
	}

}
