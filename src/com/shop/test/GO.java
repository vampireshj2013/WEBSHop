package com.shop.test;

public class GO {
	static int  a[] = {1,2,3,4,5};
	static int avg = 0;
	public static void main(String arg[])
	{
		
	System.out.println(avr(a.length-1));	
		
	}
	public static double avr(int n)//n代表最高下标
	{
		if(n ==0)
			{
			
			return a[0];
			}
		else
			{
				return (a[n]+avr(n-1)*n)/(n+1);
			}

	}
}
