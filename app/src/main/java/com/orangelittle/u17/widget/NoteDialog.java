package com.orangelittle.u17.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.orangelittle.u17.R;


/**
 * Created by Administrator on 2017/2/15 0015.
 */
public class NoteDialog extends Dialog {
    private Context context;
    private TextView tvTitle, tvContent, tvConfirm, tvCancel;
    private NoteDialogListener listener;
    private NoteDialogCancelListener cancelListener;
    private boolean isHide;

    public NoteDialog(Context context) {
        super(context, R.style.base_dialog);
        this.context = context;
        this.setCanceledOnTouchOutside(isHide);
        this.setCancelable(isHide);
        View view = LayoutInflater.from(context).inflate(R.layout.note_dialog, null);
        tvTitle = (TextView) view.findViewById(R.id.note_dialog_title);
        tvContent = (TextView) view.findViewById(R.id.note_dialog_content);
        tvConfirm = (TextView) view.findViewById(R.id.note_dialog_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.handleConfrm();
            }
        });
        tvCancel = (TextView) view.findViewById(R.id.note_dialog_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.handleCancel();
            }
        });
        setContentView(view);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    public interface NoteDialogListener {
        void handleConfrm();
        void handleCancel();
    }
    public interface NoteDialogCancelListener {
        void handleCancel();
    }

    public void setNoteDialogListener(NoteDialogListener listener) {
        this.listener = listener;
    }
    public void setNoteDialogCancelListener(NoteDialogCancelListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void closeDialog(){
        dismiss();
    }

    public void setClickOutSide(boolean isHide){
        this.isHide = isHide;
    }

    public void setConfirem(String confirem){
        tvConfirm.setText(confirem);
    }

    public void setCancel(String cancel){
        tvCancel.setText(cancel);
    }

    public void setCancelColor(int Resid){
        tvCancel.setTextColor(Resid);
    }
}
