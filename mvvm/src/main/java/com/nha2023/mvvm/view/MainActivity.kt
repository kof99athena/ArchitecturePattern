package com.nha2023.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.nha2023.mvvm.R
import com.nha2023.mvvm.databinding.ActivityMainBinding
import com.nha2023.mvvm.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    //3. MVVM [ Model - View - ViewModel ] : view와 model의 데이터를 연결해(data binding) 놓아서 model 데이터가 변경될때
    //별도의 처리코드없이 view가 자동 갱신되는 특징

    //1) Model : 이전의 다른 패턴의 Model과 같다 .[ex, Item, ItemModel]
    //2) View : 사용자가 볼 화면. 클릭이벤트를 처리하여 ViewModel에게 model 제어를 요청 [activity_main.xml, MainActivity.kt.... ]
    //3) ViewModel : 뷰와 모델을 연결하는 역할, view가 연결(binding)한 데이터를 제어하도록 요청하는 코드가 있는 클래스

    //** view는 ViewModel을 참조하고, viewModel은 Model을 참조한다.

    //MVVM을 위해서는 [databinding : 데이터 바인딩] 기술을 이용하여 개발하는 것이 일반적임.




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# view의역할
        //레이아웃 xml과 연결하는 바인딩 클래스 [activity_main.xml-->Acticity
        // 데이터바인딩은 뷰바인딩과 다르게 xml 파일의 root요소가 <layout>이여야만 바인딩클 래스가 만들어진다.

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //(뭐랑 연결 할 것인가? , R장부)
        //data binding 기능으로 액티비티에 setContentView()를 실행
        //이러면 setContentView도 대신한것이다.
        //데이터바인딩은 뷰바인딩도 같이 해준다.
        //데이터바인딩은 xml의 data 영역에 쓴 변수도 가져온다

        //기본적으로 없는것들은 Field를 쓴다.

        //# viewmodel 객체를 생성하여 layout 변수에 대입
        binding.vm = MyViewModel(this)

        object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //(글씨,시작위치,마지막위치,카운트)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }
    }
}

//## MVVM 장점
//1. MVP처럼 view와 presenter가 1:1로 대응되어 있지 않아서 화면이 많아져도 ViewModel은 재사용 가능함. 결국 전체 파일 개수가 줄어듦.
//2. 사용자의 이벤트를 ViewModel이 모두 하고 있기에 화면이 변경되어도 이벤트처리에 대한 중복 코드가 필요없음.
//3. view는 viewModel을 참조하지만 viewModel은 view를 참조하지 않기에 view가 변경되어도 뷰모델은 영향이 없음.
//4. Activity나 Fragment의 코드가 가장 간결함.

//## MVVM 단점
//1. MVVM 설계는 어렵다.
//2. view처리가 많아지면 viewModel의 코드가 많아져서 결국 비대해짐.




