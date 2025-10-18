package api.mh.facturacionElectronica.ex.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PagosDTE {

	public String codigo;
	public BigDecimal montoPago;
	public String plazo;
	public String referencia;
	public BigDecimal periodo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(BigDecimal montoPago) {
		this.montoPago = montoPago;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getPeriodo() {
		return periodo;
	}

	public void setPeriodo(BigDecimal periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, montoPago, periodo, plazo, referencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagosDTE other = (PagosDTE) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(montoPago, other.montoPago)
				&& periodo == other.periodo && Objects.equals(plazo, other.plazo)
				&& Objects.equals(referencia, other.referencia);
	}

	@Override
	public String toString() {
		return "PagosDTE [codigo=" + codigo + ", montoPago=" + montoPago + ", plazo=" + plazo + ", referencia="
				+ referencia + ", periodo=" + periodo + "]";
	}

}
