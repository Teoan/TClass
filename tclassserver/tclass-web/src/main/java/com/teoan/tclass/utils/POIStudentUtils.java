package com.teoan.tclass.utils;

import com.teoan.tclass.entity.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/8/1 16:17
 */
public class POIStudentUtils {
    /**
     * 生成excel文件
     *
     * @param studentList 学生列表
     */
    public static ResponseEntity<byte[]> students2Excel(List<Student> studentList){

        //获取当前登录用户
//        Student currentStudent = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        XSSFWorkbook workbook =new XSSFWorkbook();
        

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREEN.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(XSSFDataFormat.getBuiltinFormat("m/d/yy"));
        XSSFCreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
        dateCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        XSSFSheet sheet = workbook.createSheet("学生信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 12 * 256);
        sheet.setColumnWidth(1, 8 * 256);
        sheet.setColumnWidth(2, 8 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 10 * 256);
        sheet.setColumnWidth(5, 10 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 14 * 256);
        sheet.setColumnWidth(9, 30 * 256);
        sheet.setColumnWidth(10, 10 * 256);
        sheet.setColumnWidth(11, 15 * 256);

//        创建标题行

        XSSFRow r0 = sheet.createRow(0);
        XSSFCell c0 = r0.createCell(0);
        c0.setCellValue("学号");
        c0.setCellStyle(headerStyle);
        XSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("学生名字");
        XSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("权限身份");
        XSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("性别");
        XSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("民族");
        XSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("籍贯");
        XSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("政治面貌");
        XSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("邮箱");
        XSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("电话");
        XSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("家庭住址");
        XSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("班级职位");
        XSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("最近登录日期");
        for (int i =0;i<studentList.size();i++){
            Student student = studentList.get(i);
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0,CellType.STRING).setCellValue(student.getId());
            row.createCell(1,CellType.STRING).setCellValue(student.getName());
            row.createCell(2,CellType.STRING).setCellValue(student.getRole().getZhName());
            row.createCell(3,CellType.STRING).setCellValue(student.getGender());
            row.createCell(4,CellType.STRING).setCellValue(student.getNation().getName());
            row.createCell(5,CellType.STRING).setCellValue(student.getNativePlace());
            row.createCell(6,CellType.STRING).setCellValue(student.getPoliticsstatus().getName());
            String email = student.getEmail()!=null? student.getEmail():"";
            row.createCell(7,CellType.STRING).setCellValue(email);
            String phone = student.getPhone()!=null? student.getPhone():"";
            row.createCell(8,CellType.STRING).setCellValue(phone);
            String address = student.getAddress()!=null? student.getAddress():"";
            row.createCell(9,CellType.STRING).setCellValue(address);
            String position=student.getPosition()!=null? student.getPosition().getName():"";
            row.createCell(10,CellType.STRING).setCellValue(position);
            XSSFCell cell11 = row.createCell(11, CellType.STRING);
            cell11.setCellValue(student.getLoginTime());
            cell11.setCellStyle(dateCellStyle);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "学生数据表("+simpleDateFormat.format(new Date())+").xlsx";
        try {
            headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);

    }


    /**
     * 解析excel
     */
    public static List<Student> excel2Student(MultipartFile file, List<Role> allRoles, List<Nation> allNations, List<Politicsstatus> allPoliticsstatuses, List<Position> allPositions){
        List<Student> studentList=new ArrayList<>();
        Student student = null;


        try{

            XSSFWorkbook XSSfWorkbook = new XSSFWorkbook(file.getInputStream());

            int numberOfSheets = XSSfWorkbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                XSSFSheet sheet = XSSfWorkbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//                跳过第一行标题
                for (int j = 1; j < physicalNumberOfRows; j++) {
                    XSSFRow row = sheet.getRow(j);

                    //防止中间空行
                    if (row==null){
                        continue;
                    }

                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    student= new Student();
                    //学号为主键，自动生成，跳过第一列学号
                    for (int k = 1; k <physicalNumberOfCells ; k++) {
                        XSSFCell cell = row.getCell(k);
                        if(cell.getCellType()==CellType.STRING){
                            String cellValue = cell.getStringCellValue();
                            switch (k){
                                case 1:{student.setName(cellValue);break;}
                                case 2:{
                                    int roleIndex=allRoles.indexOf(Role.builder().zhName(cellValue).build());
                                    student.setRoleId(allRoles.get(roleIndex).getId());
                                    break;}
                                case 3:{student.setGender(cellValue);break;}
                                case 4:{
                                    int nationIndex = allNations.indexOf(Nation.builder().name(cellValue).build());
                                    student.setNationId(allNations.get(nationIndex).getId());
                                    break;}
                                case 5:{student.setNativePlace(cellValue);break;}
                                case 6:{
                                    int politicsstatusIndex = allPoliticsstatuses.indexOf(Politicsstatus.builder().name(cellValue).build());
                                    student.setPoliticId(allPoliticsstatuses.get(politicsstatusIndex).getId());
                                    break;}
                                case 7:{student.setEmail(cellValue);break;}
                                case 8:{student.setPhone(cellValue);break;}
                                case 9:{student.setAddress(cellValue);break;}
                                case 10:{
                                    if (cellValue!=null){
                                        int positionsIndex = allPositions.indexOf(Position.builder().name(cellValue).build());
                                        student.setPosId(allPositions.get(positionsIndex).getId());
                                    }
                                    break;}
                            }
                        }else {
                            student.setLoginTime(cell.getDateCellValue());
                        }
                    }
                    studentList.add(student);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return studentList;
    }
}
