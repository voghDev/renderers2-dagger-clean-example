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

import java.util.List;

import dagger.ObjectGraph;
import es.voghdev.prjrenderers.global.RenderersApplication;

public abstract class BaseActivity extends android.app.Activity {

    private ObjectGraph objectGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RenderersApplication app =  (RenderersApplication)getApplication();
        List<Object> modules = getModules();
        objectGraph = app.plus(modules);
        objectGraph.inject(this);
    }

    protected abstract List<Object> getModules();
}
