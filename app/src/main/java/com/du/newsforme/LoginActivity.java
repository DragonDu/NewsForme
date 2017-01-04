package com.du.newsforme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.du.newsforme.bean.UserInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_regist)
    Button btRegist;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.login_tecent)
    Button loginTecent;
    @BindView(R.id.login_sina)
    Button loginSina;
    @BindView(R.id.login_wechat)
    Button loginWechat;
    @BindView(R.id.ib_usermore)
    ImageButton ibUsermore;
//    @BindView(R.id.lv_user)
    ListView lvUser;

    private UserInfo userInfo;
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initEvent();
        ShareSDK.initSDK(this);
    }


    /**
     * 开启登录窗口
     *
     * @param context
     */
    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private void initEvent() {
        btRegist.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        loginSina.setOnClickListener(this);
        loginTecent.setOnClickListener(this);
        loginWechat.setOnClickListener(this);
        ibUsermore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regist:
                Snackbar.make(v, "这是要注册的节奏啊", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.bt_login:
                Snackbar.make(v, "这是要登录的节奏啊", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.login_sina:
                Snackbar.make(v, "这是要登录新浪节奏啊", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.login_tecent:
                ShareSDKforlogin();
                Snackbar.make(v, "这是要登录腾讯的节奏啊", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.login_wechat:
                Snackbar.make(v, "这是要登录微信的节奏啊", Snackbar.LENGTH_SHORT).show();
                getshouquan();
                break;
            case R.id.ib_usermore:
                Snackbar.make(v, "更多消息", Snackbar.LENGTH_SHORT).show();
                getpopwindow();
                break;

        }
    }

    /**
     * 更多用户
     */
    private void initpopwindow() {
        View pop_view = getLayoutInflater().inflate(R.layout.popwindowview, null, false);
        lvUser= (ListView) pop_view.findViewById(R.id.lv_user);
        popupWindow = new PopupWindow(pop_view, getWindow().getAttributes().width, 200);
        popupWindow.showAsDropDown(etUsername);
        lvUser.setAdapter(new ArrayAdapter<String>(this, R.layout
                .support_simple_spinner_dropdown_item, new String[]{"1", "2", "3"}));
    }

    public void getpopwindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            initpopwindow();
        }
    }

    public void ShareSDKforlogin() {
        Platform tecent = ShareSDK.getPlatform(QQ.NAME);
        tecent.SSOSetting(false);
        tecent.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Snackbar.make(getWindow().getDecorView(), "成功了？", Snackbar.LENGTH_SHORT).show();
                Log.e("授权信息输出", "----------------------------");
                platform.getDb().exportData();
                Log.e("授权信息输出", "----------------------------");
                turnout(platform.getDb());


                System.out.println("成功了");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                System.out.println("出错了？");
                Snackbar.make(getWindow().getDecorView(), "出啥错了", Snackbar.LENGTH_SHORT).show();
                System.out.println("----------");
                throwable.printStackTrace();
                System.out.println("----------");

            }

            @Override
            public void onCancel(Platform platform, int i) {
                Snackbar.make(getWindow().getDecorView(), "退出是干嘛的", Snackbar.LENGTH_SHORT).show();

            }
        });

        tecent.authorize();
    }

    public void getshouquan() {
        Platform ten = ShareSDK.getPlatform(QQ.NAME);
        Log.e("授权信息打印", "----------------------------");
        String token = ten.getDb().getToken();
        System.out.println("token:--" + token);
        String userId = ten.getDb().getUserId();
        System.out.println("userId:--" + userId);
        String userName = ten.getDb().getUserName();
        System.out.println("userN:--" + userName);
        String userIcon = ten.getDb().getUserIcon();
        System.out.println("icon:---" + userIcon);
        Log.e("授权信息打印", "----------------------------");
    }

    public void turnout(PlatformDb db) {
        userInfo = new UserInfo();
        userInfo.setToken(db.getToken());
        userInfo.setUserGender(db.getUserGender());
        userInfo.setUserIcon(db.getUserIcon());
        userInfo.setUserId(db.getUserId());
        userInfo.setUserName(db.getUserName());
        TupianClass TupianClass = new TupianClass(userInfo);
        EventBus.getDefault().post(TupianClass);
        finish();
    }

    public class TupianClass {
        UserInfo userIn;

        public TupianClass(UserInfo user) {
            userIn = user;
        }

        public UserInfo shuaxin() {
            return userIn;
        }
    }
}
