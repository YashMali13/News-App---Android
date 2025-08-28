package com.example.newsapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchBar: EditText

    private val newsList = mutableListOf<News>()
    private val filteredList = mutableListOf<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchBar = findViewById(R.id.searchBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Your original news list
        newsList.addAll(
            listOf(
                News(
                    title = "New AI model revolutionizes the industry",
                    description = "A groundbreaking AI model is set to change industries worldwide.",
                    author = "John Doe",
                    date = "2025-08-12",
                    imageUrl = "https://images.unsplash.com/photo-1677442136019-21780ecad995?w=800"
                ),
                News(
                    title = "Team wins championship after intense match",
                    description = "An incredible finale saw the underdogs take home the trophy.",
                    author = "Jane Smith",
                    date = "2025-08-11",
                    imageUrl = "https://images.unsplash.com/photo-1505842465776-3b4953ca4f44?w=800"
                ),
                News(
                    title = "Leaders gather for climate summit",
                    description = "World leaders meet to discuss urgent climate action.",
                    author = "Michael Lee",
                    date = "2025-08-10",
                    imageUrl = "https://images.unsplash.com/photo-1509395176047-4a66953fd231?w=800"
                ),
                News(
                    title = "Breakthrough in renewable energy storage",
                    description = "Scientists develop a new battery technology to store solar energy more efficiently.",
                    author = "Sarah Johnson",
                    date = "2025-08-09",
                    imageUrl = "https://images.unsplash.com/photo-1581092795360-fd1ca04f09f3?w=800"
                ),
                News(
                    title = "Tech giant unveils foldable tablet",
                    description = "A major tech company has launched its first foldable tablet with cutting-edge display.",
                    author = "David Brown",
                    date = "2025-08-08",
                    imageUrl = "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800"
                ),
                News(
                    title = "Global markets rally after economic recovery signs",
                    description = "Stock markets across the globe show strong gains after positive economic data.",
                    author = "Emily Davis",
                    date = "2025-08-07",
                    imageUrl = "https://images.unsplash.com/photo-1520607162513-77705c0f0d4a?w=800"
                ),
                News(
                    title = "Major art exhibition opens in Paris",
                    description = "A new art exhibition showcasing works from contemporary artists has opened in Paris.",
                    author = "James Wilson",
                    date = "2025-08-06",
                    imageUrl = "https://images.unsplash.com/photo-1505666287802-931dc83948e2?w=800"
                ),
                News(
                    title = "Historic peace agreement signed",
                    description = "Two neighboring countries have signed a peace agreement ending decades of conflict.",
                    author = "Olivia Martinez",
                    date = "2025-08-05",
                    imageUrl = "https://images.unsplash.com/photo-1532012197267-da84d127e765?w=800"
                )
            )
        )

        // Initially show all
        filteredList.addAll(newsList)

        newsAdapter = NewsAdapter(filteredList)
        recyclerView.adapter = newsAdapter

        // Search logic
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterNews(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterNews(query: String) {
        filteredList.clear()

        if (query.isEmpty()) {
            filteredList.addAll(newsList)
        } else {
            val lowerQuery = query.lowercase()
            filteredList.addAll(
                newsList.filter {
                    it.title.lowercase().contains(lowerQuery) ||
                            it.description.lowercase().contains(lowerQuery) ||
                            it.author.lowercase().contains(lowerQuery) ||
                            it.date.lowercase().contains(lowerQuery)
                }
            )
        }

        newsAdapter.notifyDataSetChanged()
    }
}
