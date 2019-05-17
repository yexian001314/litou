package com.sleep.uulib.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sleep.uulib.R;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * 获取地址数据并显示地址选择器
 *
 * @author 李玉江[QQ:1032694760]
 * @since 2015/12/15
 */
public class AddressPickTask extends AsyncTask<String, Void, ArrayList<Province>> {
    private Activity activity;
    private ProgressDialog dialog;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;

    public AddressPickTask(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<Province> data = new ArrayList<>();
        try {
            String json = ConvertUtils.toString(activity.getAssets().open("city.json"));
            Gson gson = new Gson();
            List<Province> provinces = gson.fromJson(json, new TypeToken<List<Province>>() {}.getType());
            data.addAll(provinces);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            callback.onAddressInitFinish(result);
        } else {
            callback.onAddressInitFailed();
        }
    }

    /**
     * 显示picker弹窗
     * @param result 城市列表
     */
    public void showPicker(ArrayList<Province> result) {
        final AddressPicker picker = new AddressPicker(activity, result);
        picker.setHideProvince(hideProvince);
        picker.setHideCounty(hideCounty);
        if (hideCounty) {
            picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
        } else {
            picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
        }
        picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
        picker.setOnAddressPickListener(callback);
        picker.setDividerVisible(false);
        View titleView = activity.getLayoutInflater().inflate(R.layout.pickerview_custom_view, null);
        picker.setHeaderView(titleView);
        picker.setShadowColor(Color.parseColor("#dcdcdc"));
        picker.setLineSpaceMultiplier(3);
        picker.setOffset(3);
        picker.setTextColor(Color.parseColor("#010101"),Color.parseColor("#adadad"));
        picker.setTopLineVisible(false);
        picker.show();
        titleView.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.onSubmit();
                picker.dismiss();
            }
        });
    }

    public interface Callback extends AddressPicker.OnAddressPickListener {

        void onAddressInitFailed();

        void onAddressInitFinish(ArrayList<Province> data);
    }

}
