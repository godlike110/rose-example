package cn.focus.dc.focuswap.model;

import cn.focus.dc.commons.model.BaseObject;

import java.util.List;
public class BuildingLayoutPhotoes extends BaseObject {

    private static final long serialVersionUID = 4879172440078249552L;

    private Integer type;

    private Integer count;

    private List<ProjPhotoesExt> photoes;
    
    private String minMaxArea;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProjPhotoesExt> getPhotoes() {
        return photoes;
    }

    public void setPhotoes(List<ProjPhotoesExt> photoes) {
        this.photoes = photoes;
    }

    public String getMinMaxArea() {
        return minMaxArea;
    }

    public void setMinMaxArea(String minMaxArea) {
        this.minMaxArea = minMaxArea;
    }
}
