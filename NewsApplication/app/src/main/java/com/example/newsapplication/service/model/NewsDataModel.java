package com.example.newsapplication.service.model;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "NewsData", id="_id")
public class NewsDataModel extends Model {
    private static final String TABLE_NAME = "NewsData";
    private static final String COL_NEWS_ID ="NewsId";
    private static final String COL_NEWS_SOURCE_ID = "NewsSourceId";
    private static final String COL_NEWS_SOURCE_NAME = "NewsSourceName";
    private static final String COL_NEWS_AUTHOR = "NewsAuthor";
    private static final String COL_NEWS_TITLE = "NewsTitle";
    private static final String COL_NEWS_DESCRIPTION = "NewsDescription";
    private static final String COL_NEWS_URL = "NewsURL";
    private static final String COL_NEWS_IMG_URL = "NewsImageURL";
    private static final String COL_NEWS_PUBLISHED_AT= "NewsPublishedAt";
    private static final String COL_NEWS_CONTENT = "NewsContent";
    private static final String COL_NEWS_CATEGORY = "NewsCategory";

    @Column(name = COL_NEWS_ID, unique = true, notNull = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long newsId;
    @Column(name = COL_NEWS_SOURCE_ID)
    public String newsSourceId;

    @Column(name = COL_NEWS_SOURCE_NAME)
    public String newsSourceName;

    @Column(name = COL_NEWS_AUTHOR)
    public String newsAuthor;

    @Column(name = COL_NEWS_TITLE)
    public String newsTitle;

    @Column(name = COL_NEWS_DESCRIPTION)
    public String newsDescription;

    @Column(name = COL_NEWS_URL)
    public String newsURL;

    @Column(name = COL_NEWS_IMG_URL)
    public String newsImageURL;

    @Column(name = COL_NEWS_PUBLISHED_AT)
    public String newsPublishedAt;

    @Column(name = COL_NEWS_CONTENT)
    public String newsContent;


    public NewsDataModel(){
        super();
    }

    public static boolean saveDataInDB(NewsData newsData) {
        try {
            if (newsData != null) {
                NewsDataModel newsDataModel = new NewsDataModel();
                newsDataModel.newsSourceId = newsData.getNewsSourceData().getId();
                newsDataModel.newsSourceName = newsData.getNewsSourceData().getName();
                newsDataModel.newsAuthor = newsData.getNewsAuthor();
                newsDataModel.newsTitle = newsData.getNewsTitle();
                newsDataModel.newsDescription = newsData.getNewsDesc();
                newsDataModel.newsURL = newsData.getNewsURL();
                newsDataModel.newsImageURL = newsData.getNewsImgURL();
                newsDataModel.newsPublishedAt = newsData.getNewsPublishedAt();
                newsDataModel.newsContent = newsData.getNewsContent();
                newsDataModel.saveToDB();
                getAll();
                return true;
            } else {
                return false;
            }
        }catch(Exception ex){
            return false;
        }
    }

    // increment newsId by 1 from last saved newsId
    private static long getNewsId() {
        long _newsId = 1;
        NewsDataModel newsData = new Select().from(NewsDataModel.class)
                .orderBy("newsId DESC")
                .limit(1)
                .executeSingle();

        if (newsData != null) {
            _newsId = newsData.newsId + 1;
        }
        return _newsId;
    }

    public synchronized void saveToDB() {
        if (this.newsId <= 0) {
            this.newsId = getNewsId();
        }
        this.save();
    }

    public static List<NewsDataModel> getAll() {
        return new Select()
                .from(NewsDataModel.class)
                .orderBy(NewsDataModel.COL_NEWS_ID)
                .execute();
    }

    public static void deleteAll(){
         new Delete().from(NewsDataModel.class).execute();
    }

    public static List<NewsDataModel> getNewsByCategory(String newsCategory) {
        return new Select()
                .from(NewsDataModel.class)
                .where(COL_NEWS_CATEGORY + "=?", newsCategory)
                .orderBy(NewsDataModel.COL_NEWS_ID)
                .execute();
    }

    public static boolean isNewsExists(NewsData newsData) {
        return new Select()
                .from(NewsDataModel.class)
                .where(COL_NEWS_URL + "=?", newsData.getNewsURL())
                .exists();
    }
}
