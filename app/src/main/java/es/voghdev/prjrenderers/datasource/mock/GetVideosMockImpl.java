package es.voghdev.prjrenderers.datasource.mock;
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

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.datasource.GetVideos;
import es.voghdev.prjrenderers.global.model.Video;

public class GetVideosMockImpl implements GetVideos{
    private static final String SAMPLE_URL = "http://lorempixel.com/500/500/animals/";

    private final Context mContext;

    public GetVideosMockImpl(Context ctx) {
        this.mContext = ctx;
    }

    @Override
    public void getVideosAsync(final Listener listener) {
        List<Video> result = getMockFavorites();
        if(null != result)
            listener.onVideoListReceived(result);
        else
            listener.onVideoListError(new Exception("No videos available"));
    }

    private List<Video> getMockFavorites(){
        List<Video> videos = new ArrayList<Video>();

        String[] titles = mContext.getResources().getStringArray(R.array.videoTitles);
        String[] descriptions = mContext.getResources().getStringArray(R.array.videoDescriptions);
        String[] urls = mContext.getResources().getStringArray(R.array.videoUrls);

        for(int i=0; i<titles.length; ++i) {
            videos.add(
                    new Video.Builder()
                            .setTitle(titles[i])
                            .setImageUrl(SAMPLE_URL+(i+1))
                            .setUrl(urls[i])
                            .setDescription(descriptions[i])
                            .build()
            );
        }
        return videos;
    }

}
