package com.sky.designpattern.template;

/**
 * ������,�䵱ģ���ɫ
 * 
 * @author administrator
 *         ģ����ָ�ڱ�Ƭ���ϰ�����д�ֺ��ڿ�,��ʹ��ë�ʻ�ɫ��Ϳ���ڿղ���,���ܿ���
 *         ���ֹ����Բ�ʧ����������,
 *         ����ģ���ϵ��ڿ���״,���Ͼ�֪��������ʲô���ӵ���,����ʵ�
 *         ��������ֳ�������������Ҫ����ʹ�õĻ����������.
 *         �ú�ɫǩ�ֱʵ�����,�����Ȼ����ǩ�ֱʵ�����;����Ǧ������,�õ���Ҳֻ���ǻҺ�ɫ��Ǧ����;
 *         �����������ɫ�Ĳ�ɫ��,��Ȼ�ܴ��������ۻ��Ķ�ɫ��.����,����ʹ�������ľ�,��������
 *         �������������Ѳ���ģ�����Ѿ��̶�����״��
 */
public abstract class AbstractDisplay
{
	// ������ʵ�ֵĳ��󷽷�
	public abstract void open();

	public abstract void print();

	public abstract void close();

	// ������ʵ�ֵķ���,final���Ա�֤�����಻�ᱻ�޸�
	public final void display()
	{
		open();
		for (int i = 0; i < 5; i++)
		{
			print();
		}
		close();
	}
}
