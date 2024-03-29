package com.teoan.tclass.work.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.work.entity.Work;

import java.io.File;

/**
 * (Work)表服务接口
 *
 * @author Teoan
 * @since 2021-05-19 17:28:18
 */
public interface WorkService extends IService<Work> {
    /**
     * 分页并高级查询数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    IPage getWorksByPage(Long current, Long size, Work work);

    /**
     * 将作业扩展名id设置为默认
     * @param EId 拓展名id
     * @return 是否成功
     */
    boolean setDefaultWorkExtensionByEId(Integer EId);


    /**
     * 获取作业文件
     */
    byte[] getFile(Integer wId, String fileName);


    /**
     * 根据作业id获取作业文件并打包为zip
     *
     * @param wId 作业id
     */
    File getZipByWId(Integer wId);
}
