package com.h.mynote.recommend.moviedetail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.h.mynote.R;
import com.h.mynote.common.NetUtil;
import com.h.mynote.recommend.addr.HttpAddr;
import com.h.mynote.recommend.book.contract.IBookView;
import com.h.mynote.recommend.book.presenter.BookPresenter;
import com.h.mynote.recommend.homepage.adapter.BookGridAdapter;
import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.movie.CastBody;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.bean.music.TagMusic;
import com.h.mynote.recommend.moviedetail.bean.MovieDetail;
import com.h.mynote.recommend.moviedetail.bean.PhotoDetail;
import com.h.mynote.recommend.moviedetail.contract.IMovieDetailView;
import com.h.mynote.recommend.moviedetail.presenter.MovieDetailPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends Activity implements IMovieDetailView, IBookView {
    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    @BindView(R.id.iv_detailCancel)
    ImageView ivCancel;
    @BindView(R.id.iv_movieImg)
    ImageView ivMovieImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_directer)
    TextView tvDirecter;
    @BindView(R.id.tv_cast)
    TextView tvCast;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.gv_photo)
    GridView gvPhoto;
    @BindView(R.id.scroll_photo)
    HorizontalScrollView scrollPhoto;

    SubjectBody subjectBody;
    String id;

    MovieDetailPresenter movieDetailPresenter;
    Context mContext;

    BookPresenter bookPresenter;
    @BindView(R.id.ll_ge2)
    LinearLayout llGe2;
    @BindView(R.id.ll_photo)
    LinearLayout llPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        mContext = MovieDetailActivity.this;

        if (getIntent().getStringExtra("id") != null) {
//            subjectBody = (SubjectBody) getIntent().getSerializableExtra("movieItem");
            id = getIntent().getStringExtra("id");

//            initView();
            if (!NetUtil.isNetworkOk(mContext)) {
                Toast.makeText(mContext, "网络连接异常，请检查网络！", Toast.LENGTH_LONG).show();
            } else {
                movieDetailPresenter = new MovieDetailPresenter(this);
                movieDetailPresenter.getMovieDetail(HttpAddr.BASE_URL, id);
            }

        }

    }

    @Override
    public void showDetail(MovieDetail movieDetail) {
        if (movieDetail != null) {
            ImageLoader.getInstance().displayImage(movieDetail.getImages().getLarge(), ivMovieImg);
            tvTitle.setText(movieDetail.getTitle());
            //导演
            List<CastBody> directors = movieDetail.getDirectors();
            String director = "";
            for(int i=0;i<directors.size();i++){
                if(i<directors.size() -1){
                    director = director + directors.get(i).getName()+",";
                }else if(i == directors.size() -1){
                    director = director + directors.get(i).getName();
                }
            }
            tvDirecter.setText(director);
            //主演
            List<CastBody> casts = movieDetail.getCasts();
            String cast = "";
            for(int i=0;i<casts.size();i++){
                if(i<casts.size() -1){
                    cast = cast + casts.get(i).getName()+",";
                }else if(i == casts.size() -1){
                    cast = cast + casts.get(i).getName();
                }
            }
            tvCast.setText(cast);
            tvYear.setText(movieDetail.getYear());
            //豆瓣评分
            tvRate.setText(String.valueOf(movieDetail.getRating().getAverage()));
            //简介
            tvSummary.setText(movieDetail.getSummary());
            //获取相关图书信息
            bookPresenter = new BookPresenter(this);
            bookPresenter.getBookResult(HttpAddr.BASE_URL, movieDetail.getTitle(), "", 10, 0);

        }
    }

    @Override
    public void showPhoto(List<PhotoDetail> photoDetails) {
        Toast.makeText(mContext, "photo", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showHotTag(List<TagMusic> tagMusics) {

    }

    //显示相关书籍推荐
    @Override
    public void showSearchBook(List<Book> books) {
        Log.d(TAG, "books.size()=" + books.size());
        if (books != null && books.size() != 0) {
            LinearLayout.LayoutParams params = getParams();
            gvPhoto.setLayoutParams(params);
            gvPhoto.setColumnWidth(100);
            gvPhoto.setHorizontalSpacing(15);
            BookGridAdapter bookGridAdapter = new BookGridAdapter(books, mContext);
            gvPhoto.setAdapter(bookGridAdapter);
        } else {
            llPhoto.setVisibility(View.GONE);
            llGe2.setVisibility(View.GONE);
        }
        //保持焦点在顶端
        ivMovieImg.setFocusable(true);
        ivMovieImg.setFocusableInTouchMode(true);
        ivMovieImg.requestLayout();

    }

    /*
    * 计算GridView 的宽度
    * */
    public LinearLayout.LayoutParams getParams() {
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (10 * (length + 4) * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        return params;
    }

    @OnClick(R.id.iv_detailCancel)
    public void detailClick() {
        finish();
    }
}
