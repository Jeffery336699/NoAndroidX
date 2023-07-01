package com.circle.drawable.mode.factroy.errer;

import com.circle.drawable.mode.factroy.abtract.RouJiaMo;
import com.circle.drawable.mode.factroy.abtract.XianRouJiaMoYLFactroy;
import com.circle.drawable.mode.factroy.data.LaRouJiaMo;

import com.circle.drawable.mode.factroy.data.SuanRouJiaMo;
import com.circle.drawable.mode.factroy.data.TianRouJiaMo;

/**
 * 生产馍的种类和你的RoujiaMoStore耦合度太高了，如果增加几种风味，删除几种风味，
 * 你得一直修改sellRouJiaMo中的方法，所以我们需要做一定的修改，此时简单工厂模式就能派上用场了。
 * TODO:Store跟肉夹馍的种类耦合度太高，Store应该只专注“买肉夹馍”的操作
 */
public class RoujiaMoStore {

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;

        if (type.equals("Suan")) {
            rouJiaMo = new SuanRouJiaMo();

        } else if (type.equals("Tian")) {
            rouJiaMo = new TianRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new LaRouJiaMo();
        }

        rouJiaMo.prepare(new XianRouJiaMoYLFactroy());
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }

}
