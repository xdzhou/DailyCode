package com.sky.designpattern.template;

public class StringDisplay extends AbstractDisplay
{
	private String string; // Ӧ������ַ���
	private int width; // ��byteΪ��λ��������ַ�����"����"

	public StringDisplay(String string)
	{
		this.string = string;
		width = string.length();
	}

	public void open()
	{ // ��ӡͷװ���ַ���
		printLine();
	}

	public void print()
	{ // ��ӡ����
		System.out.println("|" + string + "|");
	}

	public void close()
	{ // ��ӡβװ���ַ���
		printLine();
	}

	public void printLine()
	{
		System.out.print("+"); // ���"+"�ű�ʾ�߿�λ��
		for (int i = 0; i < width; ++i)
		{
			System.out.print("-"); // �����߶�
		}
		System.out.println("+"); // ���"+"�ű�ʾ�߿�λ��
	}

}
