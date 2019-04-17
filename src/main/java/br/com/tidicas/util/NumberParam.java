package br.com.tidicas.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NumberParam {	
	private Number number = null;
	
	public NumberParam(Number number) throws WebApplicationException {
		if (number==null) {			
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					.entity("Este parametro nao pode ser nulo").build());
		}
		this.number = number;
		
	}

	public Number getNumber() {
		return this.number;
	}
}