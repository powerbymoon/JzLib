package com.example.administrator.jzlib.jzlib.Util;

import android.util.Log;

import com.example.administrator.jzlib.jzlib.Been.StudentInfo;
import com.example.administrator.jzlib.jzlib.GlobleData.GlobleAtrr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by Administrator on 2015/9/16.
 */
public class JsoupUtil {

    public static Integer sumNumber;
    public static String pageNumber;
    public static String preUrl;
    public static String nextUrl;
    public static int page = 1;// 默认第一页

    public static void clearInfo() {
        page = 1;
        sumNumber = 0;
        pageNumber = null;
        preUrl = null;
        nextUrl = null;
    }

    public static List<Map<String, Object>> searchBook(String html) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> bookMap;
        Document doc;
        try {
            // 通过Jsoup的connect（）方法，将html转化成Document
            doc = Jsoup.connect(html).timeout(30 * 100000).get();
            // Log.d("注意啦2",doc.toString());
            // 判断“本馆没有您检索的纸本馆藏书目”
            String err = doc.select("p[style=font-size:14px; margin:5px 0 20px 10px;]").
                    select("span[style=color:#f00;]").text().toString();
            if (err.equals("本馆没有您检索的图书"))
                return null;
            // 获取图书总数以及页码总数
            sumNumber = getSumNumber(doc);
            pageNumber = getPageNumber(doc);
            // 获得前后页的链接
            if (sumNumber >= 20)
                getPreAndNextUrl(doc);
            //<ol id="search_book_list">
            //"li[class=book_list_info]"
            Elements books = doc.select("li[class=book_list_info]");
            Iterator<Element> book = books.iterator();
            while (book.hasNext()) {
                Element em = book.next();
                System.out.println(em.text());
                //这里的bookMap每次都要实例化一个，否则将会出现所有的内容都是最后一条的内容
                bookMap = new HashMap<String, Object>();
                // 经过多次验证，用Element(s)的text（）方法输出不带原来html的标签，而用toString的方法则会带标签
                // 用html（）方法得到标签括起来的内容
                // 解析图书部分内容
                Elements bookInfo = em.select("h3").select("a");
                // 标题和链接
                bookMap.put("bookTitle", bookInfo.text());
                bookMap.put("bookDetail", bookInfo.attr("href").toString());
                // 作者
                bookMap.put("bookCallno", em.select("h3:not(.a)").text());
                // 出版社
                bookMap.put("bookPublisher", em.select("p").text());
                // 索书号
                //bookMap.put("bookCallno", bookInfo.get(j).text());

                //bookMap.put("bookNo", bookInfo.get(j).html().toString());


                list.add(bookMap);
            }
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return list;
    }

    // 获得前后页的链接
    private static void getPreAndNextUrl(Document doc) {
        // TODO Auto-generated method stub
        Elements hrefs = doc.select("span[class=pagination]").select(
                "a[class=blue]");
        System.out.println("herfs:" + hrefs);
        if (page <= 1) {
            nextUrl = hrefs.get(0).attr("abs:href");
        }
        if (page >= Math.ceil(sumNumber / 20)) {
            preUrl = hrefs.get(0).attr("abs:href");
        }
        if ((page > 1) && (page < Math.ceil(sumNumber / 20))) {
            preUrl = hrefs.get(0).attr("abs:href");
            nextUrl = hrefs.get(1).attr("abs:href");
        }
        System.out.println("preUrl:" + preUrl);
        System.out.println("nextUrl:" + nextUrl);
    }

    // 获得页码总数
    private static String getPageNumber(Document doc) {
        // TODO Auto-generated method stub
        // 判断是否为多页。（每页默认显示20条数据）
        if (sumNumber <= 20) {
            return "1/1";
        } else {
            return doc.select("span[class=pagination]").select("b").get(0)
                    .text().toString();
        }
    }

    // 获得图书总数
    private static Integer getSumNumber(Document doc) {
        // TODO Auto-generated method stubs
        return Integer.parseInt(doc.select("strong[class=red]").text()
                .toString());
    }


    public static String detail(String html) {
        // TODO 自动生成的方法存根
        String s = "";
        Document doc;
        try {
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            Elements books = doc.select("ul[class=sharing_zy]").select("li");
            Iterator<Element> book = books.iterator();
            Element em = book.next();
            //<li><a href="http://book.douban.com/isbn/7-100-00904-9/" target="_blank">
            s = em.select("a").attr("href").toString();
            // 标题和链接
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return s;
    }

    public static List<Map<String, Object>> bookdetail(String html) {
        // TODO 自动生成的方法存根
        Document doc;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> bookMap;
        try {
            //HttpClient client0=new DefaultHttpClient();
            // HttpPost post0 = new HttpPost(html);
            //  HttpResponse response = client0.execute(post0);
            //if (response.getStatusLine().getStatusCode() == 200) {
            //  GlobleData.no=false;
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            // Log.d("DEBUG", "验证码错误"+doc.select("title"));
            bookMap = new HashMap<String, Object>();
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        String s1 = doc.select("a[class=nbg]").select("img").attr("src").toString();
                        bookMap.put("tupianurl", s1);
                        break;
                    case 1:
                        String s2 = doc.select("a[class=nbg]").select("img").attr("alt").toString();
                        bookMap.put("shuming", s2);
                        break;
                    case 2:
                        bookMap.put("jianjie", doc.select("div[class=indent]").select("div[class=intro]").text());
                        break;
                    default:
                        break;
                }
                list.add(bookMap);
            }
            // }//else{
            //  GlobleData.no=true;
            // }
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return list;
    }

    public static Boolean loginUrl(Map<String, String> map) {

        //HttpPost post = new HttpPost("http://opac.jluzh.com/reader/redr_verify.php"+ "?" +
        //"captcha="+map.get("captcha")+"number=" + map.get("number")+ "&passwd=" +map.get("passwd") + "&select=cert_no&returnUrl=");
        HttpPost post = new HttpPost("http://opac.jluzh.com/reader/redr_verify.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : map.keySet()) {

            params.add(new BasicNameValuePair(key, map.get(key)));
        }
        //post.setHeader("Cookie", "PHPSESSID="+GlobleData.cookies);

        try {
            HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
            post.setEntity(entity);

            HttpResponse response = GlobleAtrr.client.execute(post);
            //if (response.getStatusLine().getStatusCode() == 200) {
            //状态码
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
          //  Log.d("DEBUG", "获取html成功" + result);

            //HttpEntity entity = response.getEntity();
            //StringBuffer sb = new StringBuffer();
            //	BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
            ////String data = "";
            //	while ((data = br.readLine()) != null) {
            //	sb.append(data);
            //	}
            //	String  result = sb.toString();

            //Log.d("DEBUG", result);
            // int state = response.getStatusLine().getStatusCode();
            //System.out.println("state:" + result);

            //读取cookie
            //GlobleData.cookies = ((AbstractHttpClient)GlobleData.client).getCookieStore()
            //		.getCookies().get(0).getValue();

            //Log.d("DEBUG", "获取cookie成功");
		/*	if (GlobleData.cookies.isEmpty()) {
				System.out.println("-------Cookie NONE---------");
			} else {
				System.out.println(GlobleData.cookies.size());
				for (int i = 0; i < GlobleData.cookies.size(); i++) {

				System.out.println("cookies:"
						+ GlobleData.cookies.get(i).getValue());
			}
		}*/

            // 写入cookie
            // html = GlobleData.BOOK_LST + "?" + "Cookie=PHPSESSID="
            // + GlobleData.cookies.get(0).getValue();
            // System.out.println(html);
            Document doc = Jsoup.parse(result);
            //Document doc = Jsoup.connect(GlobleData.Info_URL).cookie("PHPSESSID", GlobleData.cookies).timeout(30 * 1000).get();
            // System.out.println("doc :" + doc);
            // 判断是否登录成功
            String status1 = doc.select("td[colspan=2]").select("font[color=red]").text()
                    .toString();
            if (status1.equals("验证码错误(wrong check code)")) {
                Log.d("DEBUG", "验证码错误" + status1);
            }
            //Log.d("DEBUG", "验证码错误"+status1);
            String status = doc.select("a[href=../reader/login.php]").text()
                    .toString();

            if (status.equals("登录")) {
                System.out.println("登录失败，请检查账号和密码！");
                return false;
            } else {
                System.out.println("登录成功！");
                Elements e = doc.select("strong[style=color:#F00;]");
                // 目前只要即将以及已经过期的图书的数据
                for (int i = 0; i <= 1; i++) {
                    switch (i) {
                        case 0:
                            StudentInfo.toExpire = e.get(i).text();
                            break;
                        case 1:
                            StudentInfo.expired = e.get(i).text();
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                }
                e = doc.select("div[id=mylib_info]");// <div id="mylib_info" >
                Iterator<Element> its = e.select("td").iterator();
                int i = -1;// 不想改下面的，就改这里了
                while (its.hasNext()) {
                    Element em = its.next();
                    // 删去标签里不要的内容
                    em.select("span[class=bluetext]").remove();

                    switch (i) {
                        case 0:// 姓名
                            StudentInfo.name = em.text();
                            System.out.println("name:" + em.text());
                            break;
                        case 1:// 证件号

                            break;
                        case 2:// 条码号
                            break;
                        case 3:// 失效日期
                            StudentInfo.expireData = em.text();
                            System.out.println("失效日期:" + em.text());
                            break;
                        case 4:// 办证日期
                            break;
                        case 5:// 生效日期
                            break;
                        case 6:// 最大可借图书
                            StudentInfo.maxBorrow = em.text();
                            System.out.println("最大可借图书:" + em.text());
                            break;
                        case 7:// 最大可预约图书
                            break;
                        case 8:// 最大可委托图书
                            break;
                        case 9:// 读者类型
                            StudentInfo.education = em.text();
                            break;
                        case 10:// 借阅等级
                            break;
                        case 11:// 累计借书
                            StudentInfo.sumBorrowed = em.text();
                            break;
                        case 12:// 违章次数
                            break;
                        case 13:// 欠款金额
                            StudentInfo.qiankuan = em.text();
                            break;
                        case 14:// 系别
                            break;
                        case 15:// 邮箱
                            break;
                        case 16:// 身份证号
                            //StudentInfo.idNumber = em.text();
                            //System.out.println("身份证号:" + em.text());
                            break;
                        case 17:// 工作单位
                            //StudentInfo.wockPlace = em.text();
                            //	System.out.println("工作单位:" + em.text());
                            break;
                        case 18:// 职称
                            break;
                        case 19:// 职位
                            break;
                        case 20:// 性别
                            //StudentInfo.sex = em.text();
                            //System.out.println("性别:" + em.text());
                            break;
                        case 21:// 住址
                            break;
                        case 22:// 邮编
                            break;
                        case 23:// 电话
                            break;
                        case 24:// 手机
                            //StudentInfo.tel = em.text();
                            break;
                        case 25:// 出生日期
                            break;
                        case 26:// 文化程度
                            break;
                        case 27:// 押金
                            break;
                        case 28:// 手续费
                            break;
                        default:
                            break;
                    }
                    i++;
                }
                System.out.println(i);
                return true;
            }
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }

    }

    public static List<Map<String, Object>> getBorrowedBook() {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> bookMap;
            try {
                Document doc = Jsoup.connect("http://opac.jluzh.com/reader/book_lst.php")
                        .cookie("PHPSESSID", GlobleAtrr.cookies)
                        .timeout(30 * 1000).get();
                String err = doc.select("strong[class=iconerr]").text().toString();
                if (err.equals("您的该项记录为空！"))
                    return null;
                //doc.select("table[width=100% border=0 cellpadding=5 cellspacing=1 bgcolor=#CCCCCC class=table_line]").
                //select("tr").text().toString();


                //String e1=doc.select("table").select("tr").text().toString();
                //Log.d("DEBUG", "熊出没 注意"+e1);
                Elements es= doc.select("table[width=100%]").select("tr");
                Iterator<Element> book = es.iterator();
                book.next();
                while (book.hasNext()) {
                    Element e = book.next();
                    bookMap = new HashMap<String, Object>();
                    Elements bookInfo = e.select("td");
                    // System.out.println(bookInfo.toString());
                    for (int i = 0; i < bookInfo.size(); i++) {
                        // System.out.println(bookInfo.get(i).text());
                        switch (i) {
                            case 0:
                                //bookMap.put("barcode", bookInfo.get(i).text());
                                break;
                            case 1:
                                bookMap.put("booktitle", bookInfo.get(i).text());
                                break;
                            case 2:
                                bookMap.put("borrowedDate", bookInfo.get(i).text());
                                break;
                            case 3:
                                bookMap.put("paybackDate", bookInfo.get(i).text());
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            default:
                                break;
                        }
                    }
                    list.add(bookMap);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return list;
        }

    public static Map<String,String> getPic(String html) {
        Map<String,String> map = new HashMap<String,String>();
        String s="",name;
        Document doc;
        String s1="";
        try {
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            Elements em = doc.select("div[class=pt_border]");
            //<li><a href="http://book.douban.com/isbn/7-100-00904-9/" target="_blank">
            s = em.select("img").attr("src").toString();
            name=em.select("img").attr("title").toString();
            s1=s.replaceAll("small","");

            map.put("url",s1);
            map.put("althor",name);
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return map;
    }

    public static String getArt_url(String html) {
        String s="";
        Document doc;
        try {
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            //<div class="articleCell SG_j_linedot1">
            Elements em = doc.select("div[class=articleCell SG_j_linedot1]");
            //<li><a href="http://book.douban.com/isbn/7-100-00904-9/" target="_blank">
            s = em.select("a").attr("href").toString();


        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return s;
    }

    public static String loadArt(String html) {
        String s="";
        Document doc;
        try {
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            Elements em = doc.select("div[class=content b-txt1]");
            s = em.text().toString();
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return s;
    }

    public static List<Map<String, Object>> searchBook_clasify(String html) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> bookMap;

        Document doc;
        try {
            // 通过Jsoup的connect（）方法，将html转化成Document
            doc = Jsoup.connect(html).timeout(30 * 1000).get();
            System.out.println("Success to parse!");
            // System.out.println(doc);
            //<table width="100%"
            Elements books = doc.select("table[width=100%]").select("tr");
            Iterator<Element> book = books.iterator();
            Element em = book.next();
            while (book.hasNext()) {
                em = book.next();
                System.out.println(em.text());
                // 这里的bookMap每次都要实例化一个，否则将会出现所有的内容都是最后一条的内容
                bookMap = new HashMap<String, Object>();
                // 经过多次验证，用Element(s)的text（）方法输出不带原来html的标签，而用toString的方法则会带标签
                // 用html（）方法得到标签括起来的内容
                // 解析图书部分内容


                Elements bookInfo = em.select("td");
                //int totalTds = bookInfo.size();
                for (int j = 0; j < 5; j++) {
                    switch (j) {
                        case 0:// 图书序号
                            bookMap.put("bookDetail",bookInfo.select("a").attr("href").toString());
                            break;
                        case 1:// 标题和链接
                            bookMap.put("bookTitle", bookInfo.get(j).text());
                            break;
                        case 2:// 作者
                            bookMap.put("bookAuthor", bookInfo.get(j).text());
                            break;
                        case 3:// 出版社
                            bookMap.put("bookPublisher", bookInfo.get(j).text());
                            break;
                        case 4:// 索书号
                            bookMap.put("bookCallno", bookInfo.get(j).text());
                            break;
                        default:
                            break;
                    }
                }
                list.add(bookMap);
            }
        } catch (IOException e) {
            // 解析失败！
            e.printStackTrace();
            System.out.println("Failed to Parse!");
        }
        return list;
    }
}