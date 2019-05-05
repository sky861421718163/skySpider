package com.sky.spider.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseMonitorMain implements Serializable {
    /**
     * 自增id-监测对象
     */
    private Long id;

    /**
     * 名称
     */
    private String fdName;

    /**
     * 类型：0: 集团 ,1:品牌,2:单体
     */
    private String fdType;

    /**
     * 上级id
     */
    private Long fdBrandId;

    /**
     * 官网链接
     */
    private String fdObjectWebsit;

    /**
     * 介绍
     */
    private String fdIntroduce;

    /**
     * 省
     */
    private Integer fdProvinceId;

    /**
     * 市
     */
    private Integer fdCityId;

    /**
     * 区
     */
    private Integer fdAreaId;

    /**
     * 对象logo
     */
    private String fdLogoUrl;

    /**
     * 微博链接
     */
    private String fdMicroblogUrl;

    /**
     * 创建人
     */
    private String fdCreateId;

    /**
     * 创建时间
     */
    private Date docCreateTime;

    /**
     * 修改人
     */
    private String fdAlterId;

    /**
     * 修改时间
     */
    private Date docAlterTime;

    /**
     * 是否有效
     */
    private Boolean status;

    /**
     * 排序号
     */
    private Integer fdOrder;

    private String fdSpecialUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName == null ? null : fdName.trim();
    }

    public String getFdType() {
        return fdType;
    }

    public void setFdType(String fdType) {
        this.fdType = fdType == null ? null : fdType.trim();
    }

    public Long getFdBrandId() {
        return fdBrandId;
    }

    public void setFdBrandId(Long fdBrandId) {
        this.fdBrandId = fdBrandId;
    }

    public String getFdObjectWebsit() {
        return fdObjectWebsit;
    }

    public void setFdObjectWebsit(String fdObjectWebsit) {
        this.fdObjectWebsit = fdObjectWebsit == null ? null : fdObjectWebsit.trim();
    }

    public String getFdIntroduce() {
        return fdIntroduce;
    }

    public void setFdIntroduce(String fdIntroduce) {
        this.fdIntroduce = fdIntroduce == null ? null : fdIntroduce.trim();
    }

    public Integer getFdProvinceId() {
        return fdProvinceId;
    }

    public void setFdProvinceId(Integer fdProvinceId) {
        this.fdProvinceId = fdProvinceId;
    }

    public Integer getFdCityId() {
        return fdCityId;
    }

    public void setFdCityId(Integer fdCityId) {
        this.fdCityId = fdCityId;
    }

    public Integer getFdAreaId() {
        return fdAreaId;
    }

    public void setFdAreaId(Integer fdAreaId) {
        this.fdAreaId = fdAreaId;
    }

    public String getFdLogoUrl() {
        return fdLogoUrl;
    }

    public void setFdLogoUrl(String fdLogoUrl) {
        this.fdLogoUrl = fdLogoUrl == null ? null : fdLogoUrl.trim();
    }

    public String getFdMicroblogUrl() {
        return fdMicroblogUrl;
    }

    public void setFdMicroblogUrl(String fdMicroblogUrl) {
        this.fdMicroblogUrl = fdMicroblogUrl == null ? null : fdMicroblogUrl.trim();
    }

    public String getFdCreateId() {
        return fdCreateId;
    }

    public void setFdCreateId(String fdCreateId) {
        this.fdCreateId = fdCreateId == null ? null : fdCreateId.trim();
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }

    public void setDocCreateTime(Date docCreateTime) {
        this.docCreateTime = docCreateTime;
    }

    public String getFdAlterId() {
        return fdAlterId;
    }

    public void setFdAlterId(String fdAlterId) {
        this.fdAlterId = fdAlterId == null ? null : fdAlterId.trim();
    }

    public Date getDocAlterTime() {
        return docAlterTime;
    }

    public void setDocAlterTime(Date docAlterTime) {
        this.docAlterTime = docAlterTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getFdOrder() {
        return fdOrder;
    }

    public void setFdOrder(Integer fdOrder) {
        this.fdOrder = fdOrder;
    }

    public String getFdSpecialUrl() {
        return fdSpecialUrl;
    }

    public void setFdSpecialUrl(String fdSpecialUrl) {
        this.fdSpecialUrl = fdSpecialUrl == null ? null : fdSpecialUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fdName=").append(fdName);
        sb.append(", fdType=").append(fdType);
        sb.append(", fdBrandId=").append(fdBrandId);
        sb.append(", fdObjectWebsit=").append(fdObjectWebsit);
        sb.append(", fdIntroduce=").append(fdIntroduce);
        sb.append(", fdProvinceId=").append(fdProvinceId);
        sb.append(", fdCityId=").append(fdCityId);
        sb.append(", fdAreaId=").append(fdAreaId);
        sb.append(", fdLogoUrl=").append(fdLogoUrl);
        sb.append(", fdMicroblogUrl=").append(fdMicroblogUrl);
        sb.append(", fdCreateId=").append(fdCreateId);
        sb.append(", docCreateTime=").append(docCreateTime);
        sb.append(", fdAlterId=").append(fdAlterId);
        sb.append(", docAlterTime=").append(docAlterTime);
        sb.append(", status=").append(status);
        sb.append(", fdOrder=").append(fdOrder);
        sb.append(", fdSpecialUrl=").append(fdSpecialUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}