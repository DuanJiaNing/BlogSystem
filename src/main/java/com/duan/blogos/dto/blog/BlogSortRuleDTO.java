package com.duan.blogos.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2018/2/12.
 *
 * @author DuanJiaNing
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSortRuleDTO {

    // 排序关键字
    private String key;

    // 排序说明
    private String title;

}
