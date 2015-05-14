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

/**
 * Home Activity
 */
public class HomeActivity extends Activity implements HomeFragmentContent.OnFragmentInteractionListener,searchFragment.OnFragmentInteractionListener
{

    private EditText editText;
    private TextView textView;
    private HomeFragmentContent homeFragmentContent=new HomeFragmentContent();
    private FrameLayout frameLayout;
    private searchFragment searchFragment=new searchFragment();
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

    /**
     * Method callled from fragment
     */
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

    /**
     * Start a search fragment
     * @param view
     */
    public void startSearchFragment(View view){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        fragmentTransaction.replace(frameLayout.getId(),searchFragment);
        fragmentTransaction.commit();

    }
}
