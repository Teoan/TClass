package com.teoan.tclass.user.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.teoan.tclass.common.annotation.TclassLog;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.enums.LogSeverity;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.user.dto.StudentDTO;
import com.teoan.tclass.user.dto.StudentInfoDTO;
import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.user.service.*;
import com.teoan.tclass.user.utils.FileUtils;
import com.teoan.tclass.user.utils.POIStudentUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.teoan.tclass.common.result.R;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/19 17:07
 */
@RestController
public class StudentFeignClientApi implements StudentFeignClient {

    @Autowired
    StudentService studentService;

    @Autowired
    NationService nationService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PoliticsstatusService politicsstatusService;

    @Autowired
    PositionService positionService;

    @Autowired
    RoleService roleService;

    @Autowired
    FileService fileService;

    @Autowired
    AuthUserService authUserService;

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "通过主键查询单条数据")
    public R selectOne(Serializable id) {
        StudentDTO studentDTO = new StudentDTO();
        Student student = studentService.getById(id);
        BeanUtils.copyProperties(student,studentDTO);
        return R.ok(studentDTO);
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "通过主键查询单条详情数据")
    public R getUserInfoById(Serializable id) {
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        Student student = studentService.getById(id);
        BeanUtils.copyProperties(student,studentInfoDTO);
        return R.ok(studentInfoDTO);
    }

    @Override
    @TclassLog(operSource = "user",operName = "修改",severity = LogSeverity.INFO,description = "修改用户数据")
    public R update(StudentDTO studentDTO) {
        //获取当前登录用户
        SysUser currentUser = authUserService.getCurrentUser();
        //普通用户只能修改自己的资料信息
        if(currentUser.getId().equals(studentDTO.getId())){
            Student student = new Student();
            BeanUtils.copyProperties(studentDTO,student);
            if(studentService.updateById(student)){
                return R.ok(studentService.getById(student.getId()));
            }
        }
        return R.failed("资料修改失败！");
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取民族列表")
    public R getNations() {
        return R.ok(nationService.list());
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取部门列表")
    public R getDepartments() {
        return R.ok(departmentService.list());
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取政治面貌列表")
    public R getPoliticsStatusList() {
        return R.ok(politicsstatusService.list());
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取班级职位列表")
    public R getPosition() {
        return R.ok(positionService.list());
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取权限列表")
    public R getRoles() {
        return R.ok(roleService.list());
    }

    @Override
//    @TclassLog(operSource = "user",operName = "修改",severity = LogSeverity.INFO,description = "修改用户头像")
    public R updateUserAvatar(MultipartFile avatarFile) {
        return fileService.updateUserAvatarFile(avatarFile);
    }

    @Override
//    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "获取用户头像")
    public void getUserAvatar(HttpServletResponse resp, String photoPath) {
        byte[] avayarFileByte = fileService.getUserAvatarFile(photoPath);
        if(ObjectUtils.isNotEmpty(avayarFileByte)){
            resp.setContentType("image/jpeg");
            try {
//                BufferedImage bufferedImage = ImageIO.read(avayarFile);
//                // 剪切图片
//                int imageWidth = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
//                bufferedImage = bufferedImage.getSubimage(0,0,imageWidth,imageWidth);
                ServletOutputStream outputStream = resp.getOutputStream();
                outputStream.write(avayarFileByte);
                outputStream.flush();
//                ImageIO.write(bufferedImage,"jpg",outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @TclassLog(operSource = "user",operName = "新增",severity = LogSeverity.INFO,description = "新增数据")
    public R insert(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        if(studentService.addStudent(student)){
            return R.success("添加成功");
        }
        return R.failed("添加失败，用户名重复！");
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "分页查询所有数据")
    public R getStudentByPage(Long current, Long size, StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        return R.ok(studentService.getStudentsByPage(current,size,student));
    }

    @Override
    @TclassLog(operSource = "user",operName = "查询",severity = LogSeverity.INFO,description = "通过主键查询单条详情数据")
    public R adminUpdate(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        return R.ok(this.studentService.updateById(student));
    }

    @Override
    @TclassLog(operSource = "user",operName = "修改",severity = LogSeverity.INFO,description = "重置密码")
    public R reSetPassword(Long SId) {
        return R.ok(this.studentService.reSetPasswordBySId(SId));
    }

    @Override
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "分页查询所有作业数据")
    public R delete(List<Long> idList) {
        return R.ok(this.studentService.removeByIds(idList));
    }

    @Override
    public ResponseEntity<byte[]> exportData(List<Long> idList) {
        XSSFWorkbook workbook;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "学生数据表("+simpleDateFormat.format(new Date())+").xlsx";
        try {
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            if(ObjectUtils.isEmpty(idList)){
                workbook = POIStudentUtils.students2Excel(studentService.list());
            }else{
                workbook = POIStudentUtils.students2Excel(studentService.getStudentByIds(idList));
            }
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    @Override
    @TclassLog(operSource = "user",operName = "新增",severity = LogSeverity.INFO,description = "导入学生数据")
    public R importData(MultipartFile file) {
        String extensionName = FileUtils.getExtensionName(file).toLowerCase();

        if(extensionName.equals(".xlsx")){
            List<Student> studentList = POIStudentUtils.excel2Student(file,roleService.list(),nationService.list(),politicsstatusService.list(),positionService.list());
            if(studentService.saveBatch(studentList)){
                return R.success("已成功导入"+studentList.size()+"条数据！");
            }else{
                return R.failed("数据导入失败！");
            }
        }
        else {
            return R.failed("上传文件扩展名不符合要求！");
        }
    }

    @Override
    public ResponseEntity<byte[]> getTemplate() {
        XSSFWorkbook xssfWorkbook = POIStudentUtils.getTemplate();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            xssfWorkbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            headers.setContentDispositionFormData("attachment",URLEncoder.encode("学生数据导入模板"+".xlsx","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(baos.toByteArray(),headers,HttpStatus.OK);
    }
}
