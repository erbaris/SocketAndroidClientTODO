package org.csystem.android.app.upperserver.client.viewmodel

import org.csystem.android.app.upperserver.client.MainActivity
import java.io.Serializable


class MainActivityViewModel(val activity: MainActivity){


    fun handleExitButton() = activity.onExitButtonClicked()
    fun handleConnectButton() = activity.onConnectButtonClicked()
}