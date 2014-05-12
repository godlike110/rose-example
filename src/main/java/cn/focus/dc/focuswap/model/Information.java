package cn.focus.dc.focuswap.model;

/**
 * Created with IntelliJ IDEA. User: zhengxuan Date: 13-10-25 Time: 下午1:43
 * http://10.10.90.156/wiki/index.php/News/info
 */
public class Information extends News {

    private static final long serialVersionUID = -5858004648340396089L;
    
    public Information(){}
    
    private String author;
    
    private Integer cityId;
    
    private String keywords;
    
    private Integer splitPage;//正文分页数
    
    private Integer templateType;//0:图片新闻 1:幻灯片新闻
    
    private String sourceName;//新闻来源
    
    private Integer relatedId;//相关新闻的Id
    
    private String newsDesc;
    
    private String tagsAndKeyword;
    
    private PageContent pageContent;
    
    public String getTagsAndKeyword() {
        return tagsAndKeyword;
    }

    public void setTagsAndKeyword(String tagsAndKeyword) {
        this.tagsAndKeyword = tagsAndKeyword;
    }

    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public PageContent getPageContent() {
        return pageContent;
    }

    public void setPageContent(PageContent pageContent) {
        this.pageContent = pageContent;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }   

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getShowTime() {
        return getTime();
    }

    public void setShowTime(String showTime) {
        setTime(showTime);
    }

    public Integer getSplitPage() {
        return splitPage;
    }

    public void setSplitPage(Integer splitPage) {
        this.splitPage = splitPage;
    }


    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }


}
