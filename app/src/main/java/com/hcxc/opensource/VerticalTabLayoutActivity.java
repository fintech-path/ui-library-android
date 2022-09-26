/*
 * Copyright 2007-2022 Home Credit Xinchi Consulting Co. Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2. *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hcxc.opensource;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hcxc.verticaltablayout.VerticalTabLayout;

public class VerticalTabLayoutActivity extends AppCompatActivity implements VerticalTabLayout.OnTabSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_tab_layout);
        VerticalTabLayout vTabLayout = (VerticalTabLayout) findViewById(R.id.tab_layout_1);
        for (int i = 0; i < 20; i++) {
            vTabLayout.addTab(vTabLayout.newTab().setText("TEST " + i).setIcon(R.drawable.ic_selector));
        }
        vTabLayout.setOnTabSelectedListener(this);
        vTabLayout = (VerticalTabLayout) findViewById(R.id.tab_layout_2);
        for (int i = 0; i < 20; i++) {
            vTabLayout.addTab(vTabLayout.newTab().setText("TEST " + i).setIcon(R.drawable.ic_selector));
        }
        vTabLayout.setOnTabSelectedListener(this);
        vTabLayout = (VerticalTabLayout) findViewById(R.id.tab_layout_3);
        for (int i = 0; i < 20; i++) {
            vTabLayout.addTab(vTabLayout.newTab().setText("TEST " + i).setIcon(R.drawable.ic_selector).setIconGravity(Gravity.RIGHT));
        }
        vTabLayout.setOnTabSelectedListener(this);
        vTabLayout = (VerticalTabLayout) findViewById(R.id.tab_layout_4);
        for (int i = 0; i < 20; i++) {
            vTabLayout.addTab(vTabLayout.newTab().setText("TEST " + i).setIcon(R.drawable.ic_selector).setIconGravity(Gravity.RIGHT));
        }
        vTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(VerticalTabLayout.Tab tab, int position) {
        Toast.makeText(getApplicationContext(), "onTabSelected: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReleased(VerticalTabLayout.Tab tab, int position) {

    }
}