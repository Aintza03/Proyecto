package General;

import java.util.Objects;

public class Usuario {
	protected int contraseña;
	protected String usuario;
	protected boolean admin;
	
	public Usuario(int contraseña, String usuario,boolean admin) {
		super();
		this.contraseña = contraseña;
		this.usuario = usuario;
		this.admin = admin;
	}
	public int getContraseña() {
		return contraseña;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public int hashCode() {
		return Objects.hash(admin, contraseña, usuario);
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
		return admin == other.admin && contraseña == other.contraseña && Objects.equals(usuario, other.usuario);
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
		return "Usuario: " + usuario + " admin: " + admin;
	}
	

}
