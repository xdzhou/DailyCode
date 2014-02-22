package com.sky.codingame.match;

import java.util.HashMap;
import java.util.Scanner;

public class Q2Image {
	public static void main(String args[]) {
		HashMap<Integer, Character> notes = new HashMap<Integer, Character>();
		notes.put(0, 'G');notes.put(1, 'F');notes.put(2, 'E');
		notes.put(3, 'D');notes.put(4, 'C');notes.put(5, 'B');
		notes.put(6, 'A');notes.put(7, 'G');notes.put(8, 'F');
		notes.put(9, 'E');notes.put(10, 'D');notes.put(11, 'C');
		String image[] = new String[100];
		int H,W;
		
		Scanner in = new Scanner(System.in);
		String[] hw = in.nextLine().split(" ");
		W = Integer.parseInt(hw[0]);
		H = Integer.parseInt(hw[1]);
		String buf = in.nextLine();
		image[0] = "W 10 ";
		
		int len = 10;
		int potion = 0;
		String[] data = buf.split(" ");
		for(int i=2; i<data.length; i=i+2){
			//System.out.println(data[i]);
			if(len < W) {
				image[potion] += data[i];
				int l = Integer.parseInt(data[i+1]);
				if(len+l > W){
					image[potion] += (" "+(W - len)+" ");
					potion++;
					if(potion>99) break;
					len = l-(W - len);
					if(len < W) image[potion] = data[i] + (" "+len+" ");
					else {
						int n = len/W;
						for(int j=0; j<n; j++){					
							image[potion+j] = data[i]+" "+W;
						}				
						potion += n;
						if(potion > 99) break;
						int rest = len % W;
						len = rest;
						image[potion] = data[i]+" "+rest+" ";
					}
				}else{
					image[potion] += (" "+l+" ");
					len +=l;
				}
			}
		}
		char c ='Q';
		int num = 0;
		if(image[13].contains("B 22")) num = 2;
		else if(image[37].contains("B 22")) num = 4;
		else if(image[61].contains("B 22")) num = 6;
		else if(image[85].contains("B 22")) num = 8;
		
		else if(image[4].contains("B 22")) num = 1;
		else if(image[28].contains("B 22")) num = 3;
		else if(image[52].contains("B 22")) num = 5;
		else if(image[76].contains("B 22")) num = 7;
		
		else if(image[13].contains("W 18")) {num = 2;c ='H';}
		else if(image[37].contains("W 18")) {num = 4;c ='H';}
		else if(image[61].contains("W 18")) {num = 6;c ='H';}
		else if(image[85].contains("W 18")) {num = 8;c ='H';}
		
		else if(image[4].contains("W 18")) {num = 1;c ='H';}
		else if(image[28].contains("W 18")) {num = 3;c ='H';}
		else if(image[52].contains("W 18")) {num = 5;c ='H';}
		else if(image[76].contains("W 18")) {num = 7;c ='H';}
		
		System.out.println(notes.get(num)+""+c);
	}

}
