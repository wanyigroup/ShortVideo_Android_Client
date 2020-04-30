package com.wanyi.uiframe.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.wanyi.uiframe.R;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/1/9 0009.
 */
public  abstract class BaseDialogFragment extends DialogFragment
{

    protected final String tag=getClass().getName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog);

    }



    Unbinder unbinder;
    protected View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        this.rootView = view;
        unbinder = ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    protected abstract int getLayoutId();


    public String getFmTag()
    {
        return getClass().getName();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isTouchHide())
        {
            view.setOnClickListener(new DialogDismissOnclick(this));
        }
        //view.setSaveEnabled(false);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCanceledOnTouchOutside(false);

        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);

    }


    Long nowTime=0L;
    @Override
    public void show(FragmentManager manager, String tag) {
        if(System.currentTimeMillis()-nowTime<1000) {

            Log.e(tag,"点击频率过快会崩溃的奥!");
            return;
        }
        else
        {
            nowTime= System.currentTimeMillis();
        }

        super.show(manager, tag);
    }

    protected abstract boolean isTouchHide();


    protected void showDialog(BaseDialogFragment dialogFragment)
    {
        dialogFragment.show(getFragmentManager(),dialogFragment.getFmTag());
    }

    protected void showLog(String msg)
    {
        Log.e(tag,msg);
    }

    protected void showToast(String msg)
    {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    protected boolean checkEmpty(EditText input)
    {
       boolean isEmpty =  input.getText().toString().isEmpty();
       return isEmpty;
    }

    protected int inputNumber(EditText input)
    {
       int number  = Integer.parseInt(input.getText().toString().trim());

       return number;
    }

    protected String inputText(EditText input)
    {
        String txt=input.getText().toString().trim();

        return txt;
    }










}
