package com.example.bag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 보운 on 2015-04-14.
 */
public class InputTextDialog extends DialogFragment {
    ButtonListener click;
    View v;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click = (ButtonListener) activity;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
        v = mLayoutInflater.inflate(R.layout.inputdialog, null);
        mBuilder.setView(v);
        mBuilder.setTitle("문자입력");
        mBuilder.setMessage("화면에 출력할 문구를 입력하세요");
        mBuilder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) v.findViewById(R.id.edittext);
                String str = editText.getText().toString();
                click.onDialogPositiveClick(InputTextDialog.this, str);
            }
        });
        mBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                click.onDialogNegativeClick(InputTextDialog.this);
            }
        });
        return mBuilder.create();
    }

    public void onStop() {
        super.onStop();
    }

    public interface ButtonListener {
        public void onDialogPositiveClick(DialogFragment dialog, String str);

        public void onDialogNegativeClick(DialogFragment dialog);
    }
}
