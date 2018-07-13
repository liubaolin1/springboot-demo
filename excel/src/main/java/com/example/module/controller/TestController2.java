package com.example.module.controller;

import com.example.module.excel.DbDictPo;
import com.example.module.excel.ExcelImportUtil;
import com.example.utils.common.ActionResult;
import com.example.utils.common.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.common.Print.println;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/7/13 10:01
 */
@RestController
@Api(value = "TestController2",description = "测试类2")
public class TestController2 {
    /**
     * excel 已经提供 如果要改字段 在resources下的excel的xml文件中修改
     * @param file
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "Excel导入", notes = "Excel导入")
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ActionResult imExcel(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) throws Exception {
        println("导入");
        ActionResult<List<String>> result = new ActionResult<>();
        List<String> errorList = new ArrayList<>();
        /**
         * 通过excel读取操作 读取出实体list
         */
        List<DbDictPo> list =
                ExcelImportUtil.getList(file.getInputStream(), "DbDictImport.xml", DbDictPo.class, errorList);
        if (errorList.size() > 0) {
            result.setCode(ResultEnum.FAILE.getCode());
            result.setData(errorList);
            return result;
        }
        /**
         * 存库
         */
        //dbDictService.insertListImport(list, userId);
        return result;
    }
}
