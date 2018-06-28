package co.com.ceiba.dominio.trm;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.dominio.excepcion.ObtenerTRMExcepcion;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

public class TasaRepresentativaMercado {
	
	/**
	 * Valid from and valid to TCRM date format
	 */
	private final static String DATE_RESPONSE_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
	
	/**
	 * TCRM query date format
	 */
	private final static String DATE_QUERY_FORMAT = "yyyy-MM-dd";
	
	/**
	 * TCRM query value format
	 */
	private final static String VALUE_QUERY_FORMAT = "#0.00";

	/**
	 * TCRM date to query
	 */
	private final static String DATE_TO_QUERY = "2014-08-13";
	
	/**
	 * Web Service end point
	 */
	private final static String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";

	private Long id;
	private String fechaValidaDesde;
	private String fechaValidaHasta;
	private String valor;
	private String observaciones;
	
	private SimpleDateFormat simpleDateFormat;
	private DecimalFormat decimalFormat;
	private TCRMServicesInterfaceProxy proxy;
	
	private TcrmResponse tcrmResponse;
	
	public TasaRepresentativaMercado() { 
		
		this.valor = null;
		
		//
		// Simple date format declaration
		simpleDateFormat = new SimpleDateFormat(DATE_RESPONSE_FORMAT);
		
		//
		// Decimal value format declaration
		decimalFormat = new DecimalFormat(VALUE_QUERY_FORMAT);
		
		proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFechaValidaDesde() {
		return fechaValidaDesde;
	}
	public void setFechaValidaDesde(String fechaValidaDesde) {
		this.fechaValidaDesde = fechaValidaDesde;
	}
	public String getFechaValidaHasta() {
		return fechaValidaHasta;
	}
	public void setFechaValidaHasta(String fechaValidaHasta) {
		this.fechaValidaHasta = fechaValidaHasta;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public TasaRepresentativaMercado construirTRM() {
		//
		// Gets the TCRM value for the current date
		try {
			tcrmResponse = proxy.queryTCRM(null);
		} catch (RemoteException e) {
			throw new ObtenerTRMExcepcion(e.getMessage());
		}
		
		this.id = tcrmResponse.getId();
		this.fechaValidaDesde = simpleDateFormat.format(tcrmResponse.getValidityFrom().getTime());
		this.fechaValidaHasta = simpleDateFormat.format(tcrmResponse.getValidityTo().getTime());
		this.valor = decimalFormat.format(tcrmResponse.getValue());
		
		return this;
		
	}
	
	public TasaRepresentativaMercado construirTRMPorFecha(Date fecha) throws ParseException {
		//
		// Gets the TCRM value for the given date
		DateFormat formatter = new SimpleDateFormat(DATE_QUERY_FORMAT);
		Date date = (Date)formatter.parse(DATE_TO_QUERY); 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		try {
			tcrmResponse = proxy.queryTCRM(calendar);
		} catch (RemoteException e) {
			throw new ObtenerTRMExcepcion(e.getMessage());
		}
		
		this.id = tcrmResponse.getId();
		this.fechaValidaDesde = simpleDateFormat.format(tcrmResponse.getValidityFrom().getTime());
		this.fechaValidaHasta = simpleDateFormat.format(tcrmResponse.getValidityTo().getTime());
		this.valor = decimalFormat.format(tcrmResponse.getValue());
		this.observaciones = tcrmResponse.getMessage();
		
		return this;
		
	}
	
}
