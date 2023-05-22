package com.nha2023.ex94architecturepattern

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nha2023.ex94architecturepattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //1. 아키텍쳐 패턴에 대한 수업
    //일단 아키텍쳐 패턴 없이 작성해보자 - flat하게 만들어보자
    //xml작업

    //.. 이런이런 이유로 아키텍쳐 모델을 쓰는게 좋다.
    // 새로운 모듈을 만들어서 실습 및 비교해보기
    //하나의 프로젝트 안에 여러개의 모듈(앱)이 있을수있다.


    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // # 화면 작업
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // # 클릭 이벤트에 대한 처리
        binding.btnSave.setOnClickListener { clickSave() }
        binding.btnLoad.setOnClickListener { clickLoad() }

        //이 작업이 무슨 문제가 있는지 확인해보자
    }

    // # 데이터를 제어(저장하고 읽는 삭제 : CRUD)하는 함수
    // 이런것들을 비지니스 로직 코드라고 한다. 예를 들면.. 레트로핏을 통한 네트워크 작업, DB작업 등..
    private fun clickSave(){
        val pref : SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        //.안찍고 get을 부름 -> 운영체제에서 가져온다는 뜻
        //SharedPreferences의 확장자는 .xml이다. 그래서 name을 그냥 "data"로만 쓴거다,
        //참고로 sqlite의 확장자는? .db
        //SharedPreferences는 ? 핸드폰 안에 데이터를 영구적으로 저장해주는 녀석
        //MODE_PRIVATE : 내 핸드폰에서만 쓸거야
        pref.edit().apply{
            //이 영역안에서 this는 SharedPreEditor이다. 액티비티가 아니다.
            //this.putString("name",binding.etName.text.toString()) //왜 2개? 식별자,데이터
            //this.putString("email",binding.etEmail.text.toString()) //라벨작업
            //this.commit() //트랙젝션 : 마지막에 완료라고 해줘야 실행한다. commit을 해줘야한다.

            //위의 this는 생략 가능하다.

            putString("name",binding.etName.text.toString()) //왜 2개? 식별자,데이터
            putString("email",binding.etEmail.text.toString()) //라벨작업
            commit() //트랙젝션 : 마지막에 완료라고 해줘야 실행한다. commit을 해줘야한다.
        }
    }


    // # 데이터를 제어(저장하고 읽는 삭제 : CRUD)하는 함수
    private fun clickLoad(){
        val pref : SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        var name :String? = pref.getString("name","") //이때 두번째 파라미터는 없을때를 말한다.
        //null일수도 있어서 ?을 붙인다.
        //"" : null이 아니다.

        var email : String = pref.getString("email","")!!  //!! : 너는 널이아니라고 주장한다.

        binding.tv.text = "$name : $email"

    }

}