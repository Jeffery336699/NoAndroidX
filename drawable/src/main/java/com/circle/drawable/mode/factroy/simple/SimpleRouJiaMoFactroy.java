package com.circle.drawable.mode.factroy.simple;

import com.circle.drawable.mode.factroy.abtract.RouJiaMo;
import com.circle.drawable.mode.factroy.data.LaRouJiaMo;

import com.circle.drawable.mode.factroy.data.SuanRouJiaMo;
import com.circle.drawable.mode.factroy.data.TianRouJiaMo;

/**
 * 简单工厂，把产生馍的过程拿出来
 */
public class SimpleRouJiaMoFactroy {
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new SuanRouJiaMo();

        } else if (type.equals("Tian")) {
            rouJiaMo = new TianRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new LaRouJiaMo();
        }
        return rouJiaMo;
    }

}