package com.sm.framework.control.listener;

// 这是control层中的菜单监听器，为了点击菜单中的功能显示可以操作功能的页面

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.sm.framework.control.action.org.Action;
import com.sm.framework.model.dao.impl.MyMenuDAOImpl;
import com.sm.framework.model.entity.MyMenu;
import com.sm.framework.view.MainFrame;

public class MenuListener implements ActionListener { // 继承ActionListener去监听事件，这是设置监听器去实现MainFrame的菜单功能点击事件
	
	private MainFrame mainFrame = null;               // 实例化MainFrame主页面在构造方法时会接收参数
	
	private JPanel panelBody = null;                  // panelBody获得mainframe的ContentPane
	private JPanel panelWelcome = null;               // panelWelcome获得panelBody（原mainframe）的第二个存的页面，也就是中间的操作页面
	
	private JPanel panelWork = null;                  // panelWork是中间进行操作的页面

	
	public MenuListener() {
		// TODO Auto-generated constructor stub
	}
	// 编写构造方法在MainFrame中添加监听事件
	public MenuListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		// TODO Auto-generated constructor stub
	}
	
	
	// 事件处理，点击菜单显示左下方详细操作功能按钮区域，所以这属于在别的类实现，在自己类设实现类的监听器，里面调用getPanelTop方法
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panelBody = (JPanel)this.mainFrame.getContentPane();      // 将点击之后传入的mainFrame进行getContentPane然后强转为JPanel格式给当前类的panelBody
		
		Component[] components1 = this.panelBody.getComponents();      // panelBody用getComponents获得添加的组件数组components1
		this.panelWelcome = (JPanel)components1[1];                    // components1[n]代表之前添加进panelBody(原mainFrame)的第n+1的组件，给当前类的panelWelcome
		
		this.panelWelcome.removeAll();         // 这一步的目的是为了每次点击菜单都能将原来点击而显示的业务关闭，不让显示的业务都重叠在一起
		this.panelWelcome.repaint();           // 这一步的目的是为了每次点击菜单都能将原来点击而显示的业务关闭，不让显示的业务都重叠在一起
		
		this.panelWelcome.setLayout(new BorderLayout());               // 设置panelWelcome为BorderLayout
		
		this.panelWork = new JPanel();
		this.panelWelcome.add(this.panelWork, BorderLayout.CENTER);    // 将panelWork添加设置为panelWelcome的center
		
		String menuId = e.getActionCommand();                          // 获得当前点击的按钮的functionId，因为之前每个详细操作按钮的ActionCommand都被设置为了他们各自的FunctionId
		MyMenu menuTemp = new MyMenuDAOImpl().findByMenuId(menuId);    // 调用MisFunctionDAOImpl中的findByFunctionId方法将刚获得的点击的functionId传入方法
		String functionName = menuTemp.getfunctionClass();             // 数据库中FunctionClass里存的是点击详细操作按钮之后的显示右边页面的实现类的地址，赋值给functionName
		
		try {
			// 动态加载的语句（重要！背！）
			Class functionClass = Class.forName(functionName);         // 格式：Class 对象名 = Class.forName(类的地址以及类);
			Action action = (Action)functionClass.newInstance();       // newInstance()方法就是实例化刚forname的对象functionClass，functionClass这里存的都是接口的实现类所以要强转成action
			
			action.execute(this.panelWork);                            // 然后调用action接口中的execute的方法传入panelWork实现类操作后显示，这个方法会根据每次点击的按钮不同，实例化不同的的FunctionClass（也就是不同的接口实现类，例如：如果点击的是“增加机构类别”那么实例化的是OrgTypeCreateAction实现类，显示的就是OrgTypeCreateAction的操作页面）
			
			this.mainFrame.setVisible(true);                           // 刷新语句
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.mainFrame.setVisible(true);               // 当swing添加新的组件之后需要进行刷新操作才会显示，setVisible(true)就是刷新语句
	}
	
	
}
