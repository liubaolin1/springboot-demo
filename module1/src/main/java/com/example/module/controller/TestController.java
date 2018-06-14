package com.example.module.controller;

import com.example.module.service.BidDocDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/6/14 14:34
 */
@RestController
@Api(value = "TestController",description = "测试类")
public class TestController {
    @Autowired
    private BidDocDownloadService bidDocDownloadService;

    @ApiOperation(notes = "测试方法1",value = "测试方法1")
    @RequestMapping(value = "/method1",method = RequestMethod.GET)
    public Object method1(Integer param){

        return "hello liubao";
    }

    @ApiOperation(notes = "测试方法2",value = "测试方法2")
    @RequestMapping(value = "/method2",method = RequestMethod.GET)
    public Object method2(Integer param){
        return bidDocDownloadService.selectById(param);
    }
}
