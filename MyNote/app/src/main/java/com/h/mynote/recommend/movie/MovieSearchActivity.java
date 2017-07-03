package com.h.mynote.recommend.movie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.common.TextField;
import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.adapter.BookListAdapter;
import com.h.mynote.recommend.homepage.adapter.MovieGridAdapter;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.movie.adapter.MovieAdapter;
import com.h.mynote.recommend.movie.contract.IMovieView;
import com.h.mynote.recommend.movie.presenter.MoviePresenter;
import com.h.mynote.recommend.moviedetail.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;

public class MovieSearchActivity extends Activity implements IMovieView {

    private static final String TAG = MovieSearchActivity.class.getSimpleName();
    //返回
    @BindView(R.id.iv_tvCancel)
    ImageView ivTvCancel;
    //搜索框
    @BindView(R.id.cooksearch_edit)
    TextField cooksearchEdit;
    //正在热映
    @BindView(R.id.tv_theaters)
    TextView tvTheaters;
    @BindView(R.id.gv_theaters)
    GridView gvTheaters;
    @BindView(R.id.ll_theaters)
    LinearLayout llTheaters;
    //搜索结果
    @BindView(R.id.lv_tvResult)
    ListView lvTvResult;
    @BindView(R.id.ll_tvResult)
    LinearLayout llTvResult;

    Context mContext;
    MovieGridAdapter movieGridAdapter;
    List<SubjectBody> theaters;//正在热映

    MoviePresenter moviePresenter;

    List<SubjectBody> resultList;//搜索结果
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        ButterKnife.bind(this);

        mContext = MovieSearchActivity.this;

        //正在热映
        moviePresenter = new MoviePresenter(this);
        moviePresenter.getTheaters(HttpAddr.BASE_URL,"",9,0);

    }

    @OnClick(R.id.iv_tvCancel)
    public void onClick() {
        finish();
    }

    @Override
    public void showMovieResult(List<SubjectBody> movies) {
        if(movies != null && movies.size() != 0){
            llTheaters.setVisibility(View.GONE);
            llTvResult.setVisibility(View.VISIBLE);
            resultList = movies;
            movieAdapter = new MovieAdapter(resultList,mContext);
            lvTvResult.setAdapter(movieAdapter);
        }
    }

    @Override
    public void showTheaters(List<SubjectBody> theaterList) {
        Log.d(TAG,"theaterList.size()"+theaterList.size());
        if(theaterList != null && theaterList.size() != 0){

            theaters = theaterList;
            movieGridAdapter = new MovieGridAdapter(theaterList,mContext);
            gvTheaters.setAdapter(movieGridAdapter);

//            gvTheaters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent tvIntent = new Intent(MovieSearchActivity.this,MovieDetailActivity.class);
//                    tvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    tvIntent.putExtra("id",theaters.get(position).getId());
//                    startActivity(tvIntent);
//                }
//            });
        }
    }

    @OnTextChanged(value = R.id.cooksearch_edit,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(){
        if(cooksearchEdit.getText().toString().trim().equals("")){
            llTvResult.setVisibility(View.GONE);
            llTheaters.setVisibility(View.VISIBLE);
        }else{
            moviePresenter.getMovieResult(HttpAddr.BASE_URL,cooksearchEdit.getText().toString(),10,0);
        }
    }

    @OnItemClick(R.id.lv_tvResult)
    void tvItemClick(int position){
        Intent tvIntent = new Intent(MovieSearchActivity.this,MovieDetailActivity.class);
        tvIntent.putExtra("id",resultList.get(position).getId());
//        tvIntent.putExtra("movieItem",resultList.get(position));
        tvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(tvIntent);
    }

    @OnItemClick(R.id.gv_theaters)
    void theaterItemClick(int position){
        Intent tvIntent = new Intent(MovieSearchActivity.this,MovieDetailActivity.class);
        tvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        tvIntent.putExtra("id",theaters.get(position).getId());
//        tvIntent.putExtra("movieItem",theaters.get(position));
        startActivity(tvIntent);
    }
}
