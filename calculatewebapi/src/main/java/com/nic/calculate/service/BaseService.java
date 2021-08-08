package com.nic.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.mybatisPlus.entity.CalculateBill;
import com.nic.calculate.mybatisPlus.service.impl.CalculateBillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BaseService {

    private static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

    @Autowired
    private CalculateBillServiceImpl calculateBillService;

    public BaseResponse getTestData() {
        BaseResponse<Object> response = new BaseResponse<>();


        CalculateBill entity  =  new CalculateBill();
        entity.setId(UUID.randomUUID()+"");
        entity.setBillCount(6);
        entity.setBillName("测试生成账单记录2");
        entity.setBeginTime(new Date());
        entity.setCreateUserId(UUID.randomUUID()+"");
        entity.setOriginFund(1000d);

        boolean saveSuccess = calculateBillService.save(entity);
        response.setSuccess(saveSuccess);
        return response;
    }


    private static final long  serialVersionUID=1L;

    private static final String APPID = "wxb88xxxxxxxx46140e";
    private static final String SECRET = "19fa40c6xxxxxxxx6ae971267";
    private String code;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }


//    @RequestMapping(value = "/login")
//    public String login(String code){
//        System.out.println(code);
//        System.out.println("------------------------------------");
//        //微信那边的接口，grant_type=authorization_code是固定的
//        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
//                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";
//        //发送请求给微信后端
//        CloseableHttpClient httpClient= HttpClients.createDefault();
//        HttpGet httpGet=new HttpGet(url);
//        InputStream inputStream=null;
//        CloseableHttpResponse httpResponse=null;
//        StringBuilder result=new StringBuilder();
//        try {
//            httpResponse=httpClient.execute(httpGet);
//            HttpEntity entity=httpResponse.getEntity();
//            inputStream=entity.getContent();
//            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
//            String line="";
//            while ((line=bufferedReader.readLine())!=null){
//                System.out.println(line); //这里需要使用fastjson来提取一下内容
//                JSONObject jsonObject= JSON.parseObject(line);
//                Login login=new Login();
//                login.setOpenid(jsonObject.getString("openid"));
//                login.setSession_key(jsonObject.getString("session_key"));
//                result.append(login.getOpenid()+"hello_world"+login.getSession_key());
//                System.out.println(result.toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result.toString();
//    }

}
