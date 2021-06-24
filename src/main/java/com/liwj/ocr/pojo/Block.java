package com.liwj.ocr.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: liwj
 * @create: 2021-06-24 11:01
 **/
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    private String type;
    private Line[] line;

}
