package api.mh.facturacionElectronica.ex.model.nr;

import java.util.Objects;

public class ApendiceDTE {

	public String campo;
	public String etiqueta;
	public String valor;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(campo, etiqueta, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApendiceDTE other = (ApendiceDTE) obj;
		return Objects.equals(campo, other.campo) && Objects.equals(etiqueta, other.etiqueta)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "ApendiceDTE [campo=" + campo + ", etiqueta=" + etiqueta + ", valor=" + valor + "]";
	}

}
