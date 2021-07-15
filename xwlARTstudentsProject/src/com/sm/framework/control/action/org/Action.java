package com.sm.framework.control.action.org;

// 这个接口是为了实现在MenuListener中点击操作菜单显示具体操作的页面，是control层下的action代码

import javax.swing.JPanel;

public interface Action {
	
	public void execute(JPanel panelWork);            // execute方法是实现点击详细操作显示的页面的功能，然后根据点击的按钮不同在FunctionListener中实例化不同的的FunctionClass
	
}
