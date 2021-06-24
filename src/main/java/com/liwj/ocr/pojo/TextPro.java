package com.liwj.ocr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 识别返回对象
 * @author: liwj
 * @create: 2021-06-24 10:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextPro {
    private String code;
    private com.liwj.ocr.pojo.Data data;
    private String desc;
    private String sid;

}
