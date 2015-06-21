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


import java.util.List;

import javax.inject.Inject;

import es.voghdev.prjrenderers.global.model.Customer;
import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.ui.renderer.EntityRenderer;

public abstract class VideoListPresenter extends Presenter<VideoListPresenter.View> implements EntityRenderer.OnRowClicked {
    @Inject
    List<Video> videos;

    public abstract void onItemSelected(int position);

    public interface View extends AbstractView{
        public void showLoading();
        public void hideLoading();

        public void showVideoList(List<Video> videos);
        public void showError(Exception e);

        public void showVideoClickedMessage(Video video);
        public void showCustomerClickedMessage(Customer customer);
        public void highlightVideo(Video video);
    }
}
