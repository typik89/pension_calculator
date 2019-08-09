package ru.typik.pension.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryTable {
	
	private final Map<YearMonth,BigDecimal> table = new HashMap<>();


	public BigDecimal getSalary(YearMonth yearMonth) {
		return table.get( yearMonth );
	}
	

	public YearMonth getEndYearMonth() {
		return table.keySet().stream().sorted( (el1 , el2) -> el2.compareTo( el1 ) ).findFirst().get();
	}

	public SalaryTable getSalaryTableForPeriod(YearMonth startYearMonth, YearMonth endYearMonth) {
		SalaryTable salaryTable = new SalaryTable();
		salaryTable.table.putAll( 
				table.entrySet().stream().
				filter( el -> el.getKey().compareTo( startYearMonth ) >= 0 && el.getKey().compareTo( endYearMonth ) <= 0 ).
				collect( Collectors.toMap( el -> el.getKey() , el -> el.getValue() ) ) );			
		return salaryTable;
	}

	public BigDecimal getAvgSalary() {
		return table.values().stream().filter( el -> el != null ).
				reduce( BigDecimal.ZERO , (p,q) -> p.add(q) ).
				divide( new BigDecimal( table.values().size() ) , new MathContext( 10 ) );
	}

	public YearMonth getStartYearMonth() {
		return table.keySet().stream().sorted().findFirst().get();
	}

	public void putSalary(Integer year, int month, BigDecimal salary) {
		table.put( YearMonth.of( year , month ) , salary );
	}


	public SalaryTable merge(SalaryTable salaryTableRF) {
		SalaryTable st = new SalaryTable();
		st.table.putAll( this.table );
		st.table.putAll( salaryTableRF.table );
		return st;
	}

}
