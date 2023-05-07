package org.csystem.android.app.upperserver.client.viewmodel

import org.csystem.android.app.upperserver.client.MessageActivity

class MessageActivityViewModel (val activity: MessageActivity){


    fun handleUpperButton() = activity.onUpperButtonClicked()

    fun handleExitButton() = activity.onExitButtonClicked()

}