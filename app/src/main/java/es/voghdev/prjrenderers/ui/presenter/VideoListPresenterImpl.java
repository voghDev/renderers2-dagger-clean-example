package es.voghdev.prjrenderers.ui.presenter;
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

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.datasource.GetCustomers;
import es.voghdev.prjrenderers.datasource.GetVideos;
import es.voghdev.prjrenderers.global.model.Customer;
import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.ui.renderer.model.CustomerEntity;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;
import es.voghdev.prjrenderers.ui.renderer.model.VideoEntity;

public class VideoListPresenterImpl extends VideoListPresenter {

    private final Context mContext;
    private GetVideos videosInteractor;
    private GetCustomers customersInteractor;
    private List<Customer> customers = Collections.emptyList();

    public VideoListPresenterImpl(Context context, GetVideos videosInteractor, GetCustomers customersInteractor) {
        this.mContext = context;
        this.videosInteractor = videosInteractor;
        this.customersInteractor = customersInteractor;
    }

    @Override
    public void onItemSelected(int position) {

    }

    @Override
    public void initialize() {
        view.showLoading();

        customersInteractor.getCustomersAsync(new GetCustomers.Listener() {
            @Override
            public void onCustomersAvailable(final List<Customer> list) {
                customers = list;
            }

            @Override
            public void onError(Exception e) {
                view.showError(e);
            }
        });

        videosInteractor.getVideosAsync(new GetVideos.Listener() {
            @Override
            public void onVideoListReceived(List<Video> list) {
                videos = list;
                view.showVideoList(list);
                view.hideLoading();
            }

            @Override
            public void onVideoListError(Exception e) {
                view.showError(e);
                view.hideLoading();
            }
        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onRowClicked(Entity entity) {
        if(entity instanceof VideoEntity)
            view.showVideoClickedMessage( ((VideoEntity)entity).getVideo() );
        else if(entity instanceof CustomerEntity)
            view.showCustomerClickedMessage( ((CustomerEntity)entity).getCustomer() );
    }

    @Override
    public void onThumbnailClicked(Entity entity) {
        VideoEntity videoEntity = (VideoEntity)entity;
        videoEntity.getVideo().addCustomer( getRandomCustomer() );
        view.highlightVideo( ((VideoEntity)entity).getVideo() );
        view.showVideoList(videos);
    }

    private Customer getRandomCustomer(){
        if(customers.isEmpty()) {
            return generateDummyCustomer();
        }

        int rnd = new Random().nextInt(customers.size()-1);

        return customers.get(rnd);
    }

    private Customer generateDummyCustomer(){
        Customer c = new Customer();
        c.setId(999);
        c.setName( mContext.getString(R.string.dummy_customer, c.getId()) );
        return c;
    }
}
