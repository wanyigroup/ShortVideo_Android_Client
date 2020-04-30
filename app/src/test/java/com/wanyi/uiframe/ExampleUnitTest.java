package com.wanyi.uiframe;

import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;
import com.wanyi.uiframe.usercenter.api.model.query.LoginQuery;
import com.wanyi.uiframe.usercenter.api.model.query.MobileVerifyQuery;
import com.wanyi.uiframe.usercenter.api.model.query.NormalRegisterQuery;
import com.wanyi.uiframe.usercenter.api.model.query.ResetPwdQuery;
import com.wanyi.uiframe.usercenter.realize.RefreshTokenSchedule;
import com.wanyi.uiframe.usercenter.realize.model.NormalLoginModel;
import com.wanyi.uiframe.usercenter.realize.model.NormalRegisterModel;
import com.wanyi.uiframe.usercenter.realize.model.SendVerifyModel;
import com.wanyi.uiframe.usercenter.realize.model.types.VerifyEnums;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void userTest() {
        ResetPwdQuery resetPwdQuery = new ResetPwdQuery();
        resetPwdQuery.setMobile("18010775624");
        resetPwdQuery.setNewpassword("123456");
        resetPwdQuery.setCaptcha("1234");
        UserResetPwdModel resetPwdModel = new UserResetPwdModel(resetPwdQuery);
        resetPwdModel.getResult().subscribe(iLoadResult -> {
        });
        try {
           int code = System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户注册
     */
    @Test
    public void userRegister() {
        NormalRegisterQuery registerQuery = NormalRegisterQuery.builder().username("zhaoxiaoqiang").password("abc123456")
                .email("1723544852@qq.com").mobile("18010775624").build();
        NormalRegisterModel normalRegisterModel = new NormalRegisterModel(registerQuery);
        normalRegisterModel.getResult().subscribe(iLoadResult -> {

        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userLogin() {
        System.out.println("thread:" + Thread.currentThread().getName());
        RefreshTokenSchedule refreshTokenSchedule = RefreshTokenSchedule.getSchedule();
        LoginQuery loginQuery = LoginQuery.builder().account("zhaoxiaoqiang").password("abc123456").build();
        NormalLoginModel normalLoginModel = new NormalLoginModel(loginQuery);
        normalLoginModel.getResult().subscribe(iLoadResult -> {
            if(iLoadResult.isSuccess()) {
                ITokenResult iTokenResult = (ITokenResult) iLoadResult;
                UserCenterTokenFactory.setAuthToken(iTokenResult.getToken());
                refreshTokenSchedule.execute();
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshTokenSchedule.shutdown();
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 测试验证码
     */
    @Test
    public void testVerify() {
        MobileVerifyQuery mobileVerifyQuery = MobileVerifyQuery.builder().mobile("18010775624").event(VerifyEnums.ResetPwd.getValue()).build();
        SendVerifyModel sendRegisterModel = new SendVerifyModel(mobileVerifyQuery);
        sendRegisterModel.getResult().subscribe(iLoadResult1 -> {
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReset() {
        ResetPwdQuery resetPwdQuery = new ResetPwdQuery();
        resetPwdQuery.setMobile("18010775624");
        resetPwdQuery.setNewpassword("abc123456");
        resetPwdQuery.setCaptcha("9933");
        UserResetPwdModel resetPwdModel = new UserResetPwdModel(resetPwdQuery);
        resetPwdModel.getResult().subscribe(iLoadResult2 -> {});
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }















}