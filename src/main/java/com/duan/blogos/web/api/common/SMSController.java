package com.duan.blogos.web.api.common;

import com.alibaba.fastjson.JSONObject;
import com.duan.blogos.restful.ResultBean;
import com.duan.blogos.util.JiSuApiUtils;
import com.duan.blogos.web.api.BaseCheckController;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created on 2018/2/18.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/sms")
public class SMSController extends BaseCheckController {


    /**
     * 向指定号码发送短信
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean send(HttpServletRequest request,
                           @RequestParam("phone") String phone,
                           @RequestParam("content") String content) {

        String url;
        try {
            url = JiSuApiUtils.getSmsUrl(phone, content);
        } catch (UnsupportedEncodingException e) {
            return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
        }

        OkHttpClient client = new OkHttpClient();
        Request okRequest = new Request.Builder()
                .post(new FormBody.Builder().build())
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(okRequest).execute();
        } catch (IOException e) {
            return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
        }

        if (response != null) {
            try {
                JSONObject obj = JSONObject.parseObject(response.body().string());
                String status = obj.getString("status");
                if (Integer.valueOf(status) == 0) {
                    return new ResultBean<>("");
                } else {
                    // FIXME 极速短信API“签名不存在”错误
                    return new ResultBean<>(obj.getString("msg"), ResultBean.FAIL);
                }

            } catch (IOException e) {
                return new ResultBean(exceptionManager.getUnknownException(new RequestContext(request), e));
            }

        }

        handlerOperateFail(request);
        return null;

    }
}
