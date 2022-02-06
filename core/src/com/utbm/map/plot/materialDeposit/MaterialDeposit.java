package com.utbm.map.plot.materialDeposit;

import com.utbm.Material;

public class MaterialDeposit{

    private Material material;
    private int currentAmount;

    public MaterialDeposit(Material p_material, int p_amount){
        setCurrentAmount(p_amount);
        setMaterial(p_material);
    }

    public int extract(int p_amountToExtract){
        int amountExtracted = 0;

        if(p_amountToExtract <= this.currentAmount){
            amountExtracted = p_amountToExtract;
        }else{
            amountExtracted = this.currentAmount;
        }
        this.currentAmount -= amountExtracted;
        System.out.println("Amoit extrreacted : " + amountExtracted + "restant : " +this.currentAmount);
        return amountExtracted;
    }

    public Material getMaterial() {
        return material;
    }

    private void setMaterial(Material p_material) {
        this.material = p_material;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    private void setCurrentAmount(int p_amount) {
        if(p_amount >= 0) {
            this.currentAmount = p_amount;
        }
    }
}
