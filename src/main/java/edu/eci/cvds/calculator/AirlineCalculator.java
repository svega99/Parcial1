package edu.eci.cvds.calculator;

import java.util.Optional;

import edu.eci.cvds.model.SeatCategory;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;

/**
 * Utility class to validate an airline's booking
 */
public class AirlineCalculator implements BookingCalculator {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public BookingOutput calculate(Integer seatsNumber, SeatCategory category) {
		float price = 0;
		if(seatsNumber>0 && seatsNumber<=100) {
			if (seatsNumber>=15) {
				price= (float) ((seatsNumber * category.getPrice()) - ((seatsNumber * category.getPrice()) * 0.2));
			}
			else if(seatsNumber>=10) {
				price= (float) ((seatsNumber * category.getPrice()) - ((seatsNumber * category.getPrice() )* 0.1));
			}
			else if(seatsNumber>5) {
				price= (float) ((seatsNumber * category.getPrice()) - ((seatsNumber * category.getPrice()) * 0.02));
			}
			else {
				price= (float) (seatsNumber * category.getPrice());
			}
			
			if (SeatCategory.ECONOMY_CLASS.equals(category)) {
				if (seatsNumber>50) {
					return new BookingOutput(BookingResult.SEATS_NUMBER_RESTRICTED, Optional.empty());
				}
				else {
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
				}
	
			} else if (SeatCategory.FIRST_CLASS.equals(category)) {
				if (seatsNumber>15) {
					return new BookingOutput(BookingResult.SEATS_NUMBER_RESTRICTED, Optional.empty());
				}
				else {
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
				}
			} 
			else if (SeatCategory.EMERGENCY_DOOR.equals(category)) {
				if (seatsNumber>8) {
					return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
				}
				else {
					return new BookingOutput(BookingResult.SUCCESS, Optional.of(price));
				}
			} 
			else {
				return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
			}
		}
		else {
			return new BookingOutput(BookingResult.INVALID_ENTER, Optional.empty());
		}
	}
}
