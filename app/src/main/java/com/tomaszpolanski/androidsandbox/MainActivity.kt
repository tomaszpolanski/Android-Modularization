package com.tomaszpolanski.androidsandbox

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.Menu
import android.view.MenuItem

import com.tomaszpolanski.androidsandbox.viewmodels.MainViewModel


public class MainActivity : ActionBarActivity() {

    private var mVm: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mVm = MainViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        mVm?.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
