package graphdbInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String FactFile = "./DDI/facts.txt";
		String RWFile = "./DDI/RWRPredicates.txt";
		String posFile = "./DDI/pos.txt";
		String negFile = "./DDI/neg.txt";
		String outputFileName = "./DDI/groundingsFullNoCap_test.txt";
		
		
		try
		{
			final long startTime = System.nanoTime();
			FileOutputStream OutPut = new FileOutputStream(outputFileName);
			GraphDB gdb = new GraphDB(FactFile,null,"ddi2",true);
			//System.exit(1);
			BufferedReader br = new BufferedReader(new FileReader(new File(RWFile)));
			String line = "";
			String encloser = "[\n"; String endAll = "\n]";
			//OutPut.write(encloser.getBytes());
			int i=0;
			while((line = br.readLine())!=null)
			{
				//if(i>2)
					//break;
				System.out.println(line);
				String body = line.split(":-")[1].trim();
				String[] preds = line.split("\\;");
				//System.out.println("\n"+line);
				String start = "RW: "+line;
				OutPut.write(start.getBytes());
				//OutPut.write(System.getProperty("line.separator").getBytes());
				
				/*
				 * For partial groundings 
				 * Provide comma delimited string of variable names in first argument
				 * and comma separated values of variables in the correct order
				 */
				String ret = gdb.getGroundings(null, null, preds, OutPut);  
				
				
				//OutPut.write(ret.getBytes());
				//System.out.println(ret);
				//String end = " \n ";
				OutPut.write(System.getProperty("line.separator").getBytes());
				i++;
				System.out.println(i);
			}
			OutPut.write(endAll.getBytes());
			//gdb.close();
			OutPut.close();
			//System.out.println("here");
			BufferedReader new_br = new BufferedReader(new FileReader(new File(outputFileName)));
			final long duration = System.nanoTime() - startTime;
			System.out.println(duration);
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
