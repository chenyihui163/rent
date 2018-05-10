package com.rent.entity;

import java.io.Serializable;

public class Config implements Serializable {
    private String id;

    private String houseId;

    private Integer bed;

    private Integer washClothes;

    private Integer airConditioner;

    private Integer balcony;

    private Integer fridge;

    private Integer washRoom;

    private Integer cooking;

    private Integer tv;

    private Integer sofa;

    private Integer heating;

    private Integer wardrobe;

    private Integer network;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Integer getWashClothes() {
        return washClothes;
    }

    public void setWashClothes(Integer washClothes) {
        this.washClothes = washClothes;
    }

    public Integer getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Integer airConditioner) {
        this.airConditioner = airConditioner;
    }

    public Integer getBalcony() {
        return balcony;
    }

    public void setBalcony(Integer balcony) {
        this.balcony = balcony;
    }

    public Integer getFridge() {
        return fridge;
    }

    public void setFridge(Integer fridge) {
        this.fridge = fridge;
    }

    public Integer getWashRoom() {
        return washRoom;
    }

    public void setWashRoom(Integer washRoom) {
        this.washRoom = washRoom;
    }

    public Integer getCooking() {
        return cooking;
    }

    public void setCooking(Integer cooking) {
        this.cooking = cooking;
    }

    public Integer getTv() {
        return tv;
    }

    public void setTv(Integer tv) {
        this.tv = tv;
    }

    public Integer getSofa() {
        return sofa;
    }

    public void setSofa(Integer sofa) {
        this.sofa = sofa;
    }

    public Integer getHeating() {
        return heating;
    }

    public void setHeating(Integer heating) {
        this.heating = heating;
    }

    public Integer getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(Integer wardrobe) {
        this.wardrobe = wardrobe;
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }
}