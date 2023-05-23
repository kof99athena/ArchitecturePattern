package com.nha2023.mvp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nha2023.mvp.R
import com.nha2023.mvp.databinding.ActivityMainBinding
import com.nha2023.mvp.model.Item
import com.nha2023.mvp.presenter.MainContract
import com.nha2023.mvp.presenter.MainPresenter

//2. MVP 패턴 [Model View Presenter] - 특징 : 뷰와 모델의 완전 분리가 가능하다
//view와 presenter가 해야할 작업을 미리 인터페이스로 규격화를 했다. 모듈화된 작업 템플릿을 만들때 용이한 구조

//1) Model : MVC패턴의 모델과 같은 역할 [데이터 취급 : Item class, ItemModel... , Person]
//2) View : 사용자가 볼 화면 및 이벤트 처리 [activity_main.xml, MainActivity.kt, fragment_my.xml, MyFragment.kt]
//3) Presenter : 뷰와 모델의 중계 역할. 컨트롤러와 비슷하지만 인터페이스로 역할을 정해 놓음.

//주요특징 :  뷰와 프레젠터가 해야할 작업들을 미리 interface를 통해 규격화 해놓기




class MainActivity : AppCompatActivity(), MainContract.View{

    lateinit var binding : ActivityMainBinding
    lateinit var presenter : MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //#먼저 presenter 객체 생성 및 초기화
        presenter = MainPresenter()
        presenter.initial(this)

        //# view 역할
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //사용자 클릭 이벤트를 MVP에서는 View가 받는다.
        binding.btnSave.setOnClickListener { presenter.clickSave(binding.etName.text.toString(), binding.etEmail.text.toString()) }
        binding.btnLoad.setOnClickListener { presenter.clickLoad() }


    }

//view는 절대 model을 참조하지 않는다.



    //정말 중요! Main은 MainContract에 들어있는 추상 메소드를 받아와야한다.
    override fun showData(item: Item) {
        binding.tv.text = "${item.name} : ${item.email}"
        //다른 회사에서 여기를 actionbar로 해달라면 여기 함수만 수정해주면 된다.
    }

    override fun getContext(): Context {
        return this
    }
}

//## MVP의 장점
//1. MVC와 마찬가지로 데이터를 제어하는 코드가 Activity나 Fragment클래스안에 없어서 간결하다.
//2. MVC보다 조금 명확하게 각 역할별 코드가 잘 분리되어 작성된다.
//3. 각 역할이 인터페이스로 규격화되어서 유지보수나 인수인계가 용이함.
//4. view 안에서 model을 참조하고 있지 않기에 model의 변화에 영향받지 않음.

//#MVP의 단점
//1. mvc보다 만들어야할 기본 파일들이 많아서 구조가 더 복잡해보임.
//2. view와 presenter 가 일대일 대응올 만들어지기에 파일이 엄청 많아진다.
//3. 규모가 커지면 결국 presenter가 해야할 작업이 많아서 결국 비대해짐.





