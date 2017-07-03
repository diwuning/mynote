package com.h.mynote.recommend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.h.mynote.R;
import com.h.mynote.recommend.book.BookActivity;
import com.h.mynote.recommend.bookdetail.BookDetailActivity;
import com.h.mynote.recommend.homepage.adapter.BookGridAdapter;
import com.h.mynote.recommend.homepage.adapter.MovieGridAdapter;
import com.h.mynote.recommend.homepage.adapter.MusicGridAdapter;
import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.bean.music.Music;
import com.h.mynote.recommend.homepage.bean.music.MusicEntity;
import com.h.mynote.recommend.homepage.retrofit.HttpService;
import com.h.mynote.recommend.homepage.retrofit.book.BookListPresenter;
import com.h.mynote.recommend.homepage.retrofit.book.IBookView;
import com.h.mynote.recommend.homepage.retrofit.tv.HttpMethods;
import com.h.mynote.recommend.movie.MovieSearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecommendActivity extends Activity implements IBookView {
    public static final String TAG = RecommendActivity.class.getSimpleName();
    //返回
    @BindView(R.id.iv_recommendBack)
    ImageView ivRecommendBack;
    //页头
    @BindView(R.id.rl_headCate)
    RelativeLayout rlHeadCate;
    //轮播图布局
    @BindView(R.id.ll_pic)
    LinearLayout llPic;
    //电影
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_movie)
    TextView tvMovie;
    @BindView(R.id.rl_movie)
    RelativeLayout rlMovie;
    @BindView(R.id.gv_movie)
    GridView gvMovie;
    @BindView(R.id.ll_movie)
    LinearLayout llMovie;
    //电视剧
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_tv)
    TextView tvTv;
    @BindView(R.id.rl_tv)
    RelativeLayout rlTv;
    @BindView(R.id.gv_tv)
    GridView gvTv;
    @BindView(R.id.ll_tv)
    LinearLayout llTv;
    //图书
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv_book)
    TextView tvBook;
    @BindView(R.id.rl_book)
    RelativeLayout rlBook;
    @BindView(R.id.gv_book)
    GridView gvBook;
    @BindView(R.id.ll_book)
    LinearLayout llBook;
    //音乐
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.tv_music)
    TextView tvMusic;
    @BindView(R.id.rl_music)
    RelativeLayout rlMusic;
    @BindView(R.id.gv_music)
    GridView gvMusic;
    @BindView(R.id.ll_music)
    LinearLayout llMusic;
    //总布局
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;

    Context mContext;

    List<SubjectBody> subjectList;//TOP250的返回结果
    MovieGridAdapter movieGridAdapter;

    List<Music> musicList;
    MusicGridAdapter musicGridAdapter;

    Subscriber<MovieEntity> subscriber;
    List<SubjectBody> searchSubject;//电视剧结果
    MovieGridAdapter tvGridAdapter;

    List<Book> bookList;
    BookGridAdapter bookGridAdapter;

    //MVP
    BookListPresenter bookListPresenter;
    //更多
    @BindView(R.id.tv_movieMore)
    TextView tvMovieMore;
    @BindView(R.id.tv_tvMore)
    TextView tvTvMore;
    @BindView(R.id.tv_bookMore)
    TextView tvBookMore;
    @BindView(R.id.tv_musicMore)
    TextView tvMusicMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        ButterKnife.bind(this);

        mContext = RecommendActivity.this;

//        getMovie();
//        searchMovie();
//        getMusic();

        //mvp架构获取图书列表
        bookListPresenter = new BookListPresenter(this);
//        bookListPresenter.getBookList(HttpAddr.BASE_URL, "畅销", 0, 10);
        bookListPresenter.getAllList(HttpAddr.BASE_URL,"国产剧","影视","畅销","",10,0);

    }

    /*
    * 获取电影列表
    * 没有经过封装的、原生态的Retrofit写网络请求的代码
    * */
//    public void getMovie() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(HttpAddr.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        HttpService httpService = retrofit.create(HttpService.class);
//
//        Call<MovieEntity> call = httpService.getTopMovie(0, 10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                subjectList = response.body().getSubjects();
//
//                LinearLayout.LayoutParams params = getParams();
//                gvMovie.setLayoutParams(params);
//                gvMovie.setColumnWidth(100);//设置列表项宽
//                gvMovie.setHorizontalSpacing(15);//设置列表项水平间距
//
//                movieGridAdapter = new MovieGridAdapter(subjectList, mContext);
//                Log.e(TAG, response.body().getTotal() + "");
//                gvMovie.setAdapter(movieGridAdapter);
//                //Toast.makeText(mContext,"获取列表成功",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                Toast.makeText(mContext, "获取列表失败", Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    /*
    * 获取音乐推荐
    * Retrofit和Rxjava的结合
    * */
//    public void getMusic() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(HttpAddr.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        HttpService httpService = retrofit.create(HttpService.class);
//        httpService.getMusic("影视", 0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MusicEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(RecommendActivity.this, "Get Top Music Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(RecommendActivity.this, "Get Top Music Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(MusicEntity musicEntity) {
//                        musicList = musicEntity.getMusics();
//
//                        LinearLayout.LayoutParams params = getParams();
//                        gvMusic.setLayoutParams(params);
//                        gvMusic.setColumnWidth(100);//设置列表项宽
//                        gvMusic.setHorizontalSpacing(15);//设置列表项水平间距
//                        musicGridAdapter = new MusicGridAdapter(musicList, mContext);
//                        gvMusic.setAdapter(musicGridAdapter);
//                    }
//                });
//
////        //保持焦点在顶端
////        tvMovieMore.setFocusable(true);
////        tvMovieMore.setFocusableInTouchMode(true);
////        tvMovieMore.requestLayout();
//
//    }

    /*
    * 获取电视剧列表
    * 封装后的用法
    * */
//    public void searchMovie() {
//        subscriber = new Subscriber<MovieEntity>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(RecommendActivity.this, "Get Top Movie Completed by package", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Toast.makeText(RecommendActivity.this, "Get Top Movie failed by package" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNext(MovieEntity movieEntity) {
//                searchSubject = movieEntity.getSubjects();
//                LinearLayout.LayoutParams params = getParams();
//                gvTv.setLayoutParams(params);
//                gvTv.setColumnWidth(100);//设置列表项宽
//                gvTv.setHorizontalSpacing(15);//设置列表项水平间距
//                tvGridAdapter = new MovieGridAdapter(searchSubject, mContext);
//                gvTv.setAdapter(tvGridAdapter);
//
//            }
//        };
//
//        HttpMethods.getInstance().searchMovie(subscriber, 0, 10, "国产剧");
//    }


    @OnClick(R.id.iv_recommendBack)
    public void onClick() {
        finish();
    }

    /*
    * 获取图书列表
    * mvp
    * */
    @Override
    public void showBookList(List<Book> books) {
        if (books != null && books.size() != 0) {
            bookList = books;

            LinearLayout.LayoutParams params = getParams();
            gvBook.setLayoutParams(params);
            gvBook.setColumnWidth(100);//设置列表项宽
            gvBook.setHorizontalSpacing(15);//设置列表项水平间距
            bookGridAdapter = new BookGridAdapter(books, mContext);
            gvBook.setAdapter(bookGridAdapter);
        }
    }

    /*
    * 计算GridView 的宽度
    * */
    public LinearLayout.LayoutParams getParams(){
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (10 * (length + 4) * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        return params;
    }

    /*
    * 同时进行多个网络请求
    * */
    @Override
    public void showMovieList(List<SubjectBody> movies) {
        Log.d(TAG,"showMovieList:"+movies.size()+movies.get(0).getTitle());
        LinearLayout.LayoutParams params = getParams();
        gvMovie.setLayoutParams(params);
        gvMovie.setColumnWidth(100);//设置列表项宽
        gvMovie.setHorizontalSpacing(15);//设置列表项水平间距

        movieGridAdapter = new MovieGridAdapter(movies, mContext);
//        Log.e(TAG, response.body().getTotal() + "");
        gvMovie.setAdapter(movieGridAdapter);
    }

    @Override
    public void showTvList(List<SubjectBody> tvs) {
        LinearLayout.LayoutParams params = getParams();
        gvTv.setLayoutParams(params);
        gvTv.setColumnWidth(100);//设置列表项宽
        gvTv.setHorizontalSpacing(15);//设置列表项水平间距
        tvGridAdapter = new MovieGridAdapter(tvs, mContext);
        gvTv.setAdapter(tvGridAdapter);
    }

    @Override
    public void showMusicList(List<Music> musics) {
        Log.d(TAG,""+musics.size()+musics.get(0).getTitle());
        LinearLayout.LayoutParams params = getParams();
        gvMusic.setLayoutParams(params);
        gvMusic.setColumnWidth(100);//设置列表项宽
        gvMusic.setHorizontalSpacing(15);//设置列表项水平间距
        musicGridAdapter = new MusicGridAdapter(musics, mContext);

        gvMusic.setAdapter(musicGridAdapter);
    }

    @Override
    public void showBookListNew(List<Book> books) {
        bookList = books;
        LinearLayout.LayoutParams params = getParams();
        gvBook.setLayoutParams(params);
        gvBook.setColumnWidth(100);//设置列表项宽
        gvBook.setHorizontalSpacing(15);//设置列表项水平间距
        bookGridAdapter = new BookGridAdapter(books, mContext);
        gvBook.setAdapter(bookGridAdapter);
    }

    @OnClick({R.id.tv_movieMore, R.id.tv_tvMore, R.id.tv_bookMore, R.id.tv_musicMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_movieMore:
                Intent movieIntent = new Intent(RecommendActivity.this, MovieSearchActivity.class);
                movieIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(movieIntent);
                break;
            case R.id.tv_tvMore:
                Intent tvIntent = new Intent(RecommendActivity.this, MovieSearchActivity.class);
                tvIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(tvIntent);
                break;
            case R.id.tv_bookMore:
                Intent bookIntent = new Intent(RecommendActivity.this,BookActivity.class);
                bookIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(bookIntent);
                break;
            case R.id.tv_musicMore:
                break;
        }
    }

    @OnItemClick(R.id.gv_book)
    public void OnItemClick(int position){
        Intent intent = new Intent(RecommendActivity.this, BookDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id",bookList.get(position).getId());
        startActivity(intent);
    }
}
