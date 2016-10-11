import java.util.Scanner;
import java.util.Arrays;
public class Kaprekar
{
	static final int KAPREKAR_CONST = 6174;
	public static void main(String[] args)
	{
		int input = Integer.parseInt(args[0]);
		while(input<1000&&input>0){input=input*10;}
		if(!validate(input)){System.out.println("Invalid input.");System.exit(0);}
		System.out.println("largestDig("+input+") = "+ largestDig(input));
		System.out.println("descendDig("+input+") = "+ descendDig(input));
		System.out.println("kaprekar("+input+") = "+ kaprekar(input,true));
		System.out.println("max loops: "+kaprekar(findMax(),false));
	}

	public static int largestDig(int num)
	{
		int[] digits = getDigits(num);
		int max = -1;
		for(int i : digits)
		{
			if(i>max){max=i;}
		}
		return max;
	}

	public static int[] getDigits(int n)
	{
		String number = ""+n;
		int[] digits = new int[number.length()];
		for(int i = 0; i<digits.length;i++)
		{
			digits[i] = Integer.parseInt(""+number.charAt(i));
		}
		return digits;
	}

	public static String sortDig(int num, boolean asc)
	{
		int[] digits = getDigits(num);
		Arrays.sort(digits);
		String sorted = "";
		for(int i : digits)
		{
			if(asc){sorted = sorted+i;}
			else{sorted = i+sorted;}
		}
		return sorted;
	}

	public static int descendDig(int num)
	{
		int desc = Integer.parseInt(sortDig(num,false));
		return desc;
	}

	public static int ascendDig(int num)
	{
		int asc = Integer.parseInt(sortDig(num,true));
		return asc;
	}

	public static boolean validate(int num)
	{
		//4 digits, not all the same
		String input = ""+num;
		if(!input.matches("[0-9][0-9][0-9][0-9]")){return false;}
		if(input.replaceAll(""+input.charAt(0),"").length()==0){return false;}
		return true;
	}

	public static int kaprekar(int num, boolean verbose)
	{
		//4 digits, not all the same
		int steps = 0;
		int asc;
		while(num!=KAPREKAR_CONST)
		{
			while(num<1000){num=num*10;}
			asc = ascendDig(num);
			num = descendDig(num);
			if(verbose){System.out.println(""+num+" - "+ asc + " = " + (num-asc));}
			num = num - asc;
			steps++;
		}
		return steps;
	}

	public static int findMax()
	{
		int max = 0;
		int maxval = 0;
		int curr = 0;
		for(int i = 1; i<10000; i++)
		{
			if(validate(i))
			{
				curr = kaprekar(i,false);
				if(curr>maxval){max=i;maxval=curr;}
			}
		}
		return max;
	}
}