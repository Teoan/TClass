package com.teoan.tclass.controller.admin;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.teoan.tclass.entity.*;
import com.teoan.tclass.service.*;
import com.teoan.tclass.utils.POIStudentUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
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
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Student student) {
        return success(this.studentService.updateById(student));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestParam("idList") List<Long> idList) {
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
            if(idList != null && !idList.isEmpty()){
                workbook = POIStudentUtils.students2Excel(studentService.getStudentByIds(idList));
            }else{
                workbook = POIStudentUtils.students2Excel(studentService.list());
            }
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);

    }

    /**
     * 导入学生数据
     */
    @PostMapping("/import")
    public R importData(MultipartFile file){
        if(file!=null){
            List<Student> studentList = POIStudentUtils.excel2Student(file,roleService.list(),nationService.list(),politicsstatusService.list(),positionService.list());

            if(studentService.saveBatch(studentList)){
                return success("").setMsg("已成功导入"+studentList.size()+"条数据！");
            }else{
                return failed("数据导入失败！");
            }
        }
        else {
            return failed("上传的文件为空");
        }

    }

}