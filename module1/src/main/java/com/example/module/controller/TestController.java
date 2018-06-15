package com.example.module.controller;

import com.example.module.excel.ExcelConstants;
import com.example.module.excel.ExportExcelUtil;
import com.example.module.excel.Person;
import com.example.module.service.BidDocDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private HttpServletResponse response;

    /**
     * excel导出功能
     */
    @ApiOperation(notes = "下载excel",value = "下载excel")
    @RequestMapping(value = "/method3",method = RequestMethod.GET)
    public void downloadExcel(){
        /**
         * 下载的文件名称
         */
        String fileName = ExcelConstants.FILENAME+".xls";
        /**
         * Excel中sheet的名
         */
        String sheetName = ExcelConstants.SHEETNAME;
        /**
         * 表格抬头 这个顺序要和javaBean 中的字段 顺序一致
         */
        String[] headers = ExcelConstants.HEADERS;
        /**
         * 查询出一个list
         */
        List<Person> list=getPersonList();
        /**
         * 调用辅助类来进行excel生成
         */
        ExportExcelUtil<Person> excel = new ExportExcelUtil<Person>();
        HSSFWorkbook wb=excel.exportExcel(sheetName, headers, list);
        try {
            ExportExcelUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            System.err.println("出错");
            e.printStackTrace();
        }
    }

    /**
     * 查询出了一个list
     * @return
     */
    private static List<Person> getPersonList(){
        ArrayList<Person> arrayList = new ArrayList<>();
        Person p1 = new Person("尧",0,"男","18808808888");
        Person p2 = new Person("秦始皇",2222,"男","16666606066");
        Person p3 = new Person("大禹",111,"女","17603228888");
        Person p4 = new Person("努尔哈赤",19,"男","13933133331");
        arrayList.add(p1);
        arrayList.add(p2);
        arrayList.add(p3);
        arrayList.add(p4);
        return arrayList;
    }
}
