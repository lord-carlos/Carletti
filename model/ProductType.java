package model;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/** 
 * @author M. C. Høj
 */


ublic class ProductType {
    private String name;
    private ProcessLine processLine = null;
    private ImageIcon picture = null;
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
		return picture;
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
    
    public void setPicture(ImageIcon picture) {
		this.picture = picture;
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
