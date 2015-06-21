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
import android.widget.TextView;

import butterknife.InjectView;
import es.voghdev.prjrenderers.R;
import es.voghdev.prjrenderers.global.model.Customer;
import es.voghdev.prjrenderers.ui.renderer.model.CustomerEntity;
import es.voghdev.prjrenderers.ui.renderer.model.Entity;

public class CustomerRenderer extends EntityRenderer {
    private Context mContext;

    @InjectView(R.id.tv_name)
    TextView name;

    public CustomerRenderer(Context ctx, OnRowClicked listener) {
        this.mContext = ctx;
        setListener(listener);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_customer, parent, false);
        return inflatedView;
    }

    @Override
    public void render() {
        Customer customer = ((CustomerEntity)getContent()).getCustomer();
        renderName(customer);
    }

    private void renderName(Customer c) {
        name.setText( mContext.getString(R.string.user_highlighted, c.getName()) );
    }
}
