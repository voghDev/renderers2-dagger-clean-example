package es.voghdev.prjrenderers.datasource.api.rest.model;
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

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

import es.voghdev.prjrenderers.global.model.Customer;

/**
 * Boundary between DataSource and Model.
 * Uses SimpleXml to parse an XML response, and converts the result to domain objects
 */
@Root(name = "CUSTOMERList")
public class CustomerListApiEntry{

    @ElementList(inline = true, entry = "CUSTOMER")
    List<Integer> theList;

    public List<Integer> getCustomers() {
        return theList;
    }

    public void setCustomers(List<Integer> theCustomers) {
        this.theList = theCustomers;
    }

    public List<Customer> parseCustomers(){
        List<Customer> ret = new ArrayList<Customer>();
        for(Integer i : theList){
            Customer c = new Customer();
            c.setId(i);
            c.setName("Customer #"+i);
            ret.add( c );
        }
        return ret;
    }
}