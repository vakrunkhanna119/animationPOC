package mmt.com.taptappoc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends Activity implements HomeFragmentContent.OnFragmentInteractionListener,searchFragment.OnFragmentInteractionListener
{

    EditText editText;
    TextView textView;
    HomeFragmentContent homeFragmentContent=new HomeFragmentContent();
    FrameLayout frameLayout;
    searchFragment searchFragment=new searchFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editText=(EditText)findViewById(R.id.editText);
        textView=(TextView)findViewById(R.id.textView);
        editText.setVisibility(View.GONE);
        frameLayout=(FrameLayout)findViewById(R.id.contentFragment);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(frameLayout.getId(),homeFragmentContent);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction() {

        final int mShortAnimationDuration=1000;



            textView.animate()
                    .alpha(0f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            textView.setVisibility(View.GONE);
                            editText.setAlpha(0f);
                            editText.setVisibility(View.VISIBLE);
                            editText.animate()
                                    .alpha(1f)
                                    .setDuration(mShortAnimationDuration);
                        }
                    });

    }

    public void startSearchFragment(View view){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        fragmentTransaction.replace(frameLayout.getId(),searchFragment);
        fragmentTransaction.commit();

    }
}
