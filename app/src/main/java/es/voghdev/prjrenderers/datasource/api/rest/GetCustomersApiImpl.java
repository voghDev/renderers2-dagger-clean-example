package es.voghdev.prjrenderers.datasource.api.rest;
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

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import es.voghdev.prjrenderers.datasource.GetCustomers;
import es.voghdev.prjrenderers.datasource.api.CustomerService;
import es.voghdev.prjrenderers.datasource.api.rest.model.CustomerListApiEntry;
import es.voghdev.prjrenderers.global.util.ByteHelper;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetCustomersApiImpl implements GetCustomers, Callback<CustomerListApiEntry> {
    private static final String ENDPOINT = "http://www.thomas-bayer.com/sqlrest/";

    private Listener listener;

    @Override
    public void getCustomersAsync(Listener listener) {
        this.listener = listener;

        try {
            CustomerService service = createCustomerService();
            Response rsp = service.rawQueryCustomers();
            Serializer serializer = new Persister();
            StringBuffer xmlCode = ByteHelper.getStringFromStream(rsp.getBody().in());
            CustomerListApiEntry list = serializer.read(CustomerListApiEntry.class, xmlCode.toString());
            listener.onCustomersAvailable( list.parseCustomers() );

        }catch(IOException e){
            listener.onError(e);
        }catch(Exception e){
            listener.onError(e);
        }
    }

    private CustomerService createCustomerService(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        CustomerService service = restAdapter.create(CustomerService.class);
        return service;
    }

    @Override
    public void success(CustomerListApiEntry customerListApiEntry, Response response) {
        listener.onCustomersAvailable(customerListApiEntry.parseCustomers());
    }

    @Override
    public void failure(RetrofitError error) {
        listener.onError(error);
    }
}
