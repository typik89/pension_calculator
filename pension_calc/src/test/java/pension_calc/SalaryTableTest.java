package pension_calc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.junit.Test;

import ru.typik.pension.calculator.SalaryTable;

public class SalaryTableTest {
	
	@Test
	public void test() {
		SalaryTable salaryTable = new SalaryTable();
		salaryTable.putSalary( 2010 , 1 , new BigDecimal( 100 ) );
		salaryTable.putSalary( 2010 , 2 , new BigDecimal( 200 ) );
		salaryTable.putSalary( 2010 , 3 , new BigDecimal( 100 ) );
		salaryTable.putSalary( 2010 , 4 , new BigDecimal( 200 ) );
		
		assertEquals( 150 , salaryTable.getAvgSalary().intValue() );
		
		SalaryTable cutSalaryTable = salaryTable.getSalaryTableForPeriod( YearMonth.of( 2010 , 3 ) , YearMonth.of( 2010 , 5 ) );
		assertEquals( YearMonth.of( 2010 , 3 ) , cutSalaryTable.getStartYearMonth() );
		assertEquals( YearMonth.of( 2010 , 4 ) , cutSalaryTable.getEndYearMonth() );
		assertEquals( 150 , cutSalaryTable.getAvgSalary().intValue() );
	}

}
