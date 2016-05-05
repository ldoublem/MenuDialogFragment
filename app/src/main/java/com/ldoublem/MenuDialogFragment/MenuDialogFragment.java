package com.ldoublem.MenuDialogFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuDialogFragment extends DialogFragment {


    private LinearLayout mWrapperButtons;
    private LinearLayout mWrapperText;
    private List<View> lys = new ArrayList<View>();
    Drawable mDrawable;


    public static MenuDialogFragment newInstance() {
        MenuDialogFragment contextMenuDialogFragment = new MenuDialogFragment();

        return contextMenuDialogFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MenuFragmentStyle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        initViews(rootView);
        return rootView;
    }

    @SuppressLint("NewApi")
    private void initViews(final View view) {
        mWrapperButtons = (LinearLayout) view.findViewById(R.id.wrapper_buttons);
        mWrapperText = (LinearLayout) view.findViewById(R.id.wrapper_text);

        mDrawable = getActivity().getResources().getDrawable(R.drawable.white_dra);

        mDrawable.setAlpha(230);
        view.findViewById(R.id.ly_diss).setBackground(mDrawable);

        lys.add(view.findViewById(R.id.ly_sousuo));
        lys.add(view.findViewById(R.id.ly_edit));
        lys.add(view.findViewById(R.id.ly_shijian));
        lys.add(view.findViewById(R.id.ly_note));
        lys.add(view.findViewById(R.id.ly_set));
        lys.add(view.findViewById(R.id.ly_cancel));

        view.findViewById(R.id.ly_btn_sousuo).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                scaleButton(v, new onDissView() {

                    @Override
                    public void onDissViewDo() {
                        // TODO Auto-generated method stub


                    }
                });
                //				mActivityMain.openActivity(ActivitySetting.class);

            }
        });

        view.findViewById(R.id.ly_btn_edit).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //				scaleButton(v);
                //				mActivityMain.openActivity(ActivitySetting.class);

                scaleButton(v, new onDissView() {

                    @Override
                    public void onDissViewDo() {


                    }
                });

            }
        });

        view.findViewById(R.id.ly_btn_shijian).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                scaleButton(v, new onDissView() {

                    @Override
                    public void onDissViewDo() {

                    }
                });

            }
        });

        view.findViewById(R.id.ly_btn_note).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                scaleButton(v, new onDissView() {

                    @Override
                    public void onDissViewDo() {

                    }
                });
            }
        });

        view.findViewById(R.id.ly_btn_set).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //				onDissView o =
                scaleButton(v, new onDissView() {

                    @Override
                    public void onDissViewDo() {
                        // TODO Auto-generated method stub


                    }
                });

            }
        });

        view.findViewById(R.id.ly_btn_cancel).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                scaleButton(v, null);
            }
        });

        view.findViewById(R.id.ly_diss).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dissView(new onDissView() {
                    @Override
                    public void onDissViewDo() {


                    }
                }, null);

            }
        });

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottom_in4);
        view.startAnimation(animation);
        showOtherBtns();

    }

    Animation animationout;
    Animation scale_out;

    public void scaleButton(final View v, final onDissView monDissView) {

//		mActivityMain.isShowMenu = true;

        if (scale_out == null) {
            scale_out = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_out);
        }
        scale_out.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                // TODO Auto-generated method stub
            }
        });
        //		}
        v.setClickable(false);

        v.setLayoutParams(new LinearLayout.LayoutParams(v.getWidth() + 2, v.getHeight() + 2));//因为不加2中间的空间放大时候会出错，具体什么原因找了很久都没有发现
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int w = v.getWidth();
        int h = v.getHeight();
        ViewGroup p = (ViewGroup) v.getParent();
        if (p != null) {
            v.setVisibility(View.INVISIBLE);
            p.removeAllViewsInLayout();
        }

        ((RelativeLayout) getView().findViewById(R.id.ly_diss)).addView(v);

        Message m = mHandler.obtainMessage();

        m.obj = monDissView;

        Bundle b = new Bundle();
        b.putInt("x", x);//坐标 x
        b.putInt("y", y);//坐标 y
        b.putInt("w", w);
        b.putInt("h", h);

        m.arg1 = v.getId();
        m.setData(b);
        mHandler.sendMessage(m);

    }

    private void showOtherBtns() {
        Animation scale_inOther = null;
        if (scale_inOther == null) {
            scale_inOther = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_in);
        }

        for (View btn : lys) {
            btn.startAnimation(scale_inOther);

        }
    }

    private void disOtherBtns(View v) {
        Animation scale_outOther = null;
        if (scale_outOther == null) {
            scale_outOther = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_out_small);
        }

        for (View btn : lys) {

            if (v != null) {

                if (btn.getId() == v.getId()) {

                } else {
                    btn.setVisibility(View.INVISIBLE);
                    btn.startAnimation(scale_outOther);
                }
            } else {
                btn.setVisibility(View.INVISIBLE);
                btn.startAnimation(scale_outOther);

            }
        }
        getView().startAnimation(animationout);

    }

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            Bundle b = msg.getData();
            int x = b.getInt("x", 0);
            int y = b.getInt("y", 0);
            int w = b.getInt("w", 0);
            int h = b.getInt("h", 0);
            View v = getView().findViewById(msg.arg1);

            onDissView monDissView = (onDissView) msg.obj;


            setLayout(v, x, y);

            v.startAnimation(scale_out);
            dissView(monDissView, v);

        }
    };

    public static void setLayout(View view, int x, int y) {
        MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y- margin.height, 0,0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    public void dissView(final onDissView monDissView, View v) {//v 不缩小的布局

        if (animationout == null) {
            animationout = AnimationUtils.loadAnimation(getActivity(), R.anim.push_bottom_out4);
        }
        animationout.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                getView().setVisibility(View.GONE);
                dismiss();
                if (monDissView != null)
                    monDissView.onDissViewDo();

            }
        });
        disOtherBtns(v);

    }

    public interface onDissView {
        public void onDissViewDo();
    }


}