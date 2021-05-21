package com.teoan.tclass.user.service.fallback;

import com.teoan.tclass.user.constant.R;
import com.teoan.tclass.user.dto.StudentDTO;
import com.teoan.tclass.user.service.StudentFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2021/5/21 11:23
 */
@Component
public class StudentFeignFallback implements StudentFeignClient {
    @Override
    public R selectOne(Serializable id) {
        return R.failed("查询失败");
    }

    @Override
    public R update(StudentDTO studentDTO) {
        return R.failed("更新失败");
    }

    @Override
    public R getNations() {
        return R.failed("获取失败");
    }

    @Override
    public R getDepartments() {
        return R.failed("获取失败");
    }

    @Override
    public R getPoliticsStatusList() {
        return R.failed("获取失败");
    }

    @Override
    public R getPosition() {
        return R.failed("获取失败");
    }

    @Override
    public R getRoles() {
        return R.failed("获取失败");
    }

    @Override
    public R updateUserAvatar(MultipartFile avatarFile) {
        return R.failed("更新失败");
    }

    @Override
    public void getUserAvatar(HttpServletResponse resp, String photoPath) {

    }

    @Override
    public R insert(StudentDTO student) {
        return R.failed("添加失败");
    }

    @Override
    public R getStudentByPage(Long current, Long size, StudentDTO studentDTO) {
        return R.failed("获取失败");
    }

    @Override
    public R adminUpdate(StudentDTO studentDTO) {
        return R.failed("更新失败");
    }

    @Override
    public R reSetPassword(Long SId) {
        return R.failed("重置失败");
    }

    @Override
    public R delete(List<Long> idList) {
        return R.failed("删除失败");
    }

    @Override
    public ResponseEntity<byte[]> exportData(List<Long> idList) {
        return null;
    }

    @Override
    public R importData(MultipartFile file) {
        return R.failed("导入失败");
    }

    @Override
    public ResponseEntity<byte[]> getTemplate() {
        return null;
    }
}
