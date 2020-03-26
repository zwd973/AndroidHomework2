package chapter.android.aweme.ss.com.homework;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises2);
        View rootView = findViewById(R.id.root_view);
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv1.setText(String.format("视图数目:%d", getAllChildViewCount(rootView)));
    }
    private int getViewCountOfViewGroup(ViewGroup viewGroup){
        int total=0;
        for(int i=0;i<viewGroup.getChildCount();i++){
            Object view = viewGroup.getChildAt(i);
            if(view instanceof ViewGroup){
                total += getViewCountOfViewGroup((ViewGroup) view);
            }else{
                total+=1;
            }
        }
        return total;
    }
    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        if(view instanceof ViewGroup)
            return getViewCountOfViewGroup((ViewGroup) view);
        else if(view!=null){
            return 1;
        }else{
            return 0;
        }
    }
}
