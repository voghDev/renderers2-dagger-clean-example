package es.voghdev.prjrenderers.global.interactor.impl;
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

import es.voghdev.prjrenderers.datasource.GetCustomers;
import es.voghdev.prjrenderers.global.interactor.Executor;
import es.voghdev.prjrenderers.global.interactor.Interactor;
import es.voghdev.prjrenderers.global.interactor.MainThread;
import es.voghdev.prjrenderers.global.model.Customer;

public class GetCustomersInteractorImpl implements GetCustomers, Interactor {

    private final MainThread mainThread;
    private final Executor executor;
    private final GetCustomers dataSource;
    private GetCustomers.Listener listener = new EmptyListener();

    public GetCustomersInteractorImpl(GetCustomers dataSource,
                                   Executor executor,
                                   MainThread mainThread) {
        this.dataSource = dataSource;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void getCustomersAsync(Listener listener) {
        if(listener != null)
            this.listener = listener;

        this.executor.run(this); // Runs on the corresponding thread
    }

    @Override
    public void run() {
        dataSource.getCustomersAsync(new Listener() {
            @Override
            public void onCustomersAvailable(List<Customer> customers) {
                listener.onCustomersAvailable(customers);
            }

            @Override
            public void onError(Exception e) {
                listener.onError(e);
            }
        });
    }


    private class EmptyListener implements Listener{
        public void onCustomersAvailable(List<Customer> customers) {  }
        public void onError(Exception e) {  }
    }
}
