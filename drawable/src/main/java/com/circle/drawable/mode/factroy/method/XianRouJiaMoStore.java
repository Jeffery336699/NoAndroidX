package com.circle.drawable.mode.factroy.method;


import com.circle.drawable.mode.factroy.abtract.RouJiaMo;
import com.circle.drawable.mode.factroy.abtract.RouJiaMoYLFactroy;
import com.circle.drawable.mode.factroy.abtract.XianRouJiaMoYLFactroy;

/**
 * 西安肉夹馍店
 *
 * @author zhy
 */
public class XianRouJiaMoStore extends RoujiaMoStore {

    /**
     * 这里创建肉夹馍的过程我们已经通过factory工厂解耦出来了
     */
    @Override
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new XianSuanRouJiaMo();
        } else if (type.equals("Tian")) {
            rouJiaMo = new XianTianRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new XianLaRouJiaMo();
        }
        return rouJiaMo;
    }

    @Override
    public RouJiaMoYLFactroy createYLFactory() {
        return new XianRouJiaMoYLFactroy();
    }

}