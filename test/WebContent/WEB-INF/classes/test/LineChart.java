package test;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.jfree.chart.JFreeChart; 
	import org.jfree.chart.ChartFactory; 
	import org.jfree.chart.ChartUtilities; 
	import org.jfree.chart.plot.PlotOrientation;
	import org.jfree.data.category.DefaultCategoryDataset;
	
	public class LineChart
	{
		public ResultSet sqlResult;
		
		public LineChart(ResultSet result) throws SQLException, IOException
	   {     
			SimpleDateFormat dFormat = new SimpleDateFormat("MM/yy");
			
			DefaultCategoryDataset chartData = new DefaultCategoryDataset();
	     sqlResult = result;
	     sqlResult.next();
	     while(! sqlResult.isAfterLast()){
	    	 String emp = result.getString("Worker");
	    	 chartData.setValue(result.getFloat("sngHours"), emp.substring(emp.indexOf("(")), dFormat.format(result.getDate("Month")));
	    	 sqlResult.next();
	     }
	     
			
	      JFreeChart lineChartObject = ChartFactory.createLineChart(
	         "Employee Productivity",
	         "Months", "Hrs Worked", chartData,PlotOrientation.VERTICAL,
	         true,true,false);

	      int width = 1000; /* Width of the image */
	      int height = 480; /* Height of the image */ 
	      System.out.println("Chart Created");
	      File lineChart = new File( "C:/Users/GIANLUCA/git/test/WebContent/LineChart.png" ); 
	      ChartUtilities.saveChartAsPNG(lineChart ,lineChartObject, width ,height);
	   }
	}

