package eg.edu.alexu.csd.datastructure.iceHockey.cs15;

import java.awt.Point;
import java.util.LinkedList;

public class Ice_Hockey {
	public static String to_string(int i,int j)
	{
		String x=Integer.toString(i);
		String y=Integer.toString(j);
		String z=x+","+y;
		return z;
	}
	public static LinkedList<String> probability(int i,int j,int [][]table)
	{
		LinkedList <String> points=new LinkedList();
		 if(i+1<table.length)
		 {
			 if(table[i+1][j]==1)
			 {
				 String x=to_string(i+1,j);
				 points.add(x);
			 }
		 }
		 if(i-1>=0)
		 {
			 if(table[i-1][j]==1)
			 {
				 String x=to_string(i-1,j);
				 points.add(x);
			 } 
		 }
	     if(j-1>=0)
		 {
			 if(table[i][j-1]==1)
			 {
				 String x=to_string(i,j-1);
				 points.add(x);
			 }
	   	 }
	     if(j+1<table[0].length)
		 {
			 if(table[i][j+1]==1)
			 {
				 String x=to_string(i,j+1);
				 points.add(x);
			 }
	   	 }
	     return  points;
			
	}
	public static int [] get_min_max(LinkedList<String> x)
	{
		String[] y=x.get(0).split(",");
		int min_row=Integer.parseInt(y[0]);
		int max_row=Integer.parseInt(y[0]);
		int min_column=Integer.parseInt(y[1]);
		int max_column=Integer.parseInt(y[1]);
		for(int i=1;i<x.size();i++)
		{
			String[] z=x.get(i).split(",");
			if(Integer.parseInt(z[0])<min_row)
			{
				min_row=Integer.parseInt(z[0]);
			}
			if(Integer.parseInt(z[0])>max_row)
			{
				max_row=Integer.parseInt(z[0]);
			}
			if(Integer.parseInt(z[1])<min_column)
			{
				min_column=Integer.parseInt(z[1]);
			}
			if(Integer.parseInt(z[1])>min_column)
			{
				max_column=Integer.parseInt(z[1]);
			}
		}
		int array[]=new int [4];
		array[0]=min_row;
		array[1]=min_column;
		array[2]=max_row;
		array[3]=max_column;
		return array;
	}
	public static java.awt.Point[] arrange(java.awt.Point[]x)
	{
		for(int i=0;i<x.length;i++)
		{
			for(int j=i+1;j<x.length;j++)
			{
				if(x[i].getX()>x[j].getX())
				{
					Point temp=x[i];
					x[i]=x[j];
					x[j]=temp;
				}
				else if(x[i].getX()==x[j].getX())
				{
					if(x[i].getY()>x[j].getY())
					{
						Point temp=x[i];
						x[i]=x[j];
						x[j]=temp;
					}
				}
			}
		}
		return x;
	}
	public static java.awt.Point[] findPlayer(String []photo,int team,int thersheld)
	 {
		 int [][]table=new int [photo.length][photo[0].length()];
		
		 for(int i=0;i<photo.length;i++)
		 {
			 char[] help=photo[i].toCharArray();
			 for(int j=0;j<help.length;j++)
			 {
				 char x=help[j];
				 
				 if(team==(x-'0'))
				 {
					 table[i][j]=1;
					
				 }
				 else
				 {
					 table[i][j]=0;
					 
				 }
				
			 }
		 }
		 LinkedList <String> final_result=new LinkedList();
		 for(int i=0;i<table.length;i++)
		 {
			 for(int j=0;j<table[0].length;j++)
			 {
				 if(table[i][j]==1)
				 {
					LinkedList <String> points=new LinkedList();
					LinkedList <String> gomma=new LinkedList();
					gomma.add(to_string(i,j));
					points=probability(i,j,table);
					for(int f=0;f<points.size();f++)
					{
						gomma.add(points.get(f));
					}
					int z=0;
					while(points.size()!=0)
					{
						//gomma.add(points.get(z));
						String x=points.get(z);
						String[]w=x.split(",");
						LinkedList <String> help=new LinkedList();
						help=probability(Integer.parseInt(w[0]),Integer.parseInt(w[1]),table);
						int found=0;
						LinkedList <Integer> Add=new LinkedList();
						for(int u=0;u<help.size();u++)
						{
							for(int e=0;e<gomma.size();e++)
							{
								if(help.get(u).equals(gomma.get(e)))
									{
										Add.add(u);
										break;
									}
								
							 }
						}	
						for(int e=0;e<Add.size();e++)
						{
							help.remove(Add.get(e)-e);
						}
						for(int e=0;e<help.size();e++)
						{
							gomma.add(help.get(e));
						}
						 points.remove(z);
						for(int o=0;o<help.size();o++)
						{
							points.add(help.get(o));
						}
						
					}
					int array[]=get_min_max(gomma);
					int count=0;
					for(int f=0;f<gomma.size();f++)
					{
						count=count+4;
						String[] e=gomma.get(f).split(",");
						table[Integer.parseInt(e[0])][Integer.parseInt(e[1])]=9;
					}
					if(count>=thersheld)
					{
						final_result.add(Integer.toString(array[0]+array[2]+1)+","+Integer.toString(array[1]+array[3]+1));
					}
					
					
				 }
					 
			 }
		 }
		java.awt.Point[] index=new Point[final_result.size()];
		 for(int t=0;t<final_result.size();t++)
			{
			    String[] e=final_result.get(t).split(",");
			    index[t]= new Point();
			    index[t].x=Integer.parseInt(e[1]);
			    index[t].y=Integer.parseInt(e[0]);
				//System.out.println(final_result.get(t));
			}
		 index=arrange(index);
		/* for(int t=0;t<index.length;t++)
		 {
			 System.out.println(index[t]);
		 }*/
			//System.out.println();
		return index;
	 }
	
}

