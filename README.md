# SocketAndroidClientTODO

This is TODO for Android client about TCP/IP server-client architecture

Server side had written by Oguz Karan during course. Server operation is capitalize the given string for client trainning purposes.  

# TODO List in TR
- MainActivity'de bir connect işlemini yapan button (veya istediğiniz bir görsel) olacaktır.
- Button'a click yapıldığında şu an bulunan MainActivity'deki tasarım ayrı bir activity'de açılacaktır.
- Bağlantılı olarak çalışan server'la ilgili işlemler yapılacaktır.
- EditText'e quit yazıldığında doğrudan işlemin bitmemesi için quit yazısı [quit] biçiminde
gönderilecek ve dönen cevap []'ler olmadan yazılacaktır
- İşlemin bitirilmesi için ayrı button olacaktır. Bu button ile birlikte quit mesajı gönderilecektir
- İşlem yapılırken karşı bağlantı kopmuşsa MainActivity'ye uygun mesaj verilerek geri dönülecektir
- UI tasarımını dilediğiniz gibi değiştirebilirsiniz
- Örnekte thread module'ünü değiştirmenize gerek yoktur

# TODO List in English
- There will be a button (or an image you want) that performs a connect operation in MainActivity.
- When the button is clicked, the current MainActivity design will be opened in a separate activity.
- Operations related to the connected server will be done.
- Quit text is formatted as [quit] so that the process does not end directly when quit is typed into the EditText.
will be sent and the returned response will be written without []s
- There will be a separate button to finish the process. A quit message will be sent with this button.
- If the counter connection is broken during the transaction, the MainActivity will be returned with an appropriate message.
- You can change the UI design as you wish
- You don't need to change the thread module in the example


# Technologies
- Java 
- Spring Boot
- Kotlin
- Maven
- Gradle
