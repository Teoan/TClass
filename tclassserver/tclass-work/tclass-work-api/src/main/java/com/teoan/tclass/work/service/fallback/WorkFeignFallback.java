package com.teoan.tclass.work.service.fallback;

import com.teoan.tclass.work.constant.R;
import com.teoan.tclass.work.dto.UploadDTO;
import com.teoan.tclass.work.dto.WorkDTO;
import com.teoan.tclass.work.service.WorkFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Teoan
 * @date 2021/5/21 17:47
 */
@Component
public class WorkFeignFallback implements WorkFeignClient {

    @Override
    public R selectAll(Long current, Long size, WorkDTO work) {
        return null;
    }

    @Override
    public R getExtensionList() {
        return null;
    }

    @Override
    public R uploadWorkFile(Integer wId, MultipartFile file, Integer sId) {
        return null;
    }

    @Override
    public R isUploadedWorkFile(Integer wId, Integer sId) {
        return null;
    }

    @Override
    public R deleteUploadFile(UploadDTO upload) {
        return null;
    }
}
