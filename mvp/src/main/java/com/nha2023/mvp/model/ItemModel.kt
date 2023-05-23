package com.nha2023.mvp.model

import android.content.Context


//데이터를 제어하는 기능 2개를 가진 클래스
class ItemModel constructor(val context: Context) { //데이터를 제어하기 위해서 Context 능력이 필요한 경우가 있다면 주 생성자로 전달받기

    // 1) 데이터를 저장해야함.
    //데이터가 뷰에게 있다. 화면이 갖고있다.
    //데이터를 전달받아 SharedPreferences에 데이터를 저장하는 기능
    fun saveData(name : String, email: String){
        val pref = context.getSharedPreferences("data",Context.MODE_PRIVATE)
        pref.edit().apply {
            putString("name",name) //바인딩이 없다
            putString("email",email)
            commit()
        }
    }



    // 2) SharedPreferences에서 데이터를 읽어와서 내보내는(리턴) 기능
    fun loadData() : Item{
        val pref = context.getSharedPreferences("data",Context.MODE_PRIVATE)
        val name : String = pref.getString("name","") as String // as는 형변환. String으로 형변환 하겠다.
        val email : String = pref.getString("email","") as String // as는 형변환. String으로 형변환 하겠다.

     return Item(name,email)
     //name, email 2개를 리턴해야하는데...
     //리턴은 1개 밖에 못해. 그래서 Item을 만들어서 생성자로 name, email 2개를 넣어줘야한다.

    }

    //결국 MVC의 장점
    //1. 데이터를 제어하는 코드가 Activity/Fragment 클래스에 있지 않아서 간결해짐.
    //2. 역할별로 코드가 분리되어있어서 가독성이 좋고 기능 코드 위치를 식별하여 찾기 수월하여 유지보수도 용이하다.
    //3. 모델 역할을 하는 class안에 어떤 View도 참조하고 있지 않다. 그래서  view를 변경해도 model의 코드는 전혀 변경되지 않기에 다른 view에서도 재사용이 가능하다.

    //but!!!!!!!!!!!!!!단점
    //1) Android에서는 View와 Controller 역할이 완전 분리가 어려움, Activity는 view이면서 controller역할을 함.
    //2) View에서 model 객체를 참조하고 있어서 model이 변경되면 view도 코드 변경이 불가하다.
    //3) 규모가 커지면 controller 역할을 하는 Activity의 코드는 여전히 비대해짐.



}