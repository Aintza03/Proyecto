package General;

import java.sql.Date;
import java.util.Objects;

public class Acogida extends Servicio {
protected Date fechaEntrega;

public Acogida(Date fechaInicio, Animal animal,Date fechaEntrega) {
	super(fechaInicio, animal);
	this.fechaInicio = fechaInicio;
	this.animal = animal;
	this.fechaEntrega= fechaEntrega;
}

public Date getFechaEntrega() {
	return fechaEntrega;
}

public void setFechaEntrega(Date fechaEntrega) {
	this.fechaEntrega = fechaEntrega;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(fechaEntrega);
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Acogida other = (Acogida) obj;
	return Objects.equals(fechaEntrega, other.fechaEntrega);
}

@Override
public String toString() {
	return "Acogida [fechaEntrega=" + fechaEntrega + "]";
}


}
