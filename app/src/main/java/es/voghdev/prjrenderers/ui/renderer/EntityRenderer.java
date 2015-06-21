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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;

public abstract class EntityRenderer extends Renderer<Entity> {
    protected OnRowClicked listener = new EmptyListener();

    @Override
    protected void setUpView(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    protected void hookListeners(View view) { }

    @Override
    protected abstract View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Override
    public abstract void render();

    public void setListener(OnRowClicked listener) {
        if(listener != null)
            this.listener = listener;
    }

    public interface OnRowClicked{
        public void onRowClicked(final Entity entity);
        public void onThumbnailClicked(final Entity entity);
    }

    protected class EmptyListener implements OnRowClicked{
        public void onRowClicked(Entity entity) { /* Empty */ }
        public void onThumbnailClicked(Entity entity) { /* Empty */ }
    }
}
