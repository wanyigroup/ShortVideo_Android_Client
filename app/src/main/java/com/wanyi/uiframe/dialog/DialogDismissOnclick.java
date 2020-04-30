package com.wanyi.uiframe.dialog;

import android.view.View;

import androidx.fragment.app.DialogFragment;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class DialogDismissOnclick implements View.OnClickListener
{

    private DialogFragment dialogFragment;
    public DialogDismissOnclick(DialogFragment dialogFragment)
    {
        this.dialogFragment=dialogFragment;
    }


    @Override
    public void onClick(View v) {

        dialogFragment.dismiss();
    }


}
