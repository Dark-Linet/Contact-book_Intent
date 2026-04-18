package com.example.contact_book_intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class ContactBookActivity : AppCompatActivity() {

    companion object {
        private const val PHONE_NUMBER = "+74951234567"
        private const val EMAIL_ADDRESS = "contact@example.com"
        private const val OFFICE_LAT = 60.0237
        private const val OFFICE_LON = 30.2289
        private const val OFFICE_LABEL = "Наш офис"
        private const val SHARE_TEXT = "Контакт: +7 (495) 123-45-67, contact@example.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Главный контейнер
        val mainLayout = LinearLayout(this)
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.setPadding(80, 80, 80, 80)
        mainLayout.setBackgroundColor(-0x111112)

        // Заголовок
        val titleView = TextView(this)
        titleView.text = "Контактная книга"
        titleView.textSize = 28f
        titleView.setTypeface(titleView.typeface, android.graphics.Typeface.BOLD)
        titleView.setTextColor(-0xCCCCCD)
        titleView.gravity = android.view.Gravity.CENTER
        titleView.setPadding(0, 0, 0, 60)
        mainLayout.addView(titleView)

        // Блок с контактами
        val contactBlock = LinearLayout(this)
        contactBlock.orientation = LinearLayout.VERTICAL
        contactBlock.setPadding(40, 40, 40, 40)
        contactBlock.setBackgroundColor(-0x333334)

        val phoneText = TextView(this)
        phoneText.text = getString(R.string.Phone_number)
        phoneText.textSize = 18f
        phoneText.setTextColor(-0x1)
        phoneText.setPadding(0, 0, 0, 20)
        contactBlock.addView(phoneText)

        val emailText = TextView(this)
        emailText.text = "✉️ contact@example.com"
        emailText.textSize = 18f
        emailText.setTextColor(-0x1)
        contactBlock.addView(emailText)

        val blockParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        blockParams.setMargins(0, 0, 0, 60)
        mainLayout.addView(contactBlock, blockParams)

        // Кнопка 1: Позвонить
        val btnCall = Button(this)
        btnCall.text = "📞 Позвонить"
        btnCall.textSize = 18f
        btnCall.setPadding(0, 40, 0, 40)
        btnCall.setOnClickListener { makeCall() }

        val callParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        callParams.setMargins(0, 0, 0, 20)
        mainLayout.addView(btnCall, callParams)

        // Кнопка 2: Email
        val btnEmail = Button(this)
        btnEmail.text = "✉️ Написать email"
        btnEmail.textSize = 18f
        btnEmail.setPadding(0, 40, 0, 40)
        btnEmail.setOnClickListener { sendEmail() }

        val emailParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        emailParams.setMargins(0, 0, 0, 20)
        mainLayout.addView(btnEmail, emailParams)

        // Кнопка 3: Карта
        val btnMap = Button(this)
        btnMap.text = "🗺️ Показать офис на карте"
        btnMap.textSize = 18f
        btnMap.setPadding(0, 40, 0, 40)
        btnMap.setOnClickListener { showOnMap() }

        val mapParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mapParams.setMargins(0, 0, 0, 20)
        mainLayout.addView(btnMap, mapParams)

        // Кнопка 4: Поделиться
        val btnShare = Button(this)
        btnShare.text = "📤 Поделиться контактом"
        btnShare.textSize = 18f
        btnShare.setPadding(0, 40, 0, 40)
        btnShare.setOnClickListener { shareContact() }

        mainLayout.addView(btnShare)

        setContentView(mainLayout)
    }

    private fun makeCall() {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$PHONE_NUMBER".toUri()
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "❌ Нет приложения для звонков", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmail() {
        try {
            // Тема кодируется прямо в URL
            val subjectEncoded = java.net.URLEncoder.encode("Обращение", "UTF-8")
            val mailtoUri = "mailto:$EMAIL_ADDRESS?subject=$subjectEncoded"

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = mailtoUri.toUri()
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "❌ Нет почтового приложения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showOnMap() {
        try {
            val geoUri =
                "geo:$OFFICE_LAT,$OFFICE_LON?q=$OFFICE_LAT,$OFFICE_LON($OFFICE_LABEL)".toUri()
            val intent = Intent(Intent.ACTION_VIEW, geoUri)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "❌ Нет приложения для карт", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareContact() {
        try {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, SHARE_TEXT)
            }
            val chooser = Intent.createChooser(sendIntent, "Поделиться через...")
            startActivity(chooser)
        } catch (e: Exception) {
            Toast.makeText(this, "❌ Нет приложений для отправки", Toast.LENGTH_SHORT).show()
        }
    }
}



/*
package com.example.contact_book_intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ContactBookActivity : AppCompatActivity() {

    // Контактные данные
    companion object {
        private const val PHONE_NUMBER = "+74951234567"
        private const val EMAIL_ADDRESS = "contact@example.com"
        private const val EMAIL_SUBJECT = "Обращение"
        private const val OFFICE_LAT = 60.0237
        private const val OFFICE_LON = 30.2289
        private const val OFFICE_LABEL = "Наш офис"
        private const val SHARE_TEXT = "Контакт: +7 (495) 123-45-67, contact@example.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ========== СОЗДАЁМ ИНТЕРФЕЙС ПРОГРАММНО ==========

        // Главный контейнер (корневой Layout)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 60, 60, 60)
            setBackgroundColor(-0xF0F0F1) // светло-серый фон
        }

        // ===== Заголовок =====
        val titleView = TextView(this).apply {
            text = "Контактная книга"
            textSize = 28f
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            setTextColor(-0xCCCCCD) // тёмно-серый
            gravity = android.view.Gravity.CENTER
            setPadding(0, 0, 0, 80)
        }
        mainLayout.addView(titleView)

        // ===== Карточка с контактной информацией =====
        val cardView = CardView(this).apply {
            radius = 20f
            cardElevation = 8f
            setContentPadding(40, 40, 40, 40)
            setPadding(0, 0, 0, 60)
        }

        val contactLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val phoneText = TextView(this).apply {
            text = "📞 +7 (495) 123-45-67"
            textSize = 18f
            setPadding(0, 0, 0, 20)
        }
        contactLayout.addView(phoneText)

        val emailText = TextView(this).apply {
            text = "✉️ contact@example.com"
            textSize = 18f
        }
        contactLayout.addView(emailText)

        cardView.addView(contactLayout)
        mainLayout.addView(cardView)

        // ===== Кнопка 1: Позвонить =====
        val btnCall = Button(this).apply {
            text = "📞 Позвонить"
            textSize = 18f
            setPadding(0, 40, 0, 40)
            setBackgroundColor(-0x4CAF51) // зелёный
            setTextColor(-0x1) // белый
            setOnClickListener { makeCall() }
        }
        mainLayout.addView(btnCall, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(0, 20, 0, 20) })

        // ===== Кнопка 2: Написать email =====
        val btnEmail = Button(this).apply {
            text = "✉️ Написать email"
            textSize = 18f
            setPadding(0, 40, 0, 40)
            setBackgroundColor(-0x2196F4) // синий
            setTextColor(-0x1)
            setOnClickListener { sendEmail() }
        }
        mainLayout.addView(btnEmail, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(0, 0, 0, 20) })

        // ===== Кнопка 3: Показать на карте =====
        val btnMap = Button(this).apply {
            text = "🗺️ Показать офис на карте"
            textSize = 18f
            setPadding(0, 40, 0, 40)
            setBackgroundColor(-0xFF9801) // оранжевый
            setTextColor(-0x1)
            setOnClickListener { showOnMap() }
        }
        mainLayout.addView(btnMap, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(0, 0, 0, 20) })

        // ===== Кнопка 4: Поделиться =====
        val btnShare = Button(this).apply {
            text = "📤 Поделиться контактом"
            textSize = 18f
            setPadding(0, 40, 0, 40)
            setBackgroundColor(-0x9C27B1) // фиолетовый
            setTextColor(-0x1)
            setOnClickListener { shareContact() }
        }
        mainLayout.addView(btnShare)

        // Устанавливаем созданный интерфейс
        setContentView(mainLayout)
    }

    /**
     * Задание 1: Позвонить по номеру
     * ACTION_DIAL - открывает набор номера (не требует разрешений)
     */
    private fun makeCall() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$PHONE_NUMBER")
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "❌ Нет приложения для звонков", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Задание 2: Отправить email
     * ACTION_SENDTO с mailto: - открывают только почтовые приложения
     */
    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL_ADDRESS))
            putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "❌ Нет почтового приложения", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Задание 3: Показать офис на карте
     * geo: URI с меткой
     */
    private fun showOnMap() {
        val geoUri = Uri.parse("geo:0,0?q=$OFFICE_LAT,$OFFICE_LON($OFFICE_LABEL)")
        val intent = Intent(Intent.ACTION_VIEW, geoUri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "❌ Нет приложения для карт", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Задание 4: Поделиться контактом
     * Используем createChooser (как требуется в задании)
     */
    private fun shareContact() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, SHARE_TEXT)
        }

        val chooser = Intent.createChooser(sendIntent, "Поделиться через...")

        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        } else {
            Toast.makeText(this, "❌ Нет приложений для отправки", Toast.LENGTH_SHORT).show()
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Contactbook_IntentTheme {
        Greeting("Android")
    }
}
*/