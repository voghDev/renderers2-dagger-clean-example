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

public abstract class Presenter<T extends AbstractView> {

    protected T view;

    public void setView(T view) { this.view = view; }

    public abstract void initialize();

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();
}
