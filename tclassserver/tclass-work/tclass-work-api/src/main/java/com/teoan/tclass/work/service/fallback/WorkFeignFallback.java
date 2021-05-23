package com.teoan.tclass.work.service.fallback;

import com.teoan.tclass.common.result.R;
import com.teoan.tclass.work.dto.ExtensionDTO;
import com.teoan.tclass.work.dto.UploadDTO;
import com.teoan.tclass.work.dto.WorkDTO;
import com.teoan.tclass.work.service.WorkFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/21 17:47
 */
@Component
public class WorkFeignFallback implements WorkFeignClient {

    @Override
    public R selectAll(Long current, Long size, WorkDTO work) {
        return R.failed("查询失败");
    }

    @Override
    public R getExtensionList() {
        return R.failed("获取失败");
    }

    @Override
    public R uploadWorkFile(Integer wId, MultipartFile file, Integer sId) {
        return R.failed("更新失败");
    }

    @Override
    public R isUploadedWorkFile(Integer wId, Integer sId) {
        return R.failed("查询失败");
    }

    @Override
    public R deleteUploadFile(UploadDTO upload) {
        return R.failed("删除失败");
    }

    @Override
    public R selectOne(Serializable id) {
        return R.failed("查询失败");
    }

    @Override
    public R insert(WorkDTO work) {
        return R.failed("新增失败");
    }

    @Override
    public R update(WorkDTO work) {
        return R.failed("更新失败");
    }

    @Override
    public R delete(List<Integer> idList) {
        return R.failed("删除失败");
    }

    @Override
    public ResponseEntity<byte[]> downloadWorkFile(String fileName, Integer wId) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadZipWorkFile(Integer wId) {
        return null;
    }

    @Override
    public R getUploadFileInfo(Integer wId, Long current, Long size) {
        return R.failed("获取失败");
    }

    @Override
    public R deleteUploadFileByIdList(List<UploadDTO> uploadList) {
        return R.failed("删除失败");
    }

    @Override
    public R deleteExtensionById(ExtensionDTO extension) {
        return R.failed("删除失败");
    }

    @Override
    public R addExtension(ExtensionDTO extension) {
        return R.failed("新增失败");
    }
}
