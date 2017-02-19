package com.orangelittle.u17.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orangelittle.u17.R;
import com.orangelittle.u17.entries.SignBean;
import com.orangelittle.u17.widget.SignCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class SignActivity extends BaseActivity {

    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private TextView popupwindow_calendar_month;
    private SignCalendar calendar;
    private Button btn_signIn;
    private List<String> list = new ArrayList<String>(); //设置标记列表
    boolean isinput=false;
    private String date1 = null;//单天日期

    private Realm mRealm;
    private RealmAsyncTask mRransaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mRealm=Realm.getDefaultInstance();
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        date1 =formatter.format(curDate);

        findViewById(R.id.sign_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        calendar = (SignCalendar) findViewById(R.id.popupwindow_calendar);
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
                + calendar.getCalendarMonth() + "月");
        if (null != date) {
            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");

            calendar.showCalendar(years, month);
//            calendar.setCalendarDayBgColor(date,
//                    0);
        }

        query();
        if(isinput){
            btn_signIn.setText("今日已签，明日继续");
            btn_signIn.setBackgroundResource(R.drawable.button_gray);
            btn_signIn.setEnabled(false);
        }
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today= calendar.getThisday();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           /* calendar.removeAllMarks();
           list.add(df.format(today));
           calendar.addMarks(list, 0);*/
                //将当前日期标示出来
                add(df.format(today));
                calendar.addMark(today, 0);
                query();
                HashMap<String, Integer> bg = new HashMap<String, Integer>();

                calendar.setCalendarDayBgColor(today, R.drawable.bg_sign_today);
                btn_signIn.setText("今日已签，明日继续");
                btn_signIn.setBackgroundResource(R.drawable.button_gray);
                btn_signIn.setEnabled(false);
            }
        });
//		监听所选中的日期
        calendar.setOnCalendarClickListener(new SignCalendar.OnCalendarClickListener() {

            public void onCalendarClick(int row, int col, String dateFormat) {
                int month = Integer.parseInt(dateFormat.substring(
                        dateFormat.indexOf("-") + 1,
                        dateFormat.lastIndexOf("-")));
                if (calendar.getCalendarMonth() - month == 1//跨年跳转
                        || calendar.getCalendarMonth() - month == -11) {
                    calendar.lastMonth();
                } else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
                        || month - calendar.getCalendarMonth() == -11) {
                    calendar.nextMonth();
                }
            }
        });

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new SignCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month
                        .setText(year + "年" + month + "月");
            }
        });
    }

    public void add(String date)
    {

        final SignBean signBean=new SignBean();
        signBean.setSelected(true);
        signBean.setSignDate(date);
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(signBean);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Snackbar.make(btn_signIn,"签到数据插入失败！",Snackbar.LENGTH_SHORT).show();
            }
        });


    }

    public void query()
    {
        final RealmResults<SignBean> results = mRealm.where(SignBean.class).findAll();
        for (SignBean sign : results)
        {
            list.add(sign.getSignDate());
            if(date1.equals(sign.getSignDate())){
                isinput=true;
            }
        }
        calendar.addMarks(list, 0);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRransaction != null && !mRransaction.isCancelled()) {
            mRransaction.cancel();
        }
    }
}
