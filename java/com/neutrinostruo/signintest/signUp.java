package com.neutrinostruo.signintest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.text.Html;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signUp extends AppCompatActivity {

    private signUpViewPager viewPager;
    private TextView[] dots;
    private signUpPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private int[] signUpLayouts;


    private ImageButton signUpTwoLink;
    private ImageButton signUpThreeLink;
    private ImageButton signUpLink;
    private EditText signUpEmailField;
    private EditText signUppasswordField;
    private EditText signUpConfirmpasswordField;
    private EditText signUpNameField;
    private EditText signUpMobileNo;
    private EditText signUpAdmNo;
    private Spinner signUpBranch;
    private Spinner signUpYear;
    private Spinner signUpSection;

    private ProgressBar signUpProgressBar;

    private String userEmail;
    private String userPassword;
    private String userConfirmPassword;
    private String userName;
    private String userMobile;
    private String userAdmNo;
    private String userBranch;
    private String userYear;
    private String userSection;

    private LinearLayout signUpOneContainer;
    private LinearLayout signUpTwoContainer;
    private LinearLayout signUpThreeContainer;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private userData newUser = new userData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        viewPager = (signUpViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();




        signUpLayouts = new int[] {
                R.layout.sign_up_one,
                R.layout.sign_up_two,
                R.layout.sign_up_three
        };

        viewPagerAdapter = new signUpPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPagingEnabled(false);
        addBottomDots(0);

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }



    public class signUpPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public signUpPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(signUpLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return signUpLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            addBottomDots(position);
            View view = viewPager.getChildAt(viewPager.getCurrentItem());


            switch(position){
                case 1:
                    signUpTwoContainer = (LinearLayout) findViewById(R.id.signUpTwoContainer);
                    signUpTwoContainer.getBackground().setAlpha(150);
                    signUpNameField = (EditText) findViewById(R.id.signUpNameField);
                    signUpNameField.getBackground().setAlpha(100);
                    signUpMobileNo = (EditText) findViewById(R.id.signUpPhoneField);
                    signUpMobileNo.getBackground().setAlpha(100);
                    signUpAdmNo = (EditText) findViewById(R.id.signUpAdmissionNoField);
                    signUpAdmNo.getBackground().setAlpha(100);
                    signUpBranch = (Spinner) findViewById(R.id.branchSpinner);
                    signUpYear = (Spinner) findViewById(R.id.yearSpinner);
                    signUpSection = (Spinner) findViewById(R.id.sectionSpinner);
                    signUpSection.setEnabled(true);
                    signUpThreeLink = (ImageButton) findViewById(R.id.signUpThreeLink);
                    signUpYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String userYear = signUpYear.getSelectedItem().toString().trim();
                            if(userYear.equals("1")){
                                signUpSection.setEnabled(true);
                            }
                            else{
                                signUpSection.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    signUpThreeLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            userName = signUpNameField.getText().toString().trim();
                            userMobile = signUpMobileNo.getText().toString().trim();
                            userAdmNo = signUpAdmNo.getText().toString().trim();
                            userBranch = signUpBranch.getSelectedItem().toString().trim();
                            userSection = signUpSection.getSelectedItem().toString().trim();
                            userYear = signUpYear.getSelectedItem().toString().trim();
                            if(signUpSection.isEnabled()){
                                userSection = signUpSection.getSelectedItem().toString().trim();
                            }
                            else{
                                userSection = null;
                            }

                            if(userName.equals(""))
                                Toast.makeText(signUp.this,"Enter Your Name!",Toast.LENGTH_SHORT).show();
                            else if (userMobile.length()!=10)
                                Toast.makeText(signUp.this,"Enter Correct Mobile Number!",Toast.LENGTH_SHORT).show();
                            else if(userAdmNo.equals(""))
                                Toast.makeText(signUp.this,"Enter Your Admission Number!",Toast.LENGTH_SHORT).show();
                            else
                                viewPager.setCurrentItem(position+1);

                        }
                    });
                    break;

                case 2:
                    signUpThreeContainer = (LinearLayout) findViewById(R.id.signUpThreeContainer);
                    signUpThreeContainer.getBackground().setAlpha(150);
                    signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);
                    signUpProgressBar.setVisibility(View.INVISIBLE);
                    final String displayPictureUri = "";
                    signUpLink = (ImageButton) findViewById(R.id.signUpLink);
                    signUpLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            signUpProgressBar.setVisibility(View.VISIBLE);
                            newUser = new userData(userEmail,userPassword,userName,displayPictureUri,userMobile,userAdmNo,
                                    userBranch,userYear,userSection);
                            databaseReference.child("Users").push().setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    signUpProgressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(signUp.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                    break;

            }



        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[signUpLayouts.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if(i==currentPage)
                dots[i].setTextColor(this.getResources().getColor(R.color.dot_dark,getTheme()));
            else
                dots[i].setTextColor(this.getResources().getColor(R.color.dot_light,getTheme()));
            dotsLayout.addView(dots[i]);
        }

    }

    public void signUpOneButtonClick(View v){
        signUpOneContainer = (LinearLayout) findViewById(R.id.signUpOneContainer);
        signUpOneContainer.getBackground().setAlpha(150);
        signUpTwoLink = (ImageButton) findViewById(R.id.signUpTwoLink);
        signUpEmailField = (EditText) findViewById(R.id.signUpEmailField);
        signUpEmailField.getBackground().setAlpha(100);
        signUppasswordField = (EditText) findViewById(R.id.signUppasswordField);
        signUppasswordField.getBackground().setAlpha(100);
        signUpConfirmpasswordField= (EditText) findViewById(R.id.signUpConfirmpasswordField);
        signUpConfirmpasswordField.getBackground().setAlpha(100);
        userEmail = signUpEmailField.getText().toString().trim();
        userPassword = signUppasswordField.getText().toString().trim();
        userConfirmPassword = signUpConfirmpasswordField.getText().toString().trim();
        int current = viewPager.getCurrentItem();
        if (!userEmail.equals("")&&userPassword.equals(userConfirmPassword)&&current<signUpLayouts.length) {
            viewPager.setCurrentItem(current+1);
        }
        Toast.makeText(signUp.this,userEmail,Toast.LENGTH_SHORT).show();
    }

}
