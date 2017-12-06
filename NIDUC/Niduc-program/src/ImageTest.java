import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageTest 
{
	
	public static void main(String[] args) {

	    try {
	    	double d,val;
	    	String s;
	    	char c;
	    	int i;
	        byte[] imageInByte; 
	        String imageInString = " ";
	        String imageFromString = " ";
	        String temp;
	        int binary;
	        
	        BufferedImage originalImage = ImageIO.read(new File("source.jpg"));

	        // convert BufferedImage to byte array
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(originalImage, "jpg", byteArrayOutputStream);
	        byteArrayOutputStream.flush();
	        imageInByte = byteArrayOutputStream.toByteArray();
	    	byte[] imageFromByte=imageInByte;
	        for (i = 0; i < imageInByte.length; i++)
	        {
	        	binary=imageInByte[i];//konwersja zmiennej do systemu z polaryzacj¹
	        	
	        	System.out.println(i + "/" + imageInByte.length);
	        	
	        	binary=binary+128;
	        	temp = Integer.toString(binary,2);
	        	while(temp.length()<8) temp="0"+temp;
	        	imageInString=imageInString.concat(temp);
	        }
	           
	        //Random random=new Random();
	        for (int n=1;n<imageInString.length();n++)
	        {
	        	System.out.println(n + "/" + imageInString.length());
	        	
	        	c=imageInString.charAt(n);
	        	val=Character.getNumericValue(c);
	        	val=val*5;
	        	
	        	Random random=new Random();
	        	d=random.nextGaussian();
	        	d=d/1.5;
	        	val=val+d;
	        	
	        	if (val>2.5) c='1';
	        	if (val<=2.5) c='0';
	        	
	        	s=Character.toString(c);
	        	imageFromString=imageFromString.concat(s);
	        }
	        
	        for (int n=0;n<i;n++)
	        {
	      
	        	System.out.println(n + "/" + i);
	        	temp=imageFromString.substring((8*n)+1,(8*n)+9);
	        	binary = Integer.parseInt(temp, 2);
	        	binary=binary-128;
				imageFromByte[n]=(byte) binary;

	        	
	        }
	        
	        try(  PrintWriter out = new PrintWriter( "filename.txt" )  )
	        {
	            out.println(imageInString);
	        }	        	        
	        byteArrayOutputStream.close();
	            
	        
	        
	        InputStream in = new ByteArrayInputStream(imageFromByte);
	        BufferedImage bImageFromConvert = ImageIO.read(in);

	        ImageIO.write(bImageFromConvert, "jpg", new File("output.jpg"));
	        System.out.println("Gotowe");

	    } catch (IOException e) 
	    {
	        System.out.println(e.getMessage());
	    }
	}
}