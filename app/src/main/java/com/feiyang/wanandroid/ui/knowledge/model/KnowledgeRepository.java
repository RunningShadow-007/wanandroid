package com.feiyang.wanandroid.ui.knowledge.model;

import com.feiyang.wanandroid.core.net.ApiService;
import com.feiyang.wanandroid.core.net.NetworkObserver;
import com.feiyang.wanandroid.core.net.ServiceProvider;
import com.feiyang.wanandroid.core.util.Optional;
import com.feiyang.wanandroid.ui.main.model.bean.ArticlesData;

import io.reactivex.Observable;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/13 5:39 PM<br>
 * Desc: <br>
 */
public class KnowledgeRepository {
    private static volatile KnowledgeRepository sINSTANCE;

    private ApiService mRemote;

    private KnowledgeRepository() {
        mRemote = ServiceProvider.getInstance().provide(ApiService.class);
    }

    public static KnowledgeRepository getInstance() {
        if (sINSTANCE == null) {
            synchronized (KnowledgeRepository.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new KnowledgeRepository();
                }
            }
        }
        return sINSTANCE;
    }

    /**
     * 知识体系下的文章
     *
     * @param pageNo
     * @param cid    分类的id，上述二级目录的id
     * @return
     */
    public Observable<Optional<ArticlesData>> getKnowledgeArticleList(int pageNo, int cid) {
        return NetworkObserver.on(mRemote.getKnowledgeArticleList(pageNo, cid));
    }

}
