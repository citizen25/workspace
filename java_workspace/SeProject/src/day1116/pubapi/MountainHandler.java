/*SAX Parsing�� ��� node(���, �ؽ�Ʈ �� xml�� �̷�� ��ҵ��� ������)���� �̺�Ʈ�� �߻������ִ� ��ü*/

package day1116.pubapi;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MountainHandler extends DefaultHandler{
	//�߰ߵǴ� ���� ���� ��, VO�� ���� ��, �� vo�� ��Ե� ����
	Vector<Mountain> mtList;
	Mountain mt;  //���� ���� 1���� �ӽ������� ���� ����

	//���� ����ΰ� ��� ��ġ�� �������� �˱� ���� ����
	boolean isMntnid;
	boolean isMntnnm;
	boolean isMntninfopoflc;
	boolean isMntninfohght;
	
	//xml������ ���۵� �� ȣ��Ǵ� �޼���
	public void startDocument() throws SAXException {
		
	}
	
	//�����±׸� ���� �� ȣ��Ǵ� �޼���
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {		
		//System.out.print("<"+tag+">");  //���� �±� ���
		if(tag.equals("items")) {  //Vector �����
			mtList = new Vector<Mountain>();	
		}else if(tag.equals("item")) {  //VO ����
			mt = new Mountain();
		}else if(tag.equals("mntnid")) {  //����ΰ� ���̵� �������ٰ� ǥ��
			isMntnid = true;
		}else if(tag.equals("mntnnm")) {  //����ΰ� �� �̸��� �������ٰ� ǥ��
			isMntnnm = true;
		}else if(tag.equals("mntninfopoflc")) {  //����ΰ� ���� ��ġ�� �������ٰ� ǥ��
			isMntninfopoflc = true;
		}else if(tag.equals("mntninfohght")) {  //����ΰ� ���� ���̸� �������ٰ� ǥ��
			isMntninfohght = true;
		}
	}
	
	//�±� ������ �ؽ�Ʈ�� ���� �� ȣ��Ǵ� �޼���
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		//System.out.print(data);
		
		//�±� ��ġ�� ���� ������ ���
		if(isMntnid) {
			mt.setMntnid(Integer.parseInt(data));  //���� ���̵� ���
		}else if(isMntnnm) {
			mt.setMntnnm(data);  //���� �̸� ���
		}else if(isMntninfopoflc) {
			mt.setMntninfopoflc(data);  //���� ��ġ ���
		}else if(isMntninfohght) {
			mt.setMntninfohght(Integer.parseInt(data));  //���� ���� ���
		}
	}
	
	//�ݴ� �±׸� ���� �� ȣ��Ǵ� �޼���
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.print("</"+tag+">");  //�ݴ� �±� ���
		if(tag.equals("mntnid")) {
			isMntnid = false;
		}else if(tag.equals("mntnnm")) {
			isMntnnm = false;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc = false;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght = false;
		}else if(tag.equals("item")) {  //�ݴ� item�� ������ ���Ϳ� vo ���
			mtList.add(mt);
		}
	}
	
	//xml������ ���� �� ȣ��Ǵ� �޼���
	public void endDocument() throws SAXException {
		//���Ϳ� ����� ���� �� ���� ���
		System.out.println("�˻��� ���� ������ " + mtList.size());
		for(int i=0; i<mtList.size(); i++) {
			Mountain obj = mtList.get(i);
			System.out.println("id: " + obj.getMntnid());
			System.out.println("name: " + obj.getMntnnm());
			System.out.println("location: " + obj.getMntninfopoflc());
			System.out.println("height: " + obj.getMntninfohght());
			System.out.println("--------------------------");
		}
	}
}
