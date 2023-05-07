package org.csystem.android.app.upperserver.client

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.upperserver.client.databinding.ActivityMainBinding
import org.csystem.android.app.upperserver.client.viewmodel.HostInfo
import org.csystem.android.app.upperserver.client.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    fun onConnectButtonClicked()
    {
        Intent(this, MessageActivity::class.java).apply {
            putExtra("SOCKET_INFO", mBinding.hostInfo)
            startActivity(this)
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.hostInfo = HostInfo()
        mBinding.hostInfo!!.host = "161.97.141.113"


    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }



    fun onExitButtonClicked() = finish()

}