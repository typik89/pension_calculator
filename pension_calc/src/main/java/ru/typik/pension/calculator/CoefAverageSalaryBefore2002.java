package ru.typik.pension.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoefAverageSalaryBefore2002 {
	
	private SalaryTable pfrAvgSalaryTable;
	
	
	public static class CoefCalcInfo{
		private final SalaryTable personSalaryTable;
		private final SalaryTable pfrSalaryTable;		
		public CoefCalcInfo( SalaryTable personTable , SalaryTable pfrTable ) {
			this.personSalaryTable = personTable;
			this.pfrSalaryTable = pfrTable;
		}
		public SalaryTable getPersonSalaryTable() {
			return personSalaryTable;
		}
		public SalaryTable getPfrSalaryTable() {
			return pfrSalaryTable;
		}
		public BigDecimal getCoef() {
			return getPersonSalaryTable().getAvgSalary().divide( getPfrSalaryTable().getAvgSalary() );
		}
	}
	
	
	public List<CoefCalcInfo> calculateBestCoef( PersonSalaryTable personSalaryTable ) {
		List<CoefCalcInfo> result = new ArrayList<>();
		for( SalaryTable salaryTable : personSalaryTable.getSalaryTablesOf60ConsecutiveMonths() ) {
			SalaryTable pfrPeriodTable = pfrAvgSalaryTable.getSalaryTableForPeriod( salaryTable.getStartYearMonth() , salaryTable.getEndYearMonth() );
			result.add( new CoefCalcInfo( 
					salaryTable , 
					pfrAvgSalaryTable.getSalaryTableForPeriod( salaryTable.getStartYearMonth() , salaryTable.getEndYearMonth() ) ) );			
		}
		return result;
	}

}
