package com.learn.snnipet.excel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @Author: Snowson
 * @Date: 2018/12/20 15:18
 * @Description:
 */
@Data
public class ExcelEntityVO implements Serializable {
    private static final long serialVersionUID = -3828506533313627162L;

    private String type;
    private String primaryKey;
    private List<Map<String, EntityVO>> properties;
}
