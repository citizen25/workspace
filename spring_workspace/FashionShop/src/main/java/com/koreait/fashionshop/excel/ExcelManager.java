package com.koreait.fashionshop.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	FileInputStream fis;

	public ExcelManager() {
		//로컬의 파일을 접근하기 위해서는 file stream이 필요함
		try {
			fis = new FileInputStream("C:/workspace/spring_workspace/FashionShop/src/main/webapp/resources/excel/myskill.xlsx");
			//엑셀을 제어하는 객체 생성 XSSFWorkbook
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//파일접근은 성공한 상태이므로, 이 시점부터는 sheet에 접근한다
			XSSFSheet sheet = workbook.getSheetAt(0);  //첫번째 sheet
			
			//이 시점부터는 sheet를 제어할 수 있으므로, 총 몇건이 존재하는지부터 알아본다
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("레코드 수 : " + rows);
			
			//로우와 컬럼수만큼 반복문 실행하며 데이터를 제어해보자
			for(int i=0; i<rows; i++) {
				XSSFRow row = sheet.getRow(i);  //하나의 row에 접근
				
				//컬럼수만큼 반복문 실행
				int columnCount = row.getPhysicalNumberOfCells();
				for(int a=0; a<columnCount; a++) {
					XSSFCell cell = row.getCell(a);  //column 하나에 접근

					//각 설의 자료형을 판단하여 그에 맞게 접근
					if(cell.getCellType() == CellType.STRING) {  //셀의 자료형이 문자인 경우
						System.out.print(cell.getStringCellValue() + "\t\t\t");
					} else if (cell.getCellType() == CellType.NUMERIC) {
						System.out.print((int)cell.getNumericCellValue() + "\t\t\t");
					}
				}
				System.out.println();  //줄바꿈
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new ExcelManager();
	}

}
