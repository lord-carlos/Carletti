package model;

import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
 * @author M. C. Høj
 */


public class ProductType {
    private String name;
    private ProcessLine processLine = null;
    private byte[] binaryPicture = null;
    private ArrayList<IntermediateProduct> intermediateProducts  = new ArrayList<IntermediateProduct>();

    public ProductType(String name){
    	this.setName(name);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
    	this.name=name;
    }

    public ProcessLine getProcessLine(){
        return this.processLine;
    }
    
    public ImageIcon getPicture() {
		return new ImageIcon(binaryPicture);
	}

    /**
     * not recomended to call this method
     * @param processLine
     */
    public void setProcessLine(ProcessLine processLine){
    		this.processLine=processLine;
    }

    public ArrayList<IntermediateProduct> getIntermediateProducts(){
        return this.intermediateProducts;
    }
    
    public void setPicture(ImageIcon picture) throws IOException {
    	
    	int resizeWidth = picture.getIconWidth();
    	int resizeHeight = picture.getIconHeight();
    	
    	
    	ByteArrayOutputStream buffered = new ByteArrayOutputStream();
    	
    	BufferedImage bi = new BufferedImage(resizeWidth, resizeHeight, BufferedImage.TYPE_INT_RGB);
    	
    	Panel p = new Panel();
    	Graphics2D big = bi.createGraphics();
    	big.drawImage(picture.getImage(), 0, 0, p);
    	
    	JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(buffered);
    	try {
			encoder.encode(bi);
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.binaryPicture = buffered.toByteArray();
	}

    public void addIntermediateProduct(IntermediateProduct intermediateProduct){
    	this.intermediateProducts.add(intermediateProduct);
    	if (intermediateProduct.getProductType()!=this){
    		intermediateProduct.setProductType(this);
    	}
    }

    /**
     * always call this method trougth setProductType method in IntermediateProduct
     * @param intermediateProduct
     */
    public void removeIntermediateProduct(IntermediateProduct intermediateProduct){
    	this.intermediateProducts.remove(intermediateProduct);
    }
    
    public String toString() {
    	return this.name;
    }

}
