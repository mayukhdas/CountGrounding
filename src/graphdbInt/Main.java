package graphdbInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String FactFile = "./data/facts.txt";
		String RWFile = "./data/RWRPredicates.txt";
		String outputFileName = "./data/groundingsFullNoCap_test.txt";
		
		FactFile = args[0];
		RWFile = args[1];
		outputFileName = args[2];
		
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
				String[] preds = line.split("\\;");
				System.out.println("\n"+line);
				String start = "RW: "+line;
				OutPut.write(start.getBytes());
				//OutPut.write(System.getProperty("line.separator").getBytes());
				String ret = gdb.getGroundings(null, null, preds, OutPut);
				//System.out.println(ret);
				//String end = " \n ";
				//OutPut.write(System.getProperty("line.separator").getBytes());
				i++;
				System.out.println(i);
			}
			OutPut.write(endAll.getBytes());
			//gdb.close();
			OutPut.close();
			System.out.println("here");
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
