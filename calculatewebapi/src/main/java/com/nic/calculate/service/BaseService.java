package com.nic.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nic.calculate.help.ArraysLambda;
import com.nic.calculate.help.BaseResponse;
import com.nic.calculate.mybatisPlus.entity.CalculateBill;
import com.nic.calculate.mybatisPlus.service.impl.CalculateBillServiceImpl;
import com.nic.calculate.regular.dto.BillDetailDto;
import com.nic.calculate.regular.dto.CalculateResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class BaseService {

    private static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

    @Autowired
    private CalculateBillServiceImpl calculateBillService;

    public BaseResponse getTestData() {
        BaseResponse<Object> response = new BaseResponse<>();


        CalculateBill entity = new CalculateBill();
        entity.setId(UUID.randomUUID() + "");
        entity.setBillCount(6);
        entity.setBillName("测试生成账单记录2");
        entity.setBeginTime(new Date());
        entity.setCreateUserId(UUID.randomUUID() + "");
        entity.setOriginFund(1000d);

        boolean saveSuccess = calculateBillService.save(entity);
        response.setSuccess(saveSuccess);
        return response;
    }


    private static final long serialVersionUID = 1L;

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

    public BaseResponse calculateTheBillResult(List<BillDetailDto> list) {
        BaseResponse<Object> response = new BaseResponse<>();
        //payAmount is positive Number ,the person is payer.
        List<BillDetailDto> receiveList = ArraysLambda.where(list, f -> f.getPayAmount() > 0);
        //payAmount is negative Number ,the person is receiver.
        List<BillDetailDto> payList = ArraysLambda.select(ArraysLambda.where(list, f -> f.getPayAmount() < 0), n -> new BillDetailDto(n.getUserId(), Math.abs(n.getPayAmount())));

        List<BillDetailDto> nonList = ArraysLambda.where(list, f -> f.getPayAmount() == 0);

        //they are payPool and receivePool
        double totalPayAmountPool = this.getTotalPayByList(payList);
        double totalReceiveAmountPool = this.getTotalPayByList(receiveList);

        if (totalPayAmountPool != totalReceiveAmountPool) {
            response.setMessage("支出与收入不对等，请检查数据");
            return response;
        }
        Map<String, Double> payMap = new LinkedHashMap<>();
        Map<String, Double> receiveMap = new LinkedHashMap<>();
        Collections.sort(payList);
        Collections.sort(receiveList);
        for (BillDetailDto dto : payList) {
            payMap.put(dto.getUserId(), dto.getPayAmount());
        }
        for (BillDetailDto dto : receiveList) {
            receiveMap.put(dto.getUserId(), dto.getPayAmount());
        }
        response.setData(this.calculate(totalPayAmountPool, payMap, receiveMap, nonList));
        response.setSuccess(true);
        return response;
    }

    private List<CalculateResultDto> calculate(double amountPool,
                                               Map<String, Double> payMap,
                                               Map<String, Double> receiveMap,
                                               List<BillDetailDto> nonList) {
        List<CalculateResultDto> resultList = new ArrayList<>();

        for (String receiver : receiveMap.keySet()) {
            Double amount = receiveMap.get(receiver);
            for (String payer : payMap.keySet()) {
                double payAmount = Math.abs(payMap.get(payer));

                if (payAmount == 0 || amount == 0) {
                    continue;
                }
                if (payAmount >= amount) {
                    resultList.add(new CalculateResultDto(payer, receiver, amount));
                    payMap.put(payer, sub(payAmount, amount));
                    amount = 0d;
                } else if (payAmount < amount) {
                    resultList.add(new CalculateResultDto(payer, receiver, payAmount));
                    payMap.put(payer, 0d);
                    amount = sub(amount, payAmount);
                }
            }
        }
        for (CalculateResultDto result : resultList) {
            amountPool = sub(amountPool, result.getAmount());
        }
        if (amountPool != 0) {
            return null;
        }
//        if (nonList != null && nonList.size() >0){
//            for (BillDetailDto billDetailDto : nonList) {
//                resultList.add(new CalculateResultDto(billDetailDto.getUserId(), billDetailDto.getUserId(), billDetailDto.getPayAmount()));
//            }
//        }
        return resultList;
    }

    private double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }


    private double getTotalPayByList(List<BillDetailDto> list) {
        double result = 0d;
        for (BillDetailDto billDetailDto : list) {
            result += Math.abs(billDetailDto.getPayAmount());
        }
        return result;
    }


}
