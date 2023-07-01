package com.circle.drawable.mode.factroy.simple;


import com.circle.drawable.mode.factroy.abtract.RouJiaMo;
import com.circle.drawable.mode.factroy.abtract.XianRouJiaMoYLFactroy;

/**
 * 借助简单工厂, 随便添加什么种类的馍，删除什么种类的馍就和Store无关了，就是么~人家只负责卖馍么~ 这就是简单工厂模式
 * TODO: 把error中sellRouJiaMo方法中,与馍的种类的耦合代码解耦出来了,生产馍的过程交给工厂
 */
public class RoujiaMoStore {
    private SimpleRouJiaMoFactroy factroy;

    public RoujiaMoStore(SimpleRouJiaMoFactroy factroy) {
        this.factroy = factroy;
    }

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = factroy.createRouJiaMo(type);
        rouJiaMo.prepare(new XianRouJiaMoYLFactroy());
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }

}