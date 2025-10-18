package api.mh.facturacionElectronica.ex.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TributosDTE {

	public String codigo;
	public String descripcion;
	public BigDecimal valor;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descripcion, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TributosDTE other = (TributosDTE) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "TributosDTE [codigo=" + codigo + ", descripcion=" + descripcion + ", valor=" + valor + "]";
	}

}
