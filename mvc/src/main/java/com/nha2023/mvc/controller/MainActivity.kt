package com.nha2023.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nha2023.mvc.R
import com.nha2023.mvc.databinding.ActivityMainBinding
import com.nha2023.mvc.model.Item
import com.nha2023.mvc.model.ItemModel

class MainActivity : AppCompatActivity() {

    //1. MVC (모델 뷰 컨트롤러) - 각 파일의 역할을 구분하여 작성하는 것이 특징
        //1) Model :  데이터를 저장하는 클래스나 데이터를 DB/네트워크/파일 등에서 불러오거나 저장하는 등의 작업을 하는 코드를 작성하는 파일들 (ex. Item클래스 , Person 클래스, Retrofit작업 클래스, DB 작업 클래스..)
        //2) View : 사용자가 볼 화면을 구현하는 목적의 코드가 있는 파일들 (activity_main.xml, MainActivity.kt, Fragment_my.xml, MyFragment.kt)
        //3) Controller : 모델과 뷰 사이에서 연결하는 역할, 클릭 같은 이벤트들 처리하여 뷰의 요청에 따라 model 데이터를 제어하여 뷰에게 보여주는 역할 (Activity.kt, Fragment.kt) -> 이 둘은 View의 역할이기도 함.
    //뷰의 역할도 컨트롤러 역할도 액티비티가 한다... 안드로이드에서는 완벽하게 적용하기 힘들다. 모델은 완벽하게 분리가능
    //뷰 영역에서 Acticity.kt를 빼는 사람도 있다. xml만 남기는 사람도 있음 -> 엄밀하게 말하면 틀림.

    //app 모듈에서 만든 flat한 디자인에서 MainActivity.kt에 작성한 코드들을 크게 3가지 역할로 구분.
    //#1 화면 구현작업 - View역할
    //#2 클릭 이벤트 처리 작업 - Controller 역할
    //#3 SharedPreferences를 이용하여 데이터를 저장하고 불러오는 비지니스 로직을 가진 기능 메소드 구현 - Model역할

    //역할별 파일에 대한 구분을 쉽게 하기 위해 java폴더 안에 파일의 역할별로 package를 나누기도 함.

    //컨트롤러 역할 : 뷰와 모델을 건드린다.
    // # View 참조변수
    lateinit var binding : ActivityMainBinding

    // # Model 참조변수
    lateinit var model : ItemModel //아이템모델이 필요하다. 아이템 모델안에는 CRUD 기능이 적혀있음.

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            //여기서 액티비티는 컨트롤러 역할만 한다.

            //#1. view역할 - 컨트롤러 역할을 같이한다.
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //#2 model
            model = ItemModel(this)

            //bindind과 model을 이용하여 컨트롤러를 컨트롤한다.
            // 컨트롤러 역할 : 사용자 이벤트에 대응해서 save면 save, load면 load해라
            binding.btnSave.setOnClickListener {
                model.saveData(binding.etName.text.toString(),binding.etEmail.text.toString())

            }

            binding.btnLoad.setOnClickListener {
                val item : Item = model.loadData()
                binding.tv.text = "${item.name} : ${item.email}"
            }


    }
}