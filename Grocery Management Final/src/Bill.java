import java.util.*;

public class Bill {

	HashMap<String,Float> bill=new HashMap<String,Float>();
	float totalBill;
	
	String returnString()
	{
		String str="\n\n\t\t\t\t\t";
		 Iterator hmIterator = bill.entrySet().iterator(); 
		while (hmIterator.hasNext()) { 
            Map.Entry map = (Map.Entry)hmIterator.next(); 
           str=str.concat((map.getKey() + ": "));
           str= str.concat(map.getValue().toString()+"\t");
        } 
		return(str);
	}
//	public static void main(String args[])
//	{
//		Bill b=new Bill();
//		b.bill.put("wheat", 40);
//		b.bill.put("rice", 50);
//		b.bill.put("milk", 55);
//		b.bill.put("banana", 80);
//		System.out.println("HIE");
//		System.out.println(b.returnString());
//		
//	}
	
}
