package com.h.mynote.recommend.homepage.retrofit.book;

import android.graphics.Movie;
import android.util.Log;

import com.h.mynote.recommend.homepage.bean.book.Book;
import com.h.mynote.recommend.homepage.bean.book.BookEntity;
import com.h.mynote.recommend.homepage.bean.movie.MovieEntity;
import com.h.mynote.recommend.homepage.bean.movie.SubjectBody;
import com.h.mynote.recommend.homepage.bean.music.Music;
import com.h.mynote.recommend.homepage.bean.music.MusicEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangchm on 2016/9/13 0013.
 * 图书列表的presenter
 */
public class BookListPresenter {
    public static final String TAG = BookListPresenter.class.getSimpleName();
    private IBookView mBookView;
    private IBookModel mBookModel;
    Subscriber<BookEntity> subscriber;
    List<MovieEntity> movieEntities;
    List<List<SubjectBody>> lists;
    List<SubjectBody> movieList,tvList;

    public BookListPresenter(IBookView view){
        mBookView = view;
        mBookModel = new BookListModelImpl();
    }

    /*
    * 获取所有列表
    * */
    public void getAllList(String baseUrl,String tvTag,String musicTag,String bookTag,String q,int count,int start){
        lists = new ArrayList<List<SubjectBody>>();
        Observable movieObservable = mBookModel.getMovieList(baseUrl,count,start);
        Observable tvObservable = mBookModel.getTvList(baseUrl,tvTag,count,start);
        Observable musicObservable = mBookModel.getMusicList(baseUrl,musicTag,count,start);
        Observable bookObservable = mBookModel.getBookList(baseUrl,bookTag,q,count,start+5);

        Observable.merge(movieObservable,tvObservable,musicObservable,bookObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG,o.toString());
                        if(o instanceof MovieEntity){
                            Log.d(TAG,((MovieEntity) o).getSubjects().size()+";"+((MovieEntity) o).getSubjects().toString());
                            //电影与电视剧返回相同的数据格式，添加到list时，两个请求会各添加两次，用contains判断不管用
                            movieList = ((MovieEntity) o).getSubjects();
//                            if(!lists.contains(movieList))
                            if(movieList != null && movieList.size() != 0)
                                lists.add(movieList);
                            tvList = ((MovieEntity) o).getSubjects();
//                            if(!lists.contains(tvList))
                            if(tvList != null && tvList.size() != 0)
                                lists.add(tvList);
                            Log.d(TAG,lists.size()+"list.size()");
                            if(lists != null && lists.size() != 0){
                                mBookView.showMovieList(lists.get(0));
                                mBookView.showTvList(lists.get(lists.size()-1));
                            }
                        }
                        else if(o instanceof MusicEntity){
                            List<Music> musicList = ((MusicEntity) o).getMusics();
                            if(musicList != null && musicList.size() != 0){
                                mBookView.showMusicList(musicList);
                            }
                        }
                        else if(o instanceof BookEntity){
                            List<Book> books = ((BookEntity) o).getBooks();
                            if(books != null && books.size() != 0){
                                mBookView.showBookListNew(books);
                            }
                        }
                    }
                });
    }

    /*
    * 获取图书列表
    * */
    public void getBookList(String baseUrl,String tag,String q,int start,int count){
        subscriber = new Subscriber<BookEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BookEntity bookEntity) {
                mBookView.showBookList(bookEntity.getBooks());
            }
        };
        mBookModel.getBooks(subscriber,baseUrl,tag,q,count,start);
    }
}
