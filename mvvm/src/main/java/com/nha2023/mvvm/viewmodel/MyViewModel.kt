package com.nha2023.mvvm.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.nha2023.mvvm.model.Item
import com.nha2023.mvvm.model.ItemModel

class MyViewModel (context: Context){ //멤버변수 아님(var가 없으니까). 매개변수이다.

    //view와 연결할 model 역할 클래스 참조변수
    var itemModel : ItemModel = ItemModel(context)

    //값변경이 관찰되는 특별한 데이터멤버변수 ObservableXXX
    var model : ObservableField<Item> = ObservableField()

    init {
        model.set(Item("이름 없음","이메일 없음"))
    }

    //EditText의 글씨를 가지고 있을 일반 변수
    //에딧텍스트에 있는 거를 가져온게 아니다. 이런 개념이 아님.
    //일일이 제어하는게 아님. 글씨가 바뀔때마다 반응하는 이벤트를 달면된다.
    private var name : String = ""
    private var email : String = ""

    //EditText의 글씨가 변경될때마다 반응하도록 등록한 메소드 2개
    fun changeName(s: CharSequence?, start:Int, end:Int, count:Int ){
        this.name = s.toString()
    }

    fun changeEmail(s: CharSequence?, start:Int, end:Int, count:Int ){
        this.email = s.toString()
    }



    //view의 이벤트에 반응하여 model을 제어하도록 요청하는 기능 메소드들..
    fun clickedSave(view : View){
        //데이터바인딩은 참조변수를 찾아서 누굴연결하는게 아님.
        //찾을 생각이 없다. 변수만있으면 된다.
        itemModel.saveData(name,email)
    }

    fun clickedLoad(view : View){
        val item : Item = itemModel.loadData()
        model.set(item)
    }




}