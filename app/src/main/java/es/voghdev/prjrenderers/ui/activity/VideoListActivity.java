package es.voghdev.prjrenderers.ui.activity;
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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.global.model.Customer;
import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.global.module.VideoListModule;
import es.voghdev.prjrenderers.ui.presenter.VideoListPresenter;
import es.voghdev.prjrenderers.ui.renderer.model.CustomerEntity;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;
import es.voghdev.prjrenderers.ui.renderer.model.VideoEntity;

public class VideoListActivity extends BaseActivity implements VideoListPresenter.View {
    @Inject
    VideoListPresenter presenter;

    @Inject
    RVRendererAdapter<Entity> recyclerAdapter;

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    @InjectView(R.id.video_progressBar)
    ProgressBar progressBar;

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new LinkedList<Object>();

        modules.add(new VideoListModule(this));
        return modules;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.inject(this);

        presenter.setView(this);
        presenter.initialize();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showVideoList(List<Video> videos) {
        recyclerAdapter.clear();

        for(Video v : videos) {
            recyclerAdapter.add(new VideoEntity(v));
            for(Customer c : v.getCustomersWhoLiked())
                recyclerAdapter.add(new CustomerEntity(c));
        }

        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showVideoClickedMessage(Video video) {
        Toast.makeText( this, video.getDescription(), Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void showCustomerClickedMessage(Customer customer) {
        Toast.makeText( this, getString(R.string.customer_greeting, customer.getName()), Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void highlightVideo(Video video) {
        Toast.makeText( this, getString(R.string.video_highlighted, video.getTitle()), Toast.LENGTH_SHORT ).show();
    }
}
