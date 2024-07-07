package com.binar.binarchallenge4.service;

import com.binar.binarchallenge4.entity.Order;
import com.binar.binarchallenge4.repository.OrderRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private OrderRepository orderRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Order> orders = orderRepository.findAll();
        File file = ResourceUtils.getFile("classpath:order.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("created by", "raihan");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String outputPath = System.getProperty("user.home") + "/Documents/orders.pdf";
        if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
        }

        return "";
    }
}
