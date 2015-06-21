package es.voghdev.prjrenderers.ui.renderer.builder;
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

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.voghdev.prjrenderers.global.model.Video;
import es.voghdev.prjrenderers.ui.renderer.CustomerRenderer;
import es.voghdev.prjrenderers.ui.renderer.EntityRenderer;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;
import es.voghdev.prjrenderers.ui.renderer.FavoriteVideoRenderer;
import es.voghdev.prjrenderers.ui.renderer.StandardVideoRenderer;
import es.voghdev.prjrenderers.ui.renderer.model.VideoEntity;

public class EntityRendererBuilder extends RendererBuilder<Entity> {
    @Override
    protected Class getPrototypeClass(Entity entity) {
        if(entity instanceof VideoEntity) {
            Video v = ((VideoEntity)entity).getVideo();
            if(v.isFavorite())
                return FavoriteVideoRenderer.class;

            return StandardVideoRenderer.class;
        }

        return CustomerRenderer.class;
    }

    public EntityRendererBuilder(Context context, EntityRenderer.OnRowClicked onRowClickedListener) {
        Collection<Renderer<Entity>> prototypes = getPrototypes(context, onRowClickedListener);
        setPrototypes(prototypes);
    }

    private List<Renderer<Entity>> getPrototypes(Context context, EntityRenderer.OnRowClicked onRowClickedListener) {
        List<Renderer<Entity>> prototypes = new LinkedList<Renderer<Entity>>();

        StandardVideoRenderer stdVideoRenderer = new StandardVideoRenderer(context, onRowClickedListener);
        FavoriteVideoRenderer favVideoRenderer = new FavoriteVideoRenderer(context, onRowClickedListener);
        CustomerRenderer customerRenderer = new CustomerRenderer(context, onRowClickedListener);
        prototypes.add(stdVideoRenderer);
        prototypes.add(favVideoRenderer);
        prototypes.add(customerRenderer);

        return prototypes;
    }
}
