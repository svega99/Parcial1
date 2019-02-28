package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Optional;

import org.junit.Test;

import edu.eci.cvds.calculator.AirlineCalculator;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;
import edu.eci.cvds.model.SeatCategory;

/**
 * Test class for {@linkplain AirlineCalculator} class
 */
public class AirlineCalculatorTest {

	/**
	 * The class under test.
	 */
	private AirlineCalculator calculator = new AirlineCalculator();
	private int exitoso=0;
	private int sinSillas=0;
	private int restringido=0;
	private int total=0;
	private int invalido=0;
	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void calculateTest() {
		
		qt()
	    .forAll(ReservesGenerator.categorias()
	          , ReservesGenerator.sillas())
	    .check((categoria,numsillas) -> 
	    			prueba(categoria,numsillas)
	    		); 
		
		System.out.println("Numero de sillas restringido: "+restringido);
		System.out.println("Exitoso: "+exitoso);
		System.out.println("No hay suficientes sillas: "+sinSillas);
		System.out.println("Entrada Invalida: "+invalido);
		System.out.println("Numero de pruebas: "+total);
	}
	
	public boolean prueba(SeatCategory category, int seatsNumber) {
		total++;
		BookingOutput resultado = calculator.calculate(seatsNumber,category);
		if(seatsNumber>0 && seatsNumber<=100) {
			
			if (SeatCategory.ECONOMY_CLASS.equals(category)) {
				if (seatsNumber>50) {
					restringido++;
					return BookingResult.SEATS_NUMBER_RESTRICTED==resultado.getResult();
				}
				else {
					exitoso++;
					return BookingResult.SUCCESS==resultado.getResult();
				}
	
			} else if (SeatCategory.FIRST_CLASS.equals(category)) {
				if (seatsNumber>15) {
					restringido++;
					return BookingResult.SEATS_NUMBER_RESTRICTED==resultado.getResult();
				}
				else {
					exitoso++;
					return BookingResult.SUCCESS==resultado.getResult();
				}
			} 
			else if (SeatCategory.EMERGENCY_DOOR.equals(category)) {
				if (seatsNumber>8) {
					sinSillas++;
					return BookingResult.NOT_ENOUGH_SEATS==resultado.getResult();
				}
				else {
					exitoso++;
					return BookingResult.SUCCESS==resultado.getResult();
				}
			} 
			else {
				sinSillas++;
				return BookingResult.NOT_ENOUGH_SEATS==resultado.getResult();
			}
		}
		else {
			invalido++;
			return BookingResult.INVALID_ENTER==resultado.getResult();
		}
		
		
	}
}
