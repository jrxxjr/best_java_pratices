package br.com.tidicas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class DateParam {
	private final Date date;

	public DateParam(String dateStr) throws WebApplicationException {
		if (dateStr==null || dateStr.trim().isEmpty()) {
			this.date = null;
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
					.entity("Data naop pode ser nula").build());
		}
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
				.entity("Formato invalido de data").build());
		}
	}

	public Date getDate() {
		return date;
	}
}