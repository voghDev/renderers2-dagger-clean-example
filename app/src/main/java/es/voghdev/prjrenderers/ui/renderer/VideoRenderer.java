package es.voghdev.prjrenderers.ui.renderer;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.ui.renderer.model.VideoEntity;

public abstract class VideoRenderer extends EntityRenderer {
    protected Context mContext;

    public VideoRenderer(Context ctx, OnRowClicked listener){
        mContext = ctx.getApplicationContext();
        setListener(listener);
    }

    @InjectView(R.id.iv_thumbnail)
    ImageView thumbnail;
    @InjectView(R.id.tv_title)
    TextView title;
    @InjectView(R.id.tv_description)
    TextView description;
    @InjectView(R.id.tv_label)
    TextView label;
    @InjectView(R.id.separator)
    View separator;

    @OnClick(R.id.iv_thumbnail)
    protected void onClickThumbnail(ImageView imageView){
        listener.onThumbnailClicked( getContent() );
    }

    @OnClick(R.id.root)
    protected void onClickBackground(){
        listener.onRowClicked( getContent() );
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_video, parent, false);
        ButterKnife.inject(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        Video video = ((VideoEntity)getContent()).getVideo();
        renderThumbnail(video);
        renderTitle(video);
        renderDescription(video);
        renderLabel(video);
        renderFavorite(video);
    }

    protected abstract void renderThumbnail(Video video);

    protected abstract void renderFavorite(Video video);

    private void renderTitle(Video video) {
        this.title.setText(video.getTitle());
    }

    protected abstract void renderDescription(Video video);

    protected abstract void renderLabel(Video video);
}
