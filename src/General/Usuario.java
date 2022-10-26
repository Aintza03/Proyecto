package General;

import java.util.Objects;

public class Usuario {
	protected int contraseña;
	protected String usuario;
	
	public Usuario(int contraseña, String usuario) {
		super();
		this.contraseña = contraseña;
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(contraseña, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return contraseña == other.contraseña && Objects.equals(usuario, other.usuario);
	}
	public int getContraseña() {
		return contraseña;
	}
	public void setContraseña(int contraseña) {
		this.contraseña = contraseña;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Usuario: " + usuario;
	}

}
