package com.testerhome.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.flyco.systembar.SystemBarHelper;
import com.testerhome.android.core.BaseApplication;
import com.testerhome.android.core.data.DataManager;
import com.testerhome.android.core.data.model.HelloUser;
import com.testerhome.android.core.data.model.OAuth;
import com.testerhome.android.inject.module.ActivityModule;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(BaseApplication.get(this).getComponent())
                .build();
        activityComponent.inject(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int color = getResources().getColor(R.color.colorPrimary_light);
        //方法1:删除DrawerLayout所在布局中所有fitsSystemWindows属性,尤其是DrawerLayout的fitsSystemWindows属性
        SystemBarHelper.tintStatusBarForDrawer(this, (DrawerLayout) findViewById(R.id.drawer_layout), color);
        SystemBarHelper.setPadding(this, ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                dataManager.getOAuth("xxx")
                        .flatMap(new Func1<OAuth, Observable<HelloUser>>() {
                            @Override
                            public Observable<HelloUser> call(OAuth oAuth) {
                                return dataManager.getHelloUser(oAuth.getAccessToken());
                            }
                        })
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                Log.d(TAG, "doOnSubscribe");
                            }
                        })
                        .subscribe(new Subscriber<HelloUser>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError", e);
                            }

                            @Override
                            public void onNext(HelloUser helloUser) {
                                Log.d(TAG, helloUser.toString());
                            }
                        });


            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
