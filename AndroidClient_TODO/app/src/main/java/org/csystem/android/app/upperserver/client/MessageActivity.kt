package org.csystem.android.app.upperserver.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.upperserver.client.databinding.ActivityMessageBinding
import org.csystem.android.app.upperserver.client.viewmodel.HostInfo
import org.csystem.android.app.upperserver.client.viewmodel.MainActivityViewModel
import org.csystem.android.app.upperserver.client.viewmodel.MessageActivityViewModel
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.nio.charset.StandardCharsets
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class MessageActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMessageBinding

    @Inject
    lateinit var threadPool : ExecutorService

    lateinit var socket : Socket



    private fun doSendReceiveWork(){
        val bw = BufferedWriter(OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
        val br = BufferedReader(InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
        if (mBinding.text.equals("quit")) {
            bw.write("[" + mBinding.text + "]" + "\r\n")
            bw.flush()
        }
        else {
            bw.write(mBinding.text + "\r\n")
            bw.flush()
        }
        var result = br.readLine().trim()

        if(result.equals("[QUIT]"))
            mBinding.result = "QUIT"
        else
            mBinding.result = result

    }


    private fun connect()
    {
        threadPool.execute { connectCallBack() }
    }
    private fun connectCallBack()
    {

        try {
            socket = Socket(mBinding.host, 50516)
        }
        catch (ex: IOException) {
            runOnUiThread { Toast.makeText(this, "Problem occurs while send/receive", Toast.LENGTH_LONG).show()}
            finish()
        }
        catch (ex: Throwable) {
            runOnUiThread { Toast.makeText(this, "Connect General problem occurs. Try again later", Toast.LENGTH_LONG).show()}
            finish()
        }

    }
    private fun upperCallback()
    {
        mBinding.result = ""

        try {
            doSendReceiveWork()

        }
        catch (ex: IOException) {
            runOnUiThread { Toast.makeText(this, "Problem occurs while send/receive", Toast.LENGTH_LONG).show()}
        }
        catch (ex: Throwable) {
            runOnUiThread { Toast.makeText(this, "RX/TX General problem occurs. Try again later", Toast.LENGTH_SHORT).show()}
            runOnUiThread{
                Toast.makeText(this, "Disconnected! Trying to re-connect...", Toast.LENGTH_SHORT)
                    .show()
            }
            connectCallBack()
            doSendReceiveWork()
        }
    }

    fun onUpperButtonClicked() = threadPool.execute {upperCallback()}

    private fun quitWork(){
        val bw = BufferedWriter(OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))

        bw.write("quit" + "\r\n")
        bw.flush()
        runOnUiThread { Toast.makeText(this, "It is disconnected...", Toast.LENGTH_SHORT).show()}

    }
    private fun quitCallback()
    {
        try {
            quitWork()
        }
        catch (ex: IOException) {
            runOnUiThread { Toast.makeText(this, "Problem occurs while send/receive", Toast.LENGTH_LONG).show()}
        }
        catch (ex: Throwable) {
            runOnUiThread { Toast.makeText(this, "RX/TX General problem occurs. Try again later", Toast.LENGTH_SHORT).show()}
        }
    }
    fun onDisconnectButtonClicked() = threadPool.execute {quitCallback()}

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_message)
        mBinding.viewModel = MessageActivityViewModel(this)
        mBinding.text = ""
        mBinding.result = ""
    }

    private fun initIntentData()
    {
        intent.also {
            val hostInfo = if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra("SOCKET_INFO") as HostInfo
            else
                intent.getSerializableExtra("SOCKET_INFO", HostInfo::class.java)!!

            mBinding.host = hostInfo.host


        }

    }

    private fun initialize()
    {
        initBinding()
        initIntentData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        connect()
    }
    fun onExitButtonClicked() = finish()
}