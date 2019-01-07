package com.cjwsc.idcm.model.bean.providerbean;



import com.alibaba.android.arouter.facade.annotation.Route;
import com.cjwsc.idcm.constant.IdcmConstant;


/**
 * Created by ${zipp} on 2018/1/5.
 * 功能描述：
 */
@Route(path = IdcmConstant.NOTICELIST_JSON)
public class NoticeMessageBean {

    /**
     * Title : string
     * Icon : string
     * Context : string
     * Keywords : string
     * Source : string
     * Introduce : string
     * ArticleClassID : string
     * Seotitle : string
     * Description : string
     * Remark : string
     * BrowseNum : 0
     * LanguageType : string
     * SortNumber : 0
     * InternalSort : 0
     * CreatorID : string
     * CreateTime : 2018-01-05T02:33:46.394Z
     * LastUpdateUserID : string
     * LastUpdateTime : 2018-01-05T02:33:46.394Z
     * ArticleClassName : string
     * ID : string
     */

    private String Title;
    private String Icon;
    private String Context;
    private String Keywords;
    private String Source;
    private String Introduce;
    private String ArticleClassID;
    private String Seotitle;
    private String Description;
    private String Remark;
    private int BrowseNum;
    private String LanguageType;
    private int SortNumber;
    private int InternalSort;
    private String CreatorID;
    private String CreateTime;
    private String LastUpdateUserID;
    private String LastUpdateTime;
    private String ArticleClassName;
    private String ID;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String Keywords) {
        this.Keywords = Keywords;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String Introduce) {
        this.Introduce = Introduce;
    }

    public String getArticleClassID() {
        return ArticleClassID;
    }

    public void setArticleClassID(String ArticleClassID) {
        this.ArticleClassID = ArticleClassID;
    }

    public String getSeotitle() {
        return Seotitle;
    }

    public void setSeotitle(String Seotitle) {
        this.Seotitle = Seotitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public int getBrowseNum() {
        return BrowseNum;
    }

    public void setBrowseNum(int BrowseNum) {
        this.BrowseNum = BrowseNum;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String LanguageType) {
        this.LanguageType = LanguageType;
    }

    public int getSortNumber() {
        return SortNumber;
    }

    public void setSortNumber(int SortNumber) {
        this.SortNumber = SortNumber;
    }

    public int getInternalSort() {
        return InternalSort;
    }

    public void setInternalSort(int InternalSort) {
        this.InternalSort = InternalSort;
    }

    public String getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(String CreatorID) {
        this.CreatorID = CreatorID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getLastUpdateUserID() {
        return LastUpdateUserID;
    }

    public void setLastUpdateUserID(String LastUpdateUserID) {
        this.LastUpdateUserID = LastUpdateUserID;
    }

    public String getLastUpdateTime() {
        return LastUpdateTime;
    }

    public void setLastUpdateTime(String LastUpdateTime) {
        this.LastUpdateTime = LastUpdateTime;
    }

    public String getArticleClassName() {
        return ArticleClassName;
    }

    public void setArticleClassName(String ArticleClassName) {
        this.ArticleClassName = ArticleClassName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
