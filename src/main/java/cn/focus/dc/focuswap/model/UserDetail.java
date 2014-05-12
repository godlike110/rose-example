package cn.focus.dc.focuswap.model;

import cn.focus.dc.commons.annotation.PrimaryKey;
import cn.focus.dc.commons.model.BaseObject;

public class UserDetail extends BaseObject {

    private static final long serialVersionUID = -6806170385010386311L;

    
    @PrimaryKey
    private Integer uid;

    private Integer appId;

    private String nickName;

    private String headImg;

    private Integer age;

    private Integer downPayment;

    private Integer isAccepted;

    private Integer income;

    private Integer city;
    
    private Integer targetArea;

    private Integer followCount;

    private Integer favoriteCount;

    private Integer quizCount;
    
    private java.util.Date updateTime;

    public void copy(UserDetail userdetail){
               this.uid = userdetail.uid;
               this.appId = userdetail.appId;
               this.nickName = userdetail.nickName;
               this.headImg = userdetail.headImg;
               this.age = userdetail.age;
               this.downPayment = userdetail.downPayment;
               this.isAccepted = userdetail.isAccepted;
               this.income = userdetail.income;
               this.city = userdetail.city;
               this.targetArea = userdetail.targetArea;
               this.followCount = userdetail.followCount;
               this.favoriteCount = userdetail.favoriteCount;
               this.quizCount = userdetail.quizCount;
               this.updateTime = userdetail.updateTime;
          }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Integer getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Integer isAccepted) {
        this.isAccepted = isAccepted;
    }
    
    public Integer getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Integer downPayment) {
        this.downPayment = downPayment;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(Integer targetArea) {
        this.targetArea = targetArea;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }
    
    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
    
    public Integer getQuizCount() {
        return quizCount;
    }

    public void setQuizCount(Integer quizCount) {
        this.quizCount = quizCount;
    }
    
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}
