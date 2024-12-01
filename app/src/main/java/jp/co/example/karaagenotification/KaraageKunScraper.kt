package jp.co.example.karaagenotification

import org.jsoup.Jsoup

class KaraageKunScraper {
    companion object {
        suspend fun fetchKaraageKunInfo(): String {
            val url = "https://www.lawson.co.jp/lab/karaagekun/"
            val document = Jsoup.connect(url).get()
            val elements = document.select("div.new-item") // Assuming the new items are in divs with class "new-item"
            val infoList = elements.map { element ->
                val title = element.select("h3").text()
                val date = element.select("p.date").text()
                "$title - $date"
            }
            return infoList.joinToString("\n")
        }
    }
}
