import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class Zappos {

	private String strProduct=null;
	private static int intQuantity=0;
	private static int intPriceLimit=0;
	private HashMap<String,Double> map=new HashMap<String,Double>();
	public static void main(String[] args) {
		
		Zappos obj=new Zappos();
		obj.getInputFromUser();
		Random rn=new Random();
		int i=0;
		int products[]=new int[10];
		double dPrice[]=new double[100];
		while(i<10){
			products[i]=i;
			dPrice[i]=rn.nextInt(intPriceLimit);
			if(dPrice[i]==0){
				do{
					dPrice[i]=rn.nextInt(intPriceLimit);
				}while(dPrice[i]==0);
			}
			i++;
		}
		int temp[]=new int[intQuantity];
		double sum[]=new double[100];
		obj.getCombinations(products,0,temp,sum,dPrice,0);
		obj.printResults();
	}

	public void getInputFromUser(){
		Scanner scanner = new Scanner(System.in);
		//System.out.print("Enter the product : ");
		//strProduct=scanner.nextLine();
		System.out.println();
		System.out.print("Enter no of products to be gifted : ");
		intQuantity=scanner.nextInt();
		System.out.println();
		System.out.print("Enter the amount : ");
		intPriceLimit=scanner.nextInt();
		System.out.println();
		scanner.close();
	}
	
	void printResults(){
		System.out.println("Product Combination \t Combination Sum");
		for(Map.Entry<String,Double> entry : map.entrySet()){
			System.out.println(entry.getKey()+"\t\t\t"+entry.getValue());
		}
	}
	
	void getCombinations(int products[], int index, int temp[], double sum[],double price[], int intCount)
    {
        if (index == intQuantity){
            String key="";
            for (int j=0; j<intQuantity; j++){
                key+=temp[j];
            }
            if(sum[index-1]<=intPriceLimit && sum[index-1]>(intPriceLimit-5)){
                map.put(key,sum[index-1]);
            }
            return;
        }
        
        if (intCount >= products.length)
            return;
     
        temp[index] = products[intCount];
        if(index-1>=0){
            sum[index]=sum[index-1]+price[intCount];
        }
        else{
            sum[index]=price[intCount];
        }
        getCombinations(products, index+1, temp, sum, price,intCount+1);
     
        getCombinations(products, index, temp, sum, price,intCount+1);
    }
	
	
}
