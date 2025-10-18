package api.mh.facturacionElectronica.ex.model;

import java.util.Date;
import java.util.Objects;

public class SessionToken {

	public String usuario;
	public String token;
	public Date fechaCreacion;
	public Date fechaExpiracion;
	public String estado;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaCreacion, fechaExpiracion, token, usuario, estado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionToken other = (SessionToken) obj;
		return Objects.equals(fechaCreacion, other.fechaCreacion)
				&& Objects.equals(fechaExpiracion, other.fechaExpiracion) && Objects.equals(token, other.token)
				&& Objects.equals(usuario, other.usuario) && Objects.equals(estado, other.estado);
	}

	@Override
	public String toString() {
		return "SessionToken [usuario=" + usuario + ", token=" + token + ", fechaCreacion=" + fechaCreacion
				+ ", fechaExpiracion=" + fechaExpiracion + ", estado=" + estado + "]";
	}

}
