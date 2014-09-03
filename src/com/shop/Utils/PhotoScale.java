package com.shop.Utils;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class PhotoScale {

				public static void imageScale(File fi,File fo,int nw) {
				try {
				
				/*
				AffineTransform ���ʾ 2D ����任����ִ�д� 2D ���굽���� 2D
				���������ӳ�䣬�������ߵġ�ֱ���ԡ��͡�ƽ���ԡ�������ʹ��һϵ
				��ƽ�ơ����š���ת����ת�ͼ������������任��
				*/
				AffineTransform transform = new AffineTransform();
				BufferedImage bis = ImageIO.read(fi); //��ȡͼƬ
				int w = bis.getWidth();
				int h = bis.getHeight();
				 //double scale = (double)w/h;
				int nh = (nw*h)/w ;
				double sx = (double)nw/w;
				double sy = (double)nh/h;
				transform.setToScale(sx,sy); //setToScale(double sx, double sy) ���˱任����Ϊ���ű任��
				
				/*
				 * AffineTransformOp��ʹ�÷���ת����ִ�д�Դͼ��� Raster �� 2D ���굽Ŀ��ͼ���
				 *  Raster �� 2D ���������ӳ�䡣��ʹ�õĲ�ֵ�����ɹ��췽��ͨ��
				 *  һ�� RenderingHints �����ͨ�������ж����������ֵ����֮һ��ָ����
				����ڹ��췽����ָ���� RenderingHints ������ʹ�ò�ֵ��ʾ�ͳ���
				��������ʾΪ�˲������ò�ֵ���͡�Ҫ�������ɫת��ʱ������ʹ����ɫ
				������ʾ�Ͷ�����ʾ�� ע�⣬���Ҫ��������Լ����Դͼ����Ŀ��ͼ��
				���벻ͬ�� ���� Raster ����Դͼ���е� band ���������Ŀ��ͼ����
				�� band ����
				*/
				AffineTransformOp ato = new AffineTransformOp(transform,null);
				BufferedImage bid = new BufferedImage(nw,nh,BufferedImage.TYPE_3BYTE_BGR);
				/*
				 * TYPE_3BYTE_BGR ��ʾһ������ 8 λ RGB ��ɫ������ͼ��
				 * ��Ӧ�� Windows ���� BGR ��ɫģ�ͣ������� 3 �ֽڴ�
				 * ���� Blue��Green �� Red ������ɫ��
				*/
				ato.filter(bis,bid);
				ImageIO.write(bid,"jpeg",fo);
				} catch(Exception e) {
				e.printStackTrace();
				}
				}
				
				
				/*public static void main(String arg[])
				{
					File fi = new File("D:/picture/test.jpg");
					File fo = new File("D:/picture/test8.jpg");
					imageScale(fi, fo, 150);
				}*/

}