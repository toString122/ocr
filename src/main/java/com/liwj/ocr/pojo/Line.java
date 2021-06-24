package com.liwj.ocr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: liwj
 * @create: 2021-06-24 11:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    private int confidence;
    private Word[] word;
}
