/*�����Ͱ� ����ȭ�� ���԰��踦 ǥ���� �� ���� ���Ǵ� Tree�� �������!*/

package day1117.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeApp extends JFrame{
	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {
		//Ʈ���� ������ ��带 �����غ���
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("�׼�����");
		createNode(top);  //����, �����, �Ͱ���, ��� ������ ������. ���� �ֻ��� ��带 �Ѱ��ְ��� 
		
		tree = new JTree(top);  //���⿡ ��带 ������ �μ��� �־��� ������
		scroll = new JScrollPane(tree);
		
		add(scroll);
		
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void createNode(DefaultMutableTreeNode top) {
		//��ܹ��� ž ��忡 ���ϴ� ������带 �����Ͽ� �ڽ� ���� ������
		DefaultMutableTreeNode[] node = new DefaultMutableTreeNode[4];
		node[0] = new DefaultMutableTreeNode("����");
		node[1] = new DefaultMutableTreeNode("�����");
		node[2] = new DefaultMutableTreeNode("�Ͱ���");
		node[3] = new DefaultMutableTreeNode("����");
		
		//������ ��带 top����� �������� ����
		for(DefaultMutableTreeNode obj : node) {
			top.add(obj);
		}
	}
	
	public static void main(String[] args) {
		new TreeApp();
	}
}
