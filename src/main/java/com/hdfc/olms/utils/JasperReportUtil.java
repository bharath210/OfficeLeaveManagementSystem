package com.hdfc.olms.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *@author Bharath Kumar
 *@created 15-Apr-2023
*
 */
public class JasperReportUtil {
	
	private JasperReportUtil() {
		
	}
	
	public static void generateHtmlReport( List<?> list, String entity) throws JRException, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:"+entity+".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Bharath Kumar Palasa");
  
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:\\HDFC_Training\\Mini_Project\\OfficeLeaveManagementSystem\\reports\\"+entity+".html");
	}

}
