package com.ln.utils;

import com.ln.entity.QueryVo;
import com.ln.entity.RsBean;
import com.ln.entity.SmokeBean;
import com.ln.entity.WineBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class JieXiXml {

    public static QueryVo jieXiStr1(String str1){

        QueryVo vo = null;
        Document document = null;
        Element root = null;

        try {
            document = DocumentHelper.parseText(str1);
            root = document.getRootElement();
            String uname = root.elementText("UNAME");
            String pwd = root.elementText("PWD");
            String code = root.elementText("CODE");
            vo = new QueryVo(uname,pwd,code);
            return vo;
        } catch (DocumentException e) {
        }

        return null;
    }

    public static String jieXiStr2(String str2) {
        Document document = null;
        Element root = null;
        try {
            /**
             * 这个地方报异常，就是xml解析失败
             */
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            return cardno;
        } catch (DocumentException e) {
        }
        return null;
    }

    public static String pinRsStr(String code, RsBean rs) {
        String rsstr = null;
        if (rs!=null){
            rsstr= "<CONTENT>" +
                    "   <CARDNO>"+rs.getCardno()+"</CARDNO>" +
                    "   <MADEDATE>"+ rs.getMadetime()+"</MADEDATE>" +
                    "   <ADDRESS>"+rs.getAddress()+"</ADDRESS>" +
                    "   <NAME>"+rs.getName()+"</NAME>" +
                    "   <PRICE>"+rs.getPrice()+"</PRICE>";
            if("02".equals(code)){
                rsstr+="<VOL>"+rs.getVol()+"</VOL>";
            }
            rsstr+= "</CONTENT>";
        }

        return rsstr;
    }

    public static SmokeBean jieXiStr2Smoke(String str2) {
        SmokeBean smokeBean = null;
        Document document = null;
        Element root = null;
        try {
            /**
             * 这个地方报异常，就是xml解析失败
             */
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            String madedate = root.elementText("MADEDATE");
            String address = root.elementText("ADDRESS");
            String price = root.elementText("PRICE");
            String name = root.elementText("NAME");
            smokeBean = new SmokeBean(cardno, madedate, address, price, name);
            return smokeBean;
        } catch (DocumentException e) {

        }
        return null;
    }

    public static WineBean jieXiStr2Wine(String str2) {
        WineBean wineBean = null;
        Document document = null;
        Element root = null;
        try {
            /**
             * 这个地方报异常，就是xml解析失败
             */
            document = DocumentHelper.parseText(str2);
            root = document.getRootElement();
            String cardno = root.elementText("CARDNO");
            String madedate = root.elementText("MADEDATE");
            String address = root.elementText("ADDRESS");
            String price = root.elementText("PRICE");
            String name = root.elementText("NAME");
            String vol = root.elementText("VOL");
            wineBean = new WineBean(cardno, madedate, address, price, name, vol);
            return wineBean;
        } catch (DocumentException e) {
        }

        return null;
    }
}
