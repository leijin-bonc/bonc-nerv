package com.bonc.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;

import com.bonc.model.Ftp;

/**
 * ��̬����Excelʵ����
 * 
 * @author txz&htb
 *
 *         2016-8-9 ����10:18:41
 */
public class ExportExcelUtil {

	/**
	 * 
	 * @param response
	 *            ����
	 * @param fileName
	 * @param excelHeader
	 *            excel��ͷ���飬���"����#name"��ʽ�ַ�����"����"Ϊexcel�����У� "name"Ϊ�����ֶ���
	 * @param dataList
	 *            ���ݼ��ϣ������ͷ�����е��ֶ���һ��
	 * @return ����һ��HSSFWorkbook
	 * @throws Exception
	 */
	public static <T> HSSFWorkbook export(HttpServletResponse response, String fileName, String[] excelHeader,
			Collection<List<Ftp>> dataList) throws Exception {

		// ��������
		response.setContentType("application/application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		// ����һ��Workbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();

		// ���ñ�����ʽ
		HSSFCellStyle titleStyle = wb.createCellStyle();

		// ���õ�Ԫ��߿���ʽ
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿� ϸ����
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// �±߿� ϸ����
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿� ϸ����
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿� ϸ����

		// ���õ�Ԫ����뷽ʽ
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ˮƽ����
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ��ֱ����

		// ����������ʽ
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15); // ����߶�
		titleFont.setFontName("����"); // ������ʽ
		titleStyle.setFont(titleFont);
		/*
		 * ````````````````````````````````````````````````````````````````````
		 */
		int iq = 0;
		Iterator<List<Ftp>> ft = dataList.iterator();
		while (ft.hasNext()) {
			iq = iq++;
			List<Ftp> p = ft.next();
			Ftp sw = p.get(0);
			// ��Workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
			HSSFSheet sheet = wb.createSheet(sw.getType());

			// ��������,���ֱ���
			String[] titleArray = new String[excelHeader.length];

			// �ֶ�������,bean�ֶ�
			for (int i = 0; i < excelHeader.length; i++) {
				titleArray[i] = excelHeader[i];
			}

			// ��sheet����ӱ�����
			HSSFRow row = sheet.createRow(0);// ������0��ʼ
			HSSFCell sequenceCell = row.createCell(0);// cell�� ��0��ʼ ��һ��������
			sequenceCell.setCellValue("���");
			sequenceCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(0); // �Զ����ÿ��

			// Ϊ�����и�ֵ
			for (int i = 0; i < excelHeader.length; i++) {
				HSSFCell titleCell = row.createCell(i + 1);// 0��λ�����ռ�ã�������+1
				titleCell.setCellValue(titleArray[i]);
				titleCell.setCellStyle(titleStyle);
				sheet.autoSizeColumn(i + 1);// 0��λ�����ռ�ã�������+1
			}

			// ������ʽ ��Ϊ�����������ʽ��ͬ ��Ҫ�ֿ����� ��Ȼ�Ḳ��
			HSSFCellStyle dataStyle = wb.createCellStyle();

			// �������ݱ߿�
			dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

			// ���þ�����ʽ
			dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ˮƽ����
			dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // ��ֱ����

			// ������������
			Font dataFont = wb.createFont();
			dataFont.setFontHeightInPoints((short) 12); // ����߶�
			dataFont.setFontName("����"); // ����
			dataStyle.setFont(dataFont);

			// �����������ݣ�����������
			Iterator<Ftp> it = p.iterator();// 70
			int index = 0;
			String[] field = { "sysdata", "hold", "accout", "type", "name", "files", "dfiles", "files20", "files50",
					"files100", "maxfiles" };
			while (it.hasNext()) {
				index++;// 0��λ��ռ�� ����+1
				row = sheet.createRow(index);
				// Ϊ��Ÿ�ֵ
				HSSFCell sequenceCellValue = row.createCell(0);// ���ֵ��Զ�ǵ�0��
				sequenceCellValue.setCellValue(index);
				sequenceCellValue.setCellStyle(dataStyle);
				sheet.autoSizeColumn(0);
				Ftp t = it.next();
				for (int i = 0; i < field.length; i++) {
					HSSFCell dataCell = row.createCell(i + 1);
					dataCell.setCellStyle(dataStyle);
					sheet.autoSizeColumn(i + 1);
					String fieldName = field[i];
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// ȡ�ö�ӦgetXxx()����
					Class<? extends Object> tCls = t.getClass();// ����ΪObject�Լ�����Object������
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// ͨ���������õ���Ӧ�ķ���
					Object value = getMethod.invoke(t, new Object[] {});// ��̬���÷�,�õ�����ֵ
					if (value != null) {
						dataCell.setCellValue(value.toString());// Ϊ��ǰ�и�ֵ
					}
				}
			}
		}

		OutputStream outputStream = response.getOutputStream();// ����
		wb.write(outputStream);// HSSFWorkbookд����
		// wb.close();// HSSFWorkbook�ر�
		outputStream.flush();// ˢ����
		outputStream.close();// �ر���
		return wb;

	}

}
