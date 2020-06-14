package dpusha.app.com.usha;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

public class Search extends AppCompatActivity {
    public static AHBottomNavigation bottomNavigation;
    private final int[] colors1 = {R.color.white};

    RecyclerView searchbycateg_recyclerview,recentsearch_recyclerview,search_recyclerview;

    /*List<HomeCategoryBeam> homeCategoryBeams;
    AdapterHomeCatogery adapterHomeCatogery;
*/
  /*  List<HomeMySearchBeam> homeMySearchBeams;
    AdapterHomeMySearch adapterHomeMySearch;
*/

    /*List<SearchProductBeam> searchProductBeams;
    AdapterSearchProduct adapterSearchProduct;*/


    /*CustomDialog customDialog;*/
    private static final String TAG = Search.class.getSimpleName();


    EditText searchbtn;

    LinearLayout firstlayout,seconlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
