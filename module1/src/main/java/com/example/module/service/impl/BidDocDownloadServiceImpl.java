package com.example.module.service.impl;

import com.example.module.mapper.BidDocDownloadMapper;
import com.example.module.service.BidDocDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/6/14 14:37
 */
@Service
public class BidDocDownloadServiceImpl implements BidDocDownloadService {
    @Autowired
    private BidDocDownloadMapper bidDocDownloadMapper;

    @Override
    public Object selectById(Integer param) {
        return bidDocDownloadMapper.selectByPrimaryKey(param);
    }
}
