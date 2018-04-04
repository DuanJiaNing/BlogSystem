package com.duan.blogos.dto.blog;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/4/4.
 *
 * @author DuanJiaNing
 */
@Data
public class BlogTitleIdDTO implements Serializable {

    private static final long serialVersionUID = -7530640518956302887L;

    private String title;

    private int id;

}
