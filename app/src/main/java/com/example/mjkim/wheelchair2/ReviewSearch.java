package com.example.mjkim.wheelchair2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.mjkim.wheelchair2.NameSearch.FirebaseJson;
import com.example.mjkim.wheelchair2.NaverSearch.NaverLocationAdapter;
import com.example.mjkim.wheelchair2.NaverSearch.NaverLocationList;
import com.example.mjkim.wheelchair2.NaverSearch.NaverLocationSearch;

import java.util.ArrayList;

public class ReviewSearch extends AppCompatActivity implements AbsListView.OnScrollListener{

    ImageButton back_button;
    ImageButton menu_button;
    int i;
    public static ArrayList<NaverLocationList> arrayresult;       // 네이버 지역 리스트
    ArrayList<NaverLocationList> naverLocationLists = new ArrayList<NaverLocationList>();
    private NaverLocationSearch naverLocationSearch;
    private NaverLocationAdapter adapter;            // 리스트뷰의 네이버지역검색 아답터
    private boolean lastItemVisibleFlag = false;    // 리스트 스크롤이 마지막 셀(맨 바닥)로 이동했는지 체크할 변수
    private int page = 0;                           // 페이징변수. 초기 값은 0 이다.
    private final int OFFSET = 10;                  // 한 페이지마다 로드할 데이터 갯수.
    private boolean mLockListView = false;          // 데이터 불러올때 중복안되게 하기위한 변수
    ProgressBar progressBar;                         // 데이터 로딩중을 표시할 프로그레스바
    int save = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_review_search);


        //버튼 선언
        back_button = (ImageButton)findViewById(R.id.back_b);
        menu_button = (ImageButton)findViewById(R.id.menu_b);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ openMenuTab(); }});



    }


    public void openMenuTab(){
        Intent intent = new Intent(this, MenuScreen.class);
        startActivity(intent);
    }


    public void onSearch(View v){

        naverLocationSearch = new NaverLocationSearch();
        naverLocationLists = new ArrayList<NaverLocationList>();
        arrayresult = new ArrayList<NaverLocationList>();


        EditText e1 = (EditText)findViewById(R.id.review_search);



        try {
            arrayresult = naverLocationSearch.execute(e1.getText().toString()).get();
        }catch (Exception e){
            e.printStackTrace();
        }

        ListView lv = (ListView) findViewById(R.id.location_list);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);





        adapter = new NaverLocationAdapter(ReviewSearch.this, arrayresult, save);
        lv.setAdapter(adapter);


        progressBar.setVisibility(View.GONE);

        lv.setOnScrollListener(this);
        getItem();


    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
        // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
        // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
        // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            // 화면이 바닦에 닿을때 처리
            // 로딩중을 알리는 프로그레스바를 보인다.
            progressBar.setVisibility(View.VISIBLE);

            // 다음 데이터를 불러온다.
            getItem();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
        // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
        // totalItemCount : 리스트 전체의 총 갯수
        // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }


    private void getItem(){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;


        //for(int i = 0; i < 10; i++){ //매번 스크롤을 내릴때마다 10개씩 더 추가한다
        //    int label = ((page * OFFSET) + i);
        //    if(label >= naverLocationSearch.total_num) break;
        //    naverLocationLists.add(arrayresult.get(label));
        //    System.out.println("총 갯수 "+naverLocationSearch.total_num);
        //}


        // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                mLockListView = false;
            }
        },1000);
    }


}
