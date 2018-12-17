package com.outdd.aiRead.common.util;

import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/*
 * TODO: 公共util类
 * @author VAIE
 * @date: 2018/9/23-22:19
 * @version v1.0
 */
public class CommomUtil {
    /**
     * 獲取32位uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 獲取10位uuid long形
     *
     * @return
     */
    public static long numId() {
        boolean fiag = true;
        long id = 0;
        while (fiag) {
            id = (long) (Math.random() * 10 * 900 * 900 * 900);
            String sa = String.valueOf(id);
            if (sa.length() == 10) {
                fiag = false;
            }
        }
        return id;
    }

    /**
     * 驗證當前對象不為空
     * 為空: 返回false
     * 不為空: 返回true
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        if (null == obj || "".equals(obj)) {
            return false;
        }
        return true;
    }

    /**
     * 驗證字符串是否為空
     * 為空: 返回false
     * 不為空: 返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (null != str && !"".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void getPort(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String ip = request.getRemoteAddr();//返回发出请求的IP地址
        String params = request.getQueryString();//返回请求行中的参数部分
        String host = request.getRemoteHost();//返回发出请求的客户机的主机名
        int port = request.getRemotePort();//返回发出请求的客户机的端口号。
        System.out.println(ip);
        System.out.println(url);
        System.out.println(uri);
        System.out.println(params);
        System.out.println(host);
        System.out.println(port);
    }

    /**
     * 小说前端
     * @param page
     * @return
     */
    public static String getPageDiv(PageInfo page) {
        StringBuilder sb = new StringBuilder();

        if (page.isIsFirstPage()) {// 如果是首页
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-prev lbf-pagination-disabled\" href=\"#\"><</a></li>\n");
        } else {
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-prev\" href=\"/all?pageNum=" + page.getPrePage() + "\"><</a></li>\n");
        }

        if(!page.isIsFirstPage()&& page.getPageNum()>5){
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-page \" href=\"/all?pageNum=1\">1</a></li>\n");
            sb.append("<li class=\"lbf-pagination-item\">...</li>\n");
        }
        for (int i :page.getNavigatepageNums()) {

            if (i == page.getPageNum()) {
                sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-page  lbf-pagination-current\" href=\"#\">" + i + "</a></li>\n");
            } else {
                sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-page\" href=\"/all?pageNum=" + i + "\">" + i + "</a></li>\n");
            }
        }

        if(!page.isIsLastPage()&& page.getPageNum()< (page.getPages() -3)){
            sb.append("<li class=\"lbf-pagination-item\">...</li>\n");
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-page \" href=\"/all?pageNum="+page.getPages()+"\">"+page.getPages()+"</a></li>\n");

        }

        if (page.isIsLastPage()) {// 如果是末页
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-prev lbf-pagination-disabled\" href=\"javascript:\">></a></li>\n");
        } else {
            sb.append("<li class=\"lbf-pagination-item\"><a class=\"lbf-pagination-next \" href=\"/all?pageNum=" + page.getNextPage() + "\">></a></li>\n");
        }


        sb.insert(0, "<ul class=\"lbf-pagination-item-list\">\n").append("</ul>\n");

        sb.append("<div class=\"lbf-pagination-jump\"><input type=\"text\" id=\"PAGINATION-INPUT\" class=\"lbf-pagination-input\" value=\"" + page.getPageNum() + "\"/>");
        sb.append("<a href=\"#\" class=\"lbf-pagination-go\" id=\"PAGINATION-BUTTON\">GO</a></div>");

        sb.insert(0, "<div class=\"lbf-pagination\">\n").append("</div>\n");


        return sb.toString();

    }
    /**
     * 小说后端
     * @param page
     * @return
     */
    public static String getPageDivAdmin(PageInfo page) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ye = new StringBuilder();

        ye.append("<span class=\"pagination-info\">当前第 "+page.getPageNum()+" 页，每页显示 "+page.getPageSize()+"条 ，共"+page.getPages()+"页</span>");
        ye.append("<span class=\"page-list\">");
        ye.append("<span class=\"btn-group dropup\">");
        ye.append("<button type=\"button\" class=\"btn btn-default dropdown-toggle\"data-toggle=\"dropdown\">");
        ye.append("<span class=\"page-size\">"+page.getPageSize()+"</span>");
        ye.append("<span class=\"caret\"></span>");
        ye.append("</button>");
        ye.append("<ul class=\"dropdown-menu\" role=\"menu\">");
        ye.append("<li role=\"menuitem\">" +
              "<a href=\"#\">5</a>" +
              "</li>\n" +
              "<li role=\"menuitem\" class=\"active\">" +
              "<a href=\"#\">10</a>" +
              "</li>" +
              "<li role=\"menuitem\">" +
              "<a href=\"#\">20</a>" +
              "</li>");
        ye.append("</ul>");
        ye.append("</span>");
        ye.append("选择页面");
        ye.append("</span>");
        ye.insert(0, "<div class=\"pull-left pagination-detail\">").append("</div>");

        if (page.isIsFirstPage()) {// 如果是首页
            sb.append("<li class=\"page-pre\"><a href=\"#\"><</a></li>\n");
        } else {
            sb.append("<li class=\"page-pre\"><a href=\"/#\"><</a></li>\n");
        }

        if(!page.isIsFirstPage()&& page.getPageNum()>5){
            sb.append("<li class=\"page-number\"><a  href=\"#\">1</a></li>\n");
            sb.append("<li class=\"page-number\">...</li>\n");
        }
        for (int i :page.getNavigatepageNums()) {

            if (i == page.getPageNum()) {
                sb.append("<li class=\"page-number active\"><a href=\"#\">" + i + "</a></li>\n");
            } else {
                sb.append("<li class=\"page-number\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }

        if(!page.isIsLastPage()&& page.getPageNum()< (page.getPages() -3)){
            sb.append("<li class=\"page-number\">...</li>\n");
            sb.append("<li class=\"page-number\"><a href=\"#\">"+page.getPages()+"</a></li>\n");

        }

        if (page.isIsLastPage()) {// 如果是末页
            sb.append("<li class=\"page-next\"><a href=\"javascript:\">></a></li>\n");
        } else {
            sb.append("<li class=\"page-next\"><a href=\"#\">></a></li>\n");
        }


        sb.insert(0, "<ul class=\"pagination\">\n").append("</ul>\n");


        sb.insert(0, "<div class=\"pull-right pagination\">\n").append("</div>\n");


        return ye.toString()+sb.toString();

    }
}
