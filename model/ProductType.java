package model;

import java.util.ArrayList;

public class ProductType {
    private String name;
    private ProcessLine processLine = null;
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

    /**
     * newer call this method
     * @param processLine
     * @throws RuntimeException
     */
    public void setProcessLine(ProcessLine processLine) throws RuntimeException{
    	if (this.processLine!=null){
    		throw new RuntimeException("processLine is already set");
    	} else {
    		this.processLine=processLine;
    	}
    }

    public ArrayList<IntermediateProduct> getIntermediateProducts(){
        return this.intermediateProducts;
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
    	this.removeIntermediateProduct(intermediateProduct);
    }

}
