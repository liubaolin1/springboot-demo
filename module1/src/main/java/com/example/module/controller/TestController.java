package com.example.module.controller;

import com.example.module.excel.ExcelConstants;
import com.example.module.excel.ExportExcelUtil;
import com.example.module.excel.Person;
import com.example.module.service.BidDocDownloadService;
import com.example.utils.common.ActionResult;
import com.example.utils.common.ClientUtil;
import com.example.utils.common.ResultEnum;
import com.example.utils.redis.RedisBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.utils.common.Print.println;

/**
 * @Description: 对外接口
 * @author: liubao
 * @Date: Created in 2018/6/14 14:34
 */
@RestController
@Api(value = "TestController",description = "测试类")
public class TestController {

    private static Logger logger = Logger.getLogger("TestController");
    @Autowired
    private BidDocDownloadService bidDocDownloadService;
    @Autowired
    private RedisBean redisBean;

    /**
     * 返回死数据
     * @param param
     * @return
     */
    @ApiOperation(notes = "测试方法1",value = "测试方法1")
    @RequestMapping(value = "/method1",method = RequestMethod.GET)
    public ActionResult<List<Person>> method1(Integer param){
        logger.info("测试打印日志");
        ActionResult<List<Person>> result
                = new ActionResult<>(ResultEnum.OK.getCode(),
                ResultEnum.OK.getMessage(),
                getPersonList());
        return result;
    }

    /**
     * 查询mysql redis
     * @param param
     * @return
     */
    @ApiOperation(notes = "测试方法2",value = "测试方法2")
    @RequestMapping(value = "/method2",method = RequestMethod.GET)
    public Object method2(Integer param){
        /**
         * 获取reids中的数据
         */
        Object o = redisBean.get("dict:T1000001");
        println(o);
        /**
         * 获取ip地址
         */
        String clientIp = ClientUtil.getClientIp(request);
        println(clientIp);

        return bidDocDownloadService.selectById(param);
    }

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;

    /**
     * excel导出功能
     * 在swagger2 中测试下载文件会出现乱码
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

    /**
     * 上传文件
     * @param file
     * @return
     */
    @ApiOperation(notes = "测试方法3上传",value = "测试方法3上传")
    @RequestMapping(value = "/method333",method = RequestMethod.POST)
    public Object method3(@RequestParam(value="file") MultipartFile file){
        //暂时没有储存文件的数据库 先输出一下
        println(file.getName(),file.getOriginalFilename(),file.getContentType());
        return null;
    }

}
