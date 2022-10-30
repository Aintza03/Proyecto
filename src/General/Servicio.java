package General;

import java.sql.Date;
import java.util.HashMap;
import java.util.Objects;

public abstract class Servicio {
	protected Date fecha;
	protected HashMap<Animal, Boolean> animales;
	public Servicio(Date fecha, HashMap<Animal, Boolean> animales) {
		super();
		this.fecha = fecha;
		this.animales = animales;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public HashMap<Animal, Boolean> getAnimales() {
		return animales;
	}
	public void setAnimales(HashMap<Animal, Boolean> animales) {
		this.animales = animales;
	}
	
	@Override
	public String toString() {
		return "Servicio [fecha=" + fecha + ", animales=" + animales + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(animales, fecha);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return Objects.equals(animales, other.animales) && Objects.equals(fecha, other.fecha);
	}
	
	
	

}
