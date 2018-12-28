package com.feiyang.wanandroid.ui.knowledge.activity;

import android.os.Parcel;
import android.os.Parcelable;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.databinding.ActivityKnowledgeBinding;
import com.feiyang.wanandroid.ui.knowledge.vm.KnowledgeViewModel;
import com.feiyang.wanandroid.ui.main.model.bean.KnowledgeHierarchyData;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/28 4:39 PM<br>
 * Desc: <br>
 */
public class KnowledgeActivity extends BaseActivity<KnowledgeActivity.Param, ActivityKnowledgeBinding, KnowledgeViewModel> {
    private KnowledgeHierarchyData data;

    @Override
    protected int layoutId() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected Class<KnowledgeViewModel> getVM() {
        return KnowledgeViewModel.class;
    }

    @Override
    protected void initViews() {
        super.initViews();
        if (mParam != null) {
            data = mParam.data;
        }
    }

    public static class Param implements Parcelable {
        public KnowledgeHierarchyData data;

        public Param(KnowledgeHierarchyData data) {
            this.data = data;
        }

        protected Param(Parcel in) {
            data = in.readParcelable(KnowledgeHierarchyData.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(data, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Param> CREATOR = new Creator<Param>() {
            @Override
            public Param createFromParcel(Parcel in) {
                return new Param(in);
            }

            @Override
            public Param[] newArray(int size) {
                return new Param[size];
            }
        };
    }
}
