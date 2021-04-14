package com.teoan.tclass.controller.admin;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.teoan.tclass.dto.StudentDTO;
import com.teoan.tclass.entity.*;
import com.teoan.tclass.service.*;
import com.teoan.tclass.utils.FileUtils;
import com.teoan.tclass.utils.POIStudentUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:10:41
 */
@RestController
@RequestMapping("/admin/student")
public class AdStudentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    @Resource
    private NationService nationService;

    @Resource
    private PoliticsstatusService politicsstatusService;

    @Resource
    private RoleService roleService;

    @Resource
    private PositionService positionService;



    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Student student) {

        if(studentService.addStudent(student)){
            return success("添加成功！");
        }
        return failed("添加失败，用户名重复！");
    }

    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    public R getStudentByPage(@RequestParam(defaultValue = "1")Long current, @RequestParam(defaultValue = "10")Long size, StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        return success(studentService.getStudentsByPage(current,size,student));
    }


    /**
     * 修改数据
     *
     * @param studentDTO 实体DTO
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody StudentDTO studentDTO) {

        Student student = new Student();
        BeanUtils.copyProperties(studentDTO,student);
        return success(this.studentService.updateById(student));
    }

    /**
     * 重置密码
     *
     * @param SId 实体id
     * @return 重置结果
     */
    @PutMapping("/reSetPassword/{SId}")
    public R reSetPassword(@PathVariable("SId") Long SId) {
        return success(this.studentService.reSetPasswordBySId(SId));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestBody List<Long> idList) {
        return success(this.studentService.removeByIds(idList));
    }

    /**
     * 导出学生数据
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(@RequestParam("idList") List<Long> idList) {
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

    /**
     * 导入学生数据
     */
    @PostMapping("/import")
    public R importData(MultipartFile file){
        String extensionName = FileUtils.getExtensionName(file).toLowerCase();

        if(extensionName.equals(".xlsx")){
            List<Student> studentList = POIStudentUtils.excel2Student(file,roleService.list(),nationService.list(),politicsstatusService.list(),positionService.list());
            if(studentService.saveBatch(studentList)){
                return success(true).setMsg("已成功导入"+studentList.size()+"条数据！");
            }else{
                return failed("数据导入失败！");
            }
        }
        else {
            return failed("上传文件扩展名不符合要求！");
        }

    }


    @GetMapping("/template")
    public ResponseEntity<byte[]> getTemplate() throws UnsupportedEncodingException {

        XSSFWorkbook xssfWorkbook = POIStudentUtils.getTemplate();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            xssfWorkbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",URLEncoder.encode("学生数据导入模板"+".xlsx","UTF-8"));

        return new ResponseEntity<>(baos.toByteArray(),headers,HttpStatus.OK);
    }

}