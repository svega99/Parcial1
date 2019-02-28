package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import edu.eci.cvds.model.SeatCategory;

public class ReservesGenerator {
	public static Gen<Integer> sillas(){
		return integers().between(-5, 150);
	}
	
	public static Gen<SeatCategory> categorias(){
		return Generate.enumValues(SeatCategory.class);
	}
}
