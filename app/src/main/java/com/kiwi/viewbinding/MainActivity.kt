package com.kiwi.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kiwi.viewbinding.databinding.ActivityMainBinding
import com.kiwi.viewbinding.databinding.IncludeInMergeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityBind()
        initFragmentBind()
        initIncludeNotMerge()
        initIncludeInMerge()
    }


    private fun initActivityBind() {
        mainBind = ActivityMainBinding.bind(
            LayoutInflater.from(this).inflate(
                R.layout.activity_main,
                null,
                false
            )
        )
        setContentView(mainBind.root)
        mainBind.text.text = "Activity bind"
    }

    private fun initFragmentBind() {
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }

    // 带 include 标签，但 include 里面不包含 merge
    private fun initIncludeNotMerge() {
        mainBind.includeNotMerge.notMerge.append("append")
    }

    // 带 include 标签，但 include 里面包含 merge
    private fun initIncludeInMerge() {
        val bind = IncludeInMergeBinding.bind(mainBind.root)
        bind.inMerge.text = "initIncludeInMerge"
    }

}
