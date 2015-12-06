package com.sky.codingame.training;

import java.util.HashMap;
import java.util.Scanner;

public class Resistance
{
	HashMap<Character, String> table = new HashMap<Character, String>();
	Node root = new Node();
	String morse_line;

	public static void main(String args[])
	{
		new Resistance().start();
	}

	public void start()
	{
		int num_words;

		initTable();
		Scanner in = new Scanner(System.in);
		morse_line = in.nextLine();
		num_words = Integer.parseInt(in.nextLine());
		for (int i = 0; i < num_words; i++)
		{
			String word = in.nextLine();
			// System.out.println(word);
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < word.length(); j++)
			{
				char c = word.charAt(j);
				// System.out.println(c);
				sb.append(table.get(c));
			}
			word2tree(sb.toString());
		}
		System.out.println(getNumMsg(0));
		in.close();
	}

	private int getNumMsg(int position)
	{
		int maxLen = morse_line.length();
		if (position == maxLen)
			return 1;
		else
		{
			int nbMessage = 0;
			int newPossion = position;
			Node currentNode = root;
			while (newPossion < maxLen)
			{
				if (morse_line.charAt(newPossion) == '.')
				{
					if (currentNode.dotNode != null)
					{
						currentNode = currentNode.dotNode;
						newPossion++;
						if (currentNode.isComplet)
							nbMessage += getNumMsg(newPossion);
					} 
					else
					{
						break;
					}
				} 
				else
				{
					if (currentNode.dasheNode != null)
					{
						currentNode = currentNode.dasheNode;
						newPossion++;
						if (currentNode.isComplet)
							nbMessage += getNumMsg(newPossion);
					} 
					else
					{
						break;
					}
				}
			}
			return nbMessage;
		}
	}

	private void word2tree(String morseWord)
	{
		Node currentNode = root;
		for (int i = 0; i < morseWord.length(); i++)
		{
			char c = morseWord.charAt(i);
			if (c == '.')
			{
				if (currentNode.dotNode == null)
				{
					Node newNode = new Node();
					currentNode.dotNode = newNode;
				}
				currentNode = currentNode.dotNode;
			} 
			else
			{
				if (currentNode.dasheNode == null)
				{
					Node newNode = new Node();
					currentNode.dasheNode = newNode;
				}
				currentNode = currentNode.dasheNode;
			}
			if (i == morseWord.length() - 1)
			{
				currentNode.isComplet = true;
			}
		}
	}

	private void initTable()
	{
		table.put('A', ".-");
		table.put('B', "-...");
		table.put('C', "-.-.");
		table.put('D', "-..");
		table.put('E', ".");
		table.put('F', "..-.");
		table.put('G', "--.");
		table.put('H', "....");
		table.put('I', "..");
		table.put('J', ".---");
		table.put('K', "-.-");
		table.put('L', ".-..");
		table.put('M', "--");
		table.put('N', "-.");
		table.put('O', "---");
		table.put('P', ".--.");
		table.put('Q', "--.-");
		table.put('R', ".-.");
		table.put('S', "...");
		table.put('T', "-");
		table.put('U', "..-");
		table.put('V', "...-");
		table.put('W', ".--");
		table.put('X', "-..-");
		table.put('Y', "-.--");
		table.put('Z', "--..");
	}

	private class Node
	{
		Node dotNode = null;
		Node dasheNode = null;
		boolean isComplet = false;
	}

}
