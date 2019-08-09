package ru.typik.pension.calculator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvParser {
	
	private final static Logger LOG = LoggerFactory.getLogger( CsvParser.class );

	public SalaryTable parsePfrAvgSalaryForSovietPeriod(String path) throws IOException {
		final SalaryTable salaryTable = new SalaryTable();
		Files.readAllLines( Paths.get( path ) )
			.stream()
			.takeWhile( el -> !el.isBlank() )
			.forEach( line -> {
				try {
					String[] values = line.split( "," );
					Integer year = Integer.parseInt( values[0] );
					BigDecimal salary = new BigDecimal( values[1] );
					IntStream.rangeClosed( 1 , 12 ).forEach( month -> salaryTable.putSalary( year , month , salary ) );
				}catch(Exception ex) {
					LOG.error( "Error in parsing line : '{}' " , line , ex );
				}
		});
		return salaryTable;
	}

	public SalaryTable parsePfrAvgSalaryTableForRussianPeriod(String path) throws IOException {
		List<String> lines = Files.readAllLines( Paths.get( path ) );
		Integer[] years = Stream.of( lines.get( 0 ).split( "," ) ).
				map( el -> Integer.parseInt( el ) ).
				toArray( size -> new Integer[size] );
		
		SalaryTable salaryTable = new SalaryTable();
		
		for( int monthIndex = 1; monthIndex <= 12; ++monthIndex ) {
			try {
				BigDecimal[] salaries = Stream.of( lines.get( monthIndex ).split( "," ) ).
						map( el -> new BigDecimal( el ) ).
						toArray( size -> new BigDecimal[ size ] );			
				for( int index = 0; index < years.length; ++index ) {
					salaryTable.putSalary( years[index], monthIndex , salaries[index] );
				}
			}catch(Exception ex) {
				LOG.error( "Error in parsing line : {}" , lines.get( monthIndex ) ,ex );
			}
		}
		return salaryTable;
	}
	

}
