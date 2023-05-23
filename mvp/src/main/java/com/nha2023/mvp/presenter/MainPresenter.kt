package com.nha2023.mvp.presenter

import com.nha2023.mvp.model.Item
import com.nha2023.mvp.model.ItemModel

//presenter라면 가져야 할 기능을 기술한 인터페이스를 구현해서 실제 기능을 작성하는 클래스

class MainPresenter : MainContract.Presenter {

    //이렇게 하면 규격화된다. 인터페이스를 상속받으면 반드시 써야하는 메소드 2개가 있다.

    //presenter는 view와 model을 연결해야하기에 각각의 참조변수를 멤버로 보유
    var view : MainContract.View?= null
    //1. view 역할을 수행하는 클래스는 반드시 MainContract.View 인터페이스를 구현하고 있기에 특정 Activity/Fragment를 직접 자료형으로 참조하고 있지 않습니다.
    //약한 결합...
    //MainActivity를 연결하면 강하게 적용됨.. 이러지말장
    var model : ItemModel?= null //2. model 역할을 수행하는 클래스 참조변수

    //presenter가 연결할 2개의 참조변수를 생성 및 전달받는 메소드 정의
    fun initial(view:MainContract.View){
        this.view = view
        model= ItemModel(view.getContext())
    }


    //view의 save버튼 클릭 이벤트를 대신 처리해주는 기능 메소드
    override fun clickSave(name: String, email: String) {
        model?.saveData(name,email) //model에게 저장 요청
    }


    override fun clickLoad() {

        //model에게 data를 요청
        var item : Item? = model?.loadData()

        //view에게 데이터 출력 요청
        item?.let {
            view?.showData(it)
        }
    }


}