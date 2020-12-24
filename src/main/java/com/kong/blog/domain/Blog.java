package com.kong.blog.domain;


import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//是具备生成数据库的能力
@Entity
//
@Table(name = "t_blog")
public class Blog {

    /**
     * 主键
     * 标题
     * 首图
     * 标记
     * 浏览次数
     * 是否开启赞赏
     * 版权开启
     * 是否可以转载
     * 开启评论
     * 是否可以评论
     * 是否推荐
     * 创建和修改时间
     */
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatment;
    private boolean commontabled;
    private boolean published;
    private String content;


    public String partContent(){
        return this.content.substring(0,this.content.length()/3);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    @Transient
    private String tagIds;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments=new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


    public void tagsToString(){
        StringBuilder str=new StringBuilder();
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            if (i==size-1){
                str.append(tags.get(i).getId());
            }
            else {
                str.append(tags.get(i).getId()+",");
            }
        }
        this.tagIds=str.toString();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatment=" + shareStatment +
                ", commontabled=" + commontabled +
                ", published=" + published +
                ", type=" + type +
                ", comments=" + comments +
                ", tags=" + tags +
                ", user=" + user +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatment() {
        return shareStatment;
    }

    public void setShareStatment(boolean shareStatment) {
        this.shareStatment = shareStatment;
    }

    public boolean isCommontabled() {
        return commontabled;
    }

    public void setCommontabled(boolean commontabled) {
        this.commontabled = commontabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Blog() {
    }

    public Blog(long id, String title, String firstPicture, String flag, Integer views, boolean appreciation, boolean shareStatment, boolean commontabled, boolean published, boolean recommend, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatment = shareStatment;
        this.commontabled = commontabled;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
