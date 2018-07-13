package com.example.module.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @Description:  解析页面
 * @author: liubao
 * @Date: Created in 2018/7/9 17:51
 */
public class AntServiceProcess implements PageProcessor {
    /**
     * 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
     */
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(20000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    /**
     * process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
     * @param page page
     */
    @Override
    public void process(Page page) {
        List<String> all = page.getHtml().xpath("/html/body/div[3]/div[2]/div/div[2]/dl[1]/dd/h1").all();
        page.addTargetRequests(all);
    }

    @Override
    public Site getSite() {
        return null;
    }
}
