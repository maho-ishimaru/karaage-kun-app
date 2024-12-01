package jp.co.example.karaagenotification

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val infoTextView: TextView = findViewById(R.id.infoTextView)

        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("CoroutineException", "Caught $exception")
        }

        lifecycleScope.launch(handler) {
            try {
                val doc = withContext(Dispatchers.IO) {
                    Jsoup.connect("https://www.lawson.co.jp/lab/karaagekun/").timeout(10000).get()
                }
                val info = doc.select("p.lab_top_title_slider").text()
                Log.d("KaraageKunInfo", info) // ログ出力

                withContext(Dispatchers.Main) {
                    infoTextView.text = info
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}