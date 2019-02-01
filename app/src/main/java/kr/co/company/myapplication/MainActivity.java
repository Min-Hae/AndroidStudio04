//////////////////async를 이용해 카운터 만들기/////////
package kr.co.company.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
//클래스 정의
public class MainActivity extends Activity {
    private ProgressBar mProgress;    //프로그레스 바 사용
    private int mProgressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //activity_main.xml과 연결
        mProgress = (ProgressBar)findViewById(R.id.progressBar); //프로그레스바 명시
        Button button = (Button)findViewById(R.id.button);        //버튼 명시
        //버튼 이벤트 함수
        button.setOnClickListener(new Button.OnClickListener(){
            public  void onClick(View v){
                new CounterTask().execute(0); //async를 사용한다.
            }
        });
    }
    //클래스 정의
    class CounterTask extends AsyncTask<Integer, Integer, Integer> {
        protected  void onPreExecute(){}
        protected Integer doInBackground(Integer... value){ //...은 기변 인수를 의미한다.
            //프로그레스 변수가 100보다 작으면 과정을 반복한다.
            while(mProgressStatus < 100){
                try{
                    Thread.sleep(1000);  //스스로 runnable로 복귀할수 있게 설정
                }catch (InterruptedException e){} //인터럽트 설정
                mProgressStatus++; //변수에 1을 더해준다.
                publishProgress(mProgressStatus);
            }
            return mProgressStatus; //변수 값을 반환한다.
        }
        protected  void onProgressUpdate(Integer... value){
            mProgress.setProgress(mProgressStatus); //프로그레스 바 업데이트
        }
        protected void onPostExecute(Integer result){
            mProgress.setProgress(mProgressStatus);
        }
    }
}
