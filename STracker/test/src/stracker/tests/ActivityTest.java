package src.stracker.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import src.stracker.MainActivity;
import src.stracker.R;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ActivityTest{

    @Test
    public void appName() throws Exception {
        String hello = new MainActivity().getResources().getString(R.string.app_name);
        assertThat(hello, equalTo("STracker"));
    }
}