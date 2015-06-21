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
import android.view.View;

import com.squareup.picasso.Picasso;

import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.global.model.Video;

public class StandardVideoRenderer extends VideoRenderer{

    public StandardVideoRenderer(Context ctx, EntityRenderer.OnRowClicked listener) {
        super(ctx, listener);
    }

    @Override
    protected void renderThumbnail(Video video) {
        Picasso.with(mContext)
                .load(video.getImageUrl())
                .placeholder(R.drawable.ic_launcher)
                .resizeDimen(R.dimen.row_video_image_w, R.dimen.row_video_image_h)
                .into(thumbnail);
    }

    @Override
    protected void renderFavorite(Video video) {

    }

    @Override
    protected void renderDescription(Video video) {
        this.description.setText(video.getDescription());
    }

    @Override
    protected void renderLabel(Video video) {
        this.label.setVisibility(View.GONE);
    }
}
