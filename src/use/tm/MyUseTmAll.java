package use.tm;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SqlLink.TmCon;
import manage.Tm;

public class MyUseTmAll extends JPanel implements ActionListener {

	JPanel p1, p2;
	JButton b1;
	JTable table;
	JScrollPane pane;
	JLabel l1;
	int no;
	Tm tm;
	TmCon bean = new TmCon();
	DefaultTableModel dtm;
	
	
	public MyUseTmAll() {

		this.setLayout(new BorderLayout());
		
		l1=new JLabel("题库信息");
		p1=new JPanel();
		p2=new JPanel();
		b1=new JButton("选择");
		table=new JTable();
		pane=new JScrollPane(table);
		b1.addActionListener(this);
		
		l1.setFont(new Font("", 1, 30));
		b1.setFont(new Font("", 1, 20));
		table.setFont(new Font("", 0, 20));

		p1.add(l1);
		p2.add(b1);
		
		this.add(p1,BorderLayout.NORTH);
		this.add(pane,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		LinkedList<Tm> list = bean.findAll();
		Object[][] content=new Object[list.size()][4];
		Object head[]={"题号", "题目", "正确次数", "错误次数"};
		for(int i = 0;i < list.size(); i++)
		{
			tm = list.get(i);
			content[i][0] = tm.getId() + "";
			content[i][1] = tm.getName();
			content[i][2] = tm.getTr();
			content[i][3] = tm.getFl();
		}
		dtm=(DefaultTableModel)table.getModel();
		dtm.setDataVector(content, head); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		Object obj = e.getSource();
		if(obj == b1) {
			int row=table.getSelectedRow();
			if(row < 0) 
				JOptionPane.showMessageDialog(this, "你还未选中任何信息");
			else {
				Object value = table.getValueAt(row, 0);
				int sid = Integer.parseInt(value + "");
				Tm tm = bean.findByNo(sid);
				new MyUseTmOne(this, tm);
			}
		}
	}
}
