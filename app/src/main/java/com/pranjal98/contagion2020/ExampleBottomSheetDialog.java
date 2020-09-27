package com.pranjal98.contagion2020;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        RelativeLayout all = v.findViewById(R.id.all);
        RelativeLayout category = v.findViewById(R.id.category);
        RelativeLayout state = v.findViewById(R.id.state);
        RelativeLayout city = v.findViewById(R.id.city);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("");
                dismiss();
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("category");
                dismiss();
            }
        });
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("state");
                dismiss();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("city");
                dismiss();
            }
        });

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
