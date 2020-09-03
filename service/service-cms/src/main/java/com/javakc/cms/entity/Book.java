package com.javakc.cms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_book")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid",strategy = "uuid")
    @ApiModelProperty(value = "书籍主键，采用hibernate的uuid生成32位字符串")
    private String id;

    @Column(name = "title")
    @ApiModelProperty(value = "书名")
    private String title;

    @Column(name = "gmt_create",nullable = false,updatable = false)
    @ApiModelProperty(value = "创建时间",example = "2020-02-12 9:00:12")
    @CreatedDate  //Jpa自动添加时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  //设置自动填充时的格式
    private Date gmtCreate;

    @Column(name = "gmt_modified",nullable = false,insertable = false)
    @ApiModelProperty(value = "更新时间")
    @LastModifiedDate   //Jpa自动添加时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  //设置自动填充时的格式
    private Date gmtModified;
}
