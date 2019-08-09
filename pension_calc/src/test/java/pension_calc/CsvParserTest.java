package pension_calc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;

import org.junit.Test;

import ru.typik.pension.calculator.CsvParser;
import ru.typik.pension.calculator.SalaryTable;

public class CsvParserTest {
	
	@Test
	public void test() throws IOException {
		SalaryTable salaryTableSovietPeriod = new CsvParser().parsePfrAvgSalaryForSovietPeriod( "src/main/resources/pfr_avg_salaries_soviet_period.csv" );
		assertEquals( YearMonth.of( 1960 , Month.JANUARY ) , salaryTableSovietPeriod.getStartYearMonth() );
		assertEquals( YearMonth.of( 1990 , Month.DECEMBER ) , salaryTableSovietPeriod.getEndYearMonth() );
		
		SalaryTable salaryTableRF = new CsvParser().parsePfrAvgSalaryTableForRussianPeriod( "src/main/resources/pfr_avg_salaries_rf.csv" );
		assertEquals( YearMonth.of( 1991 , Month.JANUARY ) , salaryTableRF.getStartYearMonth() );
		assertEquals( YearMonth.of( 1997 , Month.DECEMBER ) , salaryTableRF.getEndYearMonth() );
		
		SalaryTable resultSalaryTable = salaryTableSovietPeriod.merge( salaryTableRF );
		assertEquals( YearMonth.of( 1960 , Month.JANUARY ) , resultSalaryTable.getStartYearMonth() );
		assertEquals( YearMonth.of( 1997 , Month.DECEMBER ) , resultSalaryTable.getEndYearMonth() );
	}

}
