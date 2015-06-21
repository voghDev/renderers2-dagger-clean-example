package es.voghdev.prjrenderers.global.module;
/*
 * Copyright (C) 2015 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.view.LayoutInflater;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.voghdev.prjrenderers.datasource.GetCustomers;
import es.voghdev.prjrenderers.datasource.GetVideos;
import es.voghdev.prjrenderers.datasource.api.rest.GetCustomersApiImpl;
import es.voghdev.prjrenderers.datasource.mock.GetVideosMockImpl;
import es.voghdev.prjrenderers.global.interactor.impl.GetCustomersInteractorImpl;
import es.voghdev.prjrenderers.global.interactor.impl.GetVideosInteractorImpl;
import es.voghdev.prjrenderers.global.interactor.impl.MainThreadImpl;
import es.voghdev.prjrenderers.global.interactor.impl.ThreadExecutor;
import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.ui.activity.VideoListActivity;
import es.voghdev.prjrenderers.ui.presenter.VideoListPresenter;
import es.voghdev.prjrenderers.ui.presenter.VideoListPresenterImpl;
import es.voghdev.prjrenderers.ui.renderer.builder.EntityRendererBuilder;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;

@Module(injects= {VideoListActivity.class, VideoListPresenter.class}, library = true, complete = false)
public class VideoListModule {
    private Context mContext;

    RendererAdapter<Entity> adapter;

    RVRendererAdapter<Entity> recyclerAdapter;

    VideoListPresenter presenter;

    List<Video> videos;

    public VideoListModule(Context ctx){
        mContext = ctx.getApplicationContext().getApplicationContext();
        presenter = new VideoListPresenterImpl(mContext, getVideosDataSource(ctx), getCustomersDataSource(ctx) );
        videos = new ArrayList<Video>();
        adapter = new RendererAdapter<Entity>(
                LayoutInflater.from(mContext),
                new EntityRendererBuilder(mContext, presenter),
                new ListAdapteeCollection<Entity>(new ArrayList<Entity>()));
        recyclerAdapter = new RVRendererAdapter<Entity>(
                LayoutInflater.from(mContext),
                new EntityRendererBuilder(mContext, presenter),
                new ListAdapteeCollection<Entity>(new ArrayList<Entity>()));
    }

    private GetVideos getVideosDataSource(Context ctx) {
        return new GetVideosInteractorImpl(
                new GetVideosMockImpl(ctx),
                new ThreadExecutor(),
                new MainThreadImpl()
        );
    }

    private GetCustomers getCustomersDataSource(Context ctx) {
        return new GetCustomersInteractorImpl(new GetCustomersApiImpl(),
                new ThreadExecutor(),
                new MainThreadImpl()
        );
    }

    @Provides
    RendererAdapter<Entity> provideAdapter(){
        return adapter;
    }

    @Provides
    RVRendererAdapter<Entity> provideRecyclerAdapter() {
        return recyclerAdapter;
    }

    @Provides
    VideoListPresenter providePresenter() { return presenter; }

    @Provides
    List<Video> provideVideoList() { return videos; }

}
