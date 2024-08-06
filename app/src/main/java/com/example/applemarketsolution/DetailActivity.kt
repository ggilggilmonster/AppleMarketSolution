package com.example.applemarketsolution

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applemarketsolution.databinding.ActivityDetailBinding
import com.example.applemarketsolution.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.System.exit
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var likePressed = false

    private val item: MyItem? by lazy {
            intent.getParcelableExtra<MyItem>(Const.ITEM_OBJECT)
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Const.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("ggilmon", "itemPosition = $itemPosition")

        item?.let {
            binding.ivImage.setImageResource(it.image)
            binding.tvSeller.text = it.seller
            binding.tvAddress.text = it.address
            binding.tvTitle.text = it.title
            binding.tvPostDetail.text = it.postDetail
            binding.tvPrice.text = DecimalFormat("#,###").format(it.price) + "원"
        }

        likePressed = item?.likePressed == true

        binding.ivDetailLike.setImageResource(if (likePressed) R.drawable.like_pressed else R.drawable.black_likes)

        binding.ivBack.setOnClickListener {
            exit()
        }

        binding.llDetailLike.setOnClickListener {
            if (!likePressed) {
                binding.ivDetailLike.setImageResource(R.drawable.like_pressed)
                Snackbar.make(binding.detailViewLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                likePressed = true
            } else {
                binding.ivDetailLike.setImageResource(R.drawable.black_likes)
                likePressed = false
            }
        }

        binding.tvBtn.setOnClickListener {
            val aniFade: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
            binding.ivImage.startAnimation(aniFade)
        }

    }

    fun exit() {
        Log.d("ggilggil", "exit()")
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("likePressed", likePressed)
        }

        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        Log.d("ggilggil", "onBackPressed()")
        exit()
        super.onBackPressed()
    }
}