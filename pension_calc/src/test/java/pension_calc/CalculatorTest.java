package pension_calc;


import java.io.IOException;

import org.junit.Test;

import ru.typik.pension.calculator.CsvParser;
import ru.typik.pension.calculator.SalaryTable;


public class CalculatorTest {
	
	
	@Test
	public void test() throws IOException {
		CsvParser csvParser = new CsvParser();
		
		SalaryTable pfrSovietSalaryTable = csvParser.parsePfrAvgSalaryForSovietPeriod( "src/main/resources/pfr_avg_salaries_soviet.csv" );
		SalaryTable pfrRussianSalaryTable = csvParser.parsePfrAvgSalaryForSovietPeriod( "src/main/resources/pfr_avg_salaries_rf.csv" );
		SalaryTable pfr = pfrSovietSalaryTable.merge( pfrRussianSalaryTable );
		
		
	}

}
